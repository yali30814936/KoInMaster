package Core;

import Celebrities.Celebrities;
import twitter4j.JSONArray;

import java.io.*;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

/**
 * Use this static class to read or write Celebrities object into "Celebrities.json"
 */
public class CelebritiesReadWrite {
	private static final String filename = "/Data/Celebrities.json";

	public static synchronized void write(Celebrities celebrities) throws IOException {
		FileWriter fw = new FileWriter(System.getProperty("user.dir") + filename);
		fw.write(celebrities.toJSONArray().toString());
		fw.flush();
		fw.close();
	}

	public static Celebrities read() throws IOException, GeneralSecurityException, URISyntaxException {
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + filename);
			BufferedReader br = new BufferedReader(fr);
			JSONArray obj = new JSONArray(br.readLine());
			return new Celebrities(obj);
		} catch (FileNotFoundException ex) {
			return new Celebrities();
		}
	}
}
