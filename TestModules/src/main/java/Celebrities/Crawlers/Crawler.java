package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.PostList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.concurrent.Callable;

public abstract class Crawler implements Callable<PostList> {
	protected String name;        // celebrity's name
	protected PLATFORM platform;
	protected String param;

	public Crawler(String name, PLATFORM platform) {
		this.name = name;
		this.platform = platform;
	}

	// builder for file read system
	public static Crawler build(PLATFORM platform, String name, String param) throws GeneralSecurityException, IOException, URISyntaxException {
		switch (platform) {
			case YOUTUBE:
				return new YoutubeCrawler(name, param);
			case TWITTER:
				return new TwitterCrawler(name, param);
			case FACEBOOK:
				return new FacebookCrawler(name, param);
			default:
				return null;
		}
	}

	public PLATFORM getPlatform() {	return platform; }

	public String getParam() { return param; }

	public abstract String getFormatParam();

	@Override
	public abstract String toString();
}
