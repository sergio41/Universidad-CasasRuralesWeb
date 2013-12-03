package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class UserAplication implements Serializable{
	private String email;
	private String pass;
	private String estadoCivil;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String pais;
	private String edad;
	private Owner propietario;
	private Set<Book> vectorBook;
	private String perfil; 

	public UserAplication(String e, String p, String eC, String nom, String ape, String tel, String ps, String edd){
		email=e;
		pass=p;
		estadoCivil=eC;
		nombre=nom;
		apellidos=ape;
		telefono=tel;
		pais=ps;
		edad=edd;
		propietario=null;
		//vectorBook = new ArrayList<Book>();
	}
	
	public String getEmail() {return email;}
	public void setEmail(String e) {email = e;}
	
	public String getPass() {return pass;}
	public void setPass(String p) {pass = p;}
	
	public String getEstadoCivil() {return estadoCivil;}
	public void setEstadoCivil(String e) {estadoCivil = e;}
	
	public String getName() {return nombre;}
	public void setName(String name) {nombre = name;}

	public String getApellidos() {return apellidos;}
	public void setApellidos(String a) {apellidos= a;}
	
	public String getTelefono(){return telefono;}	
	public void setTelefono(String t){telefono= t;}
	
	public String getPais(){return pais;}
	public void setPais(String p){pais= p;}
	
	public String getEdad(){return edad;}	
	public void setEdad(String e){edad= e;}
		
	public void setPropietario(Owner own){propietario=own;}
	public Owner getPropietario(){return propietario;}
	
	public Set<Book> getReservas(){return vectorBook;}
	public void anadirReserva(Book reserva){ 
		System.out.println("Reserva añadida");
		vectorBook.add(reserva);}
	public void eliminarReserva(Book reserva){vectorBook.remove(reserva);}
	
	public String getPerfil(){return perfil;}
	public void setPerfil(String imagenPerfil){ perfil = imagenPerfil;}
	
	public void addRuralHouse(int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> images){
		getPropietario().addRuralHouse(new RuralHouse(numero, this, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images));
	}
		
	public void pagar(int num){
		Iterator<Book> reservasConcretas =  getReservas().iterator();
		while (reservasConcretas.hasNext()){
			Book reserva = reservasConcretas.next();
			if(reserva.getNumeroDeReserva()==num && !reserva.isPaid()){
				reserva.pagar();
			}
		}
	}	
}
