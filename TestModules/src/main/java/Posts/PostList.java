package Posts;


import Celebrities.Celebrity;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;

public class PostList extends ArrayList<Post>{

    public PostList(){};

    public PostList(JSONArray jsonArray) throws GeneralSecurityException, IOException, URISyntaxException, ParseException {
        YoutubePost temp=new YoutubePost();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject object= (JSONObject) jsonArray.get(i);
            ArrayList<String> param=new ArrayList<>();
            ArrayList<String> param2=new ArrayList<>();
            param.add(object.getString("name"));
            param.add(object.getString("text"));
            param.add(object.getString("platform"));
            param.add(object.getString("type"));
            param.add(object.getString("url"));
            param.add(object.getString("publishedTime"));
            for (String key:object.getJSONObject("media").keySet()) {
                param2.add(object.getJSONObject("media").getJSONObject(key).getString("oneMedia"));
            }
            add(temp.build(param,param2));
        }
    }

    public JSONArray toJSONArray() {
        JSONArray jsonArray = new JSONArray();

        for (Post post:this)
            jsonArray.put(post.toJSONObject());

        return jsonArray;
    }
}
