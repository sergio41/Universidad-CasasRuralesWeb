package businessLogic;
import java.rmi.*;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Date;
import domain.*;

public interface ApplicationFacadeInterface extends Remote {

	public void anadirRuralHouse(UserAplication usuario, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> imagenes) throws Exception;
	
	public void modificarRuralHouse(UserAplication usuario,  int numero,
			String description, String city, int nRooms, int nKitchen,
			int nBaths, int nLiving, int nPark) throws Exception;
		
	public void nuevoUsuario( String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception;
	
	public UserAplication modificarPerfil(UserAplication usuario, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception;
		
	public UserAplication getUsuario(UserAplication usuario) throws Exception;
	
	public UserAplication hacerLogin(String email, String pass) throws Exception;
	
	public List<String> getUserHouses(UserAplication user) throws Exception;
	
	public void recuperarContrasena( String email) throws Exception;
	
	public Owner getOwner(UserAplication usuario) throws Exception;
	
	public UserAplication modificarOwner(UserAplication usuario, String bA, String t, String i, String p, String m) throws Exception;
		
	public Vector<String> Ultimos10Tweets() throws Exception;
	
	public void hacerReserva(UserAplication usuario, int num) throws Exception;
	
	public void anadirOferta(UserAplication usuario, int numero, Date inicio, Date fin, float precio) throws Exception;

	public List<Offer> getOfertas() throws Exception;
	
	public List<String> getOfertasS() throws Exception;
	
	public List<String> getOfertasU(UserAplication user) throws Exception;
	
	public RuralHouse getCasas(int num) throws Exception;

	public Offer getOferta(int num) throws Exception;
	
	public void modificarOferta(int num, Date firstDay, Date lastDay, float price) throws Exception;
	
	public Vector<Book> getReservas(UserAplication user) throws Exception;
	
}