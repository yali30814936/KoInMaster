package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PostList;

import java.util.concurrent.Callable;

public abstract class Crawler implements Callable<PostList> {
	protected String name; // celebrity's name

	public Crawler(String name) {
		this.name = name;
	}
}
