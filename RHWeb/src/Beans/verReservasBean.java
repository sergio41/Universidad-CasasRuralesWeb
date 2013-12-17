package Beans;

import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.Book;
import domain.UserAplication;

public class verReservasBean {

	private Vector<Book> reservas;

	public verReservasBean(){
		UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			System.out.println("lo cargo");
			reservas = fachadaBean.getFachada().getReservas(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Vector<Book> getReservas() {
		return reservas;
	}

	public void setReservas(Vector<Book> reservas) {
		this.reservas = reservas;
	}

	
}
