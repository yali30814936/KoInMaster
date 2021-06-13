package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PostList;

import java.io.IOException;
import java.net.URISyntaxException;

public class FacebookCrawler extends Crawler {
    private final FacebookRequestPosts requestPosts;
    public FacebookCrawler(String name,String URL) throws URISyntaxException, IOException {
      super(name);
      long tmp = System.currentTimeMillis();
      requestPosts=new FacebookRequestPosts(FacebookPageIdFetcher.getPageId(URL));
      System.out.println(System.currentTimeMillis() - tmp);
    }

    public  PostList getList() throws IOException, InterruptedException {
        FacebookJsoup fbJsoup = new FacebookJsoup(name, requestPosts.getResponse());
        return fbJsoup.getList();
    }

    @Override
    public PostList call() throws InterruptedException, IOException { return getList(); }
}
