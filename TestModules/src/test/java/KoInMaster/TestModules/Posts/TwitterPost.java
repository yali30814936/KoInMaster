package KoInMaster.TestModules.Posts;

import twitter4j.MediaEntity;
import twitter4j.Status;

import java.util.ArrayList;

public class TwitterPost extends Post{
    private final String user;
    public TwitterPost(String name, Status status){
        super.name = name;
        platform = PLATFORM.TWITTER;
        media=new ArrayList<>();
        text = status.getText();

        // remove "RT @user" or "@user"
        text = text.replaceAll("^RT ","");
        text = text.replaceAll("^@.*? ","");

        // retweet
        if(status.isRetweet()){
            type = TYPE.RT;
            user = status.getRetweetedStatus().getUser().getScreenName();
        }
        // reply
        else if (status.getInReplyToUserId() != -1) {
            type = TYPE.REPLY;
            user = status.getUserMentionEntities()[0].getName();
        }
        // quoted
        else if (status.getQuotedStatusId() != -1) {
            type = TYPE.QUOTED;
            user = status.getQuotedStatus().getUser().getName();
        }
        // normal tweet
        else{
            type = TYPE.NONE;
            user = status.getUser().getName();
        }

        // html transform
        text = text.replaceAll("\n","<br>");
        text = "<html>"+text+"</html>";

        url = "https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId();
        publishedTime=status.getCreatedAt();
        for(MediaEntity me:status.getMediaEntities()){
            media.add(me.getMediaURL());
        }
    }
    public String getUser(){
        return user;
    }
    @Override
    public String toString() {
        return "TwitterPost{" +
                "name=" + name + '\'' +
                "user='" + user+'\''+
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", publishedTime=" + publishedTime +
                ", media=" + media +
                '}';
    }
}
