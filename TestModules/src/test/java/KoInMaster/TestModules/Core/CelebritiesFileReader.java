package KoInMaster.TestModules.Core;

import KoInMaster.TestModules.Celebrities.Celebrity;
import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Posts.PLATFORM;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelebritiesFileReader {
	private static Scanner input;
	private static final String fileName = "modules.txt";

	public static List<Celebrity> readModules() throws GeneralSecurityException, IOException, URISyntaxException {
		List<Celebrity> list = new ArrayList<>();
		Celebrity tmp;
		String name;
		String[] bufferArray, bufferArray2;

		openFile();

//			System.out.println(input.nextLine());
		while (input.hasNextLine()) {
			// 模組儲存格式：
			// 模組名稱 爬蟲器名稱:平台:id,... 過濾器路徑, 管理面板路徑...
			Matcher matcher = Pattern.compile("\"(?<nm>.*)\" \\{(?<cs>.*)\\} '(?<pth>.*)'").matcher(input.nextLine());

			if (!matcher.find()) continue;

			// name
			name = matcher.group("nm");
			tmp = new Celebrity(name);

			// crawlers
			bufferArray = matcher.group("cs").split(",");
			for (String crawler:bufferArray) {
				bufferArray2 = crawler.split(":");
				tmp.getCrawlers().put(bufferArray2[0],
				                      Crawler.build(PLATFORM.fromString(bufferArray2[1]),
				                                    name,
				                                    bufferArray2[2]));
			}

			// path
			tmp.setPath(matcher.group("pth"));

			list.add(tmp);
		}

		closeFile();

		return list;
	}

	private static void openFile() throws IOException {
		input = new Scanner(new FileReader(Paths.get(fileName).toString()));
	}

	private static void closeFile() {
		if (input != null)
			input.close();
	}
}
