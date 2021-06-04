package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Posts.Post;

import java.util.ArrayList;
import java.util.List;

public class Celebrity {
	private String name;
	private Crawler[] crawlers;

	// return a list of posts sorted by publish time
	public List<Post> getPostList() {
		List<Post> postList = new ArrayList<>();

		return postList;
	}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}
}
