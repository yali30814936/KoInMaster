package Posts;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Use this static class to read or write Celebrities object into "Celebrities.json"
 */
public class PostListReadWrite {
	private static final String filename = "/Data/PostList.json";

	public static void write(PostList posts) {
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + filename), StandardCharsets.UTF_8);
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
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + filename);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			JSONArray objs = new JSONArray();
			br.lines().forEach(s -> objs.put(new JSONObject(s)));
			br.close();
			return new PostList(objs);
		}
		catch (NoSuchElementException | FileNotFoundException ex){
			return new PostList();
		}
	}
}
