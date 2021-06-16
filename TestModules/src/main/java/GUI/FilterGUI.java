package GUI;

import Celebrities.Celebrities;
import Celebrities.Celebrity;
import Core.CelebritiesReadWrite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;

/**
 * GUI of the filter, using JTree structure
 */
public class FilterGUI extends JScrollPane {
	private final JTree jTree;
	private final DefaultMutableTreeNode top;
	private Celebrities celebrities;

	// constructor
	public FilterGUI() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		top = new DefaultMutableTreeNode(new FilterNode("選擇要顯示的模組"));
		jTree = new JTree(top);
		jTree.addMouseListener(new nodeSelected());
		jTree.setToggleClickCount(0);
		setViewportView(jTree);
	}

	/**
	 * Reload the tree by giving the celebrity list (Celebrities)
	 * @param celebrities Celebrities data.
	 */
	public void loadTree(Celebrities celebrities) {
		this.celebrities = celebrities;
		String[] buffer;
		DefaultMutableTreeNode prev, leaf;

		// initialize
		top.removeAllChildren();

		for (Celebrity cel:celebrities) {
			leaf = new DefaultMutableTreeNode(new FilterNode(cel));
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

		checkNodeEnabled(top);
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

	/**
	 * Handle mouse click event in. Triggered if there is a double click the JTree.
	 * Toggle the selected node's enabled state. This also influence its ancestors and descendants.
	 */
	private class nodeSelected extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent ev) {
			if (ev.getClickCount() != 2) return;
			if (jTree.getLastSelectedPathComponent() == null) return;

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
			DefaultMutableTreeNode parent;
			Enumeration<TreeNode> enumeration;
			boolean flag;

			if (node == null)
				return;

			setNodeEnabled(node, !((FilterNode) node.getUserObject()).isEnabled());

			parent = (DefaultMutableTreeNode) node.getParent();
			while (parent != null) {
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

			// save changes
			try {
				CelebritiesReadWrite.write(celebrities);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Set all nodes in the subtree to the target enabled state.
	 * @param node Parent node of the subtree.
	 * @param enabled The enabled state to set.
	 */
	private void setNodeEnabled(DefaultMutableTreeNode node, boolean enabled) {
		Enumeration<TreeNode> enumeration = node.children();
		while (enumeration.hasMoreElements())
			setNodeEnabled((DefaultMutableTreeNode) enumeration.nextElement(), enabled);
		((FilterNode) node.getUserObject()).setEnabled(enabled);
	}

	/**
	 * Check the tree recursively and set its own enabled flag to the proper one.
	 * If there is any descendant that is enabled, then this node is also enabled.
	 * Leaf node will not have any change.
	 * @param node Parent node of the subtree. Put root as initial call.
	 * @return Whether there is any enabled node in the subtree. Return true if there are.
	 */
	private boolean checkNodeEnabled(DefaultMutableTreeNode node) {
		Enumeration<TreeNode> enumeration = node.children();
		boolean flag = ((FilterNode) node.getUserObject()).isEnabled();
		while (enumeration.hasMoreElements()) {
			if (checkNodeEnabled((DefaultMutableTreeNode) enumeration.nextElement()))
				flag = true;
		}
		((FilterNode) node.getUserObject()).setEnabled(flag);
		return flag;
	}
}
