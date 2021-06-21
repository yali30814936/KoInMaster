
package GUI.Block;
import Posts.Post;
import Posts.TYPE;
import Posts.TwitterPost;

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

public class TwitterBlock extends JPanel{
    private HyperLink Title;
    private JLabel Date;
    private JLabel Text;
    private JLabel Mediatmp;
    private JPanel MediaPanel;
    private JButton Detail;
    private int mediaSize;
    private List<String> mediaList;
    private JPanel panel;
    private JScrollPane scrollPane;
    public TwitterBlock(Post post) throws IOException {
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (post.getType() == TYPE.TWEET) {
            Title = new HyperLink(String.format("%s在%s發布了推文", post.getName(), post.getPlatform().toString()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.RT) {
            Title = new HyperLink(String.format("%s在%s轉推了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.REPLY) {
            Title = new HyperLink(String.format("%s在%s回復了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        } else if (post.getType() == TYPE.QUOTED) {
            Title = new HyperLink(String.format("%s在%s引用了%s的推文", post.getName(), post.getPlatform().toString(), ((TwitterPost) post).getUser()), ((TwitterPost) post).getUrl());
        }

        Box hBox1 = Box.createHorizontalBox();
        Detail = new JButton("更多資訊");
        if (post.getMedia().size() <= 1) {
            Detail.setEnabled(false);
        } else {
            Detail.setEnabled(true);
        }

        hBox1.add(Title);
        hBox1.add(Box.createHorizontalGlue());
        hBox1.add(Detail);
        add(hBox1);
        Detail.addActionListener(new OpenDetail());
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        Date = new JLabel(String.format(ft.format(post.getPublishedTime())), 2);
        //Date.setBorder(new LineBorder(Color.red));
        Box hBox2 = Box.createHorizontalBox();
        hBox2.add(Date);
        hBox2.add(Box.createHorizontalGlue());
        add(hBox2);


        if (post.getType() == TYPE.QUOTED) {
            Text = new JLabel(post.getText(),2);
        } else {
            Text = new JLabel(post.getText(), 2);
        }
        //Text.setBorder(new LineBorder(Color.red));
        Box hBox3 = Box.createHorizontalBox();
        hBox3.add(Text);
        hBox3.add(Box.createHorizontalGlue());
        add(hBox3);
        Box hBox4 = Box.createHorizontalBox();
        mediaSize = post.getMedia().size();
        mediaList = post.getMedia();
        if (post.getMedia().size() != 0) {
            MediaPanel = new JPanel(new GridLayout());
            String url = post.getMedia().get(0);
            URL ur = new URL(url);
            Image image = ImageIO.read(ur);
            int x = image.getWidth(null);
            int y = image.getHeight(null);
            image = image.getScaledInstance(x / 2, y / 2, Image.SCALE_SMOOTH);
            Mediatmp = new JLabel(new ImageIcon(image), 2);
            //Mediatmp.setBorder(new LineBorder(Color.red));
            hBox4.add(Mediatmp);
            add(hBox4);
        }
    }
        private class OpenDetail implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                HyperLink moreLink = Title;
                JLabel moreDate = Date;
                JLabel moreText = Text;
                JFrame moreFrame = new JFrame();
                moreFrame.setSize(1000, 1000);
                moreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                moreFrame.setVisible(true);
                panel = new JPanel();
                panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
                Box hBox1 = Box.createHorizontalBox();
                Box hBox2 = Box.createHorizontalBox();
                Box hBox3 = Box.createHorizontalBox();
                Box hBox4 = Box.createHorizontalBox();
                Box hBox5 = Box.createVerticalBox();
                hBox1.add(moreLink);
                hBox1.add(Box.createHorizontalGlue());
                hBox5.add(hBox1);
                hBox2.add(moreDate);
                hBox2.add(Box.createHorizontalGlue());
                hBox5.add(hBox2);
                hBox3.add(moreText);
                hBox3.add(Box.createHorizontalGlue());
                hBox5.add(hBox3);
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
                    Mediatmp = new JLabel(new ImageIcon(image));
                    mediaPanel.add(Mediatmp);
                }
                hBox4.add(mediaPanel);
                hBox5.add(hBox4);
                panel.add(hBox5);
                scrollPane = new JScrollPane(panel);
                scrollPane.getVerticalScrollBar().setUnitIncrement(20);
                scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
                moreFrame.add(scrollPane);

            }
        }
    }