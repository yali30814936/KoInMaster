package GUI;

import Celebrities.Celebrities;
import Celebrities.Celebrity;
import Celebrities.Crawlers.Crawler;
import Core.CrawlPosts;
import Posts.PostList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class MainGUI extends JFrame {
	private final FilterGUI filterGUI;
	private Celebrities celebrities;
	private final JButton refreshButton;

	public MainGUI() {
		super("KoInMaster");

		filterGUI = new FilterGUI();
		add(filterGUI, BorderLayout.WEST);

		// debug
		refreshButton = new JButton("Get Posts");
		refreshButton.addActionListener(new RefreshPostList());
		add(refreshButton, BorderLayout.CENTER);
		refreshButton.setEnabled(false);
	}

	/**
	 * Call FilterGUI to reload the structure
	 * @param list celebrity list
	 */
	public void loadFilter(Celebrities list) {
		filterGUI.loadTree(list);
	}

	public void setCelebrities(Celebrities celebrities) {
		this.celebrities = celebrities;
		loadFilter(celebrities);
	}

	public void setRefreshEnabled(boolean enabled) { refreshButton.setEnabled(enabled);}

	/**
	 * Handle refresh event.
	 * Call asynchronous class to crawl.
	 */
	private class RefreshPostList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<FutureTask<PostList>> tasks = new ArrayList<>();

			refreshButton.setEnabled(false);

			for (Celebrity cel:celebrities)
				if (cel.isEnabled())
					for (Crawler cr:cel.getCrawlers().values())
						tasks.add(new FutureTask<>(cr));

			CrawlPosts crawlPosts = new CrawlPosts(tasks, refreshButton);
			crawlPosts.execute();
		}
	}
}
