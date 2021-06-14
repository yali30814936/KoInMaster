package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.PLATFORM;
import KoInMaster.TestModules.Posts.PostList;

import java.io.IOException;
import java.net.URISyntaxException;

public class FacebookCrawler extends Crawler {
    private final FacebookRequestPosts requestPosts;
    public FacebookCrawler(String name, String URL) throws URISyntaxException, IOException {
      super(name, PLATFORM.FACEBOOK);
      param = FacebookPageIdFetcher.getPageId(URL);
      requestPosts=new FacebookRequestPosts(param);
    }

    public  PostList getList() throws IOException, InterruptedException {
        FacebookJsoup fbJsoup = new FacebookJsoup(name, requestPosts.getResponse());
        return fbJsoup.getList();
    }

    @Override
    public PostList call() throws InterruptedException, IOException { return getList(); }

    @Override
    public String toString() {
        return "FacebookCrawler{" +
                "name='" + name + '\'' +
                ", platform=" + platform +
                ", postId='" + param + '\'' +
                '}';
    }
}
