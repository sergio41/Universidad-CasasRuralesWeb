package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogic.FacadeImplementation;

public class fachadaBean {

	private static FacadeImplementation fachada = null;
	
	public static FacadeImplementation getFachada() {
		if (fachada==null) obtenerFachada();
		return fachada;
	}
	public static void setFachada(FacadeImplementation fachada) {
		fachadaBean.fachada = fachada;
	}
	public static void obtenerFachada(){
		try {
			if (fachada!=null) cerrarFachada();
			fachada =  new FacadeImplementation();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void cerrarFachada(){
		if (fachada!=null)
			try {
				fachada.close();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
