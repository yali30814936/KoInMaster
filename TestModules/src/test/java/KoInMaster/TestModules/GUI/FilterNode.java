package KoInMaster.TestModules.GUI;

public class FilterNode {
	String name;


	boolean enabled = false;

	// constructor
	FilterNode(String nodeName) {
		name = nodeName;
	}

	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled;}

	// display label
	@Override
	public String toString() {
		if (enabled)
			return name;
		else
			return "<font color='gray'>" + name + "</font>";

	}
}
