package domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import externalDataSend.EnviarCorreo;

@SuppressWarnings("serial")
public class Owner implements Serializable {
	private String bankAccount = "";
	private String tipo;
	private Vector<String> idiomas;
	private String profesion;
	private String moneda;
	private Vector<RuralHouse> ruralHouses;
	
	public Owner(String bA, String t, Vector<String> i, String p, String m){
		bankAccount=bA;
		tipo=t;
		idiomas=i;
		profesion=p;
		moneda=m;
		ruralHouses=new Vector<RuralHouse>();	
	}
	
	public String getBankAccount() {return bankAccount;}

	public void setBankAccount(String bA) {bankAccount = bA;}
	
	public String getTipo() {return tipo;}

	public void setTipo(String t) {	tipo = t;}
	
	public Vector<String> getIdiomas() {return idiomas;}

	public void setIdiomas(Vector<String> i) {idiomas = i;}

	public String getProfesion() {return profesion;}

	public void setProfesion(String p) {profesion = p;}

	public String getMoneda() {return moneda;}

	public void setMoneda(String m) {moneda = m;}
	
	public Vector<RuralHouse> getRuralHouses() {return ruralHouses;}
	
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