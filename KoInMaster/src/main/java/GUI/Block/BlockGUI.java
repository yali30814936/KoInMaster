package GUI.Block;

import Core.Data;
import Posts.PostList;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

public class BlockGUI extends JPanel {
	private Data data;
	private BlockWorker worker;
	private final Box vBox;
	private final JScrollPane scrollPane;
	private boolean coolDown = false;

	public BlockGUI(JScrollPane scrollPane) {
		super();
		this.scrollPane = scrollPane;
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustBlock());
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		vBox = Box.createVerticalBox();
		add(vBox);
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void refresh() {
		PostList list = data.getContainer().getFilteredList();

		vBox.removeAll();
		vBox.revalidate();

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

	private static class RestPos extends SwingWorker<Object,Object> {
		private final JScrollPane scrollPane;

		public RestPos(JScrollPane scrollBar) {
			this.scrollPane = scrollBar;
		}

		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(100);
			scrollPane.getVerticalScrollBar().setValue(0);
			return null;
		}
	}

	private class AdjustBlock implements AdjustmentListener {
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (coolDown || worker == null) return;
			if (scrollPane.getVerticalScrollBar().getMaximum() - e.getValue() <= 20000)
				worker.loadMore();
			coolDown = true;
			CoolDown cool = new CoolDown();
			cool.execute();
		}
	}

	private class CoolDown extends SwingWorker<Object, Object> {
		@Override
		protected Object doInBackground() throws Exception {
			Thread.sleep(3000);
			coolDown = false;
			return null;
		}
	}
}
