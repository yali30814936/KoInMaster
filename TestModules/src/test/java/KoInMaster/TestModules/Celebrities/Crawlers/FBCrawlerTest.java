package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;

public class FBCrawlerTest {
	public static void main(String[] args) throws Exception {
		Crawler foo = new FacebookCrawler("百日後會爆肝的工程鹿-仮-", "https://www.facebook.com/KirinDD");
		System.out.println("ready to crawl");
		PostList list = foo.call();
		for (Post p:list)
			System.out.println(p);
	}
}
