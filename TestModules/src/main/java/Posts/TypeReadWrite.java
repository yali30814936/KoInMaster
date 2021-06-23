package Posts;

import twitter4j.JSONArray;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TypeReadWrite {
	private static final String filename = "Data/Types.json";

	public static void write(List<TYPE> types) throws IOException {
		JSONArray array = new JSONArray();
		for (TYPE type:types)
			array.put(type.toString());
		FileWriter fw = new FileWriter(Paths.get(filename).toString());
		fw.write(array.toString());
		fw.flush();
		fw.close();
	}

	public static List<TYPE> read() throws IOException{
		try {
			FileReader fr = new FileReader(Paths.get(filename).toString());
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
