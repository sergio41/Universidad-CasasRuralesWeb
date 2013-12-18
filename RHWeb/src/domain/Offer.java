package domain;

import java.io.*;
import java.util.Date;

@SuppressWarnings("serial")
public class Offer implements Serializable {
	
	private int ID;
	private Date firstDay;
	private Date lastDay;
	private int reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;

	public Offer(int num, Date primerDia, Date ultimoDia, float cost, RuralHouse casaRural){
		setID(num);
		setFirstDay(primerDia);
		setLastDay(ultimoDia);
		setReservado(0);
		setPrice(cost);
		setReserva(null);
		setRuralHouse(casaRural);
	}
	
	public Offer(Date primerDia, Date ultimoDia, int estado,  float cost, RuralHouse casaRural){
		setFirstDay(primerDia);
		setLastDay(ultimoDia);
		setReservado(estado);
		setPrice(cost);
		setReserva(null);
		setRuralHouse(casaRural);
	}
	
	public Offer(){
	}

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

	public int isReservado() {
		return reservado;
	}

	public void setReservado(int reservado) {
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
}