package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Celebrity;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * GUI of the filter, using JTree structure
 */
public class FilterGUI extends JScrollPane {
	private final JTree jTree;
	private final DefaultMutableTreeNode top;

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

	// reload the tree by giving the celebrity list
	public void loadTree(List<Celebrity> celebrities) {
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

	/**
	 * Find children nodes in first layer with specific name.
	 * @param node parent DefaultMutableTreeNode object to search
	 * @param key name of the target child.
	 * @return target DefaultMutableTreeNode child, null if not found.
	 */
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

			setNodeEnabled(node, !((FilterNode) node.getUserObject()).isEnabled());

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

			repaint();
		}
	}

	// set all children's enabled
	private void setNodeEnabled(DefaultMutableTreeNode node, boolean enabled) {
		Enumeration<TreeNode> enumeration = node.children();
		while (enumeration.hasMoreElements())
			setNodeEnabled((DefaultMutableTreeNode) enumeration.nextElement(), enabled);
		((FilterNode) node.getUserObject()).setEnabled(enabled);
	}

	// return a list of selected celebrities
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		checkLeaves(top, list);
		return list;
	}

	// check every leaves recursively
	private void checkLeaves(DefaultMutableTreeNode node, List<String> list) {
		if (node.getChildCount() == 0 && ((FilterNode) node.getUserObject()).isEnabled())
			list.add(((FilterNode) node.getUserObject()).getName());
		else {
			Enumeration<TreeNode> enumeration = node.children();
			while (enumeration.hasMoreElements())
				checkLeaves((DefaultMutableTreeNode) enumeration.nextElement(), list);
		}
	}
}
