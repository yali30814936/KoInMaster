package Celebrities.Crawlers;

import Posts.Post;

import java.util.List;

public class TwitterCrawlerTest {
    public static void main(String[] args) throws Exception {
        TwitterCrawler tweets = new TwitterCrawler("murasakishionch", "murasakishionch");
        List<Post> list = tweets.call();
        for (Post p:list)
            System.out.println(p);
    }
}
