package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class sesionBean {

	private static boolean logueado;
	private static UserAplication usuario = null;
	
	public static boolean isLogueado() {
		System.out.println("Obteniendo: " + logueado);
		return logueado;
	}
	public static void setLogueado(boolean logueado1) {
		logueado = logueado1;
		System.out.println("Set Obteniendo: " + logueado);
	}	
	public static UserAplication getUsuario() {
		return usuario;
	}
	public static void setUsuario(UserAplication usuario) {
		sesionBean.usuario = usuario;
	}
}
