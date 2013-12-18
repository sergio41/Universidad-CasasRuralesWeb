package Beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import domain.UserAplication;

public class offerBean {
	private String seleccionada;
	private List<String> casasU;
	private Date firstDay;
	private Date lastDay;
	private float price;
	
	public String getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(String seleccionada) {
		this.seleccionada = seleccionada;
		System.out.println(this.seleccionada);
	}

	public List<String> getCasasU() {
		try {
			setCasasU(fachadaBean.getFachada().getUserHouses((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")));
			return casasU;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setCasasU(List<String> casasU) {
		this.casasU = casasU;
	}
	
	public Date getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(Date primerDia) {
		firstDay=primerDia;
	}
	
	public Date getLastDay() {
		return lastDay;
	}
	
	public void setLastDay(Date ultimoDia) {
		lastDay=ultimoDia;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float p) {
		price=p;
	}
	
	public String crearOffer(){
		try {
			int i=0;
			while (seleccionada.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionada.substring(0, i-1);
			int num = Integer.parseInt(s);
			UserAplication u = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
			fachadaBean.getFachada().anadirOferta(u, num, firstDay, lastDay, price);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al insertar usuario:", e.getMessage()));  
			return "";
		}
	}
}
