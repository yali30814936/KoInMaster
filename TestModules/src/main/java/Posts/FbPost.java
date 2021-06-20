package Posts;

import twitter4j.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class FbPost extends Post{
    private Post subPost;
    private Boolean hasSubPost=false;

    public FbPost(){};

    public FbPost(String Name,String URL,List<String> MEDIA,Date TIME,String TEXT,boolean hasSubPost){
        name=Name;
        url=URL;
        media=MEDIA;
        publishedTime=TIME;
        text=TEXT;
        platform = PLATFORM.FACEBOOK;
        type = TYPE.POST;
        this.hasSubPost=hasSubPost;
    }

    public FbPost(JSONObject object) throws ParseException {

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

    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();

        obj.put("name", name);

        obj.put("text", text);

        obj.put("platform",platform.toString());

        obj.put("type", type.toString());

        obj.put("url", url);

        obj.put("publishedTime", publishedTime.getTime());


        JSONObject medias = new JSONObject();
        JSONObject sub;
        for(int i=0;i<media.size();i++){
            sub=new JSONObject();
            sub.put("oneMedia",media.get(i));
            medias.put(String.valueOf(i),sub);
        }
        obj.put("media",medias);
        return obj;
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
