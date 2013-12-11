package Beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.Book;
import domain.Fechas;
import domain.RuralHouse;
import domain.UserAplication;

public class offerBean {
	private int ruralNumber;
	private Date firstDay;
	private Date lastDay;
	private float price;
	//private boolean unidoOferta;
	
	
	public int getRuralNumber(){
		return ruralNumber;
	}
	
	public void setRuralNumber(int num){
		ruralNumber=num;
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
	
	//falta el set vector fechas
	/*
	public boolean getUnidoOferta(){
		return unidoOferta;
	}
	
	public void setUnidoOferta(boolean unido){
		unidoOferta=unido;
	}*/
	
	public String crearOffer(){
		try {
			UserAplication u = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
			fachadaBean.getFachada().anadirOferta(u, ruralNumber, firstDay, lastDay, price, false);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
