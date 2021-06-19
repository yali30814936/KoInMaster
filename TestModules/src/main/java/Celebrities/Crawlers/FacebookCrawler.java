package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.PostList;

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

    public String getFormatParam() {
        return "page id = " + param;
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
