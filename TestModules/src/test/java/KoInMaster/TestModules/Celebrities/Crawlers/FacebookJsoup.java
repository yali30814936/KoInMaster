package KoInMaster.TestModules.Celebrities.Crawlers;

import KoInMaster.TestModules.Posts.FbPost;
import KoInMaster.TestModules.Posts.Post;
import KoInMaster.TestModules.Posts.PostList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacebookJsoup {
    private  final String name;
    private Document doc;
    private Elements postItems;
    private String response;
    public FacebookJsoup(String name,String response) {
        this.name=name;
        this.response=response;
    }
    public PostList getList()throws InterruptedException{

        doc = Jsoup.parse(response);
        postItems =doc.getElementsByClass("_1dwg _1w_m _q7o");

        PostList list = new PostList();
        for(Element postItem : postItems){
            String finalText;
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
            finalText=content.html();
            finalText=finalText.replaceAll("<br>","\n");
            finalText=finalText.replaceAll("</span.*>","");
            finalText=finalText.replaceAll("<p>","");
            finalText=finalText.replaceAll("</p>","\n");
            finalText=finalText.replaceAll("</div.*>","");
            finalText=finalText.replaceAll("<div.*>","");
            finalText=finalText.replaceAll("<span.*>","");
            Date date=new Date(Long.valueOf(time.attr("data-utime"))*1000);
            String URL="https://www.facebook.com/"+postHref.attr("href");
            URL=URL.replaceAll("\\?.*","");
            Post temp=new FbPost(name,URL,mediaTemp,date,finalText);
            list.add(temp);
        }
        return list;
    }
}
