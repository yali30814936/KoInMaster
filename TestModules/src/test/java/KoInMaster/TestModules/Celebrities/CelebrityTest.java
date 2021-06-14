package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
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
	public static PostList getPosts(List<Celebrity> celebrities) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException, URISyntaxException {
		Long time1, time2, time3;
		time1 = System.currentTimeMillis();

		time2 = System.currentTimeMillis();
		System.out.println("creating celebrities: " + (time2 - time1));

		PostList posts = new PostList();
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

		return posts;
	}
}
