package KoInMaster.TestModules.Posts;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.util.ArrayList;
import java.util.Date;

public class YoutubePost extends Post{
	// url header for video hyperlink
	private final String urlHead = "https://www.youtube.com/watch?v=";

	private final String description;

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
				'}';
	}
}
