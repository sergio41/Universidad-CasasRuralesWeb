package dataAccess;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;

import domain.*;

public class DatabaseManager { 

	@SuppressWarnings("unchecked")
	public static UserAplication getUser(String email){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserAplication> result = session.createQuery("from UserAplication where email='"+email+"'").list();
		Iterator<UserAplication> it = result.iterator();
		session.getTransaction().commit();
		if(it.hasNext()) return it.next();
		else return null;
	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public static RuralHouse getRuralHouse(int houseNumber) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<RuralHouse> result = session.createQuery("from RuralHouse where HOUSENUMBER='"+houseNumber+"'").iterate();		
		if (result.hasNext()){
			RuralHouse casa = result.next();
			session.getTransaction().commit();
			return casa;} 
		else throw new Exception("La casa rural no existe.");
	}

	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public static void modificarRuralHouse(String email, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
		Iterator<RuralHouse> RHConcreto = session.createQuery("from RuralHouse where HOUSENUMBER='"+numero+"'").iterate();
		if (RHConcreto.hasNext()){
			RuralHouse casa = RHConcreto.next();
			casa.setBaths(nBaths);
			casa.setCity(city);
			casa.setDescription(description);
			casa.setRooms(nRooms);
			casa.setKitchen(nKitchen);
			casa.setLiving(nLiving);
			casa.setPark(nPark);
			session.update(casa);
			session.getTransaction().commit();
		} else throw new Exception("La casa rural no se puede modificar. No se ha encontrado en la base de datos.");
	}
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public static UserAplication nuevoOwner(UserAplication user, String bA, String t, String i, String p, String m) throws Exception{
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		 if (!result.hasNext()) throw new Exception("No existe el usuario");
		 Owner owner = new Owner(bA, t, i, p, m);
		 UserAplication auxuser = result.next();
		 auxuser.setPropietario(owner);
		 user.setPropietario(owner);
		 session.save(owner);
		 session.update(auxuser);
		 session.getTransaction().commit();
		 return user;
	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public static UserAplication anadirRuralHouse(UserAplication user,int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Set<String> images) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(user.getEmail());
		Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		if(result.hasNext())user = result.next();
		else throw new Exception("El usuario no se ha encontrado.");
		RuralHouse casa = new RuralHouse(numero, user, description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
		session.save(casa);
		session.getTransaction().commit();
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Iterator<RuralHouse> getCasasUser(UserAplication user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<RuralHouse> result = session.createQuery("from RuralHouse where USER='"+user.getEmail()+"'").iterate();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Offer> getOfertasUser(UserAplication user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer").iterate();
		List<Offer> lista = new Vector<Offer>();
		if(result.hasNext()){
			while(result.hasNext()){
				Offer oferta=result.next();
				if(oferta.getRuralHouse().getUserAplication().getEmail().compareTo(user.getEmail())==0){
					lista.add(oferta);
				}
			}
			return lista;
		}else
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Book hacerReserva(UserAplication cliente, int numeroOffer) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer where ID='"+numeroOffer+"'").iterate();
		if(result.hasNext()){
			Offer oferta = result.next();
			RuralHouse casa = oferta.getRuralHouse();
			System.out.println(oferta.isReservado());
			oferta.setReservado(1);
			Book reserva = new Book(casa,0,oferta.getPrice(),cliente,oferta);
			session.update(oferta);
			session.save(reserva);
			session.getTransaction().commit();
		}else{
			session.getTransaction().commit();
			throw new Exception("No existe la oferta");}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public static UserAplication anadirOferta(UserAplication user, int numero, Date inicio, Date fin, float precio) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<UserAplication> result = session.createQuery("from UserAplication where email='"+user.getEmail()+"'").iterate();
		if (result.hasNext()){
			session.getTransaction().commit();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Iterator<RuralHouse> result2 = session.createQuery("from RuralHouse where HouseNumber='"+numero+"'").iterate();
			 if(result2.hasNext()){
				 int ID =0;
				 Offer offer = new Offer(ID, inicio, fin, precio, result2.next());
				 session.save(offer);
				 session.getTransaction().commit();
				 return user;
			 }else{
				 session.getTransaction().commit();
				 throw new Exception("No existe la casa");}
		}
		throw new Exception("La oferta no se ha podido añadir correctamente. Lo sentimos");
	}


	@SuppressWarnings("unchecked")
	public static Iterator<Offer> getOfertasS(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer where reservado='"+0+"'").iterate();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Offer> getOfertas(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Offer> result = session.createQuery("from Offer where reservado='"+0+"'").list();
		return result;
	}

	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public static Offer getOferta(int num) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> it = session.createQuery("from Offer where ID='"+num+"'").iterate();
		if(it.hasNext())
			return it.next();
		else{
			throw new Exception("No se ha podido encontrar la reserva");
		}
	}

	@SuppressWarnings("unchecked")
	public static void modificarOferta(int num, Date firstDay, Date lastDay,
			float price) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Iterator<Offer> result = session.createQuery("from Offer where ID='"+num+"'").iterate();
		if(result.hasNext()){
			Offer oferta = result.next();
			oferta.setFirstDay(firstDay);
			oferta.setLastDay(lastDay);
			oferta.setPrice(price);
			session.update(oferta);
			session.getTransaction().commit();
		}else{
			throw new Exception("No se ha podido encontrar la reserva");
		}
	}
}
	