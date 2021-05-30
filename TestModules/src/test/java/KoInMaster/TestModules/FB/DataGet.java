package KoInMaster.TestModules.FB;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class DataGet {
    private Document doc;
    private Elements postitems;
    public  void setElement() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Documents\\GitHub\\KoInMaster\\TestModules\\chromedriver.exe");
        WebDriver browser =new ChromeDriver();
        browser.get("https://zh-tw.facebook.com/ey.gov.tw/");
       for(int i=0;i<5;i++) {
            Thread.sleep(5000);
           ((JavascriptExecutor) browser).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }
        browser.findElement(By.id("expanding_cta_close_button")).click();
        Thread.sleep(5000);
        doc = Jsoup.parse(browser.getPageSource());
        postitems=doc.getElementsByClass("_1dwg _1w_m _q7o");


        for(Element postitem :postitems){
            Elements content=postitem.getElementsByClass("_5pbx userContent _3576");
            Elements image=postitem.getElementsByClass("uiScaledImageContainer");
            Elements posthref=postitem.getElementsByClass("_5pcq");
            System.out.printf("%s%n",image.attr("src"));
            System.out.printf("%s%n",content.text());
            System.out.printf("%s%n",posthref.attr("href"));
            System.out.println("----------------");
        }
    }

}
