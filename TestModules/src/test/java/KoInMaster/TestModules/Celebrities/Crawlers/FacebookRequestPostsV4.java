package KoInMaster.TestModules.Celebrities.Crawlers;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacebookRequestPostsV4 {
	private final CloseableHttpClient http;
	private final HttpPost request;

	public FacebookRequestPostsV4(String pageId) throws URISyntaxException {
		http = HttpClients.createDefault();

		URI uri = new URIBuilder("https://www.facebook.com/api/graphql/")
				.addParameter("","")
				.addParameter("", "POST")
				.addParameter(":path:", ":path:")
					.build();
		request = new HttpPost("https://www.facebook.com/api/graphql/");
		request.addHeader(":authority:","www.facebook.com");
		request.addHeader(":method:", "POST");
		request.addHeader(":path:", "/api/graphql/");
		request.addHeader(":scheme:", "https");
		request.addHeader("accept", "*/*");
		request.addHeader("accept-encoding", "gzip, deflate, br");
		request.addHeader("accept-language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,ja;q=0.5");
		request.addHeader("content-length", "2900");
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
	}

	public String getResponse() throws IOException {
		CloseableHttpResponse response = http.execute(request);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "Unicode");
		EntityUtils.consume(entity);

		Matcher matcher = Pattern.compile("\"__html\":\"(?<html>.*?)\"}")
		                         .matcher(StringEscapeUtils.unescapeJava(body));

		response.close();

		if (matcher.find())
			return matcher.group("html");
		else
			return "";
	}
}