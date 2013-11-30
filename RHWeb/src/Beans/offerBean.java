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
	private Date firstDay;
	private Date lastDay;
	private boolean reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;
	private Vector<Fechas> vectorFechas;
	private boolean unidoOferta;
	
	public Date getFirsDay() {
		return firstDay;
	}
	public void setFirstDay(String primerDia) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			firstDay=df.parse(primerDia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Date getLastDay() {
		return lastDay;
	}
	
	public void setLastDay(String ultimoDia) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			lastDay=df.parse(ultimoDia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getReservado() {
		return reservado;
	}
	
	public void setReservado(boolean r) {
		reservado=r;
	}
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float p) {
		price=p;
	}
	
	public Book getReserva(){
		return reserva;
	}
	
	public void setReserva(Book reservaBook){
		reserva = reservaBook;
		reservado = true;
	}
	
	public RuralHouse getRuralHouse(){
		return ruralHouse;
	}
	
	public void setRuralHouse(RuralHouse house){
		ruralHouse= house;
	}
	
	public Vector<Fechas> getFechas(){
		return vectorFechas;
	}
	
	//falta el set vector fechas
	
	public boolean getUnidoOferta(){
		return unidoOferta;
	}
	
	public void setUnidoOferta(String unido){
		if (unido.compareTo("Si")==0){
			unidoOferta=true;
		}
		else unidoOferta=false;
	}
	
	public String loginUser(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", fachadaBean.getFachada().hacerLogin(email, pass));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", new Boolean(true));	
			return "ok";
		} catch (Exception e) {
			//e.printStackTrace();
			return "error";
		}
	}
}
