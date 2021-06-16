package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.PostList;
import Posts.TwitterPost;
import twitter4j.*;

import java.util.List;

public class TwitterCrawler extends Crawler{
    private final Twitter twitter;
    public TwitterCrawler(String name,String searchId) {
        super(name, PLATFORM.TWITTER);
        param = searchId;
        twitter = new TwitterFactory().getInstance();
    }
    public PostList searchTweets(String searchName) {
        PostList list = new PostList();
        try {
            List<Status> statuses = twitter.getUserTimeline(searchName);
            for (Status status : statuses) {
                list.add(new TwitterPost(name, status));
            }
            return list;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.err.println("Failed to get Tweets: " + te.getMessage());
            return list;
        }
    }

    @Override
    public PostList call() {
        return searchTweets(param);
    }

    @Override
    public String toString() {
        return "TwitterCrawler{" +
                "name='" + name + '\'' +
                ", platform=" + platform +
                ", searchId='" + param + '\'' +
                '}';
    }
}
