package KoInMaster.TestModules.Celebrities.Crawlers;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.util.concurrent.Callable;

public class YoutubeCrawlerThread implements Callable<SearchListResponse> {
	private final YouTube.Search.List request;

	public YoutubeCrawlerThread(YouTube.Search.List request) {
		this.request = request;
	}

	@Override
	public SearchListResponse call() throws Exception {
		return request.execute();
	}
}
