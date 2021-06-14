package KoInMaster.TestModules.Core;

import KoInMaster.TestModules.Celebrities.Celebrity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

public class CelebritiesFileReaderTest {
	public static void main(String[] args) throws GeneralSecurityException, IOException, URISyntaxException {
		List<Celebrity> celebrities = CelebritiesFileReader.readModules();
		System.out.println(celebrities);
	}
}
