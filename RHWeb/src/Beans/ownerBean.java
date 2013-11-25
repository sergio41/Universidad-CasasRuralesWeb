package Beans;

import java.util.Vector;

import domain.RuralHouse;

public class ownerBean {
	private String bankAccount = "";
	private String tipo;
	private Vector<String> idiomas;
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
	public Vector<String> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(Vector<String> idiomas) {
		this.idiomas = idiomas;
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

	public String registrarOwner(){
		return "ok";
	}
}
