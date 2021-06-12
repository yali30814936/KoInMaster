package KoInMaster.TestModules.FacebookV2;

import KoInMaster.TestModules.Posts.PostList;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacebookCrawlerV2 {
	private String name;
	private final String USER_AGENT = "Mozilla/5.0";
	private CloseableHttpClient http;
	private HttpPost request;

	public FacebookCrawlerV2(String name, String URL) throws IOException {
		this.name = name;
		http = HttpClients.createDefault();
		request = new HttpPost("https://www.facebook.com/api/graphql/");
		request.setHeader("content-type", "application/x-www-form-urlencoded");
		request.setHeader("content-type", "application/x-www-form-urlencoded");

		List<BasicNameValuePair> nvp = new ArrayList<>();
		nvp.add(new BasicNameValuePair("av","100003711070740"));
		nvp.add(new BasicNameValuePair("_user","100003711070740"));
		nvp.add(new BasicNameValuePair("doc_id","6037657486259372"));
		nvp.add(new BasicNameValuePair("variables","{\"UFI2CommentsProvider_commentsKey\":\"CometSinglePageContentContainerFeedQuery\",\"count\":3,\"cursor\":\"AQHRQej3PIoCeWV4oicM4v6z6EHRt5RzOfI1E0d20ZpmpZAkqA-vXWl3ZvDEqARasBgDJ7XYuICIMb_p_paj_qYVCb9cilBnkPu4lcVuk6w5SzpbvxmXnQdAVkTHkEASvB9N\",\"displayCommentsContextEnableComment\":null,\"displayCommentsContextIsAdPreview\":null,\"displayCommentsContextIsAggregatedShare\":null,\"displayCommentsContextIsStorySet\":null,\"displayCommentsFeedbackContext\":null,\"feedLocation\":\"PAGE_TIMELINE\",\"feedbackSource\":22,\"focusCommentID\":null,\"privacySelectorRenderLocation\":\"COMET_STREAM\",\"renderLocation\":\"timeline\",\"scale\":1.5,\"useDefaultActor\":false,\"id\":\"104687660964702\"}"));
		request.setEntity(new UrlEncodedFormEntity(nvp, "utf-8"));

		CloseableHttpResponse response = http.execute(request);

		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);

		System.out.println(body);

		response.close();
	}

	public PostList getPosts(String URL) {
		PostList list = new PostList();
		return list;
	}

	public static void main(String[] args) throws IOException {
		FacebookCrawlerV2 v2 = new FacebookCrawlerV2("Vtuber project", "https://www.facebook.com/Vtbproject");
	}
}
