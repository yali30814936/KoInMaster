package KoInMaster.TestModules.Celebrities.Crawlers;

import java.io.IOException;
import java.net.URISyntaxException;

public class FacebookRequestPostsV4Test {
	public static void main(String[] args) throws IOException, URISyntaxException {
		FacebookRequestPostsV4 v4 = new FacebookRequestPostsV4("");
		String response = v4.getResponse();
		System.out.println(response);
	}
}