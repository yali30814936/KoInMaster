package GUI.Setting;

import Celebrities.Celebrity;
import Celebrities.Crawlers.Crawler;
import Core.Data;
import Posts.PLATFORM;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
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
			crawlerInfo = new CrawlerInfo(entry.getKey(), entry.getValue());
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
//		getParent().setMaximumSize(new Dimension(Integer.MAX_VALUE, 20 + 36 * count));

		repaint();
		revalidate();
	}

	protected class CrawlerInfo extends Box {
		private final String name;

		public CrawlerInfo(String name, Crawler crawler) {
			super(BoxLayout.LINE_AXIS);
			this.name = name;

			setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

			JLabel platformLabel = new JLabel(crawler.getPlatform().toString());
			platformLabel.setPreferredSize(new Dimension(120, 35));
			JTextField nameField = new JTextField(name);
			nameField.setEditable(false);
			nameField.setBorder(null);
			JTextField paramField = new JTextField(crawler.getFormatParam());
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
				int confirm = JOptionPane.showConfirmDialog(getRootPane(),
				                                            "確認刪除？",
				                                            "確認",
				                                            JOptionPane.OK_CANCEL_OPTION,
				                                            JOptionPane.WARNING_MESSAGE);

				if (confirm != 0)
					return;

				data.getSelected().getCelebrity().getCrawlers().remove(name);
				showCrawlers();

				data.writeData();
			}
		}
	}

	protected class NewCrawler extends Box {
		private final JComboBox<String> platform;
		private final HintTextField crawlerName;
		private final HintTextField crawlerParam;

		public NewCrawler() {
			super(BoxLayout.LINE_AXIS);

			platform = new JComboBox<>(PLATFORM.getValues());
			platform.setMaximumSize(new Dimension(150, 35));
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
				if (!crawlerName.isValid() || !crawlerParam.isValid())
					return;

				String newName = crawlerName.getText();

				if (data.getSelected().getCelebrity().getCrawlers().keySet()
				        .stream().anyMatch(key -> key.equals(newName))) {
					int confirm = JOptionPane.showConfirmDialog(getRootPane(),
					                                            "存在同名的抓取器，是否取代？",
					                                            "確認",
					                                            JOptionPane.OK_CANCEL_OPTION,
					                                            JOptionPane.WARNING_MESSAGE);
					if (confirm != 0)
						return;
				}

				Celebrity celebrity = data.getSelected().getCelebrity();
				try {
					celebrity.getCrawlers().put(newName, Crawler.rawBuild(PLATFORM.values()[platform.getSelectedIndex()],
					                                                      celebrity.getName(),
					                                                      crawlerParam.getText()));
				} catch (GoogleJsonResponseException ex) {
					JOptionPane.showMessageDialog(null, "YouTube api 用量超標，請隔日再試", "錯誤", JOptionPane.WARNING_MESSAGE);
					ex.printStackTrace();
				} catch (GeneralSecurityException | IOException | URISyntaxException generalSecurityException) {
					JOptionPane.showMessageDialog(getRootPane(), "新增抓取器失敗！請確認參數格式是否正確");
					generalSecurityException.printStackTrace();
				}

				// save
				data.writeData();

				showCrawlers();
			}
		}
	}
}
