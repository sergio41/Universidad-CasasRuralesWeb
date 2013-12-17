package Beans;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.Book;
import domain.Offer;
import domain.UserAplication;
import businessLogic.FacadeImplementation;

public class VerOfertasBean {

	private Vector<Offer> ofertasDispo;

	public Vector<Offer> getOfertasDispo() {
		try {
			Vector<Offer> ofertas = new Vector<Offer>();
			List<Offer> ofertasl = fachadaBean.getFachada().getOfertas();
			ofertas.addAll(ofertasl);
			setOfertasDispo(ofertas);
			return ofertasDispo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setOfertasDispo(Vector<Offer> ofertasDispo) {
		this.ofertasDispo = ofertasDispo;
	}
}
