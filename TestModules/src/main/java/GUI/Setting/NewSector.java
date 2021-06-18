package GUI.Setting;

import Celebrities.Celebrities;
import Celebrities.Celebrity;
import Core.Data;
import GUI.Filter.FilterNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class NewSector extends JPanel {
	private Data data;

	public NewSector(int padding) {
		super();
		setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton directoryButton = new JButton("新增資料夾");
		directoryButton.addActionListener(new newDirectory());
		JButton celebrityButton = new JButton("創建人物");
		celebrityButton.addActionListener(new newCelebrity());

		Component struct = Box.createHorizontalStrut(padding * 2);
		struct.setMinimumSize(new Dimension(padding * 2,0));

		add(directoryButton);
		add(struct);
		add(celebrityButton);
	}

	public void setData(Data data) {
		this.data = data;
	}

	private class newDirectory implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String newDir = JOptionPane.showInputDialog(getRootPane(),
			                                            "輸入新資料夾名稱：",
			                                            "新增資料夾",
			                                            JOptionPane.PLAIN_MESSAGE);
			if (newDir == null || newDir.equals(""))
				return;


			if (isRepeatedDirectory(newDir))
				JOptionPane.showMessageDialog(getRootPane(), "同個層裡已有同名資料夾！", "新增失敗", JOptionPane.WARNING_MESSAGE);
			else {
				DefaultMutableTreeNode parent = data.getSelected().getNode();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new FilterNode(newDir));
				((DefaultTreeModel) data.getJTree().getModel()).insertNodeInto(newNode, parent, parent.getChildCount());

				// save 三期生
				TreeNode[] path = newNode.getPath();
				StringBuilder oldBuilder = new StringBuilder();
				StringBuilder newBuilder = new StringBuilder();
				for (int i = 1 ; i < path.length; i++) {
					if (i == path.length-1)
						newBuilder.append("/").append(newDir);
					else {
						newBuilder.append("/").append(path[i].toString());
						oldBuilder.append("/").append(path[i].toString());
					}
				}

				List<String> directories = data.getDirectories();
				if (path.length > 2) {
					String oldPath = oldBuilder.substring(1);
					String newPath = newBuilder.substring(1);
					boolean flag = false;
					for (int i = 0; i < directories.size(); i++) {
						if (!flag) {
							if (directories.get(i).matches(oldPath + ".*"))
								flag = true;
						} else {
							if (!directories.get(i).matches(oldPath + ".*")) {
								directories.add(i, newPath);
								break;
							}
						}
						if (i == directories.size() - 1)
							directories.add(newPath);
					}
				} else
					directories.add(newDir);


				try {
					data.writeData();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}

		private boolean isRepeatedDirectory(String newName) {
			DefaultMutableTreeNode parent = data.getSelected().getNode();
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

	private class newCelebrity implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String newName = JOptionPane.showInputDialog(getRootPane(),
			                                            "輸人物名稱：",
			                                            "新增人物",
			                                             JOptionPane.PLAIN_MESSAGE);

			if (newName == null || newName.equals(""))
				return;

			if (isRepeatedName(newName))
				JOptionPane.showMessageDialog(getRootPane(), "此名稱已被使用！", "新增失敗", JOptionPane.WARNING_MESSAGE);
			else {
				DefaultMutableTreeNode parent = data.getSelected().getNode();
				Celebrity celebrity = new Celebrity(newName);
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new FilterNode(celebrity));
				((DefaultTreeModel) data.getJTree().getModel()).insertNodeInto(newNode, parent, parent.getChildCount());

				// save
				TreeNode[] path = parent.getPath();
				StringBuilder builder = new StringBuilder();
				for (int i = 1 ; i < path.length; i++)
					builder.append("/").append(path[i].toString());

				Celebrities celebrities = data.getCelebrities();
				if (path.length > 2) {
					String storePath = builder.substring(1);
					celebrity.setPath(storePath);
				}
				celebrities.add(celebrity);

				try {
					data.writeData();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}

		private boolean isRepeatedName(String newName) {
			long count = data.getCelebrities()
			                 .stream()
			                 .filter(celebrity1 -> celebrity1.getName().equals(newName))
			                 .count();
			return count > 0;
		}
	}
}
