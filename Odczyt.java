import java.net.*;
import java.io.*;

public class Odczyt {

	public static void main(String[] args) {
		

	}
	
	public void czytaj() throws IOException{
		URL link = new URL("http://localhost:5984/katastrofy/_all_docs?keys=[%22ID1%22,%22ID2%22]&include_docs=true");
		URLConnection polaczenie = link.openConnection();
		BufferedReader response = new BufferedReader(new InputStreamReader(polaczenie.getInputStream()));
		String inputString;
		while ((inputString = response.readLine())!=null) {
			System.out.println(inputString);
			
		}
		response.close();
	} 

}
