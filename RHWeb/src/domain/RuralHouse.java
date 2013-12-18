package domain;
import java.io.Serializable;

@SuppressWarnings("serial")
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
		comentarios = "";
		calificacion = (float) 2.5;
		nCal=0;
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
