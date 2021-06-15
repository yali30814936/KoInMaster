package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PLATFORM;
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
	private final String apiKey;
	private final YouTube.Search.List request;

	public YoutubeCrawler(String name, String URL) throws GeneralSecurityException, IOException {
		super(name, PLATFORM.YOUTUBE);

		// extract channel id from URL
		Matcher matcher = Pattern.compile("((https?://)?www.youtube.com/(channel|c)/)?(?<ID>[^?]*)\\??")
		                         .matcher(URL);
		if (matcher.find())
			param = matcher.group("ID");
		else {
			param = "error"; //待處理，還沒想好
			throw new IOException("分析 '" + name + "' 的YT頻道 url='" + URL +"' 失敗");
		}

		// to load api key from properties file
		Properties props = new Properties();
		try {
			props.load(YoutubeCrawler.class.getClassLoader().getResourceAsStream("api.properties"));
		} catch (IOException e) {
			throw new IOException("YouTube api-key 載入失敗");
		}
		apiKey = props.getProperty("youtube");

		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		// this throw GeneralSecurityException
		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		YouTube youTube = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
				.setApplicationName("KoInMaster").build();
		request = youTube.search().list(Collections.singletonList("snippet"));
	}

	public PostList searchChannel(String channelId){
		PostList list = new PostList();
		try {
			SearchListResponse response = request.setKey(apiKey)
			                                     .setChannelId(channelId)
			                                     .setOrder("date")
			                                     .setEventType("live")
			                                     .setEventType("upcoming")
			                                     .setEventType("none")
			                                     .setMaxResults(20L)
			                                     .setType(Collections.singletonList("video"))
			                                     .execute();
			for (SearchResult s:response.getItems())
				list.add(new YoutubePost(name, s));
		} catch (IOException e) {
			System.err.println("搜尋 '" + name + "' 的YT頻道 id='" + channelId +"' 時發生錯誤");
		}
		return list;
	}

	@Override
	public PostList call() {
		return searchChannel(param);
	}

	@Override
	public String toString() {
		return "YoutubeCrawler{" +
				"name='" + name + '\'' +
				", platform=" + platform +
				", channelId='" + param + '\'' +
				'}';
	}
}