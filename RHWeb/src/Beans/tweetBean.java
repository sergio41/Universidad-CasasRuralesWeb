package Beans;

public class tweetBean {

	private String ultimoTweet = "Error al recibir tweets";

	public String getUltimoTweet() {
		try {
			ultimoTweet = fachadaBean.getFachada().Ultimos10Tweets().get(0);
		} catch (Exception e) {
			ultimoTweet = "Error al recibir tweets";
		}
		return ultimoTweet;
	}

	public void setUltimoTweet(String ultimoTweet) {
		this.ultimoTweet = ultimoTweet;
	}
}
