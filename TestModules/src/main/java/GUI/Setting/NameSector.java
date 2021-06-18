package GUI.Setting;

import Celebrities.Celebrities;
import Celebrities.Celebrity;
import Core.Data;
import GUI.Filter.FilterNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class NameSector extends Box {
	private final int padding;
	private Data data;
	private final JLabel nameLabel;
	private final JButton nameButton;
	private final JTextField modifyName;
	private final JButton confirmButton;
	private final JButton cancelButton;
	private final Component nameGlue;

	public NameSector(int padding) {
		super(BoxLayout.LINE_AXIS);
		this.padding = padding;

		nameLabel = new JLabel("選取一個節點來編輯");
		nameButton = new JButton("修改");
		modifyName = new JTextField();
		confirmButton = new JButton("確認");
		cancelButton = new JButton("取消");
		ModifyName listener = new ModifyName();
		nameButton.addActionListener(listener);
		modifyName.addActionListener(listener);
		confirmButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		nameButton.setVisible(false);
		modifyName.setVisible(false);
		confirmButton.setVisible(false);
		cancelButton.setVisible(false);
		add(Box.createHorizontalStrut(padding));
		add(nameLabel);
		nameGlue = Box.createHorizontalGlue();
		add(nameGlue);
		add(modifyName);
		add(Box.createHorizontalStrut(padding));
		add(confirmButton);
		add(Box.createHorizontalStrut(padding));
		add(cancelButton);
		add(nameButton);
		add(Box.createHorizontalStrut(padding));
		setMaximumSize(new Dimension(4000,60));
	}

	public void setName(String name) {
		nameLabel.setText(name);
	}

	public void setSelected(boolean enabled) {
		nameButton.setVisible(enabled);
	}

	public void toggleMode(boolean mode) {
		modifyName.setVisible(mode);
		confirmButton.setVisible(mode);
		cancelButton.setVisible(mode);
		nameLabel.setVisible(!mode);
		nameButton.setVisible(!mode);
		nameGlue.setVisible(!mode);
	}

	public void setData(Data data) {
		this.data = data;
	}

	private class ModifyName implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nameButton)
				switchToModify();
			else if (e.getSource() == confirmButton || e.getSource() == modifyName)
				confirmModify();
			else
				cancel();
		}

		private void switchToModify() {
			toggleMode(true);
			modifyName.setText(nameLabel.getText());
		}

		private void confirmModify() {
			// get new name
			String newName = modifyName.getText();
			Celebrities celebrities = data.getCelebrities();
			DefaultMutableTreeNode selectedNode = data.getSelected().getNode();

			if (nameLabel.getText().equals(newName) || newName.equals("")) {
				cancel();
				return;
			}

			// is directory
			if (!data.getSelected().getFilterNode().isCelebrity()) {
				if (isRepeatedDirectory(newName)) {
					JOptionPane.showMessageDialog(getRootPane(), "同個層裡已有同名資料夾！", "更名失敗", JOptionPane.WARNING_MESSAGE);
				} else {
					nameLabel.setText(newName);
					TreeNode[] path = selectedNode.getPath();

					// save
					StringBuilder oldBuilder = new StringBuilder();
					StringBuilder newBuilder = new StringBuilder();
					for (int i = 1 ; i < path.length; i++) {
						oldBuilder.append("/").append(path[i].toString());
						if (i == path.length-1)
							newBuilder.append("/").append(newName);
						else
							newBuilder.append("/").append(path[i].toString());
					}
					String oldPath = oldBuilder.substring(1);
					String newPath = newBuilder.substring(1);
					List<String> directories = data.getDirectories();
					for (int i = 0 ; i < directories.size(); i++)
						directories.set(i, directories.get(i).replaceAll("^" + oldPath, newPath));
					data.setDirectories(directories);

					for (Celebrity celebrity:celebrities)
						celebrity.setPath(celebrity.getPath().replaceAll("^" + oldPath, newPath));

					try {
						data.writeData();
					} catch (IOException e) {
						e.printStackTrace();
					}

					((FilterNode) selectedNode.getUserObject()).setName(newName);
					data.getJTree().getModel().valueForPathChanged(new TreePath(path), selectedNode.getUserObject());
				}
			}
			// is celebrity
			else {
				if (isRepeatedName(newName)) {
					JOptionPane.showMessageDialog(getRootPane(), "此名稱已被使用！", "更名失敗", JOptionPane.WARNING_MESSAGE);
				} else {
					data.getSelected().getCelebrity().setName(newName);
					((FilterNode) selectedNode.getUserObject()).reload();

					data.getJTree().getModel().valueForPathChanged(new TreePath(selectedNode.getPath()), selectedNode.getUserObject());

					selectedNode.getPath();

					nameLabel.setText(newName);
					try {
						data.writeData();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
			}
			cancel();
		}

		private void cancel() {
			toggleMode(false);
		}

		private boolean isRepeatedName(String newName) {
			long count = data.getCelebrities()
			                 .stream()
			                 .filter(celebrity1 -> celebrity1.getName().equals(newName))
			                 .count();
			return count > 0;
		}

		private boolean isRepeatedDirectory(String newName) {
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) data.getSelected().getNode().getParent();
			Enumeration<TreeNode> enumeration = parent.children();
			int count = 0;
			while (enumeration.hasMoreElements()) {
				FilterNode node = (FilterNode) ((DefaultMutableTreeNode) enumeration.nextElement()).getUserObject();
				if (!node.isCelebrity() && node.getName().equals(newName))
					count++;
			}
			return count > 0;
		}
	}
}
