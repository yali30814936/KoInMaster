package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlockGUI extends JPanel {
	private Data data;
	private BlockWorker worker;
	private final Box vBox;
	private final JScrollPane scrollPane;

	public BlockGUI(JScrollPane scrollPane) {
		super();
		this.scrollPane = scrollPane;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		vBox = Box.createVerticalBox();
		vBox.setMaximumSize(new Dimension(1550, Integer.MAX_VALUE));
		add(vBox);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		vBox.removeAll();

		try {
			if (worker != null)
				worker.terminate();
			worker = new BlockWorker(list, vBox);
			worker.execute();
			RestPos reset;
			reset = new RestPos(scrollPane);
			reset.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return vBox.getMinimumSize();
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
//			if (container != null) {
//				int left = (scrollPane.getHorizontalScrollBar().getMaximum() - (int) scrollPane.getSize().getWidth()) / 2 - 10;
//				scrollPane.getHorizontalScrollBar().setValue(left);
//			}
		}
	}
}
