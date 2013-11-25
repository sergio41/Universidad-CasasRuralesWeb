package domain;

import java.io.Serializable;
import java.util.Date;

public class Fechas implements Serializable {

	private Date fecha;
	private float precio;
	private Boolean reservado;
	private RuralHouse casaRural;
	private int minDias;
	private Book reserva;
	private Offer offer;
	private Boolean unidoOferta;
	
	public Fechas (Date date, float cost, RuralHouse ruralHouse, int minimoDias){
		fecha = date;
		precio = cost;
		reservado = false;
		casaRural = ruralHouse;
		minDias = minimoDias;
		reserva = null;
		offer = null;
		unidoOferta = false;
	}
	
	public Fechas ( Date date, float cost, boolean estado, RuralHouse ruralHouse,  int minimoDias){
		fecha = date;
		precio = cost;
		reservado = estado;
		casaRural = ruralHouse;
		minDias = minimoDias;
		reserva = null;
		offer = null;
		unidoOferta = false;
	}
	
	public Fechas ( Date date, float cost, boolean estado, RuralHouse ruralHouse,  int minimoDias, boolean unido){
		fecha = date;
		precio = cost;
		reservado = estado;
		casaRural = ruralHouse;
		minDias = minimoDias;
		reserva = null;
		offer = null;
		unidoOferta = unido;
	}
	
	public Date getFecha() {return fecha;}
	
	public float getPrecio() {return precio;}
	public void setPrecio(float cost){precio = cost;}
	
	public boolean isReservado() {return reservado;}
	
	public RuralHouse getCasaRural() {return casaRural;}
	
	public int getMinimoDias(){return minDias;}
	public void setMinimodias(int minimoDias){minDias=minimoDias;}
	
	public Book getReserva(){return reserva;}
	
	public Offer getOfer(){return offer;}
	public void setOferta(Offer oferta, boolean unidoAOferta){offer=oferta;unidoOferta=unidoAOferta;}
	public boolean isUnidoOferta(){return unidoOferta;}
	
	public void cancelarReserva(){reservado=false;reserva=null;}
	
	protected void hacerReserva(Book reservaBook){
		reserva = reservaBook;
		reservado = true;
	}
}
