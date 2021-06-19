package Posts;

public enum PLATFORM {
	YOUTUBE("YouTube"),
	TWITTER("Twitter"),
	FACEBOOK("Facebook");

	private final String displayName;
	PLATFORM(String displayName) {this.displayName = displayName;}

	public static PLATFORM fromString(String platform) {
		for (PLATFORM p:PLATFORM.values()) {
			if (p.displayName.equals(platform))
				return p;
		}
		throw new IllegalArgumentException("platform text analyze failed with" + platform + ".");
	}

	@Override
	public String toString() {return displayName;}
}
