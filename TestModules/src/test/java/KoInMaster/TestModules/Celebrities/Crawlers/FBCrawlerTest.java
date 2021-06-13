package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;

public class FBCrawlerTest {
	public static void main(String[] args) throws Exception {
		FacebookPageIdFetcher fetcher=new FacebookPageIdFetcher();
		fetcher.getPageId("https://www.facebook.com/KirinDD");
		FacebookRequestPosts v3 = new FacebookRequestPosts(fetcher.getPageId("https://www.facebook.com/KirinDD"));
		Crawler foo = new FacebookCrawler("百日後會爆肝的工程鹿-仮-", "https://www.facebook.com/KirinDD",v3.getResponse());
		System.out.println("ready to crawl");
		PostList list = foo.call();
		for (Post p:list)
			System.out.println(p);
	}
}
