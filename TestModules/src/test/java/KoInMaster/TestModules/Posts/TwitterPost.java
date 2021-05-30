package KoInMaster.TestModules.Posts;

import twitter4j.MediaEntity;
import twitter4j.Status;

import java.util.ArrayList;

public class TwitterPost extends Post{
    public TwitterPost(Status status){
        text="@"+status.getUser().getScreenName()+"\n"+status.getText();
        if(status.isRetweet()){
            type="RT";
        }
        else{
            type="T";
        }
        url = "https://twitter.com/"+status.getUser().getScreenName()+"status"+status.getId();
        publishedTime=status.getCreatedAt();
        media=new ArrayList<String>();
        for(MediaEntity me:status.getMediaEntities()){
            media.add(me.getMediaURL());
        }
    }

    @Override
    public String toString() {
        return "TwitterPost{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", publishedTime=" + publishedTime +
                ", media=" + media +
                '}';
    }
}
