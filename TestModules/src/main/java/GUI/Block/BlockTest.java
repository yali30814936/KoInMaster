package GUI.Block;

import Celebrities.Crawlers.TwitterCrawler;
import Posts.PostList;

import javax.swing.*;

public class BlockTest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Tweets = new TwitterCrawler("uruharushia","uruharushia");
        PostList list = Tweets.call();
        BlockList blockList = new BlockList(list);
        //JScrollPane scrollPane = new JScrollPane(blockList);
        frame.setVisible(true);
        frame.add(blockList);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
