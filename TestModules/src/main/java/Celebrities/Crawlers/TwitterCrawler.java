package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.PostList;
import Posts.TwitterPost;
import twitter4j.*;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterCrawler extends Crawler{
    private final Twitter twitter;

    public TwitterCrawler(String name,String searchId) {
        super(name, PLATFORM.TWITTER);
        param = searchId;
        twitter = new TwitterFactory().getInstance();
    }

    public static Crawler rawBuild(String name, String raw) throws IOException {
        Matcher matcher = Pattern.compile("((https?://)?(mobile.)?twitter.com/)?(?<ID>[^?]*)\\??")
                                 .matcher(raw);
        String param;
        if (matcher.find())
            param = matcher.group("ID");
        else {
            throw new IOException("分析 '" + name + "' 的Twitter帳號 url='" + raw +"' 失敗");
        }
        return new TwitterCrawler(name, param);
    }

    public PostList searchTweets(String searchName) {
        PostList list = new PostList();
        try {
            List<Status> statuses = twitter.getUserTimeline(searchName,new Paging(2));
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
    public String getFormatParam() {
        return "user name = @" + param;
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
