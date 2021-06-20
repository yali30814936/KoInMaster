package Posts;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import twitter4j.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class YoutubePost extends Post{
	// url header for video hyperlink
	private final String urlHead = "https://www.youtube.com/watch?v=";

	private String description;
	private String fullDescription = "";
	private boolean fullyLoaded = false;

	public YoutubePost(){};

	public YoutubePost(String name, SearchResult video) {
		super.name = name;
		super.platform = PLATFORM.YOUTUBE;
		SearchResultSnippet snippet = video.getSnippet();
		// use text as video title.
		text = snippet.getTitle();
		if (snippet.getLiveBroadcastContent().equals("live"))
			type = TYPE.LIVE;
		else if (snippet.getLiveBroadcastContent().equals("upcoming"))
			type = TYPE.UPCOMING;
		else
			type = TYPE.NONE;
		url = urlHead + video.getId().getVideoId();
		publishedTime = new Date(snippet.getPublishedAt().getValue());
		media = new ArrayList<>();
		media.add(snippet.getThumbnails().getMedium().getUrl());
		description = snippet.getDescription();
	}

	public YoutubePost(JSONObject object) throws ParseException {

		name = object.getString("name");

		text=object.getString("text");

		platform=PLATFORM.fromString(object.getString("platform"));

		type=TYPE.fromString(object.getString("type"));

		url=object.getString("url");

		publishedTime=new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyyy").parse(object.getString("publishedTime"));

		description=object.getString("description");

		fullDescription=object.getString("fullDescription");

		fullyLoaded=object.getBoolean("fullyLoaded");

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

		obj.put("description",description);

		obj.put("fullDescription",fullDescription);

		obj.put("fullyLoaded",fullyLoaded);

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

	public void  SetDescription(String description){
		this.description=description;
	}

	public void SetfullDescription(String fullDescription){
		this.fullDescription=fullDescription;
	}

	public void SetfullyLoaded(boolean fullyLoaded){
		this.fullyLoaded=fullyLoaded;
	}
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "YoutubePost{" +
				"name=" + name + '\'' +
				", text='" + text + '\'' +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", publishedTime=" + publishedTime +
				", media=" + media +
				", description='" + description + '\'' +
				", fullDescription='" + fullDescription + '\'' +
				", fullyLoad=" + fullyLoaded +
				'}';
	}
}
