package Posts;

import java.util.stream.Stream;

public enum TYPE {
	NONE("影片"),
	LIVE("直播"),
	UPCOMING("即將上映"),
	TWEET("推文"),
	RT("轉推"),
	REPLY("回覆推文"),
	QUOTED("引用推文"),
	POST("貼文"),
	SHARED("分享貼文");

	private final String displayName;

	TYPE(String displayName) {this.displayName = displayName;}

	public static TYPE fromString(String type) {
		for (TYPE p:TYPE.values()) {
			if (p.displayName.equals(type))
				return p;
		}
		throw new IllegalArgumentException("type text analyze failed with" + type + ".");
	}

	public static String[] getValues() {
		return Stream.of(values())
		             .map(TYPE::toString)
		             .toArray(String[]::new);
	}

	@Override
	public String toString() {return displayName;}
}
