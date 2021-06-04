package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.Post;

import java.io.IOException;
import java.util.List;

public interface Crawler {
	public abstract List<Post> getPostList() throws IOException;
}
