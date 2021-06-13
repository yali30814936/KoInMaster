package KoInMaster.TestModules.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

public class MainGUI extends JFrame {
    private final JPanel leftPanel;
    private final JPanel settingPanel;
    private final JPanel rightPanel;
    private final JButton systemSetting;
    private final JButton reload;
    private final JButton modSetting;
    private final FilterPanel filterPanel;

    public MainGUI(){
        super("標題");
        setLayout(new GridLayout(1,2));
        leftPanel=new JPanel(new GridLayout(2, 1));
        rightPanel=new JPanel();
        filterPanel=new FilterPanel();
        settingPanel=new JPanel(new GridLayout(2, 2,200,300));
        systemSetting=new JButton("系統設定");
        modSetting=new JButton("模組設定");
        reload=new JButton("重新整理");
        settingPanel.add(systemSetting);
        settingPanel.add(reload);
        settingPanel.add(modSetting);
        leftPanel.add(settingPanel);
        leftPanel.add(filterPanel);
        add(leftPanel);
        add(rightPanel);
    }


}
