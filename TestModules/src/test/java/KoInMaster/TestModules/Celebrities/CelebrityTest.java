package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Celebrities.Crawlers.FacebookCrawler;
import KoInMaster.TestModules.Posts.PLATFORM;
import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;
import KoInMaster.TestModules.Posts.PostSort;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CelebrityTest {
	public static void main(String[] args) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException, URISyntaxException {
		Long time1, time2, time3;
		time1 = System.currentTimeMillis();

		List<Celebrity> celebrities = new ArrayList<>();

		time2 = System.currentTimeMillis();
		System.out.println("creating celebrities: " + (time2 - time1));

		Celebrity tmp = new Celebrity("桐生ココ");
		List<Post> posts = new ArrayList<>();
		tmp.getCrawlers().put(PLATFORM.FACEBOOK, new FacebookCrawler("工程鹿", "https://www.facebook.com/KirinDD"));
//		tmp.getCrawlers().put(PLATFORM.YOUTUBE, new YoutubeCrawler(tmp.getName(), "UCS9uQI-jC3DE0L4IpXyvr6w"));
//		tmp.getCrawlers().add(new TwitterCrawler(tmp.getName(), "kiryucoco"));
//		tmp.getCrawlers().add(new FacebookCrawler("百日後會爆肝的工程鹿-仮-", "https://www.facebook.com/KirinDD"));
		celebrities.add(tmp);

//		tmp = new Celebrity("角巻わため");
//		tmp.getCrawlers().put(PLATFORM.YOUTUBE, new YoutubeCrawler(tmp.getName(), "UCqm3BQLlJfvkTsX_hvm0UmA"));
//		tmp.getCrawlers().add(new TwitterCrawler(tmp.getName(), "tsunomakiwatame"));
//		tmp.getCrawlers().add(new FacebookCrawler("日日好車", "https://www.facebook.com/goodfapeveryday"));
//		celebrities.add(tmp);

//		tmp = new Celebrity("白上フブキ");
//		tmp.getCrawlers().put(PLATFORM.YOUTUBE, new YoutubeCrawler(tmp.getName(), "UCdn5BQ06XqgXoAxIhbqw5Rg"));
//		tmp.getCrawlers().add(new TwitterCrawler(tmp.getName(), "shirakamifubuki"));
//		tmp.getCrawlers().add(new FacebookCrawler("Vtuber project", "https://www.facebook.com/Vtbproject"));
//		celebrities.add(tmp);
		ExecutorService getPost = Executors.newCachedThreadPool();
		List<FutureTask<PostList>> tasks = new ArrayList<>();
		for (Celebrity c:celebrities)
			for (Crawler crawl:c.getCrawlers().values())
				tasks.add(new FutureTask<>(crawl));

		time3 = System.currentTimeMillis();
		System.out.println("ready to crawl: " + (time3 - time2));
		for (FutureTask<PostList> t:tasks)
			getPost.submit(t);

		getPost.shutdown();
		for (FutureTask<PostList> t:tasks)
			posts.addAll(t.get());

		System.out.println("crawl complete: " + (System.currentTimeMillis() - time3));
		Collections.sort(posts, new PostSort().reversed());
		for (Post p:posts)
			System.out.println(p);
	}
}
