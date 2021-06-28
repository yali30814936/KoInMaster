package Posts;

import java.util.stream.Stream;

public enum PLATFORM {
	YOUTUBE("YouTube"),
	TWITTER("Twitter"),
	FACEBOOK("Facebook");

	private final String displayName;
	PLATFORM(String displayName) {this.displayName = displayName;}

	public static PLATFORM fromString(String platform) {
		for (PLATFORM p:values()) {
			if (p.displayName.equals(platform))
				return p;
		}
		throw new IllegalArgumentException("platform text analyze failed with" + platform + ".");
	}

	public static String[] getValues() {
		return Stream.of(values())
		             .map(PLATFORM::toString)
		             .toArray(String[]::new);
	}

	@Override
	public String toString() {return displayName;}
}
