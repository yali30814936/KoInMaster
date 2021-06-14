package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PLATFORM;
import KoInMaster.TestModules.Posts.PostList;
import KoInMaster.TestModules.Posts.TwitterPost;
import twitter4j.*;

import java.util.List;

public class TwitterCrawler extends Crawler{
    private Twitter twitter;
    public TwitterCrawler(String name,String searchId) {
        super(name, PLATFORM.TWITTER);
        param = searchId;
        try {
            twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get Tweets: " + te.getMessage());
            System.exit(-1);
        }
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
    public PostList call()throws Exception{
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
