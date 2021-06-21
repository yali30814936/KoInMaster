package GUI.Block;

import Posts.FbPost;
import Posts.Post;
import Posts.TYPE;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class FacebookBlock extends JPanel {
    private JButton Detail;
    private FbPost fp;
    public FacebookBlock(Post post) throws IOException {

        this.fp=(FbPost)post;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);

        Box vBox = Box.createVerticalBox();

        Box hBox = Box.createHorizontalBox();
        String topTitle;
        if(post.getType()== TYPE.POST) {
             topTitle = post.getName() + " 在 Facebook　發布了一則貼文";
        }
        else{
             topTitle = post.getName() + " 在 Facebook　分享了一則貼文";
        }
        Detail = new JButton("更多資訊");
        if (post.getMedia().size() <= 1) {
            Detail.setEnabled(false);
        } else {
            Detail.setEnabled(true);
        }

        hBox.add(new HyperLink(topTitle, post.getUrl()));
        hBox.add(Box.createHorizontalGlue()); // left align
        hBox.add(Detail);
        vBox.add(hBox);
        Detail.addActionListener(new FacebookBlock.OpenDetail());

        hBox = Box.createHorizontalBox();
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        hBox.add(new BlockTextField(ft.format(post.getPublishedTime())));
        hBox.add(Box.createHorizontalGlue()); // left align
        vBox.add(hBox);

        JLabel text = new JLabel(post.getText(), 2);
        hBox = Box.createHorizontalBox();
        hBox.add(text);
        hBox.add(Box.createHorizontalGlue());
        vBox.add(hBox);

        hBox = Box.createHorizontalBox();
        if (post.getMedia().size() != 0) {
            String url = post.getMedia().get(0);
            URL ur = new URL(url);
            Image image = ImageIO.read(ur);
            int x = image.getWidth(null);
            int y = image.getHeight(null);
            image = image.getScaledInstance(x / 2, y / 2, Image.SCALE_SMOOTH);
            JLabel Mediatmp = new JLabel(new ImageIcon(image), 2);
            hBox.add(Mediatmp);
            vBox.add(hBox);
        }



        add(vBox);
    }

    private class OpenDetail implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame moreFrame = new JFrame();
            moreFrame.setSize(1000, 1000);
            moreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            moreFrame.setVisible(true);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
            Box hBox1 = Box.createHorizontalBox();
            Box hBox2 = Box.createHorizontalBox();
            Box hBox3 = Box.createHorizontalBox();
            Box hBox4 = Box.createHorizontalBox();
            Box hBox5 = Box.createVerticalBox();
            String topTitle;
            if(fp.getType()== TYPE.POST) {
                topTitle = fp.getName() + " 在 Facebook　發布了一則貼文";
            }
            else{
                topTitle = fp.getName() + " 在 Facebook　分享了一則貼文";
            }
            hBox1.add(new HyperLink(topTitle, fp.getUrl()));
            hBox1.add(Box.createHorizontalGlue());
            hBox5.add(hBox1);
            SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
            hBox2.add(new BlockTextField(ft.format(fp.getPublishedTime())));
            hBox2.add(Box.createHorizontalGlue());
            hBox5.add(hBox2);
            JLabel text = new JLabel(fp.getText(), 2);
            hBox3.add(text);
            hBox3.add(Box.createHorizontalGlue());
            hBox5.add(hBox3);
            int mediaSize = fp.getMedia().size();
            List<String> mediaList = fp.getMedia();
            JPanel mediaPanel = new JPanel(new GridLayout(mediaSize, 1));
            for(String url:mediaList){
                URL ur = null;
                try {
                    ur = new URL(url);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Image image = null;
                try {
                    image = ImageIO.read(ur);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JLabel Mediatmp = new JLabel(new ImageIcon(image));
                mediaPanel.add(Mediatmp);
            }
            hBox4.add(mediaPanel);
            hBox5.add(hBox4);
            panel.add(hBox5);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            moreFrame.add(scrollPane);

        }
    }

}
