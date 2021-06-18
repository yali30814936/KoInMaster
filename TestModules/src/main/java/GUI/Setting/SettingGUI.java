package GUI.Setting;

import Core.Data;

import javax.swing.*;

public class SettingGUI extends JScrollPane {
	private final int padding = 20;
//	private FilterGUI filterGUI;
	private final JPanel innerPanel;
	private final Box vBox;
	private final NameSector nameSector;
	private final NewSector newSector;
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
		newSector = new NewSector(this, padding);
		newSector.setVisible(false);
		vBox.add(Box.createHorizontalBox().add(newSector));

		vBox.add(Box.createVerticalGlue());
		vBox.add(Box.createVerticalStrut(padding));
		innerPanel.add(vBox);
	}

	public void filterSelectedPerform() {
		nameSector.toggleMode(false);
		if (!data.isSelectTop()) {
			nameSector.setName(data.getSelected().getName());
			nameSector.setSelected(true);
		} else {
			nameSector.setName("選取一個節點來編輯");
			nameSector.setSelected(false);
		}
		newSector.setVisible(!data.getSelected().getFilterNode().isCelebrity());
	}

	public void setData(Data data) {
		this.data = data;
		nameSector.setData(data);
		newSector.setData(data);
	}
}
