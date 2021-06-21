package GUI.Block;

import Posts.Post;
import Posts.YoutubePost;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class YoutubeBlock extends JPanel {
	private final BlockTextArea description;
	private JButton button;
	private final YoutubePost yp;
	private boolean expanded = false;
	private final String normalHint = "展開內容";
	private final String expandedHint = "收起內容";

	public YoutubeBlock(Post post) {
		YoutubePost yp = (YoutubePost) post;
		this.yp = yp;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.white);

		Box vBox = Box.createVerticalBox();

		Box hBox = Box.createHorizontalBox();

		// name
		String topTitle = post.getName();
		switch (post.getType()) {
			case NONE:
				topTitle += " 在 YouTube 發布了一部影片";
				break;
			case LIVE:
				topTitle += " 正在 YouTube 上直播";
				break;
			case UPCOMING:
				topTitle += " 在 YouTube 排定了一部影片/直播";
		}
		hBox.add(new HyperLink(topTitle, post.getUrl()));
		hBox.add(Box.createHorizontalGlue()); // left align
		vBox.add(hBox);

		// time
		hBox = Box.createHorizontalBox();
		SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
		hBox.add(new BlockTextField(ft.format(post.getPublishedTime())));
		hBox.add(Box.createHorizontalGlue()); // left align
		vBox.add(hBox);

		// title
		hBox = Box.createHorizontalBox();
		BlockTextField title = new BlockTextField(post.getText());
		title.setFont(new Font(Font.SERIF, Font.BOLD, 22));
		hBox.add(title);
		hBox.add(Box.createHorizontalGlue()); // left align
		vBox.add(hBox);

		// description
		hBox = Box.createHorizontalBox();
		description = new BlockTextArea(yp.getDescription());
		description.setForeground(Color.gray);
		hBox.add(description);
		vBox.add(hBox);

		// expand
		hBox = Box.createHorizontalBox();
		button = new JButton(normalHint);
		button.setBorder(null);
		button.setBackground(new Color(219, 219, 219));
		button.setSize(80, 25);
		button.addActionListener(new ExpandDescription());
		hBox.add(button);
		vBox.add(hBox);

		add(vBox);
		PutImageIcon put = new PutImageIcon(vBox, yp.getMedia().get(0));
		put.execute();
	}

	private class ExpandDescription implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (expanded) {
				description.setText(yp.getDescription());
				button.setText(normalHint);
			}
			else {
				description.setText(yp.getFullDescription());
				button.setText(expandedHint);
			}

			expanded = !expanded;
		}
	}

	private static class PutImageIcon extends SwingWorker<JLabel, Object> {
		private final Box parent;
		private final String url;

		public PutImageIcon(Box parent, String url) {
			this.parent = parent;
			this.url = url;
		}

		@Override
		protected JLabel doInBackground() throws Exception {
			URL u;
			try {
				u = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
			Image image = ImageIO.read(u);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			float scale = 1200f / width;

			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(Math.round(width * scale),
			                                                                Math.round(height * scale),
			                                                                Image.SCALE_SMOOTH)));
			Box box = Box.createHorizontalBox();
			box.add(label);
			parent.add(box);
			parent.revalidate();
			return null;
		}
	}
}
