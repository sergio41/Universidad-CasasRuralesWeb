package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import externalDataSend.EnviarCorreo;

public class RuralHouse implements Serializable {

	private int houseNumber;
	private String description;
	private UserAplication user;
	private String city; 
	private int nRooms;
	private int nKitchen;
	private int nBaths;
	private int nLiving;
	private int nPark;
	/*private Set<Fechas> vectorFechas;
	private Set<Offer> vectorOfertas;
	private Set<Book> vectorReservas;
	private Set<String> vectorImage;
	private Set<String> comentarios;*/
	private String comentarios;
	private Float calificacion;
	private int nCal;
	
	
	public RuralHouse(int hNumber, UserAplication usuario, String descripcion, String ciudad, int cuartos, int cocina, int banos, int salon, int aparcamiento) {
		houseNumber = hNumber;
		description = descripcion;
		user = usuario;
		city = ciudad;
		nRooms = cuartos;
		nKitchen = cocina;
		nBaths = banos;
		nLiving = salon;
		nPark = aparcamiento;
		/*vectorFechas = new HashSet<Fechas>();
		vectorOfertas = new HashSet<Offer>();
		vectorReservas = new HashSet<Book>();*/
		comentarios = "";
		calificacion = (float) 2.5;
		nCal=0;
		//vectorImage = images;
	}

	public RuralHouse() {
	}
	
	public int getHouseNumber() {return houseNumber;}
	public void setHouseNumber(int h) {houseNumber = h;}

	public String getDescription() {return description;}	
	public void setDescription(String d) {description=d;}

	public UserAplication getUserAplication() {return user;}
	public void setUser(UserAplication o) {user =o;}
	
	public String getCity() {return city;}	
	public void setCity(String c) {city=c;}

	public int getRooms() {return nRooms;}
	public void setRooms(int r) {nRooms = r;}

	public int getKitchen() {return nKitchen;}
	public void setKitchen(int k) {nKitchen = k;}
	
	public int getBaths() {return nBaths;}
	public void setBaths(int b) {nBaths = b;}
	
	public int getLiving() {return nLiving;}
	public void setLiving(int l) {nLiving = l;}
	
	public int getPark() {return nPark;}
	public void setPark(int p) {nPark = p;}
	
	public String toString() {return this.houseNumber + ": " + this.city;}
	
