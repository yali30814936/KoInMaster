package KoInMaster.TestModules.GUI;

import KoInMaster.TestModules.Celebrities.Crawlers.TwitterCrawler;
import KoInMaster.TestModules.Posts.PostList;
import twitter4j.Twitter;

import javax.swing.*;
import java.awt.*;

public class Blocktest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        Tweets = new TwitterCrawler("usadapekora","usadapekora");
        PostList list = Tweets.call();
        frame.add(new Block(list.get(0)));
        frame.setSize(1080, 756);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
