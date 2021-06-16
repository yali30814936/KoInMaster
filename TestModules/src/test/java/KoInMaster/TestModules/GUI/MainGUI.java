package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Celebrities;
import KoInMaster.TestModules.Celebrities.Celebrity;
import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Core.CrawlPosts;
import KoInMaster.TestModules.Posts.PostList;

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
			List<String> celebritiesName = filterGUI.getList();
			List<FutureTask<PostList>> tasks = new ArrayList<>();

			refreshButton.setEnabled(false);

			for (Celebrity cel:celebrities)
				for (String key:celebritiesName)
					if (cel.getName().equals(key)) {
						for (Crawler cr:cel.getCrawlers().values())
							tasks.add(new FutureTask<>(cr));
						break;
					}

			CrawlPosts crawlPosts = new CrawlPosts(tasks, refreshButton);
			crawlPosts.execute();
		}
	}
}
