package GUI.Setting;

import Core.Data;

import javax.swing.*;
import java.awt.*;

public class SettingGUI extends JScrollPane {
	private final int padding = 20;
	private final NameSector nameSector;
	private final Box crawlersBox;
	private final CrawlerSector crawlerSector;
	private final NewSector newSector;
	private final DeleteSector deleteSector;
	private Data data;

	public SettingGUI() {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		setViewportView(innerPanel);

		// bone
		Box vBox = Box.createVerticalBox();
		vBox.add(Box.createVerticalStrut(padding));

		// name
		nameSector = new NameSector(padding);
		vBox.add(nameSector);

		// crawlers
		crawlersBox = Box.createHorizontalBox();
		crawlerSector = new CrawlerSector(padding);
		crawlersBox.add(Box.createHorizontalStrut(padding * 2));
		crawlersBox.add(crawlerSector);
		crawlersBox.add(Box.createHorizontalStrut(padding));
		crawlersBox.setVisible(false);
		vBox.add(crawlersBox);

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
		// root selected
		if (!data.isSelectTop()) {
			nameSector.setName(data.getSelected().getName());
			nameSector.setSelected(true);

			deleteSector.setVisible(true);
		} else {
			nameSector.setName("選取一個節點來編輯");
			nameSector.setSelected(false);

			deleteSector.setVisible(false);
		}

		crawlersBox.setVisible(data.getSelected().getFilterNode().isCelebrity());
		if (data.getSelected().getFilterNode().isCelebrity())
			crawlerSector.showCrawlers();

		newSector.setVisible(!data.getSelected().getFilterNode().isCelebrity());
	}

	public void setData(Data data) {
		this.data = data;
		nameSector.setData(data);
		crawlerSector.setData(data);
		newSector.setData(data);
		deleteSector.setData(data);
	}
}
