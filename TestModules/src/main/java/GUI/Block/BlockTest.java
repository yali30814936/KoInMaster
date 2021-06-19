package GUI.Block;

import Celebrities.Crawlers.TwitterCrawler;
import Posts.PostList;

import javax.swing.*;

public class BlockTest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Tweets = new TwitterCrawler("momosuzunene","momosuzunene");
        PostList list = Tweets.call();
        TwitterBlock blockPanel = new TwitterBlock(list.get(13));
        JScrollPane scrollPane = new JScrollPane(blockPanel);
        frame.add(scrollPane);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
