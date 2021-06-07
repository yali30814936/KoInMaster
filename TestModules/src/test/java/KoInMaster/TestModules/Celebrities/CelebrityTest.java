package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Celebrities.Crawlers.YoutubeCrawler;
import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;
import KoInMaster.TestModules.Posts.PostSort;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CelebrityTest {
	public static void main(String[] args) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException {
		Long time1, time2, time3;
		time1 = System.currentTimeMillis();

		List<Celebrity> celebrities = new ArrayList<>();

		time2 = System.currentTimeMillis();
		System.out.println("creating celebrities: " + (time2 - time1));
		Celebrity tmp = new Celebrity("Hololiveに肝を捧げよう");
		List<Post> posts = new ArrayList<>();
		tmp.getCrawlers().add(new YoutubeCrawler(tmp.getName(), "UCLB9LPemPH4fb8Nci1cDzBg"));
		celebrities.add(tmp);
		tmp = new Celebrity("hololive 露西婭監視中");
		tmp.getCrawlers().add(new YoutubeCrawler(tmp.getName(), "UCXR8XqwKWHSFQ26ec9-4NUA"));
		celebrities.add(tmp);
		tmp = new Celebrity("山寨猫 Copy Cat");
		tmp.getCrawlers().add(new YoutubeCrawler(tmp.getName(), "UCCd5UjY31hqHjo2pE_Q3Y2g"));
		celebrities.add(tmp);
		ExecutorService getPost = Executors.newCachedThreadPool();
		List<FutureTask<PostList>> tasks = new ArrayList<>();
		for (Celebrity c:celebrities)
			for (Crawler crawl:c.getCrawlers())
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
