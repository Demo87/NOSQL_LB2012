import java.net.*;
import java.io.*;

public class Odczyt {

	public static void main(String[] args) throws IOException {
		
		czytaj();

	}
	
	public static void czytaj() throws IOException{
		FileWriter plik = new FileWriter("httpresponse1.txt");
		//BufferedWriter bufor = new BufferedWriter(plik);
		
		URL link = new URL("http://localhost:5984/katastrofy/_all_docs?keys=[%22ID1%22,%22ID2%22]&include_docs=true");
		URLConnection polaczenie = link.openConnection();
		BufferedReader response = new BufferedReader(new InputStreamReader(polaczenie.getInputStream()));
		String inputString;
		
		while ((inputString = response.readLine())!=null) {
			//System.out.println(inputString);
			//bufor.write(inputString.toString());
			plik.write(inputString.toString());
			
		}
		response.close();
		plik.close();
		
		//bufor.flush();
		//bufor.close();
	} 

}
