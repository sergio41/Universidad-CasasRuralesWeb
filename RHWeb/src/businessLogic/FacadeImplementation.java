package businessLogic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import configuration.Config;
import dataAccess.DatabaseManager;
import dataAccess.DatabaseManager;
import domain.Book;
import domain.Fechas;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.UserAplication;
import externalDataSend.EnviarCorreo;
import externalDataSend.GestionTwitter;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface, Serializable {
	private Vector<String> twitter10;
	
	public FacadeImplementation() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("FacadeImplementation: Se crea la instancia");
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DatabaseManager.openDatabase(dataBaseOpenMode);
	}
	
	public void eliminarCasaRural(UserAplication usuario, int numero) throws Exception {
		System.out.println("FacadeImplementation: Se elimina la casa rural: " + numero);
		DatabaseManager.eliminarCasaRural(usuario, numero);
		File carpet = new File("\\imagenes\\"+usuario.getEmail()+"\\"+numero);
		if(!eliminarCarpetaConfotos(carpet))
			System.out.println("No se han eliminado guay las fotos");
	}
	
	@SuppressWarnings("unused")
	public boolean eliminarCarpetaConfotos(File directory) {
		System.out.println("FacadeImplementation: Se elimina la carpeta con fotos: " + directory.toPath());
		// System.out.println("removeDirectory " + directory);
		if (directory == null) return false;
		if (!directory.exists())
			return true;
		if (!directory.isDirectory())
			return false;
		String[] list = directory.list();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!eliminarCarpetaConfotos(entry))
						return false;
				} else {
					if (!entry.delete())
						return false;
				}
			}
		}

		return directory.delete();
	}
		

	public void anadirRuralHouse(UserAplication usuario, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> imagenes) throws Exception{
		System.out.println("FacadeImplementation: Añadir nueva casa Rural");
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {			
				if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
				if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
				if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
				int numero = getNumeroCR();
				DatabaseManager.anadirRuralHouse(usuario, numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, imagenes);
			}
	}
	
	public void modificarContraseña(UserAplication usuario, String pass) throws Exception {
		System.out.println("FacadeImplementation: modificar contraseña");
		DatabaseManager.modificarContrasena(usuario, pass);
	}
	
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException, Exception {
		System.out.println("FacadeImplementation: obterner todas las casas rurales");
		return null;
		//return DatabaseManager.getCasasRuralesTodas();
	}
	
	public void close() throws RemoteException{
		System.out.println("FacadeImplementation: cerrar la BD");
		DatabaseManager.close();
	}
	
	public void modificarOwner(UserAplication usuario, String bA, String t, String i, String p,	String m) throws Exception {
		System.out.println("FacadeImplementation: modificar owner");
		if(usuario.tieneProp())
			usuario= DatabaseManager.modificarOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
		else 
			usuario= DatabaseManager.nuevoOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
	}
	
	public void modificarRuralHouse(UserAplication usuario, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<ImageIcon> imagenes) throws Exception {
		System.out.println("FacadeImplementation: modificar casa rural");
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {					
			if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
			//DatabaseManager.modificarRuralHouse(usuario.getEmail(), numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, setGuardarImagenes(usuario.getEmail(), imagenes, numero));
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
			//DatabaseManager.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad, setGuardarPerfil(email, perfil));
			try {
				System.out.println("FacadeImplementation: intentar mandar mail");
				EnviarCorreo.enviarCorreos(email, "Registro en Villatripas de Arriba", "Te has registrado en villatripas de arriba con el email: " + email);
				GestionTwitter.enviarTweet("Bienvenid@: " + nombre + " " + Calendar.getInstance().getTime().toString());
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Este error no es significativo.", javax.swing.JOptionPane.ERROR_MESSAGE);
				//throw new Exception("Error al enviar el email de registro o envio de tweet automatico.");
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


	public void modificarPerfil(UserAplication usuario, String estadoCivil, String nombre,String apellidos, String telefono, String pais, String edad, ImageIcon perfil) throws Exception {
		System.out.println("FacadeImplementation: modificar perfil");
		if (nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else usuario = DatabaseManager.modificarUsuario(usuario, estadoCivil, nombre, apellidos, telefono, pais, edad, setGuardarPerfil(usuario.getEmail(), perfil));		
	}
	
	public int getNumeroCR() throws Exception{
		/*System.out.println("FacadeImplementation: numero casa rural");
		Vector<RuralHouse> vector = DatabaseManager.getCasasRuralesTodas();
		int max = 0;
		java.util.Iterator<RuralHouse> i = vector.iterator();
		while (i.hasNext()) {
			int aux = i.next().getHouseNumber();
			if( max < aux) max = aux;
		}
		max++;*/
		return 0;
	}
	
	public int getNumeroReserva() throws Exception{
		System.out.println("FacadeImplementation: numero reserva");
		Vector<Book> vector = DatabaseManager.getTodasLasReservas();
		int max = 0;
		java.util.Iterator<Book> i = vector.iterator();
		while (i.hasNext()) {
			int aux = i.next().getNumeroDeReserva();
			if( max < aux) max = aux;
		}
		max++;
		System.out.println("FacadeImplementationFin: numero reserva");
		return max;
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
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin) throws Exception{
		System.out.println("FacadeImplementation: casas rurales disponibles");
		return null;
		//return DatabaseManager.casasRuralesDisponibles(inicio, fin);
	}
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin,
			String Ciudad) throws Exception {
		System.out.println("FacadeImplementation: casas rurales disponibles ciudad");
		return null;
		//return DatabaseManager.casasRuralesDisponibles(inicio, fin, Ciudad);
	}
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad, int Banos, int habita, int cocina, int sala, int park) throws Exception{
		System.out.println("FacadeImplementation: casas rurales disponibles todo");
		return null;
		//return DatabaseManager.casasRuralesDisponibles(inicio, fin, Ciudad, Banos, habita, cocina, sala, park);
	}
	
	public void anadirOferta(UserAplication usuario, int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception{
		System.out.println("FacadeImplementation: anadir oferta");
		DatabaseManager.anadirOferta(usuario, numero, inicio, fin, precio, obligatorio);
	}


	public List<String> getOfertas() throws Exception {
		System.out.println("FacadeImplementation: get ofertas");
		List<String> ofertas = new Vector<String>();
		Iterator<Offer> i = DatabaseManager.getOfertas();
		while (i.hasNext()){
			Offer oferta = i.next();
			ofertas.add(oferta.getID()+" / "+ oferta.getPrimerDia().toString()+ " / " + oferta.getUltimoDia().toString());
		}
		return ofertas;
	}

	@Override
	public Vector<RuralHouse> getCasas(String ciudad, int Banos, int Habita,
			int Cocina, int Estar, int Park) throws Exception {
		System.out.println("FacadeImplementation: get casas todo");
		/*Vector<RuralHouse> vector;
		vector = DatabaseManager.getHouse(ciudad, Banos, Habita,
					Cocina, Estar, Park);
		if(vector==null){
			throw new Exception("No existen casas con esos datos.");
		}
		return vector;*/
		return null;
	}
	
	public RuralHouse getCasas(int num) throws Exception {
		System.out.println("FacadeImplementation: get casas numero");
		/*RuralHouse vector;
		vector = DatabaseManager.getRuralHouse(num);
		if(vector==null){
			throw new Exception("No existen casas con esos datos.");
		}
		return vector;*/
		return null;
	}

	public Vector<ImageIcon> getFotosRH(int numeroDeCasa) throws Exception {
		/*System.out.println("FacadeImplementation: get fotos casa rural");
		Vector<ImageIcon> aux = new Vector<ImageIcon>();
		RuralHouse casa = DatabaseManager.getRuralHouse(numeroDeCasa);
		Iterator<String> i = casa.getImages().iterator();
		while (i.hasNext()) aux.add(new ImageIcon(i.next()));*/
		return null;
	}


	public ImageIcon getFotoPerfil(String email) throws Exception {
		System.out.println("FacadeImplementation: get foto perfil");
		if (DatabaseManager.getUser(email).getPerfil() == null) return null;
		return new ImageIcon(DatabaseManager.getUser(email).getPerfil());
	}
	
	private Vector<String> setGuardarImagenes(String email, Vector<ImageIcon> imagenes, int numeroCasaRural) throws Exception {
		System.out.println("FacadeImplementation: guardar imagenes");
		File fCarpeta = new File("\\imagenes\\"+email+"\\"+numeroCasaRural);
		if (fCarpeta.exists()) fCarpeta.delete();
		fCarpeta.mkdirs();
		Vector<String> aux = new Vector<String>();
		Iterator<ImageIcon> i = imagenes.iterator();
		while (i.hasNext()){
			try {
				Image auxi = i.next().getImage();
				File fDestino = new File("\\imagenes\\"+email+"\\"+numeroCasaRural+"\\"+auxi.toString());
				BufferedImage bi = toBufferedImage(auxi);
				ImageIO.write(bi, "png", fDestino);
				aux.add(fDestino.getPath());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return aux;
	}
	
	private String setGuardarPerfil(String email, ImageIcon imagen) throws Exception {
		System.out.println("FacadeImplementation:  guardar imagen perfil");
		if (imagen == null) return null;
		try { 
			File fCarpeta = new File("\\imagenes\\"+email+"\\Perfil");
			if (fCarpeta.exists()) fCarpeta.delete();
			fCarpeta.mkdirs();
			File fDestino = new File("\\imagenes\\"+email+"\\Perfil\\perfil");
			BufferedImage bi = toBufferedImage(imagen.getImage());
			ImageIO.write(bi, "png", fDestino);
			return fDestino.getPath();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	private BufferedImage toBufferedImage(Image src) {
		int w = src.getWidth(null);
		int h = src.getHeight(null);
		int type = BufferedImage.TYPE_INT_RGB;  // other options
		BufferedImage dest = new BufferedImage(w, h, type);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}

	public void anadirFechas(UserAplication usuario, int numero, Date inicio, Date fin, float precio, int minimoDeDias) throws Exception {
		System.out.println("FacadeImplementation: anadir fecha");
		//DatabaseManager.anadirFechas(usuario, numero, inicio, fin, precio, minimoDeDias);
	}

	public Set<Fechas> getFechas(UserAplication usuario, int numeroRH) throws Exception{
		System.out.println("FacadeImplementation: fet fechas");
		/*Iterator<RuralHouse> i = usuario.getPropietario().getRuralHouses().iterator();
		while (i.hasNext()){
			RuralHouse aux = i.next();
			if (aux.getHouseNumber() == numeroRH) return aux.getFechas();
		}
		throw new Exception("Ha ocurrido un error a la hora de encontrar fechas");*/
		return null;
	}
	
	public void eliminarOferta(UserAplication usuario, int nRH, Date ini, Date fin) throws Exception{
		System.out.println("FacadeImplementation: elimina oferta");
		DatabaseManager.eliminarOferta(usuario, nRH, ini, fin);
	}
	
	public void eliminarFecha(UserAplication usuario, int nRH, Date ini) throws Exception{
		System.out.println("FacadeImplementation: elimina fecha");
		DatabaseManager.eliminarFecha(usuario, nRH, ini);
	}

	public void eliminarReserva(int num) throws Exception{
		System.out.println("FacadeImplementation: eliminar reserva");
		//DatabaseManager.eliminarReserva(num);
	}
	
	public Book pagar(int num, UserAplication user) throws Exception{
		System.out.println("FacadeImplementation: pagar");
		return DatabaseManager.pagar(num, user);
	}

	@Override
	public void anadirCalificacionACasaRural(int numero, String comentario, int puntuacion) throws Exception {
		System.out.println("FacadeImplementation: añadir calificacion a casa rural");
		//DatabaseManager.anadirCalificacionACasaRural(numero, comentario, puntuacion);
		
	}
	
	public Book getReserva(int num) throws Exception{
		return DatabaseManager.getReserva(num);
	}

	@Override
	public void cambiarContra(UserAplication usuario, String text)throws Exception {
			DatabaseManager.cambiarContraseña(usuario,text);
	}
}

