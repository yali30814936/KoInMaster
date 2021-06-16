package GUI;

import Celebrities.Celebrity;

public class FilterNode {
	private String name;
	private Celebrity celebrity;
	private boolean enabled = false;

	/**
	 * Constructor connected to a Celebrity object.
	 * @param celebrity The Celebrity object which will be connected to.
	 */
	public FilterNode(Celebrity celebrity) {
		this.celebrity = celebrity;
		name = celebrity.getName();
		enabled = this.celebrity.isEnabled();
	}

	/**
	 * Constructor as folder
	 * @param folderName Name of the folder.
	 */
	public FilterNode(String folderName) {
		name = folderName;
	}

	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (celebrity != null)
			celebrity.setEnabled(enabled);
	}

	public String getName(){ return name; }

	// display label
	@Override
	public String toString() {
		if (enabled)
			return "<html><b>" + name + "</b></html>";
		else
			return "<html><font color='Gray'>" + name + "</font></html>";

	}
}
