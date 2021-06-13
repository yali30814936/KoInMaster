package KoInMaster.TestModules.Posts;

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

	public String getName() {return name;}

	public String getText() {
		return text;
	}

	public void setType(TYPE type) {
		this.type = type;
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
}
