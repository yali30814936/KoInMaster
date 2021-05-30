import Post.Twitterpost;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
public class Twittergettweet {
    public static void main(String[] args) {
        try {

            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getUserTimeline("usadapekora");
            List<Twitterpost> list = new ArrayList<>();
            for(Status status:statuses){
                list.add(new Twitterpost(status));
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get Tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
