package domain;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Offer implements Serializable {
	
	private int ID;
	private Date firstDay;
	private Date lastDay;
	private boolean reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;
	//private Set<Fechas> vectorFechas;
	private boolean unidoOferta;

	public Offer(int num, Date primerDia, Date ultimoDia, float cost, RuralHouse casaRural, /*Set<Fechas> fechas,*/ boolean unidoAFecha){
		setID(num);
		setFirstDay(primerDia);
		setLastDay(ultimoDia);
		setReservado(false);
		setPrice(cost);
		setReserva(null);
		setRuralHouse(casaRural);
		//vectorFechas = fechas;
		setUnidoOferta(unidoAFecha);
		//setUnidoAFechaExternamente();
	}
	
	public Offer(Date primerDia, Date ultimoDia, boolean estado,  float cost, RuralHouse casaRural, /*Set<Fechas> fechas, */boolean unidoAFecha){
		setFirstDay(primerDia);
		setLastDay(ultimoDia);
		setReservado(estado);
		setPrice(cost);
		setReserva(null);
		setRuralHouse(casaRural);
		//vectorFechas = fechas;
		setUnidoOferta(unidoAFecha);
		//setUnidoAFechaExternamente();
	}
	
	public Offer(){
	}
	
	/*private void setUnidoAFechaExternamente(){
		if(vectorFechas != null){
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				Fechas auxi = i.next();
				if (auxi.getPrecio()==0)auxi.setOferta(this, true);
				else auxi.setOferta(this, unidoOferta);
			}
		}	
	}*/
	


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Book getReserva() {
		return reserva;
	}

	public void setReserva(Book reserva) {
		this.reserva = reserva;
	}

	public RuralHouse getRuralHouse() {
		return ruralHouse;
	}

	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}

	public boolean isUnidoOferta() {
		return unidoOferta;
	}

	public void setUnidoOferta(boolean unidoOferta) {
		this.unidoOferta = unidoOferta;
	}
	
	/*public boolean contiene(Date fecha){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()) if (i.next().getFecha().compareTo(fecha) == 0) return true;
		return false;
	}*/
}