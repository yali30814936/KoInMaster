package Posts;


import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PostList extends ArrayList<Post>{

    public PostList(){super();};

    public PostList(List<Post> list) {
        super();
        addAll(list);
    }

    public PostList(JSONArray jsonArray) throws GeneralSecurityException, IOException, URISyntaxException, ParseException {

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
            if(object.getString("platform").equals("YouTube")){
                param.add(object.getString("description"));
                param.add(object.getString("fullDescription"));
            }
            else if(object.getString("platform").equals("Twitter")) {
                param.add(object.getString("user"));
            }
            for (String key:object.getJSONObject("media").keySet()) {
                param2.add(object.getJSONObject("media").getJSONObject(key).getString("oneMedia"));
            }
            add(Post.build(param,param2));
        }
    }

    public JSONArray toJSONArray() {
        JSONArray jsonArray = new JSONArray();

        for (Post post:this)
            jsonArray.put(post.toJSONObject());

        return jsonArray;
    }
}
