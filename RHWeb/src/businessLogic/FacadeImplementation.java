package businessLogic;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

import dataAccess.DatabaseManager;
import domain.Book;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.UserAplication;
import externalDataSend.EnviarCorreo;
import externalDataSend.GestionTwitter;


@SuppressWarnings("serial")
public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface, Serializable {

	private Vector<String> twitter10;
		
	public FacadeImplementation() throws Exception{
		
	}
	public void anadirRuralHouse(UserAplication usuario, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> imagenes) throws Exception{
		System.out.println("FacadeImplementation: Añadir nueva casa Rural");
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {			
				if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
				if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
				if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
				DatabaseManager.anadirRuralHouse(usuario, 0, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, imagenes);
			}
	}
	
	public UserAplication modificarOwner(UserAplication usuario, String bA, String t, String i, String p,	String m) throws Exception {
		System.out.println("FacadeImplementation: modificar owner");
		if(bA.compareTo("")!=0){
			if(usuario.tieneProp())
				return DatabaseManager.modificarOwner(usuario, bA, t, i, p, m);
			else 
				return DatabaseManager.nuevoOwner(usuario, bA, t, i, p, m);
		}throw new Exception("Error al crear Propierario");
	}
	
	public List<String> getUserHouses(UserAplication user){
		List<String> casas = new Vector<String>();
		Iterator<RuralHouse> i = DatabaseManager.getCasasUser(user);
		while (i.hasNext()){
			RuralHouse casa = i.next();
			casas.add(casa.getHouseNumber()+" / "+ casa.getCity().toString());
		}
		return casas;
	}
	
	public void modificarRuralHouse(UserAplication usuario, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark) throws Exception {
		System.out.println("FacadeImplementation: modificar casa rural");
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {					
			if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
			DatabaseManager.modificarRuralHouse(usuario.getEmail(), numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
		}
	}
	
	public void nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		System.out.println("FacadeImplementation: crear usuario");
		String exp = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; 
		CharSequence seq = email;
		Pattern pattern = Pattern.compile(exp,Pattern.CASE_INSENSITIVE); 
		java.util.regex.Matcher m = pattern.matcher(seq);
		if (!m.matches()) throw new Exception("Algunos datos obligatorios faltan.");
		System.out.println("FacadeImplementation: email OK");
		if (email.compareTo("")==0 || pass.compareTo("")==0 || nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			if (edad.compareTo("")==0) edad = null;
			if (apellidos.compareTo("")==0) apellidos = null;
			if (telefono.compareTo("")==0) telefono = null;
			System.out.println("FacadeImplementation: insertar usuario");
			DatabaseManager.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
			System.out.println("FacadeImplementation: insertado usuario");
			try {
				System.out.println("FacadeImplementation: intentar mandar mail");
				EnviarCorreo.enviarCorreos(email, "Registro en Villatripas de Arriba", "Te has registrado en villatripas de arriba con el email: " + email);
				GestionTwitter.enviarTweet("Bienvenid@: " + nombre + " " + Calendar.getInstance().getTime().toString());
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Este error no es significativo.", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
	
	public UserAplication getUsuario(UserAplication usuario) throws Exception {
		System.out.println("FacadeImplementation: devolver usuario");
		return DatabaseManager.getUser(usuario.getEmail());
	}

	public Owner getOwner(UserAplication usuario) throws Exception {
		System.out.println("FacadeImplementation: devolver owner");
		return usuario.getPropietario();
	}	
	
	
	public void recuperarContrasena(String email) throws Exception {
		System.out.println("FacadeImplementation: recuperar contraseña");
		UserAplication user = DatabaseManager.getUser(email);
		if (user == null) throw new Exception("El email no existe.");
		else {
			try {
				EnviarCorreo.enviarCorreos(user.getEmail(), "Contraseña", "Tu contraseña es " + user.getPass());
			} catch (Exception e) {
				throw new Exception("Error al enviar el email de recuperacion de contraseña. Intentelo mas tarde.");
			}
		}
	}
	
	public UserAplication  hacerLogin(String email, String pass) throws Exception {
		System.out.println("FacadeImplementation: hacer login");
		System.out.println("FacadeImplementation: hacer login " + email);
		System.out.println("FacadeImplementation: hacer login " + pass);
		if (email ==null || pass == null) throw new Exception("Algunos datos obligatorios faltan.");
		if (email.compareTo("")==0 || pass.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		UserAplication usuario = DatabaseManager.getUser(email, pass);
		if (usuario == null){
			throw new Exception("No se ha podido hacer Login");
		} else {
			return usuario;
		}
	}


	public UserAplication modificarPerfil(UserAplication usuario, String estadoCivil, String nombre,String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		System.out.println("FacadeImplementation: modificar perfil");
		if (nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else usuario = DatabaseManager.modificarUsuario(usuario, estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
		return usuario;		
	}

	public Vector<String> Ultimos10Tweets() throws Exception {
		System.out.println("FacadeImplementation: Ultimos 10 tweets");
		try {
			Iterator<String> i = GestionTwitter.getTodosTweets().iterator();
			twitter10 = new Vector<String>();
			int x = 0;
			while (i.hasNext()){
				twitter10.add(i.next());
				x++;
				if(x==10) break;
			}
			System.out.println("FacadeImplementationFin: Ultimos 10 tweets");
			return twitter10;
		} catch (Exception e) {
			System.out.println("FacadeImplementationFin: Ultimos 10 tweets, error");
			if (twitter10 == null)throw new Exception();			
			return twitter10;
		}		
	}

	public void hacerReserva(UserAplication usuario, int num)  throws Exception{
		System.out.println("FacadeImplementation: hacer reserva");
		DatabaseManager.hacerReserva(usuario, num);
	}
	
	public void anadirOferta(UserAplication usuario, int numero, Date inicio, Date fin, float precio) throws Exception{
		System.out.println("FacadeImplementation: anadir oferta");
		DatabaseManager.anadirOferta(usuario, numero, inicio, fin, precio);
	}


	public List<String> getOfertasS() throws Exception {
		System.out.println("FacadeImplementation: get ofertas");
		List<String> ofertas = new Vector<String>();
		Iterator<Offer> i = DatabaseManager.getOfertasS();
		while (i.hasNext()){
			Offer oferta = i.next();
			ofertas.add(oferta.getID()+" / "+ oferta.getFirstDay().toString()+ " / " + oferta.getLastDay().toString());
		}
		return ofertas;
	}
	
	public List<String> getOfertasU(UserAplication user) throws Exception {
		System.out.println("FacadeImplementation: get ofertas");
		List<String> ofertas = new Vector<String>();
		List<Offer> i = DatabaseManager.getOfertasUser(user);
		int j = 0;
		while (j<i.size()){
			Offer oferta = i.get(j);
			ofertas.add(oferta.getID()+" / "+ oferta.getFirstDay().toString()+ " / " + oferta.getLastDay().toString());
			j++;
		}
		return ofertas;
	}
	
	public List<Offer> getOfertas() throws Exception {
		return DatabaseManager.getOfertas();
	}

	public RuralHouse getCasas(int num) throws Exception {
		System.out.println("FacadeImplementation: get casas numero");
		RuralHouse vector= DatabaseManager.getRuralHouse(num);
		return vector;
	}

	public Vector<Book> getReservas(UserAplication user) throws Exception {
		System.out.println("Aqui");
		return DatabaseManager.getReservas(user);
	}

	@Override
	public Offer getOferta(int num) throws Exception {
		return DatabaseManager.getOferta(num);
	}

	public void modificarOferta(int num, Date firstDay, Date lastDay, float price) throws Exception {
		DatabaseManager.modificarOferta(num, firstDay, lastDay, price);
		
	}
	public void close() {
	}
}

