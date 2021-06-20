package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.io.IOException;

public class BlockGUI extends JScrollPane {
	private Data data;

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		removeAll();

		try {
			JPanel blockPanel = BlockList.generateBlockList(list);
			add(blockPanel);
			setViewportView(blockPanel);
			repaint();
			revalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
