package KoInMaster.TestModules.YouTube.getUploadInformations;

//import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class getUploadList {
	private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private YouTube youTube;

	public getUploadList() {
		youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest httpRequest) throws IOException {}
		}).setApplicationName("getUploadList").build();
	}

	public static void main(String[] args) throws IOException {
//		String url = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails&id=UC1DCedRgGHBdm81E1llLhOQ&key=AIzaSyBreiraSgMSXyel4Pk0wXZu-v8HejFIoIk";

	}
}