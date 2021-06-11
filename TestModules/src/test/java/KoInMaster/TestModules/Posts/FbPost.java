package KoInMaster.TestModules.Posts;

import java.util.List;
import java.util.Date;
public class FbPost extends Post{
    private  String url;
    private  List<String> media;
    //private Date publishedTime;
    private String text;

    public FbPost(String URL,List<String> MEDIA,Date TIME,String TEXT){
        url=URL;
        media=MEDIA;
        publishedTime=TIME;
        text=TEXT;
    }

    @Override
    public String toString() {
        return "FacebookPost{" +
                "name=" + name + '\'' +
                "text='" + text +
                ", url='" + url + '\'' +
                ", media=" + media +
                ", publishedTime=" + publishedTime
                ;
    }
}
