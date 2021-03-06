package Posts;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import twitter4j.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class YoutubePost extends Post{
	// url header for video hyperlink
	private final String urlHead = "https://www.youtube.com/watch?v=";

	private String description;
	private String fullDescription;

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
		description = snippet.getDescription();
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


	public String getFullDescription() {
		return fullDescription;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public void setFullDescription(String fullDescription){
		this.fullDescription=fullDescription;
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
				'}';
	}
}
