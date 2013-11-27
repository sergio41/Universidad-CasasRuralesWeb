package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class sesionBean {

	private static boolean logueado = false;
	private static UserAplication usuario = null;
	
	public static boolean isLogueado() {
		return logueado;
	}
	public static void setLogueado(boolean logueado) {
		sesionBean.logueado = logueado;
	}	
	public static UserAplication getUsuario() {
		return usuario;
	}
	public static void setUsuario(UserAplication usuario) {
		sesionBean.usuario = usuario;
	}
}
