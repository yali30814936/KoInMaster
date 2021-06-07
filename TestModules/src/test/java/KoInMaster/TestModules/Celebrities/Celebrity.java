package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;

import java.util.ArrayList;
import java.util.List;

public class Celebrity {
	private String name;
	private List<Crawler> crawlers;

	public Celebrity(String name) {
		this.name = name;
		crawlers = new ArrayList<>();
	}

	public List<Crawler> getCrawlers() {return crawlers;}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}
}
