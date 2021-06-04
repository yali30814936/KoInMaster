package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.util.List;

public interface Crawler {
	String platform  = "none";
	String url = "none";
	List<Post> getPostList();
}
