package Core;

import Celebrities.Celebrity;
import GUI.Filter.FilterNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class Selected {
	private String name;
	private DefaultMutableTreeNode node;
	private FilterNode filterNode;
	private Celebrity celebrity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DefaultMutableTreeNode getNode() {
		return node;
	}

	public void setNode(DefaultMutableTreeNode node) {
		this.node = node;
	}

	public FilterNode getFilterNode() {
		return filterNode;
	}

	public void setFilterNode(FilterNode filterNode) {
		this.filterNode = filterNode;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}
}
