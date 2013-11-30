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
	private boolean reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;
	private Vector<Fechas> vectorFechas;
	private boolean unidoOferta;
	
	
	public int getRuralNumber(){
		return ruralNumber;
	}
	
	public void setRuralNumber(int num){
		ruralNumber=num;
	}
	public Date getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String primerDia) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			firstDay= (Date) df.parse(primerDia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Date getLastDay() {
		return lastDay;
	}
	
	public void setLastDay(String ultimoDia) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
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
	
	public Vector<Fechas> getVectorFechas(){
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
	
	public String anadirOferta(){
		try {
			UserAplication u = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
			fachadaBean.getFachada().anadirOferta(u, ruralNumber, firstDay, lastDay, price, unidoOferta);
			return "ok";
		} catch (Exception e) {
			//e.printStackTrace();
			return "error";
		}
	}
}
