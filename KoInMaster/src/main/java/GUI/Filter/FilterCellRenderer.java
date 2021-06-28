package GUI.Filter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class FilterCellRenderer extends DefaultTreeCellRenderer {
	private final Font enabledFont = new Font(Font.SERIF, Font.BOLD, 17);
	private final Font disabledFont = new Font(Font.SERIF, Font.PLAIN, 18);

	public Component getTreeCellRendererComponent(JTree tree,
	                                              Object value,
	                                              boolean sel,
	                                              boolean expanded,
	                                              boolean leaf,
	                                              int row,
	                                              boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		// enabled
		if (isEnabled(value)) {
			setFont(enabledFont);
			setForeground(Color.BLACK);
		} else {
			setFont(disabledFont);
			setForeground(Color.GRAY);
		}

		// is a leaf folder
		if (leaf && !isCelebrity(value))
			setIcon(getDefaultClosedIcon());

		return this;
	}

	protected boolean isEnabled(Object obj) {
		return ((FilterNode)((DefaultMutableTreeNode) obj).getUserObject()).isEnabled();
	}

	protected boolean isCelebrity(Object obj) {
		return ((FilterNode)((DefaultMutableTreeNode) obj).getUserObject()).isCelebrity();
	}
}
