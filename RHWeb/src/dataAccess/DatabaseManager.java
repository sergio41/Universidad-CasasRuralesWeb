package dataAccess;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;

import com.db4o.*;

import configuration.Config;
import domain.*;

public class DatabaseManager { 
	private static ObjectContainer  db;
	
    public static void openDatabase(String mode){
		Config c=Config.getInstance();
		String db4oFileName=c.getDb4oFilename();
		if (mode.compareTo("open")==0) {
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			System.out.println("DataBase Opened");
		} else if (mode.compareTo("initialize")==0){
			new File(db4oFileName).delete();
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			db.commit();
			System.out.println("DataBase Initialized");
		}
	}
	
	public static void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	
	public static boolean comprobarEmail(String email){	
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet<RuralHouse> userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static boolean comprobarEmailAndPass(String email, String pass){	
		UserAplication u = new UserAplication(email, pass, null, null, null, null, null, null);
		ObjectSet<RuralHouse> userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static UserAplication getUser(String email){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserAplication> result = session.createQuery("from UserAplication where email='"+email+"'").list();
		Iterator<UserAplication> it = result.iterator();
		session.getTransaction().commit();
		if(it.hasNext()) return it.next();
		else return null;
	}
	
	public static UserAplication getUser(String email, String pass){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		UserAplication user = new UserAplication();
		session.beginTransaction();
		System.out.println(email+pass);
		Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+email+"' and pass='"+ pass+"'").iterate();
		if(result.hasNext()){user = result.next();
			session.getTransaction().commit();
			return user;
		}else
			return null;
	}	
	/*
	public static RuralHouse getRuralHouse(int houseNumber) throws Exception{
		RuralHouse rh = new RuralHouse(houseNumber, null,null,null,0,0,0,0,0,null);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		if (ruralHouseConcretos.hasNext()) return ruralHouseConcretos.next();
		else throw new Exception("La casa rural no existe.");
	}
	
	public static Vector<RuralHouse> getCasasRuralesTodas(){
		Vector<RuralHouse> vector = new Vector<RuralHouse>();
		RuralHouse rh = new RuralHouse(0, null,null,null,0,0,0,0,0, null);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		while (ruralHouseConcretos.hasNext())vector.add(ruralHouseConcretos.next());
		return vector;
	}
	*/
	public static UserAplication nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 List<UserAplication> result = session.createQuery("from UserAplication where email='"+email+"'").list();
		 if (result.size()>0) throw new Exception("El email ya esta usado. Logueate");
		 UserAplication user = new UserAplication(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
		 user.setPerfil(perfil);
		 session.save(user);
		 session.getTransaction().commit();
		 return user;
	}
	/*
	public static UserAplication modificarRuralHouse(String email, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<String> images) throws Exception {
		ObjectSet<RuralHouse> RHConcreto = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));
		if (RHConcreto.hasNext()){
			RuralHouse casa = RHConcreto.next();
			casa.setBaths(nBaths);
			casa.setCity(city);
			casa.setDescription(description);
			casa.setRooms(nRooms);
			casa.setKitchen(nKitchen);
			casa.setLiving(nLiving);
			casa.setPark(nPark);
			casa.setImages(images);
			db.store(casa);
			db.commit();
			return getUser(email);
		} else throw new Exception("La casa rural no se puede modificar. No se ha encontrado en la base de datos.");
	}*/
	
	public static UserAplication modificarUsuario(UserAplication user, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<UserAplication> userConcretos = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		if (userConcretos.hasNext()){ 
			UserAplication user2 = userConcretos.next();
			user2.setApellidos(apellidos);
			user2.setEdad(edad);
			user2.setEstadoCivil(estadoCivil);
			user2.setName(nombre);
			user2.setPais(pais);
			user2.setTelefono(telefono);
			session.update(user2);
			user = user2;
			session.getTransaction().commit();
			return user;
		}else throw new Exception("El usuario no existe");
		
	}

	public static UserAplication nuevoOwner(UserAplication user, String bA, String t, String i, String p, String m) throws Exception{
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		 if (!result.hasNext()) throw new Exception("No existe el usuario");
		 Owner owner = new Owner(bA, t, i, p, m);
		 UserAplication auxuser = result.next();
		 auxuser.setPropietario(owner);
		 session.save(owner);
		 session.update(auxuser);
		 session.getTransaction().commit();
		 return user;
	}
	
	public static UserAplication modificarOwner(UserAplication user, String bA, String t, String i, String p, String m) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
		Iterator<Owner> result = session.createQuery("from Owner where BANKACCOUNT='"+user.getPropietario().getBankAccount()+"'").iterate();
		if (result.hasNext()){
			Owner ownerConcreto = result.next();
			ownerConcreto.setBankAccount(bA);
			ownerConcreto.setIdiomas(i);
			ownerConcreto.setMoneda(m);
			ownerConcreto.setProfesion(p);
			ownerConcreto.setTipo(t);
			session.update(ownerConcreto);
			user.setPropietario(ownerConcreto);
			session.getTransaction().commit();
			return user;
		} else throw new Exception("El propietario no se ha encontrado.");
	}
	
