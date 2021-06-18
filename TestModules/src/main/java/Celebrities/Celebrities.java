package Celebrities;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Celebrities extends ArrayList<Celebrity> {
	// constructor
	public Celebrities() {}

	public Celebrities(List<Celebrity> list) {
		addAll(list);
	}

	public Celebrities(JSONArray jsonArray) throws GeneralSecurityException, IOException, URISyntaxException {
		for (int i = 0; i < jsonArray.length(); i++)
			add(new Celebrity((JSONObject) jsonArray.get(i)));
	}

	public JSONArray toJSONArray() {
		JSONArray jsonArray = new JSONArray();

		for (Celebrity celebrity:this)
			jsonArray.put(celebrity.toJSONObject());

		return jsonArray;
	}
}
