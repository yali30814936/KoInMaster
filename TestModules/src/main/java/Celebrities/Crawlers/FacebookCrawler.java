package Celebrities.Crawlers;

import Posts.PLATFORM;
import Posts.PostList;

import java.io.IOException;
import java.net.URISyntaxException;

public class FacebookCrawler extends Crawler {
    private final FacebookRequestPosts requestPosts;
    public FacebookCrawler(String name, String pageId) throws URISyntaxException{
        super(name, PLATFORM.FACEBOOK);
        param = pageId;
        requestPosts = new FacebookRequestPosts(param);
    }

    public static Crawler rawBuild(String name, String raw) throws URISyntaxException, IOException {
        String pageId = FacebookPageIdFetcher.getPageId(raw);
        return new FacebookCrawler(name, pageId);
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
