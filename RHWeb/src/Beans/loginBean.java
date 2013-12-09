package Beans;

import javax.faces.context.FacesContext;

import domain.Owner;
import domain.UserAplication;

public class loginBean {
	private String email;
	private String pass;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String loginUser(){
		try {
			UserAplication user = fachadaBean.getFachada().hacerLogin(email, pass);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", new Boolean(true));	
			if(user.tieneProp()){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("propietario", new Boolean(true));	
			}else
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("propietario", new Boolean(false));
			return "ok";
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			return "error";
		}
	}
}
