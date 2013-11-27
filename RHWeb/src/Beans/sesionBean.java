package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class sesionBean {

	private boolean logueado;
	private UserAplication usuario = null;
	
	public boolean isLogueado() {
		System.out.println("Obteniendo: " + logueado);
		return logueado;
	}
	public void setLogueado(boolean logueado1) {
		logueado = logueado1;
		System.out.println("Set Obteniendo: " + logueado);
	}	
	public UserAplication getUsuario() {
		return usuario;
	}
	public void setUsuario(UserAplication usuario) {
		this.usuario = usuario;
	}
}
