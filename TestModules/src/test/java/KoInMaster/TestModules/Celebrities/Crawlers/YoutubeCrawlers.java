package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeCrawlers implements Crawler, Runnable{
	private final String platform;
	private final String url;
	private final String apiKey;
	private final String channelId;
	private final YouTube.Search.List request;
	private final List<Post> postList;

	public YoutubeCrawlers(String URL, List<Post> list) throws IOException, GeneralSecurityException {
		platform = "youtube";

		// extract channel id from URL
		url = URL.replaceAll("\\?.*", "");

		Matcher matcher = Pattern.compile("(www.youtube.com/(channel|c)/)?(?<ID>[^?]*)\\??")
		                         .matcher(URL);
		if (matcher.find())
			channelId = matcher.group("ID");
		else
			channelId = "error"; //待處理，還沒想好

		// to load api key from properties file
		Properties props = new Properties();

		// this throw IOException
		props.load(YoutubeCrawlers.class.getClassLoader().getResourceAsStream("api.properties"));
		apiKey = props.getProperty("youtube");

		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		// this throw GeneralSecurityException
		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		YouTube youTube = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
				.setApplicationName("KoInMaster").build();
		request = youTube.search().list(Collections.singletonList("snippet"));

		postList = list;
	}

	public List<Post> searchChannel(String channelId) throws IOException {
		List<Post> list = new ArrayList<>();

		// live
		SearchListResponse response = request.setKey(apiKey)
		                                     .setChannelId(channelId)
		                                     .setOrder("date")
		                                     .setEventType("live")
		                                     .setMaxResults(2L)
		                                     .setType(Collections.singletonList("video"))
		                                     .execute();
		if (response.getItems().size() != 0)    list.add(new YoutubePost(response.getItems().get(0)));

		// upcoming
		response = request.setKey(apiKey)
		                  .setChannelId(channelId)
		                  .setOrder("date")
		                  .setEventType("upcoming")
		                  .setMaxResults(10L)
		                  .setType(Collections.singletonList("video"))
		                  .execute();
		for (SearchResult s: response.getItems()) {
			// skip the result that is already started
			if (list.size() > 0 && s.getId().getVideoId().equals(list.get(0).getUrl().replaceAll("https://www\\.youtube\\.com/watch\\?v=", "")))
				continue;
			list.add(new YoutubePost(s));
		}

		// completed(normal videos)
		response = request.setKey(apiKey)
		                  .setChannelId(channelId)
		                  .setOrder("date")
		                  .setEventType("none")
		                  .setMaxResults(10L)
		                  .setType(Collections.singletonList("video"))
		                  .execute();
		for (SearchResult s: response.getItems()) {
			boolean flag = false;
			for (Post p:list)
				// modify the video's type that is actually completed
				if (s.getId().getVideoId().equals(p.getUrl().replaceAll("https://www\\.youtube\\.com/watch\\?v=", ""))) {
					flag = true;
					p.setType("youtube-" + s.getSnippet().getLiveBroadcastContent());
					break;
				}
			if (!flag)  list.add(new YoutubePost(s));
		}

		return list;
	}

	@Override
	public List<Post> getPostList() throws IOException {
		return searchChannel(channelId);
	}

	public String getPlatform() {
		return platform;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public void run() {
		
	}
}