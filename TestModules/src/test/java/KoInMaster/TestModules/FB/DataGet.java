package KoInMaster.TestModules.FB;

import KoInMaster.TestModules.Posts.FbPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.IOException;


public class DataGet {
    private Document doc;
    private Elements postitems;
    private List<FbPost> totalPost;
    public  void setElement() throws InterruptedException, ParseException {
        totalPost=new ArrayList<>();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Documents\\GitHub\\KoInMaster\\TestModules\\chromedriver.exe");
        WebDriver browser =new ChromeDriver();
        browser.get("https://zh-tw.facebook.com/ey.gov.tw/");
        //for(int i=0;i<5;i++) {
        //         Thread.sleep(5000);
        //         ((JavascriptExecutor) browser).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        //    }
        //browser.findElement(By.id("expanding_cta_close_button")).click();
        Thread.sleep(15000);
        doc = Jsoup.parse(browser.getPageSource());
        postitems=doc.getElementsByClass("_1dwg _1w_m _q7o");


        for(Element postitem :postitems){
            List<String> mediaTemp=new ArrayList<>();
            Elements content=postitem.getElementsByClass("_5pbx userContent _3576");
            Elements postimages=postitem.getElementsByClass("_5dec _xcx");
            Elements images=postitem.getElementsByClass("scaledImageFitWidth img");
            Elements images2=postitem.getElementsByClass("scaledImageFitHeight img");
            Elements posthref=postitem.getElementsByClass("_5pcq");
            Elements time=postitem.getElementsByClass("_5ptz timestamp livetimestamp");
            for(Element postimage:postimages){
                //System.out.printf("%s%n","https://www.facebook.com/"+postimage.attr("href"));
                mediaTemp.add("https://www.facebook.com/"+postimage.attr("href").replaceAll("\\?.*",""));
            }
            for(Element image :images){
                //System.out.printf("%s%n",image.attr("src"));
                mediaTemp.add(image.attr("src"));
            }
            for(Element image2 :images2){
               // System.out.printf("%s%n",image2.attr("src"));
                mediaTemp.add(image2.attr("src"));
            }
            //System.out.printf("%s%n",content.text());
            //System.out.printf("%s%n",time.attr("data-utime"));
            Date date=new Date(Integer.valueOf(time.attr("data-utime")));
            String URL="https://www.facebook.com/"+posthref.attr("href");
            URL=URL.replaceAll("\\?.*","");
            FbPost temp=new FbPost(URL,mediaTemp,date,content.text());
            totalPost.add(temp);

        }

        browser.close();
    }
    public List<FbPost> getFbPost(){
        return totalPost;
    }
}
