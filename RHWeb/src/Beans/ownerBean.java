package Beans;

import java.util.Vector;

import domain.RuralHouse;

public class ownerBean {
	private String bankAccount = "";
	private String tipo;
	private String idiomas1;
	private String idiomas2;
	private String idiomas3;
	private String profesion;
	private String moneda;

	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getIdiomas1() {
		return idiomas1;
	}
	public void setIdiomas1(String idiomas1) {
		this.idiomas1 = idiomas1;
	}
	public String getIdiomas2() {
		return idiomas2;
	}
	public void setIdiomas2(String idiomas2) {
		this.idiomas2 = idiomas2;
	}
	public String getIdiomas3() {
		return idiomas3;
	}
	public void setIdiomas3(String idiomas3) {
		this.idiomas3 = idiomas3;
	}
	public String registrarOwner(){
		/*Vector<String> idiomas = new Vector<String>();
		idiomas.add(idiomas1);
		idiomas.add(idiomas2);
		idiomas.add(idiomas3);
		DatosPrincipalesBean datos = null;
		datos.getFacadeInterface();*/
		return "ok";
	}

}
