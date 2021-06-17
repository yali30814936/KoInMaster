package GUI.Filter;

import Celebrities.Celebrity;

public class FilterNode {
	private String name;
	private Celebrity celebrity = null;
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

	public void setName(String name) {
		this.name = name;
	}
	public String getName(){ return name; }

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void reload() {
		if (celebrity != null)
			name = celebrity.getName();
	}

	public boolean isCelebrity() {
		return celebrity != null;
	}

	// display label
	@Override
	public String toString() {
		return name;
	}
}
