package GUI.Block;

import Posts.Post;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BlockGenerator extends SwingWorker<Object, Object> {
	private final Box parent;
	private final Post post;
	private final Component struct;

	public BlockGenerator(Box parent, Component struct, Post post) {
		this.parent = parent;
		this.post = post;
		this.struct = struct;
	}

	@Override
	protected Object doInBackground() {
		JPanel block = null;
		switch (post.getPlatform()) {
			case TWITTER:
				block = new TwitterBlock(post);
				parent.setBorder(new LineBorder(Color.cyan, 3));
				break;
			case YOUTUBE:
				block = new YoutubeBlock(post);
				parent.setBorder(new LineBorder(new Color(215, 30, 24), 3));
				break;
			case FACEBOOK:
				block = new FacebookBlock(post);
				parent.setBorder(new LineBorder(Color.blue,3));
				break;
		}
		parent.add(block);
		struct.setVisible(true);
		return null;
	}
}
