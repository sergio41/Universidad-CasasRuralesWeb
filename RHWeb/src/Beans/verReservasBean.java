package Beans;

import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.Book;
import domain.UserAplication;

public class verReservasBean {

	private Vector<Book> reservas;

	public Vector<Book> getReservas() {
		UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			System.out.println("lo cargo");
			setReservas(fachadaBean.getFachada().getReservas(user));
			return reservas;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void setReservas(Vector<Book> reservas) {
		this.reservas = reservas;
	}

	
}
