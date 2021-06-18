package GUI.Setting;

import Core.Data;
import Core.Selected;
import GUI.Filter.FilterNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSector extends JPanel {
	private Data data;
	private SettingGUI parent;

	public DeleteSector(SettingGUI parent,  int padding) {
		super();
		this.parent = parent;
		setAlignmentX(CENTER_ALIGNMENT);

		JButton deleteButton = new JButton("刪除");
		deleteButton.addActionListener(new deleteNode());
		deleteButton.setForeground(Color.getHSBColor(358, 0.71f, 0.55f));

		add(deleteButton);
	}

	public void setData(Data data) {
		this.data = data;
	}

	private class deleteNode implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// directory
			if (data.getSelected().getCelebrity() == null) {
				// has children
				int confirm;
				if (data.getSelected().getNode().getChildCount() == 0) {
					confirm = JOptionPane.showConfirmDialog(getRootPane(),
					                                            "確認刪除資料夾？",
					                                            "test",
					                                            JOptionPane.OK_CANCEL_OPTION,
					                                            JOptionPane.WARNING_MESSAGE);
					if (confirm != 0)
						return;
				} else {
					confirm = JOptionPane.showConfirmDialog(getRootPane(),
					                                        "<html><div align='center'>確認刪除資料夾？<br><font color=red>注意：資料夾的內容將被刪除</div></html>",
					                                        "test",
					                                        JOptionPane.OK_CANCEL_OPTION,
					                                        JOptionPane.WARNING_MESSAGE);
					if (confirm != 0)
						return;
				}

			}
			// celebrity
			else {
				int confirm = JOptionPane.showConfirmDialog(getRootPane(),
				                                            "確認刪除人物？",
				                                            "確認",
				                                            JOptionPane.OK_CANCEL_OPTION,
				                                            JOptionPane.WARNING_MESSAGE);
				if (confirm != 0)
					return;

				data.getCelebrities().remove(data.getSelected().getCelebrity());
				((DefaultTreeModel) data.getJTree().getModel()).removeNodeFromParent(data.getSelected().getNode());
			}

			// simulate root node selected.
			Selected selected = new Selected();
			selected.setNode(data.getTop());
			selected.setFilterNode((FilterNode) data.getTop().getUserObject());
			selected.setCelebrity(null);
			data.setSelected(selected);
			parent.filterSelectedPerform();

//			try {
//				data.writeData();
//			} catch (IOException ioException) {
//				ioException.printStackTrace();
//			}
		}
	}
}
