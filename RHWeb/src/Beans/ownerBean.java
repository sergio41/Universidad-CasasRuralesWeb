package Beans;

import javax.faces.context.FacesContext;
import domain.UserAplication;

public class ownerBean {
	private String bankAccount = "";
	private String tipo="";
	private String idiomas1="";
	private String idiomas2="";
	private String idiomas3="";
	private String profesion="";
	private String moneda="";

	public String getBankAccount() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario")){
			setBankAccount(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPropietario().getBankAccount());
		}
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getTipo() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario")){
			setTipo(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPropietario().getTipo());
		}
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProfesion() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario")){
			setProfesion(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPropietario().getProfesion());
		}
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getMoneda() {
		if((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propietario")){
			setMoneda(((UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getPropietario().getMoneda());
		}
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getIdiomas1() {
		return idiomas1;
	}
	public void setIdiomas1(String idiomas1) {
		this.idiomas1 = idiomas1;
	}
	public String getIdiomas2() {
		return idiomas2;
	}
	public void setIdiomas2(String idiomas2) {
		this.idiomas2 = idiomas2;
	}
	public String getIdiomas3() {
		return idiomas3;
	}
	public void setIdiomas3(String idiomas3) {
		this.idiomas3 = idiomas3;
	}
	public String registrarOwner(){
		String idiomas = idiomas1+ " / "+idiomas2+" / "+idiomas3;
		try {
			UserAplication user = (UserAplication) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			UserAplication userMod = fachadaBean.getFachada().modificarOwner(user, bankAccount, tipo, idiomas, profesion, moneda);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", userMod);	
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("propietario", new Boolean(true));	
			System.out.println(user.getEmail() + user.getName() + user.getApellidos());
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
