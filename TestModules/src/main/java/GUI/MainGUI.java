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

import static javax.swing.SpringLayout.*;

public class MainGUI extends JFrame {
	private final JButton toggleSettingButton;
	private final FilterGUI filterGUI;
	private Celebrities celebrities;
	private final JButton refreshButton;
	private final SettingGUI settingGUI;

	public MainGUI() {
		super("KoInMaster");
		SpringLayout springLayout = new SpringLayout();
		Container contentPane = getContentPane();
		contentPane.setLayout(springLayout);

		// filter
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		// setting button
		toggleSettingButton = new JButton("模組設定");
		toggleSettingButton.addActionListener(new toggleSetting());
		// filterGUI
		filterGUI = new FilterGUI();
		Box hBox1 = Box.createHorizontalBox();
		Box hBox2 = Box.createHorizontalBox();
		// fit in
		hBox1.add(toggleSettingButton);
		hBox2.add(filterGUI);
		Box vBox = Box.createVerticalBox();
		// combine
		vBox.add(hBox1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox2);
		filterPanel.add(vBox);
//		hBox2.setBorder(BorderFactory.createCompoundBorder(
//				BorderFactory.createLineBorder(Color.red),
//				hBox2.getBorder()));
//		filterPanel.setBorder(BorderFactory.createCompoundBorder(
//				BorderFactory.createLineBorder(Color.BLUE),
//				filterPanel.getBorder()));

		// setting filter panel layout.
		contentPane.add(filterPanel);
		springLayout.putConstraint(NORTH, filterPanel, 10, NORTH, contentPane);
		springLayout.putConstraint(SOUTH, filterPanel, -10, SOUTH, contentPane);
		springLayout.putConstraint(WEST, filterPanel, 10, WEST, contentPane);
		springLayout.putConstraint(EAST, filterPanel, -300, HORIZONTAL_CENTER, contentPane);

		// setting GUI
		settingGUI = new SettingGUI();
//		settingGUI.setVisible(false);
		contentPane.add(settingGUI);
		springLayout.putConstraint(NORTH, settingGUI, 10, NORTH, contentPane);
		springLayout.putConstraint(SOUTH, settingGUI, -10, SOUTH, contentPane);
		springLayout.putConstraint(EAST, settingGUI, -10, EAST, contentPane);
		springLayout.putConstraint(WEST, settingGUI, 10, EAST, filterPanel);

		// debug button
		refreshButton = new JButton("Get Posts");
		refreshButton.addActionListener(new RefreshPostList());
		contentPane.add(refreshButton);
		refreshButton.setEnabled(false);
	}

	/**
	 * Call FilterGUI to reload the structure
	 */
	public void loadFilter() {
		filterGUI.loadTree();
	}

	public void setCelebrities(Celebrities celebrities) {
		this.celebrities = celebrities;
		filterGUI.setCelebrities(celebrities);
		loadFilter();
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

	private class toggleSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (settingGUI.isVisible()) {
				settingGUI.setVisible(false);
				toggleSettingButton.setText("模組設定");
			} else {
				settingGUI.setVisible(true);
				toggleSettingButton.setText("關閉面板");

			}
		}
	}

}
