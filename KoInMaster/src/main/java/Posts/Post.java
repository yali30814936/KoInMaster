package Posts;

import twitter4j.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Post {
	protected String name;
	protected String text;


	protected PLATFORM platform;
	protected TYPE type;
	protected String url;
	protected Date publishedTime;
	protected List<String> media;

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public PLATFORM getPlatform() {
		return platform;
	}

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

	public abstract JSONObject toJSONObject();

	public static Post build(List<String> param, List<String> param2) throws ParseException {
		switch (PLATFORM.fromString(param.get(2))) {
			case YOUTUBE:
				YoutubePost temp = new YoutubePost();
				temp.name = param.get(0);
				temp.text = param.get(1);
				temp.platform = PLATFORM.fromString(param.get(2));
				temp.type = TYPE.fromString(param.get(3));
				temp.url = param.get(4);
				temp.publishedTime = new Date(Long.parseLong(param.get(5)));
				temp.media = param2;
				temp.setDescription(param.get(6));
				temp.setFullDescription(param.get(7));
				return temp;
			case TWITTER:
				TwitterPost temp2 = new TwitterPost();
				temp2.name = param.get(0);
				temp2.text = param.get(1);
				temp2.platform = PLATFORM.fromString(param.get(2));
				temp2.type = TYPE.fromString(param.get(3));
				temp2.url = param.get(4);
				temp2.publishedTime = new Date(Long.parseLong(param.get(5)));
				temp2.SetUser(param.get(6));
				temp2.media = param2;
				return temp2;
			case FACEBOOK:
				FbPost temp3 = new FbPost();
				temp3.name = param.get(0);
				temp3.text = param.get(1);
				temp3.platform = PLATFORM.fromString(param.get(2));
				temp3.type = TYPE.fromString(param.get(3));
				temp3.url = param.get(4);
				temp3.publishedTime = new Date(Long.parseLong(param.get(5)));
				temp3.media = param2;
				return temp3;
			default:
				return null;
		}
	}

	public void fromJsonObject(JSONObject object) throws ParseException {

		name = object.getString("name");

		text = object.getString("text");

		platform = PLATFORM.fromString(object.getString("platform"));

		type = TYPE.fromString(object.getString("type"));

		url = object.getString("url");

		publishedTime = new Date(Long.parseLong(object.getString("publishedTime")));

		ArrayList<String> tempMedias = new ArrayList<>();
		for (String key : object.getJSONObject("media").keySet()) {
			tempMedias.add(object.getJSONObject("media").getJSONObject(key).getString("oneMedia"));
		}
		media = tempMedias;
	}
}

