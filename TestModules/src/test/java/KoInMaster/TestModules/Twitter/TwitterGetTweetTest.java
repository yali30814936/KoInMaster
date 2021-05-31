package KoInMaster.TestModules.Twitter;

public class TwitterGetTweetTest {
    private static TwitterGetTweet Tweets;
    public static void main(String[] args){
        Tweets = new TwitterGetTweet("usadapekora");
        System.out.println(Tweets);
    }
}
