import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Elastic {

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	/*public static void main(String[] args) throws MalformedURLException,
			IOException {
		// TODO Auto-generated method stub
		System.out.println("JUPI");
		wpisz();
	}*/

	public static void wpisz() throws MalformedURLException, IOException {
		HttpURLConnection con = (HttpURLConnection) new URL(
				"http://localhost:9200/").openConnection();
		con.setRequestMethod("GET");
		// con.getOutputStream().write("LOGIN".getBytes("UTF-8"));
		// con.getInputStream();
		System.out.println(con.getContentType());
		BufferedReader response = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputString = "";
		// String inputString2 = "";
		response.readLine();
		while ((inputString = response.readLine()) != null) {
			System.out.println(inputString);

		}
		response.close();

	}

}
