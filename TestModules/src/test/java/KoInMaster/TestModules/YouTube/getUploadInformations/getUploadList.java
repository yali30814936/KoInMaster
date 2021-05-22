package KoInMaster.TestModules.YouTube.getUploadInformations;

import KoInMaster.TestModules.Posts.Post;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class getUploadList {
	private Properties props;
	private final JsonFactory JSON_FACTORY;
	private final NetHttpTransport httpTransport;
	private final YouTube youTube;
	private final YouTube.Search.List request;
	private SearchListResponse response;

	public getUploadList() throws IOException, GeneralSecurityException {
		// to load api key from properties file
		props = new Properties();
		// this throw IOException
		props.load(getUploadList.class.getClassLoader().getResourceAsStream("api.properties"));
		JSON_FACTORY = JacksonFactory.getDefaultInstance();
		// this throw GeneralSecurityException
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		youTube = new YouTube.Builder(httpTransport, JSON_FACTORY, null).build();
		request = youTube.search().list(Collections.singletonList("snippet"));
	}

	public List<Post> searchChannel(String channelId) throws IOException {
		List<Post> list = new ArrayList<Post>();

		response = request.setKey(props.getProperty("youtube"))
		                  .setChannelId(channelId)
		                  .setOrder("date")
		                  .setEventType("live")
		                  .setMaxResults(2L)
		                  .setType(Collections.singletonList("video"))
		                  .execute();


		return list;
	}
}