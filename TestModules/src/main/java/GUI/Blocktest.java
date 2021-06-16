package GUI;

import Celebrities.Crawlers.TwitterCrawler;
import Posts.PostList;

import javax.swing.*;

public class Blocktest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Tweets = new TwitterCrawler("shirakamifubuki","shirakamifubuki");
        PostList list = Tweets.call();
        TwitterBlock bpanel = new TwitterBlock(list.get(10));
        JScrollPane scrollPane = new JScrollPane(bpanel);
        frame.add(scrollPane);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
