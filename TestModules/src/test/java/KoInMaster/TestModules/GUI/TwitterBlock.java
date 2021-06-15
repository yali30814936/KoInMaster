package KoInMaster.TestModules.GUI;
import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.TYPE;
import KoInMaster.TestModules.Posts.TwitterPost;
import twitter4j.MediaEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class TwitterBlock extends JPanel{
    private JLabel Title;
    private JLabel Date;
    private JLabel Text;
    private JLabel Mediatmp;
    private JPanel MediaPanel;
    public TwitterBlock(Post post) throws IOException {
        super(new GridBagLayout());
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.weightx=1;
        gridBagConstraints.weighty=1;
        gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
        if(post.getType()==TYPE.NONE) {
            Title = new JLabel(String.format("%s在%s平台發布了貼文", post.getName(), post.getPlatform().toString(), post.getType()));
        }
        else if(post.getType()==TYPE.RT){
            Title = new JLabel(String.format("%s在%s平台轉推了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()));
        }
        else  if(post.getType()==TYPE.REPLY){
            Title = new JLabel(String.format("%s在%s平台回復了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()));
        }
        else if(post.getType()==TYPE.QUOTED){
            Title = new JLabel(String.format("%s在%s平台引用了%s的貼文", post.getName(), post.getPlatform().toString(), ((TwitterPost)post).getUser()));
        }
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=3;
        add(Title,gridBagConstraints);
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.weightx=3;
        SimpleDateFormat ft = new SimpleDateFormat("MM月dd日 HH:mm");
        Date=new JLabel(String.format(ft.format(post.getPublishedTime())));
        add(Date,gridBagConstraints);
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.gridheight=2;
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
        add(Text,gridBagConstraints);
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        if(post.getMedia().size()!=0) {
            MediaPanel = new JPanel(new GridLayout());
            String url = post.getMedia().get(0);
            URL ur = new URL(url);
            Image image = ImageIO.read(ur);
            Mediatmp = new JLabel(new ImageIcon(image));
            MediaPanel.add(Mediatmp);
            add(MediaPanel, gridBagConstraints);
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
            add(MediaPanel, gridBagConstraints);
        }
    }
}
