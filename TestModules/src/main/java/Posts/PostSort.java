package Posts;

import java.util.Comparator;

public class PostSort implements Comparator<Post> {
	@Override
	public int compare(Post o1, Post o2) {
		return o1.getPublishedTime().compareTo(o2.getPublishedTime());
	}

	@Override
	public Comparator<Post> reversed() {
		return Comparator.super.reversed();
	}
}
