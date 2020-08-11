/* 
 * MainApp runs GUI 
 */

package application;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * public class MainApp runs the overall program
 * 
 * 10.00 6 August 2020 Daniel Kim Team D
 */

public class MainApp extends Application {
	private static Stage stage;

	/**
	 * Start sets the initial menu screen when you run app
	 * 
	 * @param primaryStage is the stage that is to be set to shown
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
			Scene scene = new Scene(loader.load(new FileInputStream("src/view/Main.fxml")), 500, 500);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Morris");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			System.out.println("Problem loading Main.fxml");
		}
	}

	/**
	 * Idea borrowed from
	 * https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx
	 * and PollTrackerApp.java from Team Mission 2
	 * 
	 * @param fxmlFile
	 */
	public static void changeScreen(String fxmlFile) {
		try {
			FXMLLoader loader = new FXMLLoader();
			Scene scene = new Scene(loader.load(new FileInputStream(fxmlFile)));
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println("Problem loading " + fxmlFile);
		}
	}

	/**
	 * main is what launches the program when it is actually run
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
