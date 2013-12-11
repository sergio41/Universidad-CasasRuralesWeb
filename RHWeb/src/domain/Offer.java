package domain;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Offer implements Serializable {
	
	private Date firstDay;
	private Date lastDay;
	private boolean reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;
	//private Set<Fechas> vectorFechas;
	private boolean unidoOferta;

	public Offer(Date primerDia, Date ultimoDia, float cost, RuralHouse casaRural, /*Set<Fechas> fechas,*/ boolean unidoAFecha){
		firstDay = primerDia;
		lastDay = ultimoDia;
		reservado = false;
		price = cost;
		reserva = null;
		ruralHouse = casaRural;
		//vectorFechas = fechas;
		unidoOferta = unidoAFecha;
		//setUnidoAFechaExternamente();
	}
	
	public Offer(Date primerDia, Date ultimoDia, boolean estado,  float cost, RuralHouse casaRural, /*Set<Fechas> fechas, */boolean unidoAFecha){
		firstDay = primerDia;
		lastDay = ultimoDia;
		reservado = estado;
		price = cost;
		reserva = null;
		ruralHouse = casaRural;
		//vectorFechas = fechas;
		unidoOferta = unidoAFecha;
		//setUnidoAFechaExternamente();
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
	
	public Date getPrimerDia(){return firstDay;}
	public Date getUltimoDia(){return lastDay;}
	
	public boolean isReservado(){return reservado;}
	
	public float getPrice(){return price;}
	public void setPrice(float precio){ price = precio;}
	
	public Book getReserva(){return reserva;}
	
	public RuralHouse getRuralHouse() {return ruralHouse;}

	//public Set<Fechas> getFechas(){return vectorFechas;}
	
	public boolean isUnidoAFechas(){return unidoOferta;}
	public void setUnidoAFechas(boolean unidoAfecha){unidoOferta = unidoAfecha;}
	
	protected void hacerReserva(Book reservaBook){
		reserva = reservaBook;
		reservado = true;
	}

	public void cancelarReserva(){
		reserva=null;
		reservado = false;		
	}
	
	/*public boolean contiene(Date fecha){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()) if (i.next().getFecha().compareTo(fecha) == 0) return true;
		return false;
	}*/
}