package KoInMaster.TestModules.GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Filter extends JScrollPane {
	// constructor
	public Filter() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("選擇要顯示的模組");
		JTree jTree = new JTree(top);
		jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		setViewportView(jTree);

		DefaultMutableTreeNode hololive = new DefaultMutableTreeNode("Hololive");
		hololive.add(new DefaultMutableTreeNode("0 gen"));
		DefaultMutableTreeNode nijisanji = new DefaultMutableTreeNode("彩虹社");
//		nijisanji.
		top.add(hololive);
		top.add(nijisanji);
	}

	// test use main
	public static void main(String[] args) {
		Filter filter = new Filter();
		JFrame frame = new JFrame("Filter Test");
		frame.add(filter);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setVisible(true);
	}
}
