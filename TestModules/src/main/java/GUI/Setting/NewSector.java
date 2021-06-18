package GUI.Setting;

import Core.Data;
import GUI.Filter.FilterNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class NewSector extends JPanel {
	private final int padding;
	private final SettingGUI parent;
	private Data data;

	public NewSector(SettingGUI parent, int padding) {
		super();
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.parent = parent;
		this.padding = padding;

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
			if (isRepeatedDirectory(newDir))
				JOptionPane.showMessageDialog(getRootPane(), "同個層裡已有同名資料夾！", "新增失敗", JOptionPane.WARNING_MESSAGE);
			else {
				System.out.println(newDir);
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

			if (isRepeatedName(newName))
				JOptionPane.showMessageDialog(getRootPane(), "此名稱已被使用！", "新增失敗", JOptionPane.WARNING_MESSAGE);
			else {
				System.out.println("Clear:" + newName);
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
