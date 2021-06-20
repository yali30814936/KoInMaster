package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.io.IOException;

public class BlockGUI extends JPanel {
	private Data data;

	public BlockGUI() {
		super();
	}



	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		removeAll();

		try {
			add(new BlockList(list));
			repaint();
			revalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
