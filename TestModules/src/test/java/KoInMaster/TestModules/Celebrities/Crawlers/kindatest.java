package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.util.List;

public class kindatest {
	public static void main(String[] args) throws Exception {
		Crawler foo = new YoutubeCrawler("雪花ラミィ","UCFKOVgVbGmX65RxO3EtH3iw");
		System.out.println("ready to crawl");
		List<Post> list = foo.call();
		for (Post p:list)
			System.out.println(p);
	}
}
