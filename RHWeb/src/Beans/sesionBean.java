package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class sesionBean {

	private boolean logueado = false;
	private UserAplication usuario = null;
	private boolean propietariado = false;
	
	public boolean isLogueado() {
		if ((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login") != null){
			logueado = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		} else {
			setLogueado(false);
			logueado =false;}
		System.out.println("Obteniendo: " + logueado);
		return logueado;
	}
	public void setLogueado(boolean logueado1) {
		logueado = logueado1;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", logueado);
		System.out.println("Set Obteniendo: " + logueado);
	}	
	public UserAplication getUsuario() {
		return usuario;
	}
	public void setUsuario(UserAplication usuario) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
		this.usuario = usuario;
	}
	public String logout(){
		setLogueado(false);
		setPropietariado(false);
		setUsuario(null);
		return "ok";
	}
	public boolean isPropietariado() {
		if ((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario") != null){
			propietariado = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario");
		} else propietariado = false;
		System.out.println("Obteniendo: " + propietariado);
		return propietariado;
	}
	public void setPropietariado(boolean propietariado1) {
		propietariado = propietariado1;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("propietario", propietariado);
		System.out.println("Set Obteniendo: " + propietariado);
	}
}
