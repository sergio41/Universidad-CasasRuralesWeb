package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import domain.Offer;
import domain.RuralHouse;
import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class modificarOfertaBean {

	
	private String seleccionada;
	private List<String> ofertaU;
	private Date firstDay;
	private Date lastDay;
	private float price;

	public List<String> getOfertaU() {
		try {
			setOfertaU(fachadaBean.getFachada().getOfertasU((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")));
			return ofertaU;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setOfertaU(List<String> ofertaU) {
		this.ofertaU = ofertaU;
	}


	public String getSeleccionada() {
		return seleccionada;
	}


	public void setSeleccionada(String seleccionada) {
		
		try {
			int i=0;
			while (seleccionada.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionada.substring(0, i-1);
			int num = Integer.parseInt(s);
			Offer oferta = fachadaBean.getFachada().getOferta(num);
			setFirstDay(oferta.getFirstDay());
			setLastDay(oferta.getLastDay());
			setPrice(oferta.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.seleccionada = seleccionada;
	}
	
	public String actualizar(){
		try {
			int i=0;
			while (seleccionada.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionada.substring(0, i-1);
			int num = Integer.parseInt(s);
			UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			fachadaBean.getFachada().modificarOferta(num ,firstDay, lastDay, price);
			return "ok";
		} catch (Exception e) {
			return "error";
		}
	}

	public Date getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
