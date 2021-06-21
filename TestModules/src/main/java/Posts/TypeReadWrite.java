package Posts;

import twitter4j.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TypeReadWrite {
	private static final String filename = "Types.json";

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
		FileReader fr = new FileReader(Paths.get(filename).toString());
		BufferedReader br = new BufferedReader(fr);
		JSONArray obj = new JSONArray(br.readLine());
		List<TYPE> list = new ArrayList<>();
		for (int i = 0; i < obj.length(); i++)
			list.add(TYPE.fromString(obj.getString(i)));
		return list;
	}
}
