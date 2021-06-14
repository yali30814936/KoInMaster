package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawlers;

import java.util.ArrayList;
import java.util.List;

public class Celebrity {
	private String name;
	private final Crawlers crawlers;
	private final List<String> filterPaths;
	private String managePath;

	public Celebrity(String name) {
		this.name = name;
		crawlers = new Crawlers();
		filterPaths = new ArrayList<>();
	}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}

	public Crawlers getCrawlers() {return crawlers;}

	public List<String> getFilterPaths() { return filterPaths; }


	public String getManagePath() {	return managePath; }

	public void setManagePath(String managePath) { this.managePath = managePath; }

	@Override
	public String toString() {
		return "Celebrity{" +
				"name='" + name + '\'' +
				", crawlers=" + crawlers +
				", filterPaths=" + filterPaths +
				", managePath='" + managePath + '\'' +
				'}';
	}
}
