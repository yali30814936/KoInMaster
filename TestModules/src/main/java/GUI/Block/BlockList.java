package GUI.Block;


import Posts.Post;
import Posts.PostList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class BlockList extends JPanel {
    public BlockList(PostList postsList) throws IOException {
        super();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        Box vBox = Box.createVerticalBox();
        Box hBox ;
        for(Post post:postsList){
            hBox = Box.createHorizontalBox();
            JPanel block = null;
            switch (post.getPlatform()) {
                case TWITTER:
                    block = new TwitterBlock(post);
                    hBox.setBorder(new LineBorder(Color.cyan,3));
                    break;
                case YOUTUBE:
                    block = new YoutubeBlock(post);
                    hBox.setBorder(new LineBorder(new Color(215, 30, 24)));
                    break;
                case FACEBOOK:

                    break;
            }
            hBox.add(block);
            vBox.add(hBox);
            vBox.add(Box.createVerticalStrut(20));
        }
        add(vBox);
    }
}
