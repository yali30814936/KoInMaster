package Posts;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Use this static class to read or write Celebrities object into "Celebrities.json"
 */
public class PostListReadWrite {
	private static final String filename = "/Data/PostList.json";

	public static void write(PostList posts) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(System.getProperty("user.dir") + filename);
			for(Post p:posts){
				fw.write(p.toJSONObject().toString()+"\n");
			}
			fw.flush();
			fw.close();
		} catch (FileNotFoundException ex) {
			File nFile = new File(System.getProperty("user.dir") + "/Data/");
			nFile.mkdirs();
			write(posts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PostList read() throws IOException, GeneralSecurityException, URISyntaxException, ParseException {
		try {
			FileReader input = new FileReader(System.getProperty("user.dir") + filename);
			BufferedReader bufferedReader = new BufferedReader(input);
			JSONArray objs = new JSONArray();
			bufferedReader.lines().forEach(s -> objs.put(new JSONObject(s)));
			bufferedReader.close();
			return new PostList(objs);
		}
		catch (NoSuchElementException | FileNotFoundException ex){
			return new PostList();
		}
	}
}
