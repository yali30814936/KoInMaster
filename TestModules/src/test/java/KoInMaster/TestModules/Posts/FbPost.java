package KoInMaster.TestModules.Posts;

import java.util.List;
import java.util.Date;
public class FbPost extends Post{
    private  String url;
    private  List<String> media;
    //private Date publishedTime;
    private String text;
    private String name;
    private Post subPost;
    private Boolean hasSubPost;
    public FbPost(String Name,String URL,List<String> MEDIA,Date TIME,String TEXT,boolean hasSubPost){
        name=Name;
        url=URL;
        media=MEDIA;
        publishedTime=TIME;
        text=TEXT;
        platform = PLATFORM.FACEBOOK;
        type = TYPE.NONE;
        this.hasSubPost=hasSubPost;
    }
    public void  setSubPost(Post subPost){
        this.subPost=subPost;
    }
    public Boolean getHasSubPost(){
        return hasSubPost;
    }
    @Override
    public String toString() {
        if(getHasSubPost()){
            return "FacebookPost{" +
                    "name=" + name + '\'' +
                    "text='" + text +
                    ", url='" + url + '\'' +
                    ", media=" + media +
                    ", publishedTime=" + publishedTime +
                    "subPost" +  subPost.toString();
        }
        else{
            return "FacebookPost{" +
                    "name=" + name + '\'' +
                    "text='" + text +
                    ", url='" + url + '\'' +
                    ", media=" + media +
                    ", publishedTime=" + publishedTime
                    ;
        }

    }

}
