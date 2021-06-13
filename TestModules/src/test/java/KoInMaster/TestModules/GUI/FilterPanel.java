package KoInMaster.TestModules.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FilterPanel extends JPanel {
    private DefaultListModel listModel;
    private ArrayList<DefaultListModel> listModels;
    private JList list;
    private JList list2;
    private TextField addText;
    private TextField addText2;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private ArrayList<String> buffer;
    private JPanel upPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private final JButton add;
    private final JButton allSelect;
    private TextField celebrityList;
    public  FilterPanel(){
        listModels=new ArrayList<>();
        listModel=new DefaultListModel();
        buffer=new ArrayList<>();
        celebrityList=new TextField(25);
        celebrityList.setEditable(false);
        upPanel=new JPanel();
        centerPanel=new JPanel();
        eastPanel=new JPanel();
        upPanel.setLayout(new GridLayout(1,3));
        centerPanel.setLayout(new GridLayout(1,2));
        eastPanel.setLayout(new GridLayout(2,1));
        list = new JList();
        list2 = new JList(listModel);
        scrollPane=new JScrollPane(list);
        scrollPane2=new JScrollPane(list2);
        scrollPane3=new JScrollPane(celebrityList);
        addText=new TextField(10);
        addText2=new TextField(10);
        listModels=new ArrayList<>();
        listModel = new DefaultListModel();
        add=new JButton("新增");
        allSelect=new JButton("分類全選");
        list.setModel(listModel);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addText.getText().trim().equals(""))
                    return;
                addItem(addText.getText().trim(),addText2.getText().trim());
            }
        });
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
                celebrityList.setText(buffer.toString());
            }
        });
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
                celebrityList.setText(buffer.toString());
            }
        });
        upPanel.add(addText);
        upPanel.add(addText2);
        upPanel.add(add);
        centerPanel.add(scrollPane);
        centerPanel.add(scrollPane2);
        eastPanel.add(allSelect);
        eastPanel.add(scrollPane3);
        add(upPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(eastPanel,BorderLayout.EAST);
    }
    public void addItem(String str,String str2) {
        int i,flag=1;
        for (i = 0; i < listModel.size(); i++) {
            if (listModel.get(i).equals(str)) {
                for(int j=0;j<listModels.get(i).size();j++){
                    if(listModels.get(i).get(j).equals(str2)){
                        flag=0;
                    }
                }
                if(flag==1){
                    listModels.get(i).addElement(str2);
                }
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
