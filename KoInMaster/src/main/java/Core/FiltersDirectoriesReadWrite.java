package Core;

import twitter4j.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FiltersDirectoriesReadWrite {
	private static final String filename = "/Data/Directories.json";

	public static void write(List<String> folders) {
		JSONArray array = new JSONArray(folders);
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + filename), StandardCharsets.UTF_8);
			fw.write(array.toString());
			fw.flush();
			fw.close();
		} catch (FileNotFoundException ex) {
			File nFile = new File(System.getProperty("user.dir") + "/Data/");
			nFile.mkdirs();
			write(folders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> read() throws IOException{
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + filename);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			JSONArray obj = new JSONArray(br.readLine());
			List<String> list = new ArrayList<>();
			for (int i = 0; i < obj.length(); i++)
				list.add(obj.getString(i));
			return list;
		} catch (FileNotFoundException ex) {
			return new ArrayList<>();
		}
	}
}
