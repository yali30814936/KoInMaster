package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawlers;

public class Celebrity {
	private String name;
	private Crawlers crawlers;

	public Celebrity(String name) {
		this.name = name;
		crawlers = new Crawlers();
	}

	public Crawlers getCrawlers() {return crawlers;}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}
}
