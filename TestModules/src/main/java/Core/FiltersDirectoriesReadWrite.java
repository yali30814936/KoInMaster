package Core;

import twitter4j.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FiltersDirectoriesReadWrite {
	private static final String filename = "Directories.json";

	public static void write(List<String> folders) throws IOException {
		JSONArray array = new JSONArray(folders);
		FileWriter fw = new FileWriter(Paths.get(filename).toString());
		fw.write(array.toString());
		fw.flush();
		fw.close();
	}

	public static List<String> read() throws IOException{
		FileReader fr = new FileReader(Paths.get(filename).toString());
		BufferedReader br = new BufferedReader(fr);
		JSONArray obj = new JSONArray(br.readLine());
		List<String> list = new ArrayList<>();
		for (int i = 0; i < obj.length(); i++)
			list.add(obj.getString(i));
		return list;
	}

	public static void main(String[] args) throws IOException {
		List<String> test = new ArrayList<>();
		test.add("hololive/零期生");
		test.add("hololive/一期生");
		test.add("hololive/二期生");
		test.add("nijisanji");
		write(test);
	}
}