package KoInMaster.TestModules.Posts;

public enum PLATFORM {
	YOUTUBE("YouTube"),
	TWITTER("推特"),
	FACEBOOK("臉書");

	private final String displayName;
	PLATFORM(String displayName) {this.displayName = displayName;}

	@Override
	public String toString() {return displayName;}
}
