package Posts;

import Celebrities.Crawlers.Crawler;
import Celebrities.Crawlers.FacebookCrawler;
import Celebrities.Crawlers.TwitterCrawler;
import Celebrities.Crawlers.YoutubeCrawler;
import org.openqa.selenium.Platform;
import twitter4j.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class Post {
	protected String name;
	protected String text;


	protected PLATFORM platform;
	protected TYPE type;
	protected String url;
	protected Date publishedTime;
	protected List<String> media;

	public String getName() {return name;}

	public String getText() {
		return text;
	}

	public void setType(TYPE type) { this.type = type; }

	public PLATFORM getPlatform() { return platform; }

	public TYPE getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public Date getPublishedTime() {
		return publishedTime;
	}

	public List<String> getMedia() {
		return media;
	}

	public abstract String toString();

	public Post build(List<String> param,List<String> param2 )throws ParseException{
		Post temp;
		switch (PLATFORM.fromString(param.get(2))) {
			case YOUTUBE:
				temp= new YoutubePost();
				temp.name= param.get(0);
				temp.text= param.get(1);
				temp.platform= PLATFORM.fromString(param.get(2));
				temp.type= TYPE.fromString(param.get(3));
				temp.url= param.get(4);
				temp.publishedTime= new SimpleDateFormat("dow mmm dd hh:mm:ss zzz yyyy").parse(param.get(5));
				temp.media=param2;
				return temp;
			case TWITTER:
				temp= new TwitterPost();
				temp.name= param.get(0);
				temp.text= param.get(1);
				temp.platform= PLATFORM.fromString(param.get(2));
				temp.type= TYPE.fromString(param.get(3));
				temp.url= param.get(4);
				temp.publishedTime= new SimpleDateFormat("dow mmm dd hh:mm:ss zzz yyyy").parse(param.get(5));
				temp.media=param2;
				return temp;
			case FACEBOOK:
				temp= new FbPost();
				temp.name= param.get(0);
				temp.text= param.get(1);
				temp.platform= PLATFORM.fromString(param.get(2));
				temp.type= TYPE.fromString(param.get(3));
				temp.url= param.get(4);
				temp.publishedTime= new Date(Long.parseLong(param.get(5)));
				temp.media=param2;
				return temp;
			default:
				return null;
		}
	}
	public void fromJsonObject(JSONObject object) throws ParseException {

		name = object.getString("name");

		text=object.getString("text");

		platform=PLATFORM.fromString(object.getString("platform"));

		type=TYPE.fromString(object.getString("type"));

		url=object.getString("url");

		publishedTime=new Date(Long.parseLong(object.getString("publishedTime")));

		ArrayList<String> tempMedias=new ArrayList<>();
		for (String key:object.getJSONObject("media").keySet()) {
			tempMedias.add(object.getJSONObject("media").getJSONObject(key).getString("oneMedia"));
		}
		media=tempMedias;
	}

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();

		obj.put("name", name);

		obj.put("text", text);

		obj.put("platform",platform.toString());

		obj.put("type", type.toString());

		obj.put("url", url);

		obj.put("publishedTime",Long.toString(publishedTime.getTime()));

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
}
