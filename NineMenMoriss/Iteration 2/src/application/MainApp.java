package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainApp extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
			Scene scene = new Scene(loader.load(new FileInputStream("src/view/Main.fxml")),500,500);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Morris");
	        primaryStage.setScene(scene);
	        primaryStage.show();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem loading Main.fxml");
		}
	}
	
	/**
	 * Idea borrowed from https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx
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
			// TODO Auto-generated catch block
			System.out.println("Problem loading " + fxmlFile);
		}		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
