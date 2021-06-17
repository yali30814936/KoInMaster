package GUI.Setting;

import Celebrities.Celebrities;
import Celebrities.Celebrity;
import GUI.Filter.FilterGUI;
import GUI.Filter.FilterNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class SettingGUI extends JScrollPane {
	private final int padding = 20;
	private FilterGUI filterGUI;
	private Celebrities celebrities;
	private Celebrity celebrity;
	private DefaultMutableTreeNode selectedNode;
	private final JPanel innerPanel;
	private final Box vBox;
	private final NameSector nameSector;

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

		vBox.add(Box.createVerticalGlue());
		vBox.add(Box.createVerticalStrut(padding));
		innerPanel.add(vBox);
	}

	public void setFilterGUI(FilterGUI filterGUI) {
		this.filterGUI = filterGUI;
		nameSector.setFilterGUI(filterGUI);
	}

	public void setCelebrities(Celebrities celebrities) {
		this.celebrities = celebrities;
		nameSector.setCelebrities(celebrities);
	}

	public void filterSelectedPerform(DefaultMutableTreeNode node) {
		selectedNode = node;
		nameSector.toggleMode(false);
		celebrity = ((FilterNode) node.getUserObject()).getCelebrity();
		nameSector.setName(((FilterNode) node.getUserObject()).getName());
		nameSector.setInitVisible();
		nameSector.setSelectedNode(selectedNode);
		nameSector.setCelebrity(celebrity);
	}
}
