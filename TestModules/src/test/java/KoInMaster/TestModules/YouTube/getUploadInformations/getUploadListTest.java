package KoInMaster.TestModules.YouTube.getUploadInformations;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class getUploadListTest {
	private static getUploadList solution;

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		solution = new getUploadList();

		System.out.println(solution.searchChannel("UCIG9rDtgR45VCZmYnd-4DUw"));
		System.out.println(solution.searchChannel("UCIG9rDtgR45VCZmYnd-4DUw"));
	}
}
