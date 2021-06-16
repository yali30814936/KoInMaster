package Core;

import Celebrities.Celebrity;
import Celebrities.CelebrityTest;
import Posts.Post;
import Posts.PostList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CelebritiesFileReaderTest {
	public static void main(String[] args) throws GeneralSecurityException, IOException, URISyntaxException, ExecutionException, InterruptedException {
		long t1, t2;
		t1 = System.currentTimeMillis();
		List<Celebrity> celebrities = CelebritiesFileReader.readModules();

		t2 = System.currentTimeMillis();
		System.out.println("Read: " + (t2 - t1));

		PostList postList = CelebrityTest.getPosts(celebrities);

		System.out.println("Finish crawling: " + (System.currentTimeMillis() - t2));
		for (Post p:postList)
			System.out.println(p);
	}
}