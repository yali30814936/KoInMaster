package Core;

import Celebrities.Celebrities;
import Posts.PostListContainer;
import Posts.PostListReadWrite;
import Posts.TYPE;
import Posts.TypeReadWrite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class Data {
	private List<String> directories;
	private Celebrities celebrities;
	private PostListContainer container;
	private Selected selected;
	private JTree jTree;
	private DefaultMutableTreeNode top;
	private List<TYPE> types;

	public Data() {}

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
		readTypes();
	}

	public void writeData() {
		CelebritiesReadWrite.write(celebrities);
		FiltersDirectoriesReadWrite.write(directories);
	}

	public JTree getJTree() {
		return jTree;
	}

	public void setJTree(JTree jTree) {
		this.jTree = jTree;
	}

	public PostListContainer getContainer() {
		return container;
	}

	public void setContainer(PostListContainer container) {
		this.container = container;
	}

	public void readPosts() {
		try {
			container = new PostListContainer(PostListReadWrite.read());
		} catch (IOException | GeneralSecurityException | URISyntaxException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void writePosts() {
		PostListReadWrite.write(container.getOriginalList());
	}

	public List<String> getEnabledNames() {
		return celebrities.stream()
		                  .filter(celebrity -> celebrity.isEnabled())
		                  .map(celebrity -> celebrity.getName())
		                  .collect(Collectors.toList());
	}

	public List<TYPE> getTypes() {
		return types;
	}

	public void setTypes(List<TYPE> types) {
		this.types = types;
	}

	public void writeTypes() {
		TypeReadWrite.write(types);
	}

	public void readTypes() {
		try {
			types = TypeReadWrite.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
