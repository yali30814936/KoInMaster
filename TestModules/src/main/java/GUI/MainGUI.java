package GUI;

import Celebrities.Celebrity;
import Core.Data;
import GUI.Block.BlockGUI;
import GUI.Filter.FilterGUI;
import GUI.Setting.SettingGUI;
import GUI.Type.TypeRefreshGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import static javax.swing.SpringLayout.*;

public class MainGUI extends JFrame {
	private final JButton toggleSettingButton;
	private final FilterGUI filterGUI;
	private final SettingGUI settingGUI;
	private final TypeRefreshGUI typeRefreshGUI;
	private final BlockGUI blockGUI;
	private Data data;

	public MainGUI() {
		super("KoInMaster");
		SpringLayout springLayout = new SpringLayout();
		Container contentPane = getContentPane();
		contentPane.setLayout(springLayout);

		// filter
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		// setting button
		toggleSettingButton = new JButton("模組設定");
		toggleSettingButton.addActionListener(new toggleSetting());
		// filterGUI
		filterGUI = new FilterGUI();
		Box hBox1 = Box.createHorizontalBox();
		Box hBox2 = Box.createHorizontalBox();
		// fit in
		hBox1.add(toggleSettingButton);
		hBox2.add(filterGUI);
		Box vBox = Box.createVerticalBox();
		// combine
		vBox.add(hBox1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox2);
		filterPanel.add(vBox);

		// setting filter panel layout.
		contentPane.add(filterPanel);
		springLayout.putConstraint(NORTH, filterPanel, 10, NORTH, contentPane);
		springLayout.putConstraint(SOUTH, filterPanel, -10, SOUTH, contentPane);
		springLayout.putConstraint(WEST, filterPanel, 10, WEST, contentPane);
		springLayout.putConstraint(EAST, filterPanel, -300, HORIZONTAL_CENTER, contentPane);

		// setting GUI
		settingGUI = new SettingGUI();
		settingGUI.setVisible(false);
		filterGUI.addSelectEventListener(settingGUI);
		contentPane.add(settingGUI);
		springLayout.putConstraint(NORTH, settingGUI, 10, NORTH, contentPane);
		springLayout.putConstraint(SOUTH, settingGUI, -10, SOUTH, contentPane);
		springLayout.putConstraint(EAST, settingGUI, -10, EAST, contentPane);
		springLayout.putConstraint(WEST, settingGUI, 10, EAST, filterPanel);

		// type-refresh GUI
		typeRefreshGUI = new TypeRefreshGUI();
		contentPane.add(typeRefreshGUI);
		springLayout.putConstraint(NORTH, typeRefreshGUI, 10, NORTH, contentPane);
		springLayout.putConstraint(EAST, typeRefreshGUI, -10, EAST, contentPane);
		springLayout.putConstraint(WEST, typeRefreshGUI, 10, EAST, filterPanel);

		// Block GUI
		blockGUI = new BlockGUI();
		blockGUI.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(blockGUI);
		springLayout.putConstraint(NORTH, blockGUI, 3, SOUTH, typeRefreshGUI);
		springLayout.putConstraint(EAST, blockGUI, -10, EAST, contentPane);
		springLayout.putConstraint(SOUTH, blockGUI, 0, SOUTH, contentPane);
		springLayout.putConstraint(WEST, blockGUI, 10, EAST, filterPanel);
	}

	/**
	 * Call FilterGUI to reload the structure
	 */
	public void loadFilter() {
		filterGUI.loadTree();
	}

	public void setFilterEnabled(boolean enabled) {
		toggleSettingButton.setEnabled(enabled);
	}

	public void setRefreshEnabled(boolean enabled) {
		typeRefreshGUI.setEnabled(enabled);
	}

	public void refreshBlock() {
		data.getContainer().setNameWhiteList(data.getCelebrities()
		                                         .stream()
		                                         .filter(Celebrity::isEnabled)
		                                         .map(Celebrity::getName)
		                                         .collect(Collectors.toList()));
		data.getContainer().setTypeWhiteList(typeRefreshGUI.getEnabledTypes());
		blockGUI.refresh();
	}

	public void savePosts() {
		data.writePosts();
	}

	public void setData(Data data) {
		this.data = data;
		filterGUI.setData(data);
		settingGUI.setData(data);
		typeRefreshGUI.setData(data);
		blockGUI.setData(data);
	}

	private class toggleSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (settingGUI.isVisible()) {
				settingGUI.setVisible(false);
				toggleSettingButton.setText("模組設定");
			} else {
				settingGUI.setVisible(true);
				toggleSettingButton.setText("關閉面板");
			}
		}
	}
}
