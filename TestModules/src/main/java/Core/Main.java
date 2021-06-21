package Core;

import GUI.MainGUI;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

public class Main {

	public static void main(String[] args) {
		// set image property
		System.setProperty("sun.java2d.uiScale", "1.0");

		// show main window
		MainGUI app = new MainGUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(1080, 720);
		app.setVisible(true);

		Data data = new Data();

		// load celebrities
		try {
			data.readData();
		} catch (GeneralSecurityException | URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(app, e.getMessage(), "讀取模組時發生錯誤！", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}

		// load type

		// setup filter
		app.setData(data);
		app.loadFilter();
		app.setFilterEnabled(true);

		// load PostList
		data.readPosts();
		app.setRefreshEnabled(true);
		app.refreshBlock();
	}
}
