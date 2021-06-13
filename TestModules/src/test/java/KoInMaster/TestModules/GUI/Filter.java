package KoInMaster.TestModules.GUI;

import javax.swing.*;
import java.awt.*;

public class Filter extends JScrollPane {
	public Filter(JComponent component) {
		super(component);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	}

	public static void main(String[] args) {
		JPanel panel = new JPanel(new GridLayout(10,1));
		Filter filter = new Filter(panel);
		panel.add(new JCheckBox("Test1"));
		panel.add(new JCheckBox("Test2"));
		panel.add(new JCheckBox("Test3"));
		panel.add(new JCheckBox("Test4"));
		panel.add(new JCheckBox("Test5"));
//		javax.swing.J
		JFrame frame = new JFrame("Filter Test");
		frame.add(filter);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setVisible(true);
	}
}
