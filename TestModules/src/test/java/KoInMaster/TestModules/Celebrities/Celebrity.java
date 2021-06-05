package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Posts.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Celebrity {
	private String name;
	private List<Callable<Post>> crawlers;

	// return a list of posts sorted by publish time
	public List<Post> getPostList() {
		List<Post> postList = new ArrayList<>();

		return postList;
	}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}
}
