package GUI.Block;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class HyperLink extends JLabel {
    private String text,url;
    private boolean isSupported;
    public HyperLink (String text,String url){
        this.text = text;
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(isSupported);
                if (isSupported)
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setText(false);
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new java.net.URI(HyperLink.this.url));
                } catch (Exception ex) {
                }
            }
        });
    }
    private void setText(boolean b) {
        if (!b)
            setText("<html><font color=blue><u>" + text);
        else
            setText("<html><font color=red><u>" + text);
    }
}
