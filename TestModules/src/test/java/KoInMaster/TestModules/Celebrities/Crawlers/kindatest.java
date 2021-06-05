package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.util.List;
import java.util.concurrent.Callable;

public class kindatest {
	public static void main(String[] args) throws Exception {
		Callable<List<Post>> foo = new YoutubeCrawler("UCvInZx9h3jC2JzsIzoOebWg");
		System.out.println("ready to crawl");
		List<Post> list = foo.call();
		for (Post p:list)
			System.out.println(p);
	}
}
