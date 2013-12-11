package domain;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Book implements Serializable {

	private Date bookDate;
	private int bookNumber;
	private boolean isPaid;
	private float precio;
	private UserAplication reservante;
	private Offer offer;
//	private Set<Fechas> vectorFechas;
	private RuralHouse casa;
	
	public Book(RuralHouse ruralHouse, int numeroReserva, float cost, UserAplication cliente/*, Set<Fechas> fechas*/) {
		bookDate = new java.util.Date(System.currentTimeMillis());
		bookNumber = numeroReserva;
		isPaid = false;
		precio = cost;
		reservante = cliente;
		offer = null;
		//vectorFechas = fechas;
		casa = ruralHouse;
		//extenderReserva();
	}
	
	public Book(RuralHouse ruralHouse, int numeroReserva, float cost, UserAplication cliente, Offer oferta/*, Set<Fechas> fechas*/) {
		bookDate = new java.util.Date(System.currentTimeMillis());
		bookNumber = numeroReserva;
		isPaid = false;
		precio = cost;
		reservante = cliente;
		offer = oferta;
		//vectorFechas = fechas;
		casa = ruralHouse;
		//extenderReserva();
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
	
	public Date getFechaDeReserva(){return bookDate;}
	
	public int getNumeroDeReserva() {return bookNumber;}
	
	public void setPagado(boolean pagar) {isPaid = pagar;}
	public boolean isPaid() {return isPaid;}
	
	public float getPrecio(){return precio;}
	public void setPrecio(float cost){precio = cost;}
	
	public UserAplication getCliente(){return reservante;}
	
	public Offer getOffer() {return offer;}
	
	//public Set<Fechas> getFechas(){return vectorFechas;}
	
	public void pagar(){isPaid=true;}

	public RuralHouse getCasa() {
		return casa;
	}

}