	public static UserAplication modificarContrasena(UserAplication user, String pass) throws Exception {
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.setPass(pass);
			db.store(userConcreto);
			db.commit();
			return userConcreto;
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	
	public static UserAplication anadirRuralHouse(UserAplication user,int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> images) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		UserAplication userAux = new UserAplication();
		session.beginTransaction();
		System.out.println(user.getEmail());
		Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		if(result.hasNext())user = result.next();
		else throw new Exception("El usuario no se ha encontrado.");
		//user.addRuralHouse(numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images);
		RuralHouse casa = new RuralHouse(numero, user, description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
		session.save(casa);
		session.getTransaction().commit();
		return user;
		/*
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.addRuralHouse(numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images);
			db.store(userConcreto);
			db.commit();
			return userConcreto;
		} else throw new Exception("El usuario no se ha encontrado.");*/
	}
	
	public static UserAplication eliminarCasaRural(UserAplication user, int numero) throws Exception {
		RuralHouse eliminar = null;
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			//Iterator<RuralHouse> i = userConcreto.getPropietario().getRuralHouses().iterator();
			System.out.println("eliminarCasaRural: A");
			/*while (i.hasNext()){
				System.out.println("eliminarCasaRural: B");
				RuralHouse casa = i.next();
				System.out.println("eliminarCasaRural: C");
				if(casa.getHouseNumber() == numero){
					System.out.println("vD");
					eliminar = userConcreto.getPropietario().eliminarRH(numero);
					System.out.println("eliminarCasaRural: E");
					if ( eliminar != null) {
						System.out.println("eliminarCasaRural: F");
						Iterator<Fechas> aux1 = eliminar.eliminarTodasFechas().iterator();
						while (aux1.hasNext()){
							Fechas fechita = aux1.next();

							db.delete(fechita);
						}
						Iterator<Book> aux2 = eliminar.eliminarTodasReserva().iterator();
						while(aux2.hasNext()){
							Book reservita= aux2.next();
							userConcreto.eliminarReserva(reservita);
							db.delete(reservita);							
						}
						Iterator<Offer> aux3 = eliminar.eliminarTodasOfertas().iterator();
						while (aux3.hasNext()){
							Offer ofertita= aux3.next();
							db.delete(ofertita);
						}
						db.delete(eliminar);
						db.store(userConcreto);
						db.commit();
						System.out.println("eliminarCasaRural: G");
						return userConcreto;
					}
					break;
				}
			}*/
			throw new Exception("La casa rural no ha podido ser eliminada.");
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	/*
	public static void eliminarReserva(int num) throws Exception{
		System.out.println(num);
		ObjectSet<Book> reservasConcretas = db.queryByExample(Book.class);
		if (reservasConcretas.hasNext()){
			Book reserv = reservasConcretas.next();
			if(reserv.getNumeroDeReserva()==num){
			reserv.getOffer().getRuralHouse().eliminarReserva(num);
			reserv.getOffer().cancelarReserva();
			db.store(reserv.getOffer());
			Set<Fechas> f = reserv.getFechas();
			Iterator<Fechas> it = f.iterator();
			while(it.hasNext()){
				it.next().cancelarReserva();
				db.store(it.next());
			}	
			reserv.getCliente().eliminarReserva(reserv);
			db.store(reserv.getCliente());
			db.store(reserv.getOffer().getRuralHouse());
			db.delete(reserv);
			db.commit();
		
			}		
		}
	}
	*/
	public static void eliminarFecha(UserAplication usuario, int nRH, Date ini) throws Exception{
		System.out.println(nRH);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(usuario.getEmail(), null, null, null, null, null, null, null));
		/*if (userConcretos.hasNext()){
			UserAplication user = userConcretos.next();
			Iterator<RuralHouse> iter = user.getPropietario().getRuralHouses().iterator();
			while(iter.hasNext()){
				RuralHouse casa = iter.next();
				if(casa.getHouseNumber()==nRH){
					Fechas eliminar = casa.eliminarFecha(ini);
					if(eliminar.isReservado()){
						db.delete(eliminar.getReserva());
					}
					db.delete(eliminar);
					db.store(casa);
					db.store(user);
					db.commit();
				}
			}	
		}*/
	}
	/*
	public static Vector<RuralHouse> getHouse(String ciudad, int banos,
			int habita, int cocina, int estar, int park) {
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<RuralHouse> casasConcretas = db.queryByExample(new RuralHouse(0, null, null, ciudad, habita, cocina, banos, estar, park, null));	
		while (casasConcretas.hasNext())
			result.add(casasConcretas.next());
		return result;	
	}	
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(new Fechas(inicio, 0, false, null, 0));	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if (rh.disponibleFechas(inicio, fin)) result.add(rh);
		}
		return result;		
	}
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(new Fechas(inicio, 0, false, null, 0));	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if (rh.getCity().equalsIgnoreCase(Ciudad) && rh.disponibleFechas(inicio, fin)) result.add(rh);
		}
		return result;	
	}
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String ciudad, int banos, int habita, int cocina, int sala, int park){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		Fechas fechas= new Fechas(inicio, 0, false, null, 0, true);
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(fechas);	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if(ciudad==null)
				ciudad=rh.getCity();
			if(banos==-1)
				banos=rh.getBaths();
			if(habita==-1)
				habita=rh.getRooms();
			if(cocina==-1)
				cocina=rh.getKitchen();
			if(sala==-1)
				sala=rh.getLiving();
			if(park==-1)
				park=rh.getPark();
			if (rh.getCity().equalsIgnoreCase(ciudad) && 
					rh.getBaths()==banos && 
					rh.getRooms()==habita && 
					rh.getKitchen()==cocina && 
					rh.getLiving()==sala && 
					rh.getPark()==park && rh.disponibleFechas(inicio, fin)){ 
						result.add(rh);
					}
			}
		return result;	
	}
	*/
	public static Book hacerReserva(UserAplication cliente, int numeroOffer) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer where ID='"+numeroOffer+"'").iterate();
		if(result.hasNext()){
			Offer oferta = result.next();
			RuralHouse casa = oferta.getRuralHouse();
			Book reserva = new Book(casa,0,oferta.getPrice(),cliente,oferta);
			session.save(reserva);
			session.getTransaction().commit();
		}else{
			session.getTransaction().commit();
			throw new Exception("No existe la oferta");}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static UserAplication anadirOferta(UserAplication user, int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		if (result.hasNext()){
			UserAplication auxuser = result.next();
			session.getTransaction().commit();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Iterator<RuralHouse> result2 = session.createQuery("from RuralHouse where HouseNumber='"+numero+"'").iterate();
			 if(result2.hasNext()){
				 int ID =0;
				 Offer offer = new Offer(ID, inicio, fin, precio, result2.next() ,obligatorio);
				 session.save(offer);
				 session.getTransaction().commit();
				 return user;
			 }else{
				 session.getTransaction().commit();
				 throw new Exception("No existe la casa");}
		}
		throw new Exception("La oferta no se ha podido añadir correctamente. Lo sentimos");
	}
	/*
	public static UserAplication anadirFechas(UserAplication user, int numero, Date inicio, Date fin, float precio, int minimoDeDias) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			System.out.println("numero: " + numero);
			ObjectSet<RuralHouse> rHConcretos = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));	
			if (rHConcretos.hasNext() ){
				RuralHouse rHConcreto = rHConcretos.next();
				System.out.println("Inicio: " + inicio.toString());
				System.out.println("Fin: " + fin.toString());
				if (!rHConcreto.anadirFechas(inicio, fin, precio, minimoDeDias)) throw new Exception("La oferta no se ha podido añadir correctamente. Lo sentimos");
				db.store(rHConcreto);
				//db.store(userConcreto);
				db.commit();
				return userConcreto;
			}
		}
		throw new Exception("La oferta no se ha podido añadir correctamente. Lo sentimos");
	}
*/
	@SuppressWarnings("deprecation")
	public static void eliminarOferta(UserAplication usuario, int nRH, Date ini, Date fin) throws Exception {
		ini = new Date(ini.getYear(), ini.getMonth(), ini.getDate());
		fin = new Date(fin.getYear(), fin.getMonth(), fin.getDate());
		ObjectSet<UserAplication> usuarioConcreto = db.queryByExample(new UserAplication(usuario.getEmail(), null, null, null, null, null, null, null));
		/*if (usuarioConcreto.hasNext()){
			UserAplication user = usuarioConcreto.next();
			//Iterator<RuralHouse> casasConcretas = user.getPropietario().getRuralHouses().iterator();
			while(casasConcretas.hasNext()){
				RuralHouse casa = casasConcretas.next();
				if(casa.getHouseNumber()==nRH){
					Offer eliminar = casa.eliminarOferta(ini, fin);
					Set<Fechas> f = eliminar.getFechas();
					Iterator<Fechas> it = f.iterator();
					while(it.hasNext()){
						it.next().cancelarReserva();
						db.store(it.next());
					}
					db.store(casa);
					db.delete(eliminar);
					db.delete(eliminar.getReserva());
					db.store(user);
					db.commit();
					break;
				}
			}
		}else 
			throw new Exception("La oferta no se ha podido eliminar correctamente. Lo sentimos");*/
	}
	
	public static Vector<Book> getTodasLasReservas(){
		Vector<Book> aux = new Vector<Book>();
		ObjectSet<Book> reservasConcretas = db.queryByExample(Book.class);	
		while (reservasConcretas.hasNext()) aux.add(reservasConcretas.next());
		return aux;
	}

	public static Book pagar(int num, UserAplication user) throws Exception {
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			//userConcreto.pagar(num);					
			db.store(userConcreto);
			db.commit();
			return getReserva(num);
		}
		throw new Exception("No se ha podido pagar, intentelo de nuevo.");
	}	
	/*
	public static void anadirCalificacionACasaRural(int numero, String comentario, int puntuacion) throws Exception {
		ObjectSet<RuralHouse> RHConcreto = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));
		if (RHConcreto.hasNext()){
			RuralHouse casa = RHConcreto.next();
			casa.anadirCalificacion(comentario, puntuacion);
			db.store(casa);
			db.commit();
		} else throw new Exception("La casa rural no se ha modificado. El propietario ha podido borrar la Casa Rural.");
	}
	*/
	
