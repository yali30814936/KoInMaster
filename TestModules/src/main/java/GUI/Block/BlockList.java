package GUI.Block;


import Posts.PLATFORM;
import Posts.Post;
import Posts.PostList;
import twitter4j.Twitter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlockList extends JScrollPane {
    private JScrollPane scrollPane;
    private JPanel panel;
    private JPanel block;
    public BlockList(PostList postsList) throws IOException {
        panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        Box vBox = Box.createVerticalBox();
        Box hBox ;
        for(Post post:postsList){
            if(post.getPlatform()== PLATFORM.TWITTER) {
                block = new TwitterBlock(post);
                hBox = Box.createHorizontalBox();
                hBox.add(block);
                vBox.add(hBox);
            }
        }
        panel.add(vBox);
        setViewportView(panel);
        this.getVerticalScrollBar().setUnitIncrement(20);
    }
}
