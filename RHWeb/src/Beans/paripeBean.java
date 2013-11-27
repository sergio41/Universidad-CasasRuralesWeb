package Beans;

public class paripeBean {
	private boolean logueado = false;

	public boolean isLogueado() {
		logueado = sesionBean.isLogueado();
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
		sesionBean.setLogueado(logueado);
	}
	
	public String logueadoTrue() {
		sesionBean.setLogueado(true);
		return "ok";
	}
	public String logueadoFalse() {
		sesionBean.setLogueado(false);
		return "ok";
	}
}