	public static Iterator<Offer> getOfertasS(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer where reservado='"+1+"'").iterate();
		return result;
	}
	
	public static List<Offer> getOfertas(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Offer> result = session.createQuery("from Offer where reservado='"+1+"'").list();
		return result;
	}
	
	public static Book getReserva(int num) throws Exception{
		ObjectSet<Book> reservConcretas = db.queryByExample(Book.class);
		while (reservConcretas.hasNext()){
			Book reserva = reservConcretas.next();
			if(reserva.getBookNumber()==num)
				return reserva;
		}
		throw new Exception("No existe la reserva");
	}

	public static void cambiarContraseña(UserAplication user, String text) throws Exception {
		ObjectSet<UserAplication> userConcretos = db.queryByExample(new UserAplication(user.getEmail(), null, null, null, null, null, null, null));
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.setPass(text);
			db.store(userConcreto);
		}else{
			throw new Exception("No se ha podido cambiar la contraseña");}
	}

	public static Vector<Book> getReservas(UserAplication user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("voy a buscar las reservas");
		List<Book> result = session.createQuery("from Book where RESERVANTE='"+user.getEmail()+"'").list();
		Vector<Book> reservas = new Vector<Book>();
		reservas.addAll(result);
		for(int i=0; i<reservas.size(); i++){
			System.out.println(reservas.get(i).getBookNumber());
		}
		return reservas;
	}
}
	