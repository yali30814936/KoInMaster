package GUI;

import GUI.Filter.FilterNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingGUI extends JScrollPane {
	private final JPanel innerPanel;
	private final Box vBox;
	private final JLabel nameLabel;
	private final JButton nameButton;
	private final int padding = 20;

	public SettingGUI() {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);

		innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		setViewportView(innerPanel);

		// stew
		vBox = Box.createVerticalBox();
		vBox.add(Box.createVerticalStrut(padding));

		// title
		Box nameBox = Box.createHorizontalBox();
		nameLabel = new JLabel("選取一個節點來編輯");
		nameButton = new JButton("修改名稱");
		nameButton.addActionListener(new ModifyName());
		nameBox.add(Box.createHorizontalStrut(padding));
		nameButton.setVisible(false);
		nameBox.add(nameLabel);
		nameBox.add(Box.createHorizontalGlue());
		nameBox.add(nameButton);
		nameBox.add(Box.createHorizontalStrut(padding));
		nameBox.setMaximumSize(new Dimension(4000,50));
		vBox.add(nameBox);

		vBox.add(Box.createVerticalGlue());
		vBox.add(Box.createVerticalStrut(padding));
		innerPanel.add(vBox);
	}

	private class ModifyName implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	public void filterSelectedPerform(FilterNode node) {
		nameLabel.setText(node.getName());
		nameButton.setVisible(true);
	}
}
