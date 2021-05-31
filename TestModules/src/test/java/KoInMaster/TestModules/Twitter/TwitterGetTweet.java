package KoInMaster.TestModules.Twitter;
import KoInMaster.TestModules.Posts.TwitterPost;
import KoInMaster.TestModules.Posts.Post;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
public class TwitterGetTweet {
    public TwitterGetTweet(String searchid) {
        try {

            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getUserTimeline(searchid);
            List<Post> list = new ArrayList<Post>();
            for(Status status:statuses){
                list.add(new TwitterPost(status));
            }

            System.out.println(list);

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get Tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
