package Post;
import jdk.jshell.Snippet;
import twitter4j.MediaEntity;
import twitter4j.Status;

import java.util.List;
public class Twitterpost extends Post{
    public Twitterpost(Status status){
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
    public String toString(){
        return text;
    }
}
