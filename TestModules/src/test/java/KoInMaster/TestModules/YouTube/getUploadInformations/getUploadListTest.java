package KoInMaster.TestModules.YouTube.getUploadInformations;

import KoInMaster.TestModules.Posts.Post;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class getUploadListTest {
	private static getUploadList solution;

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		solution = new getUploadList();

		System.out.println("ready to crawl");
		List<Post> list = solution.searchChannel("UCDqI2jOz0weumE8s7paEk6g");
		for (Post p:list)
			System.out.println(p);
	}
}
