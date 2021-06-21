package GUI.Block;

import Core.Data;
import Posts.Post;
import Posts.PostList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class BlockGUI extends JScrollPane {
	private Data data;
	private JPanel panel;
	private Box vBox;

	public BlockGUI() {
		super();
		getVerticalScrollBar().setUnitIncrement(100);
		getHorizontalScrollBar().setUnitIncrement(40);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		if (panel != null)
			remove(panel);

		try {
			vBox = Box.createVerticalBox();
			Box hBox;
			for(Post post:list){
				hBox = Box.createHorizontalBox();
				JPanel block = null;
				switch (post.getPlatform()) {
					case TWITTER:
						block = new TwitterBlock(post);
						hBox.setBorder(new LineBorder(Color.cyan, 3));
						break;
					case YOUTUBE:
						block = new YoutubeBlock(post);
						hBox.setBorder(new LineBorder(new Color(215, 30, 24), 3));
						break;
					case FACEBOOK:
						block=new FacebookBlock(post);
						hBox.setBorder(new LineBorder(Color.blue,3));
						break;
				}
				hBox.add(block);
				vBox.add(hBox);
				vBox.add(Box.createVerticalStrut(20));
			}
//			vBox.setMaximumSize(new Dimension(1300, Integer.MAX_VALUE));
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(vBox);
			setViewportView(panel);
			RestPos reset;
			reset = new RestPos(this);
			reset.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return vBox.getPreferredSize();
	}

	private static class RestPos extends SwingWorker<Object,Object> {
		private final JScrollPane scrollPane;
		private Component container;

		public RestPos(JScrollPane scrollBar) {
			this.scrollPane = scrollBar;
		}

		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(100);
			return null;
		}

		@Override
		protected void done() {
			scrollPane.getVerticalScrollBar().setValue(0);
			if (container != null) {
				int left = (scrollPane.getHorizontalScrollBar().getMaximum() - (int) scrollPane.getSize().getWidth()) / 2 - 10;
				scrollPane.getHorizontalScrollBar().setValue(left);
			}
		}
	}
}
