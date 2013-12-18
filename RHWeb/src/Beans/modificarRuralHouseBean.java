package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import domain.RuralHouse;
import domain.UserAplication;

public class modificarRuralHouseBean {

	
	private String seleccionada;
	private List<String> casasU;
	private String city;
	private String description;
	private int nRooms;
	private int nKitchen;
	private int nBaths;
	private int nLiving;
	private int nPark;

	public String getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(String seleccionada) {
		this.seleccionada = seleccionada;
		try {
			int i=0;
			while (seleccionada.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionada.substring(0, i-1);
			int num = Integer.parseInt(s);
			RuralHouse casa = fachadaBean.getFachada().getCasas(num);
			setCity(casa.getCity());
			setDescription(casa.getDescription());
			setnBaths(casa.getBaths());
			setnKitchen(casa.getKitchen());
			setnLiving(casa.getLiving());
			setnPark(casa.getPark());
			setnRooms(casa.getRooms());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.seleccionada);
	}

	public List<String> getCasasU() {
		try {
			setCasasU(fachadaBean.getFachada().getUserHouses((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")));
			//setSeleccionada(casasU.get(0));
			return casasU;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setCasasU(List<String> casasU) {
		this.casasU = casasU;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getnRooms() {
		return nRooms;
	}

	public void setnRooms(int nRooms) {
		this.nRooms = nRooms;
	}

	public int getnKitchen() {
		return nKitchen;
	}

	public void setnKitchen(int nKitchen) {
		this.nKitchen = nKitchen;
	}

	public int getnBaths() {
		return nBaths;
	}

	public void setnBaths(int nBaths) {
		this.nBaths = nBaths;
	}

	public int getnLiving() {
		return nLiving;
	}

	public void setnLiving(int nLiving) {
		this.nLiving = nLiving;
	}

	public int getnPark() {
		return nPark;
	}

	public void setnPark(int nPark) {
		this.nPark = nPark;
	}
	
	public String serviceChange(){
		return city;
	}

	public String actualizar(){
		try {
			int i=0;
			while (seleccionada.charAt(i)!= '/'){
				i++;
			}
			String s = seleccionada.substring(0, i-1);
			int num = Integer.parseInt(s);
			UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			fachadaBean.getFachada().modificarRuralHouse(user, num ,description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
			return "ok";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al insertar casa:", e.getMessage()));  
			return "";
		}
	}
	
}
