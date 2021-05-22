package KoInMaster.TestModules.YouTube.getUploadInformations;

/**
 * Sample Java code for youtube.search.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/guides/code_samples#java
 */

import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class ApiExample {
	private static Properties props = new Properties();

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
		YouTube.Search.List request = youtubeService.search()
		                                            .list(Collections.singletonList("snippet"));
		props.load(ApiExample1.class.getClassLoader().getResourceAsStream("api.properties"));
		SearchListResponse response = request.setKey(props.getProperty("youtube"))
		                                     .setChannelId("UC1DCedRgGHBdm81E1llLhOQ")
		                                     .setEventType("live")
		                                     .setMaxResults(5L)
		                                     .setType(Collections.singletonList("video"))
		                                     .execute();
		System.out.println(response);
	}
}