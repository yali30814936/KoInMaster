package KoInMaster.TestModules.GUI;
import javax.swing.*;
public class MainGUItest {
    public static void main(String[] args){
        //新建新的視窗
        MainGUI test =new MainGUI();
        test.setSize(1080, 756);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }
}
