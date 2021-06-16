package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.Post;
import Posts.PostList;

public class YoutubeCrawlerTest {
	public static void main(String[] args) throws Exception {
		Crawler crawler = Crawler.build(PLATFORM.fromString("YouTube"),
		                                "ときのそら",
		                                "UCp6993wxpyDPHUpavwDFqgg");
		PostList list = crawler.call();
		for (Post p:list)
			System.out.println(p);
	}
}
