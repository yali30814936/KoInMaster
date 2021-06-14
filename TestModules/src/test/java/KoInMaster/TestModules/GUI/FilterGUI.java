package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Celebrity;
import KoInMaster.TestModules.Core.CelebritiesFileReader;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FilterGUI extends JScrollPane {
	private JTree jTree;
	private DefaultMutableTreeNode top;

	// constructor
	public FilterGUI() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		top = new DefaultMutableTreeNode("選擇要顯示的模組");
		jTree = new JTree(top);
		jTree.addMouseListener(new nodeSelected());
		jTree.setToggleClickCount(0);
		setViewportView(jTree);
	}

	public void loadTree(List<Celebrity> celebrities) {
		String path;
		String[] buffer;
		DefaultMutableTreeNode prev, leaf;

		for (Celebrity cel:celebrities) {
			leaf = new DefaultMutableTreeNode(new FilterNode(cel.getName()));
			buffer = cel.getPath().split("/");
			prev = top;
			for (String bf:buffer) {
				DefaultMutableTreeNode curr = find(prev, bf);

				// not found
				if (curr == null){
					curr = new DefaultMutableTreeNode(new FilterNode(bf));
					prev.add(curr);
				}
				prev = curr;
			}
			prev.add(leaf);
		}
		jTree.expandRow(0);
	}

	private DefaultMutableTreeNode find(DefaultMutableTreeNode node, String key) {
		Enumeration<TreeNode> enumeration = node.children();
		DefaultMutableTreeNode tmp;
		while (enumeration.hasMoreElements()) {
			tmp = (DefaultMutableTreeNode) enumeration.nextElement();

			// found
			if (tmp.getUserObject().toString().equals("<html><b>" + key + "</b></html>") || tmp.getUserObject().toString().equals("<html><font color='Gray'>" + key + "</font></html>"))
				return tmp;
		}
		return null;
	}

	private class nodeSelected extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent ev) {
			if (ev.getClickCount() < 2) return;
			if (jTree.getLastSelectedPathComponent() == top) return;

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
			DefaultMutableTreeNode parent;
			Enumeration<TreeNode> enumeration;
			boolean flag;

			if (node == null)
				return;

			// debug
//			System.out.println("Selected " + node.getUserObject().toString());

			FilterNode selected = (FilterNode) node.getUserObject();
			boolean state = selected.isEnabled();

			parent = (DefaultMutableTreeNode) node.getParent();
			while (parent != top) {
				flag = false;
				enumeration = parent.children();
				while (enumeration.hasMoreElements())
					if (((FilterNode)((DefaultMutableTreeNode)enumeration.nextElement()).getUserObject()).isEnabled()) {
						flag = true;
						break;
					}
				((FilterNode)parent.getUserObject()).setEnabled(flag);
				parent = (DefaultMutableTreeNode) parent.getParent();
			}

			toggleNode(node, !state);

			repaint();
		}
	}

	private void toggleNode(DefaultMutableTreeNode node, boolean enabled) {
		Enumeration<TreeNode> enumeration = node.children();
		while (enumeration.hasMoreElements())
			toggleNode((DefaultMutableTreeNode) enumeration.nextElement(), enabled);
		((FilterNode) node.getUserObject()).setEnabled(enabled);
	}

	public List<String> getList() {
		List<String> list = new ArrayList<>();
		checkLeaves(top, list);
		return list;
	}

	private void checkLeaves(DefaultMutableTreeNode node, List<String> list) {
		if (node.getChildCount() == 0 && ((FilterNode) node.getUserObject()).isEnabled())
			list.add(((FilterNode) node.getUserObject()).getName());
		else {
			Enumeration<TreeNode> enumeration = node.children();
			while (enumeration.hasMoreElements())
				checkLeaves((DefaultMutableTreeNode) enumeration.nextElement(), list);
		}
	}

	// test use main
	public static void main(String[] args) throws GeneralSecurityException, IOException, URISyntaxException {
		JLabel label = new JLabel("<html><font color=Gray>Test</font></html>");
		FilterGUI filter = new FilterGUI();
		filter.loadTree(CelebritiesFileReader.readModules());
		JFrame frame = new JFrame("Filter Test");
		frame.add(filter, BorderLayout.WEST);
		frame.add(label, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setVisible(true);
	}
}
