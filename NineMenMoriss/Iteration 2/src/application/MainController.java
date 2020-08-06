/**
 * Sets up the initial screen of the GUI
 * 
 * @author Daniel Kim
 * @version 1.0
 * 10.00 6 August 2020
 * Team D
 */
package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainController {
	private Stage ruleStage;
	private boolean firstRulesOpen = false;

	@FXML
	private Button quitButton;

	@FXML
	private Button rulesButton;

	@FXML
	private Button tutorialButton;

	@FXML
	private Button playButton;

	/**
	 * The board fxml file is displayed when the play button is clicked.
	 * 
	 * @param event
	 */
	@FXML
	public void playGame(ActionEvent event) {
		MainApp.changeScreen("src/view/Board.fxml");
	}

	/**
	 * The overall app is terminated when the quit button is clicked.
	 * 
	 * @param event
	 */
	@FXML
	public void quitApp(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * When the rules button is clicked then a separate window is opened with 
	 * all the rules and restrictions of the game.
	 * 
	 * @param event
	 */
	@FXML
	public void displayRules(ActionEvent event) {
		if (!firstRulesOpen) {
			// only create a new rule stage when it is loaded for the first time. Otherwise simply show the stage.
			try {
				ruleStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Scene scene = new Scene(loader.load(new FileInputStream("src/view/Rules.fxml")), 600, 365);
				ruleStage.initStyle(StageStyle.UTILITY);
				ruleStage.setTitle("Rules");
				ruleStage.setScene(scene);
				firstRulesOpen = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Problem loading Rules.fxml");
			}
		}

		ruleStage.show();
	}

	/**
	 * Initializes the main screen
	 */
	@FXML
	void initialize() {
		assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert tutorialButton != null : "fx:id=\"tutorialButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'Main.fxml'.";
	}
}