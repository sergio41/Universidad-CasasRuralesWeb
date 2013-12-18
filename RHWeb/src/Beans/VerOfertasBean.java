package Beans;

import java.util.List;
import java.util.Vector;
import domain.Offer;

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
