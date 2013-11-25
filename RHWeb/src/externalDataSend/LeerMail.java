package externalDataSend;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerMail {
	
	public LeerMail(){
		
	}
	public  String leerEmail(String titulo, String texto){
		BufferedReader br = null;
		FileReader fr1 = null, fr2 = null, fr3 = null;
		String aux = "";
		try {
			String archivo1s = this.getClass().getResource("/localData/email1.html").getFile();
			File archivo1f = new File (archivo1s);
			fr1 = new FileReader (archivo1f);
			br = new BufferedReader(fr1);
			String linea1;
			while((linea1=br.readLine())!=null)aux = aux.concat(linea1);
			
			aux = aux.concat(titulo);
			
			String archivo2s = this.getClass().getResource("/localData/email2.html").getFile();
			File archivo2f = new File (archivo2s);
			fr2 = new FileReader (archivo2f);
			br = new BufferedReader(fr2);
			String linea2;
			while((linea2=br.readLine())!=null)aux = aux.concat(linea2);
			
			aux = aux.concat(texto);
			
			String archivo3s = this.getClass().getResource("/localData/email3.html").getFile();
			File archivo3f = new File (archivo3s);
			fr3 = new FileReader (archivo3f);
			br = new BufferedReader(fr3);
			String linea3;
			while((linea3=br.readLine())!=null)aux = aux.concat(linea3);
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != fr1 ) fr1.close();
				if( null != fr2 ) fr2.close();
				if( null != fr3 ) fr3.close();
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		return aux;
	}	
}
