package GUI.Block;

import Posts.Post;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BlockGenerator extends SwingWorker<Object, Object> {
	private final Box parent;
	private final Post post;
	private SwingWorker<Object, Object> worker;

	public BlockGenerator(Box parent, Post post) {
		this.parent = parent;
		this.post = post;
	}

	@Override
	protected Object doInBackground() throws Exception {
		JPanel block = null;
		switch (post.getPlatform()) {
			case TWITTER:
				block = new TwitterBlock(post);
				worker = ((TwitterBlock) block).getWorker();
				parent.setBorder(new LineBorder(Color.cyan, 3));
				break;
			case YOUTUBE:
				block = new YoutubeBlock(post);
				worker = ((YoutubeBlock) block).getWorker();
				parent.setBorder(new LineBorder(new Color(215, 30, 24), 3));
				break;
			case FACEBOOK:
				block = new FacebookBlock(post);
				worker = ((FacebookBlock) block).getWorker();
				parent.setBorder(new LineBorder(Color.blue,3));
				break;
		}
		parent.add(block);
		return null;
	}

	public void terminate() {
		if (worker != null)
			worker.cancel(true);
		cancel(true);
	}
}
