package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Crawlers.TwitterCrawler;
import KoInMaster.TestModules.Posts.PostList;

import javax.swing.*;

public class Blocktest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Tweets = new TwitterCrawler("minatoaqua","minatoaqua");
        PostList list = Tweets.call();
        Block bpanel = new Block(list.get(9));
        JScrollPane scrollPane = new JScrollPane(bpanel);
        frame.add(scrollPane);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
