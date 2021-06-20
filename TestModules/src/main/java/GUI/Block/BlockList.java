package GUI.Block;


import Posts.PLATFORM;
import Posts.Post;
import Posts.PostList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class BlockList {
    public static JPanel generateBlockList(PostList postsList) throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Box vBox = Box.createVerticalBox();
        Box hBox ;
        for(Post post:postsList){
            if(post.getPlatform()== PLATFORM.TWITTER) {
                JPanel block = new TwitterBlock(post);
                hBox = Box.createHorizontalBox();
                hBox.add(block);
                hBox.setBorder(new LineBorder(Color.cyan,3));
                vBox.add(hBox);
                vBox.add(Box.createVerticalStrut(20));
            }
        }

        panel.add(vBox);
        return panel;
    }
}
