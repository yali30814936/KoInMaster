package KoInMaster.TestModules.GUI;

public class FilterNode {
	private String name;


	private boolean enabled = false;

	// constructor
	FilterNode(String nodeName) {
		name = nodeName;
	}

	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled;}

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
