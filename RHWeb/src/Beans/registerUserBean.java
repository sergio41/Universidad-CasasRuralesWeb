package Beans;

import java.util.Vector;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import domain.Book;
import domain.Owner;

public class registerUserBean {
	public static ApplicationFacadeInterface facadeInterface;
	private String email;
	private String pass;
	private String estadoCivil;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String pais;
	private String edad;
	private String perfil;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	} 	
	public String registrarUser(){
		try {
			facadeInterface =  new FacadeImplementation();
			facadeInterface.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
			return "ok";
		} catch (Exception e) {
			return "error";
		}
	}
}
