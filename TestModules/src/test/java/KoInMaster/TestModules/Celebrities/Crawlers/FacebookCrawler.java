package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.FbPost;
import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacebookCrawler extends Crawler {
    private final String URL;
    private FacebookJsoup fbJsoup;
    private FacebookPageIdFetcher fetcher;
    private FacebookRequestPosts requestPosts;
    public FacebookCrawler(String name,String URL) {
      super(name);
      this.URL=URL;
        fetcher=new FacebookPageIdFetcher();
    }

    public  PostList getList() throws URISyntaxException, IOException, InterruptedException {
        requestPosts=new FacebookRequestPosts(fetcher.getPageId(URL));
        fbJsoup=new FacebookJsoup(name,requestPosts.getResponse());
        return fbJsoup.getList();
    }

    @Override
    public PostList call() throws InterruptedException, URISyntaxException, IOException { return getList(); }
}
