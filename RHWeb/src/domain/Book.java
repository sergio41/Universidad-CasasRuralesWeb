package domain;

import java.io.*;
import java.util.Date;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private Date bookDate;
	private int bookNumber;
	private boolean isPaid;
	private float precio;
	private UserAplication reservante;
	private Offer offer;
	private RuralHouse casa;
	
	public Book(RuralHouse ruralHouse, int numeroReserva, float cost, UserAplication cliente/*, Set<Fechas> fechas*/) {
		setBookDate(new java.util.Date(System.currentTimeMillis()));
		setBookNumber(numeroReserva);
		setPaid(false);
		setPrecio(cost);
		setReservante(cliente);
		setOffer(null);
		setCasa(ruralHouse);
	}
	
	public Book(RuralHouse ruralHouse, int numeroReserva, float cost, UserAplication cliente, Offer oferta/*, Set<Fechas> fechas*/) {
		setBookDate(new java.util.Date(System.currentTimeMillis()));
		setBookNumber(numeroReserva);
		setPaid(false);
		setPrecio(cost);
		setReservante(cliente);
		setOffer(oferta);
		setCasa(ruralHouse);
	}
	
	public Book() {
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public UserAplication getReservante() {
		return reservante;
	}

	public void setReservante(UserAplication reservante) {
		this.reservante = reservante;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public RuralHouse getCasa() {
		return casa;
	}

	public void setCasa(RuralHouse casa) {
		this.casa = casa;
	}
	
	/*private void extenderReserva(){
		if(offer !=null) offer.hacerReserva(this);
		if(vectorFechas!=null){
			Iterator<Fechas> i = vectorFechas.iterator();
			while(i.hasNext()){
				Fechas aux = i.next();
				System.out.println("   " +   aux.getFecha().toString());
				aux.hacerReserva(this);
			}
		}
	}*/


}