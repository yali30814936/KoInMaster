package GUI.Block;

import Celebrities.Crawlers.TwitterCrawler;
import Posts.PostList;
import Posts.PostListReadWrite;

import javax.swing.*;
import java.awt.*;

public class BlockTest {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        SpringLayout sp = new SpringLayout();
        content.setLayout(sp);

        TwitterCrawler tweets = new TwitterCrawler("tomoe_shirayuki", "tomoe_shirayuki");
//        TwitterBlock blockPanel = new TwitterBlock(list.get(13));
//        JScrollPane scrollPane = new JScrollPane(blockPanel);
//        frame.add(scrollPane);
        JScrollPane jScrollPane = new JScrollPane();
        content.add(jScrollPane);
        sp.putConstraint("East", jScrollPane, 0, "East", content);
        sp.putConstraint("South", jScrollPane, 0, "South", content);
        sp.putConstraint("West", jScrollPane, 0, "West", content);
        sp.putConstraint("North", jScrollPane, 0, "North", content);

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

//        Thread.sleep(2000);
        System.out.println("Start");
        PostList list = PostListReadWrite.read();
        System.out.println("Start2");
        BlockList blockList = new BlockList(list);
        System.out.println("Start3");
        jScrollPane.setViewportView(blockList);
        frame.revalidate();
        System.out.println(jScrollPane.getSize());
    }
}