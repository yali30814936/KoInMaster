package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawlers;

public class Celebrity {
	private String name;
	private final Crawlers crawlers;
	private String path;

	public Celebrity(String name) {
		this.name = name;
		crawlers = new Crawlers();
		path = "";
	}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}

	public Crawlers getCrawlers() {return crawlers;}

	public String getPath() { return path; }

	public void setPath(String path) { this.path = path; }

	@Override
	public String toString() {
		return "Celebrity{" +
				"name='" + name + '\'' +
				", crawlers=" + crawlers +
				", path=" + path +
				'}';
	}
}
