package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class sesionBean {

	private boolean logueado = false;
	private UserAplication usuario = null;
	
	public boolean isLogueado() {
		if ((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login") != null){
			logueado = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		}
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
		this.usuario = usuario;
	}
	public String logout(){
		setLogueado(false);
		return "ok";
	}
}
