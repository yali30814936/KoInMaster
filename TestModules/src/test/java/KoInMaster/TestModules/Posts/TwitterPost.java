package KoInMaster.TestModules.Posts;

import twitter4j.MediaEntity;
import twitter4j.Status;

import java.util.ArrayList;

public class TwitterPost extends Post{
    private final String user;
    private final Status status;
    public TwitterPost(String name, Status status){
        super.name = name;
        platform = PLATFORM.TWITTER;
        media=new ArrayList<>();
        text = status.getText();
        this.status = status;

        // retweet
        if(status.isRetweet()){
            type = TYPE.RT;
            user = status.getRetweetedStatus().getUser().getScreenName();
            text = status.getRetweetedStatus().getText();
        }
        // reply
        else if (status.getInReplyToUserId() != -1) {
            type = TYPE.REPLY;
            if (status.getUserMentionEntities().length > 0)
                user = status.getUserMentionEntities()[0].getName();
            else
                user = "自己";
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

        // remove "RT @user" or "@user"
        text = text.replaceAll("^RT ","");
        text = text.replaceAll("^@.*? ","");
        text = text.replaceAll("https://.*?$","");
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

    public Status getStatus() {
        return status;
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
