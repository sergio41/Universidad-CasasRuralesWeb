package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import domain.UserAplication;

public class registerUserBean {
	private String email="";
	private String pass="";
	private String estadoCivil="";
	private String nombre="";
	private String apellidos="";
	private String telefono="";
	private String pais="";
	private String edad="";
	private String perfil="";
	
	public String getEmail() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setEmail(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getEmail());
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setPass(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPass());
		}
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEstadoCivil() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setEstadoCivil(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getEstadoCivil());
		}
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getNombre() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setNombre(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getName());
		}
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setApellidos(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getApellidos());
		}
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setTelefono(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getTelefono());
		}
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPais() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setPais(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPais());
		}
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEdad() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setEdad(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getEdad());
		}
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getPerfil() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")==true){
			setPerfil(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPerfil());
		}
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	} 	
	public String registrarUser(){
		try {
			if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login")!=true){
				fachadaBean.getFachada().nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", fachadaBean.getFachada().hacerLogin(email, pass));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", new Boolean(true));	
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("propietario", new Boolean(false));
			}else{
				UserAplication user = fachadaBean.getFachada().modificarPerfil((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"),estadoCivil, nombre, apellidos, telefono, pais, edad, perfil);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
			}
			return "ok";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al insertar usuario:", e.getMessage()));  
			e.printStackTrace();
			return "";
		}
	}
}
