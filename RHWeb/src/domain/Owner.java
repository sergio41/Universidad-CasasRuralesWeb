package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Owner implements Serializable {
	private String bankAccount = "";
	private String tipo;
	private String idiomas;
	private String profesion;
	private String moneda;
	
	public Owner(String bA, String t, String i, String p, String m){
		bankAccount=bA;
		tipo=t;
		idiomas=i;
		profesion=p;
		moneda=m;
	}
	
	public Owner(){
	}
	
	public String getBankAccount() {return bankAccount;}

	public void setBankAccount(String bA) {bankAccount = bA;}
	
	public String getTipo() {return tipo;}

	public void setTipo(String t) {	tipo = t;}
	
	public String getIdiomas() {return idiomas;}

	public void setIdiomas(String i) {idiomas = i;}

	public String getProfesion() {return profesion;}

	public void setProfesion(String p) {profesion = p;}

	public String getMoneda() {return moneda;}

	public void setMoneda(String m) {moneda = m;}
}