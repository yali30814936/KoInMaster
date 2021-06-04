package KoInMaster.TestModules.Celebrities.Crawlers;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kindatest {
	public static void main(String[] args) {
		Matcher matcher = Pattern.compile("(www.youtube.com/(channel|c)/)?(?<ID>[^?]*)\\??")
		                         .matcher("UCIG9rDtgR45VCZmYnd-4DUw");
		if (matcher.find())
			System.out.println(URLDecoder.decode(matcher.group("ID"), StandardCharsets.UTF_8));
		else
			System.out.println("Nope");
	}
}
