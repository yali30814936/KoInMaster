package GUI.Setting;

import Core.Data;

import javax.swing.*;
import java.awt.*;

public class SettingGUI extends JScrollPane {
	private final int padding = 20;
	private final JPanel innerPanel;
	private final Box vBox;
	private final NameSector nameSector;
	private final NewSector newSector;
	private final DeleteSector deleteSector;
	private Data data;

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

		// name
		nameSector = new NameSector(padding);
		vBox.add(nameSector);

		// new
		newSector = new NewSector(padding);
		newSector.setVisible(false);
		Box newBox = Box.createHorizontalBox();
		newBox.setMaximumSize(new Dimension(4000, 60));
		newBox.add(newSector);
		vBox.add(newBox);

		// delete
		deleteSector = new DeleteSector(this, padding);
		deleteSector.setVisible(false);
		Box delBox =  Box.createHorizontalBox();
		delBox.add(deleteSector);
		vBox.add(delBox);

		vBox.add(Box.createVerticalGlue());
		vBox.add(Box.createVerticalStrut(padding));
		innerPanel.add(vBox);
	}

	public void filterSelectedPerform() {
		nameSector.toggleMode(false);
		if (!data.isSelectTop()) {
			nameSector.setName(data.getSelected().getName());
			nameSector.setSelected(true);
			deleteSector.setVisible(true);
		} else {
			nameSector.setName("選取一個節點來編輯");
			nameSector.setSelected(false);
			deleteSector.setVisible(false);
		}
		newSector.setVisible(!data.getSelected().getFilterNode().isCelebrity());
	}

	public void setData(Data data) {
		this.data = data;
		nameSector.setData(data);
		newSector.setData(data);
		deleteSector.setData(data);
	}
}
