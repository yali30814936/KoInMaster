package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.util.List;

public class TwitterCrawlerTest {
    private static TwitterCrawler Tweets;
    public static void main(String[] args) throws Exception {
        Tweets = new TwitterCrawler("usadapekora","usadapekora");
        List<Post> list = Tweets.call();
        for (Post p:list)
            System.out.println(p);
    }
}
