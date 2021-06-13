package KoInMaster.TestModules.Celebrities.Crawlers;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacebookPageIdFetcher {
	public static String getPageId(String url) throws URISyntaxException, IOException {
		CloseableHttpClient http = HttpClients.createDefault();
		URI uri = new URIBuilder(url).build();
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = http.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity);
		EntityUtils.consume(entity);

		Matcher matcher = Pattern.compile("page_id=(?<id>\\d*)")
		                         .matcher(StringEscapeUtils.unescapeJava(body));

		response.close();
		if (matcher.find())
			return matcher.group("id");
		else
			return "";
	}

	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println(getPageId("https://www.facebook.com/Vtbproject/"));
	}
}
