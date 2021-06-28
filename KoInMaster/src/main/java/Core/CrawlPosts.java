package Core;

import GUI.MainGUI;
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
	private final Data data;

	public CrawlPosts(List<FutureTask<PostList>> tasks, JButton button, Data data) {
		this.tasks = tasks;
		this.button = button;
		this.data = data;
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
			data.getContainer().add(get());
			data.writePosts();
			System.out.println("Done");
			((MainGUI) SwingUtilities.getWindowAncestor(button)).setRefreshEnabled(true);
			((MainGUI) SwingUtilities.getWindowAncestor(button)).refreshBlock();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
