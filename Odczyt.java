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
		String inputString = "";
		String inputString2 = "";
		int i = 0;
		response.readLine();
		while ((inputString = response.readLine())!=null) {
			int indek = inputString.indexOf(",\"doc\":");
			inputString = inputString.substring(indek+7);
			inputString =  inputString.substring(0, inputString.length()-2)+",";
			plik.write(inputString.toString()+"\n");
			i++;
			System.out.println(i);
			inputString2=inputString;
		}
		plik.write(inputString2);
		response.close();
		plik.close();
		
		//bufor.flush();
		//bufor.close();
	} 

}
