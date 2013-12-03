package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import externalDataSend.EnviarCorreo;

@SuppressWarnings("serial")
public class Owner implements Serializable {
	private String bankAccount = "";
	private String tipo;
	private Set<String> idiomas;
	private String profesion;
	private String moneda;
	private Set<RuralHouse> ruralHouses;
	
	public Owner(String bA, String t, Set<String> i, String p, String m){
		bankAccount=bA;
		tipo=t;
		idiomas=i;
		profesion=p;
		moneda=m;
		ruralHouses=new HashSet<RuralHouse>();	
	}
	
	public String getBankAccount() {return bankAccount;}

	public void setBankAccount(String bA) {bankAccount = bA;}
	
	public String getTipo() {return tipo;}

	public void setTipo(String t) {	tipo = t;}
	
	public Set<String> getIdiomas() {return idiomas;}

	public void setIdiomas(Set<String> i) {idiomas = i;}

	public String getProfesion() {return profesion;}

	public void setProfesion(String p) {profesion = p;}

	public String getMoneda() {return moneda;}

	public void setMoneda(String m) {moneda = m;}
	
	public Set<RuralHouse> getRuralHouses() {return ruralHouses;}
	
	public void addRuralHouse(RuralHouse rh) {ruralHouses.add(rh);}
	
	public RuralHouse eliminarRH(int numero){
		Iterator<RuralHouse> i = ruralHouses.iterator();
		RuralHouse aux = null;
		while (i.hasNext()){
			RuralHouse aux1 = i.next();
			if (aux1.getHouseNumber() == numero) {
				aux = aux1;
				ruralHouses.remove(aux1);
				break;
			}
		}
		if (aux != null)
			try {
				EnviarCorreo.enviarCorreos(aux.getUserAplication().getEmail(), "Modificacion de la Casa Rural", "La casa rural con numero " + aux.getHouseNumber() + "se ha eliminado correctamente.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return aux;
			
	}
}