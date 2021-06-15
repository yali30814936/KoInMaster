package KoInMaster.TestModules.Celebrities.Crawlers;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class FacebookRequestPostsV4 {
	private final CloseableHttpClient http;
	private final HttpPost request;

	public FacebookRequestPostsV4(String pageId) throws URISyntaxException, UnsupportedEncodingException {
		http = HttpClients.createDefault();

		request = new HttpPost("https://www.facebook.com/api/graphql/");
		request.addHeader(":authority:","www.facebook.com");
		request.addHeader(":method:", "POST");
		request.addHeader(":path:", "/api/graphql/");
		request.addHeader(":scheme:", "https");
		request.addHeader("accept", "*/*");
		request.addHeader("accept-encoding", "gzip, deflate, br");
		request.addHeader("accept-language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,ja;q=0.5");
//		request.addHeader("content-length", "2900");
		request.addHeader("content-type", "application/x-www-form-urlencoded");
		request.addHeader("cookie", "datr=BXPrXyePk2Uvvx2SyTE96T_5; sb=CXPrX5yITWcbjp1HIUx86er2; c_user=100003711070740; _fbp=fb.1.1621006601702.127726326; dpr=1.25; wd=1536x754; xs=36%3AvgPhr1VqFiV8Yg%3A2%3A1609265931%3A-1%3A11299%3A%3AAcXhsG8CDMNNVmOQ5gFDeFeiBgLQOLma-5QamgdwLHI9Kw; fr=1SKNXTA4HLuVZDtdL.AWWcnz8zXblmoKHYL06Z5kC32wo.BgxixF.0Y.GDG.0.0.BgxixF.; spin=r.1003969814_b.trunk_t.1623753494_s.1_v.2_");
		request.addHeader("origin", "https://www.facebook.com");
		request.addHeader("referer", "https://www.facebook.com/KirinDD");
		request.addHeader("sec-ch-ua", "Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
		request.addHeader("sec-ch-ua-mobile", "?0");
		request.addHeader("sec-fetch-dest", "empty");
		request.addHeader("sec-fetch-mode", "cors");
		request.addHeader("sec-fetch-site", "same-origin");
		request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.101 Safari/537.36");
		request.addHeader("x-fb-friendly-name", "CometModernPageFeedPaginationQuery");
		request.addHeader("x-fb-lsd", "N1mX6RbkOZ0P2j3szF7px0");

		StringEntity params = new StringEntity("{\"av\": 100829168379535, \"__usid\": \"5-Tquqydf15zsvwp:Pquqydcujpbhk:0-Aquqydf17o84v9-RV=5:F=\", \"__user\": 100003711070740, \"__a\": 1, \"__dyn\": \"7AzHxqU5a5Q1ryaxG4VuC0BVU98nwgU76byQdwSwAyU8EW1twYwJyEiwp8O2S1DwUx609vCxS320om78-221Rwwwg8vy8465o-cwfG12wOKi8wGwFyE2ly84e2-2l2UtG7o4y0Mo4G4UcUC68f85qfK6E7e58jwGzEaE5e7oqBwJK5Umxm5oe888Gqexfxmu3W3y1MBwxy88EbUbE7uUuBwJwSyES\", \"__csr\": \"gbIQlcxdZjONIy97nE9gDsBsYJhIhA3L8yiJFb8gBLqsSWQTHmJ9GGvh94Ax595ZHEJiiWGGLp5OrOSkBH_JdemmiBtala88bQrVGipLkRBELlHYx5kyZb-FBHhQpjFb9WKcB8mZdvy4A8cbVfKBZdpdXWDGcqylnqyedmExpqHAh5B8V6qQAiCEyKQkyF7J5AgjqiXGAWykZkVbKQ9CWUOF9fgVqhbCy9ptoCRkCWprsKmmquvGcyrGmi49pJ2uEjK4boznCAzayUyuFEOjhHADUK4VEC-8xS8GhJ7zWjGF-4FBBKdCyQ8hpV98R1Siic-cxm9zFU-WpVbAhWUyiHyefzp4hzaKQoxoLCxKU-hbUC4EgK59oymUKp4CDxd3Utz9oS5oyK68K4GByU8ofoszUhKm5Ehh-6edxjBzoozUoDVUhxGfAykq5ocp8Sex2ivix2bAhWgFxyaCDu5UThKUN4ADDxC5pUng5mbg6m0hx5xqm0F8sx6EgxjzQoFVWU8VU5O0HE4C5kpKuGDl2owIG86Q4fc-0o20g65GhA2V1F0moaod8oJxuqK10ghgTxdFyEOEPCyoFaXy4EAwjwoEuye4UaU6i7A68m85E7i1exq4odVpVEOexq0KE7qfy9Eap41Qg2YwSwVyU9EGipAg4-7Ux0IyBK8Fep0Uwm86i1Bwji5h8iwo9pETQq7UdpoboSey84i1qoO4o9A5maz8mzXwUwjAawBwh9Uvw8a1NwmokzEcVE2rxKU8oiU9Uco1fEK2CcwxyEKECaxN1Fpo4i3G8xC8wjQ5Wovg664j1V1KuSm620RV48wu6chXtQbDe2eEqghyUK5UBADS6pQ4AkwIwK58hgSlzWBAixC13yooKEboy2meUvwJg5C6EfE4-1nwBw42a1Hw9C1Twro0E61_wcyqv5wIz88Uhg5ebzE8u15GAi4u6S2KQ9zK1vG0CE2Rw9wR9O3EGQXU9Wtm4USGkB4iWF1jAzWpE410q2qGfxt3UeUpxb8EURA8ibaPXWaU--Bizy0ISBmy4gLUAKmcy3QSQfy2GWCJbja8gOeISAXXK8qF3FainrGbxu262KE8HhE6e4E8Q6Afw8C1hx63q5e78768yJ0Exy5K4qxO4UBa14xyqEq9g8ohmmaU-cUPgG58ggSQ0K8z-u0DUWNM5u13LqgLGqGK5Usr8rwl4i8zA4oN4eipH_Dupx5eDZLGYB5Sqq8h9654p15AwxJngtF8N5Ki-agiTpDqiUkDAQjKUxajyoGbykHAxuswpK78qWG0DU2ew\", \"__req\": 13, \"__hs\": \"18793.EXP3:comet_pkg.2.1.0.0\", \"dpr\": 1.5, \"__ccg\": \"EXCELLENT\", \"__rev\": 1003970210, \"__s\": \"6p7yvd:cj5t61:c6plag\", \"__hsi\": \"6974024685924915965-0\", \"__comet_req\": 1, \"fb_dtsg\": \"AQF1mkiBnFq9dw0:AQEG67qnTxvZbOE\", \"jazoest\": 22603, \"lsd\": \"N1mX6RbkOZ0P2j3szF7px0\", \"__spin_r\": 1003970210, \"__spin_b\": \"trunk\", \"__spin_t\": 1623766656, \"fb_api_caller_class\": \"RelayModern\", \"fb_api_req_friendly_name\": \"CometModernPageFeedPaginationQuery\", \"variables\": {\"UFI2CommentsProvider_commentsKey\":\"CometSinglePageContentContainerFeedQuery\",\"count\":3,\"cursor\":\"AQHReGOsYrYHp59ob67AA1oqUlO6vi9W9EK0o4XfGwNMMVt0Ab8qpcu2FALTr2DTet2Ei51fidY1pyb2O3Pj4iD47t4FiDNJcArA8eXZYPCIwJlLOM6wzI6PlI_xnRbZMy0t\",\"displayCommentsContextEnableComment\":null,\"displayCommentsContextIsAdPreview\":null,\"displayCommentsContextIsAggregatedShare\":null,\"displayCommentsContextIsStorySet\":null,\"displayCommentsFeedbackContext\":null,\"feedLocation\":\"PAGE_TIMELINE\",\"feedbackSource\":22,\"focusCommentID\":null,\"privacySelectorRenderLocation\":\"COMET_STREAM\",\"renderLocation\":\"timeline\",\"scale\":1.5,\"useDefaultActor\":false,\"id\":\"100829168379535\"}, \"server_timestamps\": true, \"doc_id\": 4037932039646967}");
		request.setEntity(params);
	}

	public String getResponse() throws IOException {
		CloseableHttpResponse response = http.execute(request);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "Unicode");
		EntityUtils.consume(entity);
		return body;
//		return response.toString();
	}
}