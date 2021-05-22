package KoInMaster.TestModules.Posts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public abstract class Post {
	String text;
	// type format: "platform-type". ex:youtube-live
	String type;
	String url;
	Date publishedTime;
	List<String> media;

	public String getText() {
		return text;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
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
}
