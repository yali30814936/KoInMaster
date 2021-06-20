package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.io.IOException;

public class BlockGUI extends JScrollPane {
	private Data data;
	private JPanel panel;

	public BlockGUI() {
		super();
		getVerticalScrollBar().setUnitIncrement(20);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		if (panel != null)
			remove(panel);

		try {
			panel = new BlockList(list);
			setViewportView(panel);
			getVerticalScrollBar().setValue(0);
			RestPos reset = new RestPos(getVerticalScrollBar());
			reset.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class RestPos extends SwingWorker<Object,Object> {
		private JScrollBar scrollBar;
		public RestPos(JScrollBar scrollBar) {
			this.scrollBar = scrollBar;
		}
		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(100);
			return null;
		}

		@Override
		protected void done() {
			scrollBar.setValue(0);
		}
	}
}
