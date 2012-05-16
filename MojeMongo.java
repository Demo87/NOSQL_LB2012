import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import com.mongodb.util.JSON;

public class MojeMongo {

	public static void main(String[] args) throws MongoException, IOException {
		/*wstaw("tatry");
		wstaw("meteo");
		wstaw("pojufo");
		wstaw("katastrofy");*/
		wstawMR("pojufo");

	}

	public static void wstaw(String nazwa) throws MongoException, IOException {
		Mongo mon = new Mongo();
		mon.dropDatabase(nazwa);
		DB baza = mon.getDB(nazwa);
		System.out.println(baza.getStats());
		System.out.println(mon.getAllAddress());
		System.out.println(mon.getConnectPoint());
		Set<String> kolekcje = baza.getCollectionNames();
		System.out.println("Kolekcje");
		
		for (String s : kolekcje) {
			System.out.println(s);
		}
		System.out.println("Koniec kolekcji");
		DBCollection kolekcja = baza.getCollection(nazwa);
		BufferedReader plik = new BufferedReader(new FileReader(nazwa + ".txt"));
		String linia = "";
		int i = 0;
		while ((linia = plik.readLine()) != null) {
			i++;
			// linia=linia.substring(1);
			if(linia.length()>5){
			try{
			linia = linia.substring(0, linia.lastIndexOf(","));
			}
			catch (Exception e) {
				System.out.println(linia);
				linia=linia.substring(0,linia.length()-1);
			}
			if(linia.endsWith("}")){
				
			}
			else{
				linia = linia + "}";
			}
			// System.out.println(i+" tu jest linia "+linia);
			String lista[] = linia.split(",");
			// System.out.println(lista[0]);
			// System.out.println(lista[1]);
			linia = "{"
					+ linia.replace(lista[0] + ",", "").replace(lista[1] + ",",
							"");
			// System.out.println(i+" tu jest linia "+linia);
			//linia = linia.replaceAll("\"", "\'");
			//System.out.println(i + " tu jest linia " + linia);
			System.out.println(nazwa+" "+i);
			DBObject obiekt = (DBObject) JSON.parse(linia);
			//System.out.println(obiekt.get("id"));
			kolekcja.insert(obiekt);

		}
		}
	}
	
	public static void wstawMR(String nazwa) throws MongoException, IOException, StringIndexOutOfBoundsException {
		Mongo mon = new Mongo();
		mon.dropDatabase(nazwa);
		DB baza = mon.getDB(nazwa);
		System.out.println(baza.getStats());
		System.out.println(mon.getAllAddress());
		System.out.println(mon.getConnectPoint());
		Set<String> kolekcje = baza.getCollectionNames();
		System.out.println("Kolekcje");
		final String MAP = "function() { this.LongDescription.match(/[a-z]+/g).forEach( " +
                "  function(z){                  " +
                "     emit( z , { count : 1 } ); " +
                "  });                           " +
                "}; " ;
		final String REDUCE = "function( key , values ){               " +
			    "  var total = 0;                        " +
			    "  for ( var i=0; i<values.length; i++ ) " +
			    "      total += values[i].count;         " +
			    "  return { count : total };             " +
			    "}; " ;
		for (String s : kolekcje) {
			System.out.println(s);
		}
		System.out.println("Koniec kolekcji");
		DBCollection kolekcja = baza.getCollection(nazwa);
		BufferedReader plik = new BufferedReader(new FileReader(nazwa + ".txt"));
		String linia = "";
		int i = 0;
		while ((linia = plik.readLine()) != null) {
			i++;
			// linia=linia.substring(1);
			if(linia.length()>5){
				try{
				linia = linia.substring(0, linia.lastIndexOf(","));
				}
				catch (Exception e) {
					System.out.println(linia);
					linia=linia.substring(0,linia.length()-1);
				}
			if(linia.endsWith("}")){
				
			}
			else{
				linia = linia + "}";
			}
			// System.out.println(i+" tu jest linia "+linia);
			String lista[] = linia.split(",");
			// System.out.println(lista[0]);
			// System.out.println(lista[1]);
			linia = "{"
					+ linia.replace(lista[0] + ",", "").replace(lista[1] + ",",
							"");
			// System.out.println(i+" tu jest linia "+linia);
			//linia = linia.replaceAll("\"", "\'");
			//System.out.println(i + " tu jest linia " + linia);
			System.out.println(nazwa+" "+i);
			DBObject obiekt = (DBObject) JSON.parse(linia);
			//System.out.println(obiekt.get("id"));
			kolekcja.insert(obiekt);

		}
		}
		MapReduceOutput mapReduceOut = kolekcja.mapReduce(MAP, REDUCE, "Zredukowane",null); 
		System.out.println(mapReduceOut.getCommandResult());
	
	}
}
