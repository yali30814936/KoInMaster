package KoInMaster.TestModules.GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class FilterGUI extends JScrollPane {
	// constructor
	public FilterGUI() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("選擇要顯示的模組");
		JTree jTree = new JTree(top);
		setViewportView(jTree);

		DefaultMutableTreeNode hololive = new DefaultMutableTreeNode("Hololive");
		hololive.add(new DefaultMutableTreeNode("0 gen"));
		DefaultMutableTreeNode nijisanji = new DefaultMutableTreeNode("彩虹社");
		top.add(hololive);
		top.add(nijisanji);
	}

	// test use main
	public static void main(String[] args) {
		FilterGUI filter = new FilterGUI();
		JFrame frame = new JFrame("Filter Test");
		frame.add(filter);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setVisible(true);
	}
}
