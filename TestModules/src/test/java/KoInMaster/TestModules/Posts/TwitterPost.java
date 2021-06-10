package KoInMaster.TestModules.Posts;
import twitter4j.MediaEntity;
import twitter4j.Status;
import java.util.ArrayList;
public class TwitterPost extends Post{
    private String user;
    public TwitterPost(Status status){
        user=status.getUser().getScreenName();
        text=status.getText();
        String str=text;
        text=str.replaceAll("RT ","");
        str=text;
        text=str.replaceAll(" https://.*","");
        if(status.isRetweet()){
            type="Twitter-RT";
        }
        else{
            type="Twitter-T";
        }
        if(type.equals("Twitter-RT")){
            user=status.getRetweetedStatus().getUser().getScreenName();
        }
        text=text.replaceAll("@.*: ","");
        url = "https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId();
        publishedTime=status.getCreatedAt();
        media=new ArrayList<String>();
        for(MediaEntity me:status.getMediaEntities()){
            media.add(me.getMediaURL());
        }
    }
    @Override
    public String toString() {
        return "TwitterPost{" +
                "user='" + user+'\''+
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", publishedTime=" + publishedTime +
                ", media=" + media +
                '}';
    }
}
