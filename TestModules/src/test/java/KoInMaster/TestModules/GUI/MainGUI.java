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
    private final JPanel filterPanel;
    private final JPanel rightPanel;
    private final JPanel filterButton;
    private final JButton systemSetting;
    private final JButton reload;
    private final JButton add;
    private final JButton allSelect;
    private final JButton printAll;
    private final JButton modSetting;
    private DefaultListModel listModel;
    private ArrayList<DefaultListModel> listModels;
    private JList list;
    private JList list2;
    private TextField addText;
    private TextField addText2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private ArrayList<String> buffer;
    public MainGUI(){
        super("標題");
        setLayout(new GridLayout(1,2));
        leftPanel=new JPanel(new GridLayout(2, 1));
        rightPanel=new JPanel();
        filterPanel=new JPanel();
        filterButton=new JPanel(new GridLayout(1,3));
        listModels=new ArrayList<>();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list2 = new JList(listModel);
        addText=new TextField(10);
        addText2=new TextField(10);
        buffer=new ArrayList<>();
        printAll=new JButton("印出");
        add=new JButton("新增");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addText.getText().trim().equals(""))
                    return;
                addItem(addText.getText().trim(),addText2.getText().trim());
            }
        });
        printAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<buffer.size();j++){
                    System.out.printf("%s ",buffer.get(j));
                }
                System.out.println();
            }
        });
        allSelect=new JButton("分類全選");
        allSelect.addActionListener(new ActionListener() {
            int flag=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel temp=(DefaultListModel)list2.getModel();
                for(int j=0;j<temp.size();j++){
                    for(int k=0;k<buffer.size();k++){
                        if(buffer.get(k).equals(temp.get(j))){
                            flag=0;
                        }
                    }
                    if(flag==1){
                        buffer.add(String.valueOf(temp.get(j)));
                    }
                    flag=1;
                }
            }
        });
        list.setFixedCellWidth(100);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    list2.setModel(listModels.get(index));
                }
            }
        });
        list2.addMouseListener(new MouseAdapter() {
            DefaultListModel temp;
            int flag=1;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int indx = list2.locationToIndex(e.getPoint());
                    temp=(DefaultListModel)list2.getModel();
                    for(int k=0;k<buffer.size();k++){
                        if(buffer.get(k).equals(temp.get(indx))){
                            flag=0;
                        }
                    }
                    if(flag==1){
                        buffer.add(String.valueOf(temp.get(indx)));
                    }
                    flag=1;
                }
                }
        });
        scrollPane=new JScrollPane(list);
        scrollPane2=new JScrollPane(list2);
        settingPanel=new JPanel(new GridLayout(2, 2,200,300));
        systemSetting=new JButton("系統設定");
        modSetting=new JButton("模組設定");
        reload=new JButton("重新整理");
        settingPanel.add(systemSetting);
        settingPanel.add(reload);
        settingPanel.add(modSetting);
        filterPanel.add(scrollPane);
        filterPanel.add(scrollPane2);
        filterPanel.add(addText);
        filterPanel.add(addText2);
        filterButton.add(add);
        filterButton.add(allSelect);
        filterButton.add(printAll);
        filterPanel.add(filterButton);
        leftPanel.add(settingPanel);
        leftPanel.add(filterPanel);
        add(leftPanel);
        add(rightPanel);
    }

    public void addItem(String str,String str2) {
        int i;
        for (i = 0; i < listModel.size(); i++) {
            if (listModel.get(i).equals(str)) {
                listModels.get(i).addElement(str2);
                list2.setModel(listModels.get(i));
                return;
            }
        }
        listModel.addElement(str);
        listModels.add(new DefaultListModel());
        listModels.get(i).addElement(str2);
        list2.setModel(listModels.get(i));
    }
}
