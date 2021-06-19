package GUI.Type;

import Celebrities.Celebrity;
import Celebrities.Crawlers.Crawler;
import Core.CrawlPosts;
import Core.Data;
import Posts.PostList;
import Posts.TYPE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class TypeRefreshGUI extends Box {
	private final JButton refreshButton;
	private final List<TypeCheckBox> checkBoxes;
	private Data data;

	public TypeRefreshGUI() {
		super(BoxLayout.LINE_AXIS);

		checkBoxes = new ArrayList<>();
		JPanel panel = new JPanel();

		RefreshType listener = new RefreshType();
		for (TYPE type:TYPE.values()) {
			TypeCheckBox checkBox = new TypeCheckBox(type);
			checkBox.setSelected(true);
			checkBox.addActionListener(listener);
			checkBoxes.add(checkBox);
			panel.add(checkBox);
		}

		refreshButton = new JButton("重新整理");
		refreshButton.addActionListener(new RefreshPosts());

		add(panel);
		add(refreshButton);
	}

	public void setData(Data data) {
		this.data = data;
	}

	private class TypeCheckBox extends JCheckBox {
		private TYPE type;

		public TypeCheckBox(TYPE type) {
			super(type.toString());
		}

		public TYPE getType() {
			return type;
		}
	}

	private class RefreshType implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			data.getContainer().setTypeWhiteList(checkBoxes.stream()
			                                               .filter(AbstractButton::isSelected)
			                                               .map(TypeCheckBox::getType)
			                                               .collect(Collectors.toList()));
		}
	}

	private class RefreshPosts implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<FutureTask<PostList>> tasks = new ArrayList<>();

			refreshButton.setEnabled(false);

			for (Celebrity cel:data.getCelebrities())
				if (cel.isEnabled())
					for (Crawler cr:cel.getCrawlers().values())
						tasks.add(new FutureTask<>(cr));

			CrawlPosts crawlPosts = new CrawlPosts(tasks, refreshButton, data);
			crawlPosts.execute();
		}
	}
}
