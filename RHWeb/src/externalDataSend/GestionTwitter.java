package externalDataSend;

import java.util.List;
import java.util.Vector;

import twitter4j.*;
import twitter4j.conf.*;

public class GestionTwitter {
	private static Twitter twitter;
		
	private static void inicializarTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("xEIAPcAcPKSnUj3cQVmkqQ");
		cb.setOAuthConsumerSecret("XwLA9bC2RwW5HMMVF1Qx7icMiu9MsoXNAmEEXDM3ng");
		cb.setOAuthAccessToken("1318353908-WXP45IIedFVt8iGtBajcEdXYw013vxxQpyk2gBh");
		cb.setOAuthAccessTokenSecret("MAtTy95rsDhbAChmALQglVtwIayNjDdFMBgD156w");
		twitter = new TwitterFactory(cb.build()).getInstance();
	}
	
	public static void enviarTweet(String s) throws Exception{
		if (twitter == null) inicializarTwitter();
		Status status;
		try {
			status = twitter.updateStatus(s);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			throw new Exception("Twitter: No se ha podido enviar");
		}
	}
	
	public static Vector<String> getTodosTweets() throws Exception {
		if (twitter == null) inicializarTwitter();
		Vector<String> vectorTweets = new Vector<String>();
		try {
			List<Status> statuses = twitter.getHomeTimeline();
			for (Status status : statuses) vectorTweets.add(status.getText());
			System.out.println("Showing home timeline.");
			return vectorTweets;
		} catch (TwitterException e) {
			System.out.println("Error Twitter: " + e.getMessage());
			throw new Exception("Twitter: No se ha podido recibir los tweets");
		}
	}
	
	public static String getUltimoTweet() throws Exception{
		if (twitter == null) inicializarTwitter();
		try {
			List<Status> statuses = twitter.getHomeTimeline();
			return statuses.get(0).getText();
		} catch (TwitterException e) {
			throw new Exception("Twitter: No se ha podido recibir los tweets");
		}
	}
}
