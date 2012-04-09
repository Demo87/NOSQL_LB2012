import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBDecoder;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;


public class Wczytywanie {

	public static void main(String[] args) throws MongoException, IOException {
		wstaw("tatry");

	}
	
	public static void wstaw(String nazwa) throws MongoException, IOException{
		Mongo mon = new Mongo();
		DB baza = mon.getDB(nazwa);
		System.out.println(baza.getStats());
		System.out.println(mon.getAllAddress());
		System.out.println(mon.getConnectPoint());
		Set<String> kolekcje = baza.getCollectionNames();
		System.out.println("Kolekcje");
		for(String s: kolekcje){
			System.out.println(s);
		}
		System.out.println("Koniec kolekcji");
		DBCollection kolekcja = baza.getCollection(nazwa);
		BufferedReader plik = new BufferedReader(new FileReader(nazwa+".txt"));
		String linia = "";
		int i =0;
		while(((linia=plik.readLine())!=null)&& i<1){
			i++;
			linia = linia.substring(1);
			System.out.println(i+" tu jest linia "+linia);			
			String lista[] = linia.split("[{}]");
			System.out.println(lista[0]);
			System.out.println(lista[1]);
/*			String lista[] = linia.split(",\"");
			lista[0]=lista[0].replace("{\"","");
			for (int j = 0; j < lista.length; j++) {
				String element = lista[j];
				String tmp[] = element.split(":");
				System.out.println(tmp[0]+" a teraz wartość  "+tmp[1]);
			}*/

		}
	}

}
