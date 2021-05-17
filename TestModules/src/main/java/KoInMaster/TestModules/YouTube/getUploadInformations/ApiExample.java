package KoInMaster.TestModules.YouTube.getUploadInformations;

/**
 * Sample Java code for youtube.channels.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/guides/code_samples#java
 */

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class ApiExample {
	// You need to set this value for your code to compile.
	// For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
	private static final Properties props = new Properties();
//	private static final String DEVELOPER_KEY = ;

	private static final String APPLICATION_NAME = "API code samples";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/**
	 * Build and return an authorized API client service.
	 *
	 * @return an authorized API client service
	 * @throws GeneralSecurityException, IOException
	 */
	public static YouTube getService() throws GeneralSecurityException, IOException {
		final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * Call function to create API service object. Define and
	 * execute API request. Print API response.
	 *
	 * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
	 */
	public static void main(String[] args)
			throws GeneralSecurityException, IOException, GoogleJsonResponseException {
		YouTube youtubeService = getService();
		// Define and execute the API request
		props.load(ApiExample.class.getClassLoader().getResourceAsStream("api.properties"));
		YouTube.Channels.List request = youtubeService.channels()
		                                              .list(Collections.singletonList("snippet,contentDetails"));
		ChannelListResponse response = request.setKey(props.getProperty("youtube"))
		                                      .setId(Collections.singletonList("UC1DCedRgGHBdm81E1llLhOQ"))
		                                      .execute();
		System.out.println(response);
	}
}