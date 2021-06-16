package Core;

import Posts.Post;
import Posts.PostList;
import Posts.PostSort;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * SwingWorker tool, which generate a thread pool to get posts from every crawler
 */
public class CrawlPosts extends SwingWorker<PostList,Object> {
	private final List<FutureTask<PostList>> tasks;
	private final JButton button;

	public CrawlPosts(List<FutureTask<PostList>> tasks, JButton button) {
		this.tasks = tasks;
		this.button = button;
	}

	private PostList getPostList() throws ExecutionException, InterruptedException {
		PostList posts = new PostList();

		// put tasks into pool
		ExecutorService getPost = Executors.newCachedThreadPool();

		// execute threads
		for (FutureTask<PostList> t:tasks)
			getPost.submit(t);

		// collect results
		getPost.shutdown();
		for (FutureTask<PostList> t:tasks)
			posts.addAll(t.get());

		// sort by reversed publish time
		posts.sort(new PostSort().reversed());
		return posts;
	}

	@Override
	protected PostList doInBackground() throws Exception {
		return getPostList();
	}

	@Override
	protected void done() {
		try {
			// debug
			for (Post p:get())
				System.out.println(p);
			button.setEnabled(true);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
