package GUI.Block;
import Posts.Post;
import Posts.TYPE;
import Posts.TwitterPost;
import twitter4j.MediaEntity;

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
    public TwitterBlock(Post post) throws IOException {

        super(new GridLayout(0,1));

        if(post.getType()==TYPE.NONE) {
            Title = new HyperLink(String.format("%s在%s平台發布了貼文", post.getName(), post.getPlatform().toString(), post.getType()),((TwitterPost)post).getUrl());
        }
        else if(post.getType()==TYPE.RT){
            Title = new HyperLink(String.format("%s在%s平台轉推了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()),((TwitterPost)post).getUrl());
        }
        else  if(post.getType()==TYPE.REPLY){
            Title = new HyperLink(String.format("%s在%s平台回復了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()),((TwitterPost)post).getUrl());
        }
        else if(post.getType()==TYPE.QUOTED){
            Title = new HyperLink(String.format("%s在%s平台引用了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()),((TwitterPost)post).getUrl());
        }
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(Title);

        Detail = new JButton("更多資訊");
        if(post.getMedia().size()<=1){
            Detail.setEnabled(false);
        }
        else {
            Detail.setEnabled(true);
        }
        panel.add(Detail);
        add(panel);
        Detail.addActionListener(new OpenDetail());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        Date=new JLabel(String.format(ft.format(post.getPublishedTime())));
        panel.add(Date);

        if(post.getType()==TYPE.QUOTED){
            String qu =((TwitterPost)post).getStatus().getQuotedStatus().getText();
            qu = qu.replaceAll("https://.*?$","");
            String tmp = ((TwitterPost)post).getStatus().getText();
            tmp = tmp.replaceAll("https://.*?$","");
            tmp = tmp+"\n\n<hr>"+qu;
            tmp = tmp.replaceAll("^RT ","");
            tmp = tmp.replaceAll("^@.*? ","");
            tmp = tmp.replaceAll("\n","<br>");

            tmp = "<html>"+tmp+"</html>";
            Text = new JLabel(tmp);
        }
        else {
            Text = new JLabel(post.getText());
        }
        panel.add(Text);

        mediaSize = post.getMedia().size();
        mediaList=post.getMedia();
        if(post.getMedia().size()!=0) {
            MediaPanel = new JPanel(new GridLayout());
            String url = post.getMedia().get(0);
            URL ur = new URL(url);
            Image image = ImageIO.read(ur);
            Mediatmp = new JLabel(new ImageIcon(image));
            MediaPanel.add(Mediatmp);
            panel.add(MediaPanel);
        }
        else if(post.getType()==TYPE.QUOTED){
            for(MediaEntity me:((TwitterPost)post).getStatus().getQuotedStatus().getMediaEntities()){
                post.getMedia().add(me.getMediaURL());
            }
            MediaPanel = new JPanel(new GridLayout());
            String url = post.getMedia().get(0);
            URL ur = new URL(url);
            Image image = ImageIO.read(ur);
            Mediatmp = new JLabel(new ImageIcon(image));
            MediaPanel.add(Mediatmp);
            panel.add(MediaPanel);
        }
        add(panel);
    }
    private class OpenDetail implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame moreFrame = new JFrame();
            moreFrame.setVisible(true);
            moreFrame.setLayout( new GridLayout(0,1));
            JPanel morePanel = new JPanel();
            morePanel.setLayout(new BoxLayout(morePanel,BoxLayout.Y_AXIS));
            morePanel.add(Title);
            morePanel.add(Date);
            morePanel.add(Text);
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
            morePanel.add(mediaPanel);
            JScrollPane scrollPane = new JScrollPane(morePanel);
            moreFrame.add(scrollPane);
            moreFrame.setSize(1000, 1000);
        }
    }
}
