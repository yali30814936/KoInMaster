package Posts;

import twitter4j.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TypeReadWrite {
	private static final String filename = "/Data/Types.json";

	public static void write(List<TYPE> types) {
		JSONArray array = new JSONArray();
		for (TYPE type:types)
			array.put(type.toString());
		FileWriter fw = null;
		try {
			fw = new FileWriter(System.getProperty("user.dir") + filename);
			fw.write(array.toString());
			fw.flush();
			fw.close();
		} catch (FileNotFoundException ex) {
			File nFile = new File(System.getProperty("user.dir") + "/Data/");
			nFile.mkdirs();
			write(types);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<TYPE> read() throws IOException{
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + filename);
			BufferedReader br = new BufferedReader(fr);
			JSONArray obj = new JSONArray(br.readLine());
			List<TYPE> list = new ArrayList<>();
			for (int i = 0; i < obj.length(); i++)
				list.add(TYPE.fromString(obj.getString(i)));
			return list;
		} catch (FileNotFoundException ex) {
			return new ArrayList<>();
		}
	}
}
