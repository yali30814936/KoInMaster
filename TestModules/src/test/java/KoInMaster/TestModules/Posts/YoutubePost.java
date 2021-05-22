package KoInMaster.TestModules.Posts;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.util.ArrayList;
import java.util.Date;

public class YoutubePost extends Post{
	// url header for video hyperlink
	private final String urlHead = "https://www.youtube.com/watch?v=";
	private final String description;

	public YoutubePost(SearchResult video) {
		SearchResultSnippet snippet = video.getSnippet();
		// use text as video title.
		text = snippet.getTitle();
		type = "youtube-";
		if (snippet.getLiveBroadcastContent() == "none")
			type += "video";
		else
			type += snippet.getLiveBroadcastContent();
		url = urlHead + video.getId().getVideoId();
		publishedTime = new Date(snippet.getPublishedAt().getValue());
		media = new ArrayList<>();
		media.add(snippet.getThumbnails().getMedium().getUrl());
		description = snippet.getDescription();
	}

	@Override
	public String toString() {
		return "YoutubePost{" +
				"text='" + text + '\'' +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", publishedTime=" + publishedTime +
				", media=" + media +
				", urlHead='" + urlHead + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
