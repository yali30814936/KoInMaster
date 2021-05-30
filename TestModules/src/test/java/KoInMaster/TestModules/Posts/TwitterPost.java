package KoInMaster.TestModules.Posts;

import twitter4j.MediaEntity;
import twitter4j.Status;
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
