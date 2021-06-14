package KoInMaster.TestModules.Core;

import KoInMaster.TestModules.Celebrities.Celebrity;
import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Posts.PLATFORM;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class CelebritiesFileWriterTest {
	public static void main(String[] args) throws GeneralSecurityException, IOException, URISyntaxException {
		List<Celebrity> celebrities = new ArrayList<>();
		Celebrity celebrity = new Celebrity("夏色まつり");
		celebrity.getCrawlers().put("Twitter", Crawler.build(PLATFORM.TWITTER, celebrity.getName(), "shirakamifubuki"));
		celebrity.getCrawlers().put("YouTube", Crawler.build(PLATFORM.YOUTUBE, celebrity.getName(), "UCdn5BQ06XqgXoAxIhbqw5Rg"));

		celebrity.getFilterPaths().add("hololive/一期生");
		celebrity.setManagePath("hololive/一期生");

		celebrities.add(celebrity);

		// write file
		CelebritiesFileWriter writer = new CelebritiesFileWriter();
		writer.write(celebrities);
	}
}
