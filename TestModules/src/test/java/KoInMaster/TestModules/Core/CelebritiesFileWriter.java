package KoInMaster.TestModules.Core;

import KoInMaster.TestModules.Celebrities.Celebrity;
import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class CelebritiesFileWriter {
	private Formatter output;
	private final String fileName = "modules.txt";

	public void write(List<Celebrity> list) throws IOException {
		openFile();

		for (Celebrity c:list) {
			// 模組儲存格式：
			// 模組名稱 爬蟲器名稱:平台:id,... 過濾器路徑, 管理面板路徑...

			List<String> crawlers = new ArrayList<>();
			for (Map.Entry<String, Crawler> entry:c.getCrawlers().entrySet())
				crawlers.add(String.format("%s:%s:%s",
				                           entry.getKey(),
				                           entry.getValue().getPlatform(),
				                           entry.getValue().getParam()
				                           ));
			output.format("\"%s\" {%s} [%s] '%s'%n",
			              c.getName(),
			              String.join(",", crawlers),
			              String.join(",", c.getFilterPaths()),
			              c.getManagePath());

		}

		closeFile();
	}

	private void openFile() throws IOException {
		FileWriter fw = new FileWriter(Paths.get(fileName).toString(), true);
		output = new Formatter(fw);
	}

	private void closeFile() {
		if (output != null)
			output.close();
	}
}
