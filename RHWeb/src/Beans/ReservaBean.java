package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import businessLogic.FacadeImplementation;

public class ReservaBean {

	
	private String seleccionado;
	private List<String> ofertasDispo;

	public List<String> getOfertasDispo() {
		try {
			setOfertasDispo(fachadaBean.getFachada().getOfertas());
			return ofertasDispo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setOfertasDispo(List<String> ofertasDispo) {
		this.ofertasDispo = ofertasDispo;
	}
	
	public String getseleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(String sel) {
		seleccionado = sel;
	}
	
	public String reservar(){
		int i=0;
		while (seleccionado.charAt(i)!= '/'){
			i++;
		}
		String s = seleccionado.substring(0, i-1);
		int num = Integer.parseInt(s);
		System.out.println(num);
		return "ok";
	}

}
