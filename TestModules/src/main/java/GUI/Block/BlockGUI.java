package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlockGUI extends JScrollPane {
	private Data data;
	private final JPanel panel;
	private BlockWorker worker;

	public BlockGUI() {
		super();
		getVerticalScrollBar().setUnitIncrement(100);
		getHorizontalScrollBar().setUnitIncrement(40);
		panel = new JPanel();
		setViewportView(panel);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		panel.removeAll();

		try {
			if (worker != null)
				worker.terminate();
			worker = new BlockWorker(list, panel);
			worker.execute();
			RestPos reset;
			reset = new RestPos(this);
			reset.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class RestPos extends SwingWorker<Object,Object> {
		private final JScrollPane scrollPane;
		private Component container;

		public RestPos(JScrollPane scrollBar) {
			this.scrollPane = scrollBar;
		}

		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(100);
			return null;
		}

		@Override
		protected void done() {
			scrollPane.getVerticalScrollBar().setValue(0);
			if (container != null) {
				int left = (scrollPane.getHorizontalScrollBar().getMaximum() - (int) scrollPane.getSize().getWidth()) / 2 - 10;
				scrollPane.getHorizontalScrollBar().setValue(left);
			}
		}
	}
}
