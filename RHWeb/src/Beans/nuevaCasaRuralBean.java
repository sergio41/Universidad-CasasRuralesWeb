package Beans;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.faces.context.FacesContext;

import domain.UserAplication;

public class nuevaCasaRuralBean {

	private String description;
	private String city; 
	private int nRooms;
	private int nKitchen;
	private int nBaths;
	private int nLiving;
	private int nPark;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}

	
	public String insertarCasaRural(){
		Set<String> images = new HashSet<String>();
		images.add(image1);
		images.add(image2);
		images.add(image3);
		images.add(image4);
		try {
			UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			fachadaBean.getFachada().anadirRuralHouse(user, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images);
			System.out.println(user.getEmail() + " " + user.getName() + " " + user.getApellidos());
			return "ok";
		} catch (Exception e) {
			return "error";
		}
	}
}
