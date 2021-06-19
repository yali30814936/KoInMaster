package GUI.Setting;

import Celebrities.Crawlers.Crawler;
import Core.Data;
import Posts.PLATFORM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CrawlerSector extends Box {
	private Data data;

	public CrawlerSector(int padding) {
		super(BoxLayout.PAGE_AXIS);
		setAlignmentY(CENTER_ALIGNMENT);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void showCrawlers() {
		// clear all
		removeAll();

		add(Box.createVerticalStrut(10));

		int count = 1;

		// show crawlers
		CrawlerInfo crawlerInfo;
		for (Map.Entry<String, Crawler> entry:data.getSelected().getCelebrity().getCrawlers().entrySet()) {
			crawlerInfo = new CrawlerInfo(entry.getValue().getPlatform(), entry.getKey(), entry.getValue().getFormatParam());
//			crawlerInfo.setBorder(new LineBorder(Color.red));
			add(crawlerInfo);
			add(Box.createVerticalStrut(10));
			count++;
		}

		// new sector
		add(new NewCrawler());
		add(Box.createVerticalStrut(10));
		// resize
//		setMinimumSize(new Dimension(Integer.MAX_VALUE, 20 + 36 * count));
		getParent().setMaximumSize(new Dimension(Integer.MAX_VALUE, 20 + 36 * count));
	}

	protected static class CrawlerInfo extends Box {

		public CrawlerInfo(PLATFORM platform, String name, String param) {
			super(BoxLayout.LINE_AXIS);
			setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));

			JLabel platformLabel = new JLabel(platform.toString());
			platformLabel.setPreferredSize(new Dimension(85, 26));
			JTextField nameField = new JTextField(name);
			nameField.setEditable(false);
			nameField.setBorder(null);
			JTextField paramField = new JTextField(param);
			paramField.setEditable(false);
			paramField.setBorder(null);

			add(platformLabel);
			add(nameField);
			add(paramField);

			JButton deleteButton = new JButton("移除");
			deleteButton.addActionListener(new DeleteAction());
			add(deleteButton);
		}

		private class DeleteAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		}
	}

	protected static class NewCrawler extends Box {
		private final JComboBox<String> platform;
		private final HintTextField crawlerName;
		private final HintTextField crawlerParam;

		public NewCrawler() {
			super(BoxLayout.LINE_AXIS);

			platform = new JComboBox<>(PLATFORM.getValues());
			platform.setMaximumSize(new Dimension(150, 26));
			add(platform);

			crawlerName = new HintTextField("輸入識別名稱");
			add(crawlerName);

			crawlerParam = new HintTextField("輸入抓取參數 (網址、Id...etc)");
			add(crawlerParam);

			JButton button = new JButton("新增抓取器");
			button.addActionListener(new AddCrawler());
			add(button);
		}

		private class AddCrawler implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(platform.getSelectedIndex());
			}
		}
	}
}
