package Posts;

public enum TYPE {
	NONE("NONE"),
	LIVE("LIVE"),
	UPCOMING("UPCOMING"),
	RT("RT"),
	REPLY("REPLY"),
	QUOTED("QUOTED");

	private final String displayName;

	TYPE(String displayName) {this.displayName = displayName;}

	public static TYPE fromString(String type) {
		for (TYPE p:TYPE.values()) {
			if (p.displayName.equals(type))
				return p;
		}
		throw new IllegalArgumentException("type text analyze failed with" + type + ".");
	}

	@Override
	public String toString() {return displayName;}
}
