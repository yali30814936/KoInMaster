package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.util.List;

public class TwitterCrawlerTest {
    public static void main(String[] args) throws Exception {
        TwitterCrawler tweets = new TwitterCrawler("usadapekora", "usadapekora");
        List<Post> list = tweets.call();
        for (Post p:list)
            System.out.println(p);
    }
}
