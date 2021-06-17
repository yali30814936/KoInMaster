package Posts;

import Celebrities.Celebrities;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Use this static class to read or write Celebrities object into "Celebrities.json"
 */
public class PostListReadWrite {
	private static final String filename = "PostList.json";

	public static void write(PostList posts) throws IOException {
		FileWriter fw = new FileWriter(Paths.get(filename).toString());
		for(Post p:posts){
			fw.write(p.toJSONObject().toString()+"\n");
		}
		fw.flush();
		fw.close();
	}

	public static PostList read() throws IOException, GeneralSecurityException, URISyntaxException, ParseException {
		Scanner input=new Scanner(Paths.get(filename));
		JSONArray objs = new JSONArray();
		try {
			while (input.hasNext()){
				JSONObject obj = new JSONObject(input.nextLine());
				objs.put(obj);
			}
		}
		catch (NoSuchElementException  elementException){
			System.out.println("File improperly formed. Terminating");
		}
		PostList posts=new PostList(objs);
		return posts;
	}
}