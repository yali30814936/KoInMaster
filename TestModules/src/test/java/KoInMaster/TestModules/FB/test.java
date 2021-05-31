package KoInMaster.TestModules.FB;
import KoInMaster.TestModules.Posts.FbPost;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        DataGet test =new DataGet();
        try {
            test.setElement();
            List<FbPost> Posts=test.getFbPost();
            for(FbPost post:Posts)
            System.out.printf("%s%n",post.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e){

        }
    }
}
