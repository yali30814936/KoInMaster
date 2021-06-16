package KoInMaster.TestModules.Celebrities;

import KoInMaster.TestModules.Celebrities.Crawlers.Crawler;
import KoInMaster.TestModules.Celebrities.Crawlers.Crawlers;
import KoInMaster.TestModules.Posts.PLATFORM;
import twitter4j.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Map;

public class Celebrity {
	private String name;
	private final Crawlers crawlers;
	private String path;
	private boolean enabled;

	public Celebrity(String name) {
		this.name = name;
		crawlers = new Crawlers();
		path = "";
	}

	public Celebrity(JSONObject object) throws GeneralSecurityException, IOException, URISyntaxException {
		this("");

		// name
		name = object.getString("name");

		// crawlers
		Iterator<String> crawler = object.getJSONObject("crawlers").keys();
		JSONObject c;
		if (crawler.hasNext()) {
			String key = crawler.next();
			c = object.getJSONObject("crawlers").getJSONObject(key);
			crawlers.put(key,
			             Crawler.build(PLATFORM.fromString(c.getString("platform")),
			                           name,
			                           c.getString("param")));
		}

		// path
		path = object.getString("path");

		// enabled
		enabled = object.getBoolean("enabled");
	}

	public void setName(String name) {this.name = name;}

	public String getName() {return name;}

	public Crawlers getCrawlers() {return crawlers;}

	public String getPath() { return path; }

	public void setPath(String path) { this.path = path; }

	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled; }

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		// name
		obj.put("name", name);

		// crawlers
		JSONObject cls = new JSONObject();
		JSONObject tmp;
		for (Map.Entry<String, Crawler> c:crawlers.entrySet()) {
			tmp = new JSONObject();
			tmp.put("platform", c.getValue().getPlatform().toString());
			tmp.put("param", c.getValue().getParam());

			cls.put(c.getKey(), tmp);
		}
		obj.put("crawlers", cls);

		// path
		obj.put("path", path);

		// enabled
		obj.put("enabled", enabled);

		return obj;
	}

	@Override
	public String toString() {
		return "Celebrity{" +
				"name='" + name + '\'' +
				", crawlers=" + crawlers +
				", path=" + path +
				'}';
	}
}
