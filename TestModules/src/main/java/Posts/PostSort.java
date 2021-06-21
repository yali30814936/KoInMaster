package Posts;

import java.util.Comparator;

public class PostSort implements Comparator<Post> {
	@Override
	public int compare(Post o1, Post o2) {
		if (o1.getType() == TYPE.LIVE && o2.getType() != TYPE.LIVE)
			return 1;
		else if (o1.getType() != TYPE.LIVE && o2.getType() == TYPE.LIVE)
			return -1;
		else
			return o1.getPublishedTime().compareTo(o2.getPublishedTime());
	}

	@Override
	public Comparator<Post> reversed() {
		return Comparator.super.reversed();
	}
}
