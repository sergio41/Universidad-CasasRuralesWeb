package Beans;

import javax.faces.context.FacesContext;

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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", fachadaBean.getFachada().hacerLogin(email, pass));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", new Boolean(true));	
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
