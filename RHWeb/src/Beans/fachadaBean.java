package Beans;

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	public static void cerrarFachada(){
		if (fachada!=null)
			fachada.close();
	}
}
