package Beans;

import java.util.Vector;

import domain.UserAplication;

public class nuevaCasaRuralBean {

	private String description;
	private String city; 
	private int nRooms;
	private int nKitchen;
	private int nBaths;
	private int nLiving;
	private int nPark;
	private Vector<String> vectorImage;
	
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
	public Vector<String> getVectorImage() {
		return vectorImage;
	}
	public void setVectorImage(Vector<String> vectorImage) {
		this.vectorImage = vectorImage;
	}
	
	public String insertarCasaRural(){
		return "ok";
	}
}