	/*public Set<Book> getReservas(){return vectorReservas;}
	
	public Set<Offer> getOfertas(){ return vectorOfertas;}
	public void anadirOferta(Date primerDia, Date ultimoDia, float precio, boolean obligatorio) throws Exception{
		Fechas auxFecha = null;	
		//Comprobar si esta reservado:
		Date auxp = (Date)primerDia.clone();
		while (auxp.compareTo(ultimoDia)<=0){
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				auxFecha = i.next();				
				if (auxp.compareTo(auxFecha.getFecha())==0) {
					if (auxFecha.isReservado()) throw new Exception ("Hay una reserva en las fechas seleccionadas.");
					if (auxFecha.getOfer()!=null) throw new Exception ("Hay otra fecha para las fechas solicitadas. Eliminela antes.");
					break;
				}
			}
			auxp.setTime(auxp.getTime()+1*24*60*60*1000);
		}
		//Hacer lo demas:
		Set<Fechas> auxVectorFechas = new HashSet<Fechas>();
		Set<Fechas> auxVectorFechasNuevas = new HashSet<Fechas>();
		Date primero = (Date) primerDia.clone();
		float precioPorDia = precio/getDias((Date)primerDia.clone(), (Date)ultimoDia.clone());
		auxp = (Date)primerDia.clone();
		auxFecha = null;
		while (auxp.compareTo(ultimoDia)<=0){
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				auxFecha = i.next();				
				if (auxp.compareTo(auxFecha.getFecha())==0) break;
			}
			if (auxFecha == null || (auxFecha.getFecha().compareTo(auxp)!=0)){
				auxFecha = new Fechas((Date) auxp.clone(), precioPorDia, this, 0);
				vectorFechas.add(auxFecha);
				auxVectorFechasNuevas.add(auxFecha);
			}
			auxVectorFechas.add(auxFecha);
			auxp.setTime(auxp.getTime()+1*24*60*60*1000);
		}
		Offer oferta = new Offer(primero, ultimoDia, precio, this, auxVectorFechas, obligatorio);
		vectorOfertas.add(oferta);
		Iterator<Fechas> i = auxVectorFechas.iterator();
		while(i.hasNext()){
			Fechas auxi = i.next();
			Iterator<Fechas> j = vectorFechas.iterator();
			while(j.hasNext()){
				Fechas auxj = j.next();
				if (auxj.equals(auxi)) auxj.setOferta(oferta, obligatorio);
			}
		}
	}
	*//*
	public Set<Fechas> getFechas(){return vectorFechas;}
	
	@SuppressWarnings("deprecation")
	private boolean anadirFecha( Date date, float precio, int minimoDias){
		Date auxDate = new Date(date.getYear(), date.getMonth(), date.getDate());
		Fechas fecha = new Fechas(auxDate, precio, this, minimoDias);
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0){
				if (aux.isReservado()) return false;
				aux.setPrecio(precio);
				aux.setMinimodias(minimoDias);
				return true;
			}
		}
		vectorFechas.add(fecha);
		return true;
	}
	@SuppressWarnings("deprecation")
	public boolean anadirFechas( Date primerDia, Date ultimoDia,  float precio, int minimoDias){
		Date auxDate = new Date(primerDia.getYear(), primerDia.getMonth(), primerDia.getDate());
		Date auxUltimoDia = new Date(ultimoDia.getYear(), ultimoDia.getMonth(), ultimoDia.getDate());
		boolean auxB = true;
		if(auxDate.equals(auxUltimoDia))if (!anadirFecha(auxDate, precio, minimoDias)) auxB = false;
		while (auxDate.compareTo((auxUltimoDia))<=0){
			if (!anadirFecha(auxDate, precio, minimoDias)) auxB = false;
			
			auxDate.setTime(auxDate.getTime()+1*24*60*60*1000);
		}
		return auxB;
	}
	*/
	/*
	public Set<String> getImages(){
		return vectorImage;
	}
	
	public void a�adirImagen(String image) throws Exception {
			vectorImage.add(image);
	}
	
	
	
	private boolean disponibleFecha(Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0 && !aux.isReservado() && !aux.isUnidoOferta()) return true;
			else if (aux.getFecha().compareTo(date)==0 && (aux.isReservado() || aux.isUnidoOferta())) return false;
		}
		return false;
	}
	
	public boolean disponibleFechas(Date inicio, Date fin){
		Date aux = inicio;
		while (aux.compareTo(fin) !=0 && disponibleFecha(aux)) aux.setTime(aux.getTime()+1*24*60*60*1000);
		if (aux.compareTo(fin) ==0 && disponibleFecha(aux)) return true;
		return (disponibleFechaOferta(inicio, fin) != null);
	}
	
	private Offer disponibleFechaOferta(Date inicio, Date fin){
		System.out.println("holccccc:   " + inicio.toString() + fin.toString());
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (aux.getPrimerDia().equals(inicio) && aux.getUltimoDia().equals(fin) && !aux.isReservado()) return aux;
		}
		return null;
	}
	
	private Fechas getFechaConcreta(Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0 && !aux.isReservado()) return aux;
		}
		return null;
	}
	
	private Set<Fechas> getFechas(Date inicio, Date fin){
		Set<Fechas> aux = new HashSet<Fechas>();
		Date auxInicio = (Date) inicio.clone();
		Date auxFin = (Date) fin.clone();
		auxFin.setTime(auxFin.getTime()+1*24*60*60*1000);
		while (auxInicio.compareTo(auxFin) !=0){
			Fechas auxFecha = getFechaConcreta(auxInicio);
			if (auxFecha == null) break;
			aux.add(auxFecha);
			auxInicio.setTime(auxInicio.getTime()+1*24*60*60*1000);
		}
		if(auxInicio.compareTo(auxFin) !=0 || aux.isEmpty()) return null;
		return aux;
	}
	
	public Book hacerReserva(UserAplication cliente, int numeroDeReserva, Date inicio, Date fin) throws Exception{
		Offer auxOferta = disponibleFechaOferta((Date)inicio.clone(), (Date)fin.clone());
		Book reserva = null;
		System.out.print("primer dia: " + inicio.toString());
		System.out.print("ultimo dia: " + fin.toString());
		if (disponibleFechas((Date)inicio.clone(), (Date)fin.clone()) || (auxOferta != null && !auxOferta.isReservado())){
			Set<Fechas> auxFechas = (Set<Fechas>) getFechas((Date)inicio.clone(), (Date)fin.clone());
			if (auxOferta != null && auxFechas != null){
				reserva = new Book(this, numeroDeReserva, auxOferta.getPrice(), cliente, auxOferta, auxFechas);
				cliente.anadirReserva(reserva);
				vectorReservas.add(reserva);
			} else if (auxOferta == null && auxFechas != null){
				float precio = 0;
				Iterator<Fechas> i = auxFechas.iterator();
				while (i.hasNext()){
					Fechas aux = i.next();
					System.out.println("while dia: " + aux.getFecha().toString());
					precio = precio + aux.getPrecio();
				}
				reserva = new Book(this, numeroDeReserva, precio, cliente, auxFechas);
				vectorReservas.add(reserva);
				cliente.anadirReserva(reserva);
				//eliminarOfertaQueContenga(vectorFechas);
			}
		} 
		if (reserva==null) throw new Exception("Lamentablemente, no se ha podido reservar");
		return reserva;		
	}
	
	public void eliminarOfertaQueContenga(Vector<Fechas> vectorFe){
		Iterator<Fechas> i = vectorFe.iterator();
		while (i.hasNext()) eliminarOfertaQueContenga(i.next().getFecha());
	}
	
	public void eliminarOfertaQueContenga(Date fecha){
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (aux.contiene(fecha)) vectorOfertas.remove(aux);
		}
	}
	
	public Offer eliminarOferta(Date inicio, Date fin) throws Exception{
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (inicio.equals(aux.getPrimerDia())){
				if (fin.equals(aux.getUltimoDia())){
					if (aux.isReservado()) EnviarCorreo.enviarCorreos(aux.getReserva().getCliente().getEmail(), "Su reserva numero " + aux.getReserva().getNumeroDeReserva() + " se ha cancelado", "Lamentablemente, su reserva ha sido cancelado debido a que el prop�etario de la casa rural ha eliminado la oferta.");
					vectorOfertas.remove(aux);
					return aux;
				}
				break;
			}
		}
		return null;
	}

	public void setImages(Vector<String> images) {
		vectorImage= new HashSet<String>();
		for(int i =0; i<images.size();i++){
			vectorImage.add(images.get(i));
		}
	}
	
	public Set<Fechas> eliminarTodasFechas(){
		Set<Fechas> auxVectorFechas = new HashSet<Fechas>();
		auxVectorFechas = vectorFechas;
		vectorFechas = new HashSet<Fechas>();
		return auxVectorFechas;
	}

	public Set<Offer> eliminarTodasOfertas(){
		Set<Offer> auxVectorOffer = new HashSet<Offer>();
		auxVectorOffer = vectorOfertas;
		vectorOfertas = new HashSet<Offer>();
		return auxVectorOffer;
	}
	
	public void eliminarOferta(Fechas f){
		vectorFechas.remove(f);
	}
	
	public Fechas eliminarFecha(Date f) throws Exception{
		Iterator<Fechas> fechas = vectorFechas.iterator();
		while(fechas.hasNext()){
			Fechas fecha= fechas.next();
			if(fecha.getFecha().equals(f)){
				if (fecha.isReservado()) EnviarCorreo.enviarCorreos(fecha.getReserva().getCliente().getEmail(), "Su reserva numero " + fecha.getReserva().getNumeroDeReserva() + " se ha cancelado", "Lamentablemente, su reserva ha sido cancelado debido a que el prop�etario de la casa rural ha eliminado la disponibilidad de la fecha.");
				if(fecha.getOfer()==null){
					vectorFechas.remove(fecha);
					return fecha;
				}else{
					throw new Exception("La fecha no se puede eliminar porque forma parte de una oferta.");}
			}	
		}
		return null;
	}
	
	public Book eliminarReserva(int num) throws Exception{
		Iterator<Book> iter = vectorReservas.iterator();
		while(iter.hasNext()){
			Book reserv = iter.next();
			if(reserv.getNumeroDeReserva()==num){
				vectorReservas.remove(reserv);
				return reserv;
			}
		}	
		throw new Exception("No existia dicha reserva"); 
	}
	
	public Set<Book> eliminarTodasReserva(){
		@SuppressWarnings("unchecked")
		Set<Book> auxVectorBook =   vectorReservas; //AQUI SE HACIA UN CLONE; SI NO FUNCIONA TE  JODES
		Iterator<Book> iterator= vectorReservas.iterator();
		while (iterator.hasNext()){
			Book actual = iterator.next();
			try {
				EnviarCorreo.enviarCorreos(actual.getCliente().getEmail(), "Reserva: " + actual.getNumeroDeReserva() , "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
			} catch (Exception e) {
				e.getMessage();
			}
			auxVectorBook.add(actual);
		}
		/*for (int i = 0; i<vectorReservas.size(); i++){
			try {
				EnviarCorreo.enviarCorreos(vectorReservas.get(i).getCliente().getEmail(), "Reserva: " + vectorReservas.get(i).getNumeroDeReserva() , "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
			} catch (Exception e) {
				e.getMessage();
			}
			auxVectorBook.add(vectorReservas.get(i));
		}
		vectorReservas = new HashSet<Book>();
		return auxVectorBook;
	}
	
	private int getDias(Date fInicial, Date fFinal) {
        Calendar ci = Calendar.getInstance();
        ci.setTime(fInicial);
        Calendar cf = Calendar.getInstance();
        cf.setTime(fFinal);
        long ntime = cf.getTimeInMillis() - ci.getTimeInMillis();
        return (int)Math.ceil((double)ntime / 1000 / 3600 / 24);
    }*/

	public void anadirCalificacion(String comentario, int puntuacion){
		comentarios = comentarios +" / "+ comentario;
		float aux = calificacion*nCal;
		nCal=nCal+1;
		calificacion= (aux+puntuacion)/nCal;
	}
	
	public String getComentarios(){
		return comentarios;
	}
	
	public Float getNotaMedia(){
		return calificacion;
	}
	
	public int getnCal(){
		return nCal;
	}
}
