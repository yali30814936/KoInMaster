package Core;

import Celebrities.Celebrities;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Data {
	private List<String> directories;
	private Celebrities celebrities;
	private Selected selected;
	private JTree jTree;
	private DefaultMutableTreeNode top;

	public Celebrities getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(Celebrities celebrities) {
		this.celebrities = celebrities;
	}

	public Selected getSelected() {
		return selected;
	}

	public void setSelected(Selected selected) {
		this.selected = selected;
	}

	public List<String> getDirectories() {
		return directories;
	}

	public void setDirectories(List<String> directories) {
		this.directories = directories;
	}

	public DefaultMutableTreeNode getTop() {
		return top;
	}

	public void setTop(DefaultMutableTreeNode top) {
		this.top = top;
	}

	public boolean isSelectTop() {
		return top == selected.getNode();
	}

	public void readData() throws GeneralSecurityException, IOException, URISyntaxException {
		celebrities = CelebritiesReadWrite.read();
		directories = FiltersDirectoriesReadWrite.read();
	}

	public void writeData() throws IOException {
		CelebritiesReadWrite.write(celebrities);
		FiltersDirectoriesReadWrite.write(directories);
	}

	public JTree getJTree() {
		return jTree;
	}

	public void setJTree(JTree jTree) {
		this.jTree = jTree;
	}
}
