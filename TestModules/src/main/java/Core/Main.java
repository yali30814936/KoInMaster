package Core;

import Celebrities.Celebrities;
import GUI.MainGUI;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Main {
	private static MainGUI app;
	private static Celebrities celebrities;
	private static List<String> directories;

	public static void main(String[] args) {
		// show main window
		app = new MainGUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(1080,720);
		app.setVisible(true);

		// load celebrities
		try {
			celebrities = CelebritiesReadWrite.read();
			directories = FiltersDirectoriesReadWrite.read();
		} catch (GeneralSecurityException | URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(app, e.getMessage(), "讀取模組時發生錯誤！", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}

		// setup filter
		app.setDirectories(directories);
		app.setCelebrities(celebrities);
		app.loadFilter();
		app.setFunctionEnabled(true);
	}
}
