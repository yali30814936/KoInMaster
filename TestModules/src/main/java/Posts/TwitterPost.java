package Posts;

import twitter4j.JSONObject;
import twitter4j.MediaEntity;
import twitter4j.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TwitterPost extends Post{
    private  String user;
    private  Status status;

    public TwitterPost(){};
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
            text = status.getText()+"\n\n<hr>"+status.getQuotedStatus().getText();
        }
        // normal tweet
        else{
            type = TYPE.TWEET;
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
        if(type==TYPE.QUOTED){
            for (MediaEntity me : status.getQuotedStatus().getMediaEntities()) {
                media.add(me.getMediaURL());
            }
        }
        else {
            for (MediaEntity me : status.getMediaEntities()) {
                media.add(me.getMediaURL());
            }
        }
    }

    public TwitterPost(JSONObject object) throws ParseException {

        name = object.getString("name");

        text=object.getString("text");

        platform=PLATFORM.fromString(object.getString("platform"));

        type=TYPE.fromString(object.getString("type"));

        url=object.getString("url");

        publishedTime=new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyyy").parse(object.getString("publishedTime"));

        ArrayList<String> tempMedias=new ArrayList<>();
        for (String key:object.getJSONObject("media").keySet()) {
            tempMedias.add(object.getJSONObject("media").getJSONObject(key).getString("oneMedia"));
        }
        media=tempMedias;
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
