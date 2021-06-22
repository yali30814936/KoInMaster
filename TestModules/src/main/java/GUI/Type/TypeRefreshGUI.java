package GUI.Type;

import Celebrities.Celebrity;
import Celebrities.Crawlers.Crawler;
import Core.CrawlPosts;
import Core.Data;
import GUI.MainGUI;
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

		for (TYPE type:TYPE.values()) {
			TypeCheckBox checkBox = new TypeCheckBox(type);
			checkBox.setSelected(false);
			checkBox.addActionListener(e -> getEnabledTypes());
			checkBoxes.add(checkBox);
			panel.add(checkBox);
		}

		RefreshType listener = new RefreshType();
		JButton filterButton = new JButton("套用過濾");
		filterButton.addActionListener(listener);

		refreshButton = new JButton("重新抓取");
		refreshButton.addActionListener(new RefreshPosts());

		add(panel);
		add(Box.createHorizontalGlue());
		add(filterButton);
		add(Box.createHorizontalStrut(10));
		add(refreshButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (JCheckBox checkBox:checkBoxes)
			checkBox.setEnabled(enabled);
		refreshButton.setEnabled(enabled);
	}

	public void setData(Data data) {
		this.data = data;
		for (TypeCheckBox b:checkBoxes)
			if (data.getTypes().stream().anyMatch(type -> type == b.type))
				b.setSelected(true);
	}

	public void getEnabledTypes() {
		List<TYPE> list = checkBoxes.stream()
		                            .filter(AbstractButton::isSelected)
		                            .map(TypeCheckBox::getType)
		                            .collect(Collectors.toList());
		data.setTypes(list);
		data.writeTypes();
	}

	private static class TypeCheckBox extends JCheckBox {
		private final TYPE type;

		public TypeCheckBox(TYPE type) {
			super(type.toString());
			this.type = type;
		}

		public TYPE getType() {
			return type;
		}
	}

	private class RefreshType implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((MainGUI) SwingUtilities.getWindowAncestor(refreshButton)).refreshBlock();
		}
	}

	private class RefreshPosts implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<FutureTask<PostList>> tasks = new ArrayList<>();

			setEnabled(false);

			for (Celebrity cel:data.getCelebrities())
				if (cel.isEnabled())
					for (Crawler cr:cel.getCrawlers().values())
						tasks.add(new FutureTask<>(cr));

			CrawlPosts crawlPosts = new CrawlPosts(tasks, refreshButton, data);
			crawlPosts.execute();
		}
	}
}
