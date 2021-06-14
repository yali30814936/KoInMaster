package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Crawlers.TwitterCrawler;
import KoInMaster.TestModules.Posts.PostList;

import javax.swing.*;

public class Blocktest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Tweets = new TwitterCrawler("usadapekora","usadapekora");
        PostList list = Tweets.call();
        JPanel panel = new JPanel();
        panel.add(new Block(list.get(0)));
        Filter filter = new Filter();
        frame.add(filter);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
