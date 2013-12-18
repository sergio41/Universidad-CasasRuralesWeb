package Beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.Book;
import domain.RuralHouse;
import domain.UserAplication;

public class offerBean {
	private int ruralNumber;
	private Date firstDay;
	private Date lastDay;
	private float price;
	
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
	
	public String crearOffer(){
		try {
			UserAplication u = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
			fachadaBean.getFachada().anadirOferta(u, ruralNumber, firstDay, lastDay, price);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
