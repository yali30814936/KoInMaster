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

public class FacebookCrawlerV3 {
	private final CloseableHttpClient http;
	private final HttpGet request;

	public FacebookCrawlerV3(String pageId) throws URISyntaxException {
		http = HttpClients.createDefault();

		URI uri = new URIBuilder("https://www.facebook.com/pages_reaction_units/more/?surface=www_pages_home&referrer&privacy_mutation_token=eyJ0eXBlIjowLCJjcmVhdGlvbl90aW1lIjoxNjIzNDE2NDc3LCJjYWxsc2l0ZV9pZCI6NjgzMjQxNzc1NzExMjE3fQ%3D%3D&fb_dtsg_ag&__user=0&__a=1&__dyn=7AgNe5Gmawgrolg5K8G6EjheC1szobpEnz8nwgU5GexZ3ocWwAyUuKewhE4mdwJx64e2q3qcw8258e8hwj82oG3i0wpk2u2-263WWwSxu15wgE46fw9C48szU2mwwwg8vy8465o-cypojwp87612wOKi8wGwFyE6K3a1txm2l2UtG7o4y2O0B85WEeE5m1mzUuxe6Fo5e4Ueo2swkEbElxm3y1lxu16wa-0kS1AyES&__csr=&__req=6&__hs=18789.PHASED%3ADEFAULT.2.0.0.0&dpr=1.5&__ccg=EXCELLENT&__rev=1003951477&__s=bv9qk2%3Aql2gni%3Aaom84m&__hsi=6972520678623437500-0&__comet_req=0&__spin_r=1003951477&__spin_b=trunk&__spin_t=1623416477")
					.addParameter("page_id", pageId)
					.addParameter("cursor", "{\"card_id\":\"videos\",\"has_next_page\":true}")
					.addParameter("unit_count", "10")
					.build();
		request = new HttpGet(uri);

	}

	public String getResponse() throws IOException {
		CloseableHttpResponse response = http.execute(request);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, "Unicode");
		EntityUtils.consume(entity);

		Matcher matcher = Pattern.compile("\"__html\":\"(?<html>.*)\"}]],\"jsmods\":\\{\"")
		                         .matcher(StringEscapeUtils.unescapeJava(body));

		response.close();

		if (matcher.find())
			return matcher.group("html");
		else
			return "";
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		FacebookCrawlerV3 v3 = new FacebookCrawlerV3("1421110321440678");
	}
}