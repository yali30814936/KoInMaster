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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacebookCrawler extends Crawler {
    private  final String url;
    private Document doc;
    private Elements postItems;
    public FacebookCrawler(String name,String URL) {
        super(name);
        this.url=URL;

    }
    public  PostList getList()throws InterruptedException{
        long t1 = System.currentTimeMillis();
//        System.out.println("ready to launch");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("-headless");
        System.setProperty("webdriver.chrome.driver", Paths.get("chromedriver.exe").toAbsolutePath().toString());
        WebDriver browser =new ChromeDriver(chromeOptions);

        long t2 = System.currentTimeMillis();
//        System.out.println("ready to get: " + (t2 - t1));
        browser.get(url);
        for(int i=0;i<3;i++) {
            Thread.sleep(1500);
            ((JavascriptExecutor) browser).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }
        browser.findElement(By.id("expanding_cta_close_button")).click();
        //Thread.sleep(500);
        doc = Jsoup.parse(browser.getPageSource());
        postItems =doc.getElementsByClass("_1dwg _1w_m _q7o");

        long t3 = System.currentTimeMillis();
//        System.out.println("ready to parse: " + (t3 - t2));
        browser.close();
        PostList list = new PostList();
        for(Element postItem : postItems){
            List<String> mediaTemp=new ArrayList<>();
            Elements content=postItem.getElementsByClass("_5pbx userContent _3576");
            Elements images=postItem.getElementsByClass("scaledImageFitWidth img");
            Elements images2=postItem.getElementsByClass("scaledImageFitHeight img");
            Elements postHref=postItem.getElementsByClass("_5pcq");
            Elements time=postItem.getElementsByClass("_5ptz");
            for(Element image :images){
                mediaTemp.add(image.attr("src"));
            }
            for(Element image2 :images2){
                mediaTemp.add(image2.attr("src"));
            }
            Date date=new Date(Long.valueOf(time.attr("data-utime"))*1000);
            String URL="https://www.facebook.com/"+postHref.attr("href");
            URL=URL.replaceAll("\\?.*","");
            Post temp=new FbPost(name,URL,mediaTemp,date,content.text());
            list.add(temp);

        }

//        System.out.println("Complete: " + (System.currentTimeMillis() - t3));
        return list;
    }

    @Override
    public PostList call()throws InterruptedException{ return getList(); }
}
