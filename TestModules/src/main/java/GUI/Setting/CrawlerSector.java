package GUI.Setting;

import Core.Data;
import Posts.PLATFORM;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrawlerSector extends Box {
	private Data data;

	public CrawlerSector(int padding) {
		super(BoxLayout.PAGE_AXIS);

	}

	public void setData(Data data) {
		this.data = data;
	}

	public void showCrawlers() {
		removeAll();

	}

	protected class newCrawler extends Box {

		public newCrawler() {
			super(BoxLayout.LINE_AXIS);

			List<String> platforms = Stream.of(PLATFORM.values())
			                               .map(PLATFORM::toString)
			                               .collect(Collectors.toList());
		}
	}
}
