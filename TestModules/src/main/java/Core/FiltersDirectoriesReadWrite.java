package Core;

import twitter4j.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FiltersDirectoriesReadWrite {
	private static final String filename = "/Data/Directories.json";

	public static void write(List<String> folders) throws IOException {
		JSONArray array = new JSONArray(folders);
		FileWriter fw = new FileWriter(System.getProperty("user.dir") + filename);
		fw.write(array.toString());
		fw.flush();
		fw.close();
	}

	public static List<String> read() throws IOException{
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + filename);
			BufferedReader br = new BufferedReader(fr);
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
