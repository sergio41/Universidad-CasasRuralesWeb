package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class ReservaBean {

	
	private String seleccionado;
	private List<String> ofertasDispo;

	public List<String> getOfertasDispo() {
		try {
			setOfertasDispo(fachadaBean.getFachada().getOfertasS());
			return ofertasDispo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setOfertasDispo(List<String> ofertasDispo) {
		this.ofertasDispo = ofertasDispo;
	}
	
	public String getseleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(String sel) {
		seleccionado = sel;
	}
	
	public String reservar(){
		try {
			int i=0;
			while (seleccionado.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionado.substring(0, i-1);
			int num = Integer.parseInt(s);
			UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			fachadaBean.getFachada().hacerReserva(user, num);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
