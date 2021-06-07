package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PostList;
import KoInMaster.TestModules.Posts.YoutubePost;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeCrawler extends Crawler{
	private final String platform;
	private final String url;
	private final String apiKey;
	private final String channelId;
	private final YouTube.Search.List request;

	public YoutubeCrawler(String name, String URL) throws IOException, GeneralSecurityException {
		super(name);
		platform = "youtube";

		// extract channel id from URL
		url = URL.replaceAll("\\?.*", "");

		Matcher matcher = Pattern.compile("((https?://)?www.youtube.com/(channel|c)/)?(?<ID>[^?]*)\\??")
		                         .matcher(URL);
		if (matcher.find())
			channelId = matcher.group("ID");
		else
			channelId = "error"; //待處理，還沒想好

		// to load api key from properties file
		Properties props = new Properties();

		// this throw IOException
		props.load(YoutubeCrawler.class.getClassLoader().getResourceAsStream("api.properties"));
		apiKey = props.getProperty("youtube");

		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		// this throw GeneralSecurityException
		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		YouTube youTube = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
				.setApplicationName("KoInMaster").build();
		request = youTube.search().list(Collections.singletonList("snippet"));
	}

	public PostList searchChannel(String channelId) throws InterruptedException, IOException {
		PostList list = new PostList();
		SearchListResponse response = request.setKey(apiKey)
		                                     .setChannelId(channelId)
				                             .setOrder("date")
				                             .setEventType("live")
				                             .setEventType("upcoming")
				                             .setEventType("none")
				                             .setMaxResults(10L)
		                                     .setType(Collections.singletonList("video"))
		                                     .execute();
		for (SearchResult s:response.getItems())
			list.add(new YoutubePost(super.name, s));
		return list;
	}

	public String getPlatform() {
		return platform;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public PostList call() throws Exception {
		return searchChannel(channelId);
	}
}