package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;



public class MainController {
	private Stage ruleStage;
	private boolean firstTutOpen = false;

    @FXML
    private Button quitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button playButton;

    @FXML
    public void playGame(ActionEvent event) {
    	MainApp.changeScreen("src/view/Board.fxml");
    }

    @FXML
    public void quitApp(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    public void displayRules(ActionEvent event) {
    	if (!firstTutOpen) {
    		try {
    			ruleStage = new Stage();
    			FXMLLoader loader = new FXMLLoader();
    			Scene scene = new Scene(loader.load(new FileInputStream("src/view/Rules.fxml")),600,365);
    			ruleStage.setResizable(false);
    			ruleStage.setTitle("Rules");
    			ruleStage.setScene(scene);
    			ruleStage.show();		
    			firstTutOpen = true;
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			System.out.println("Problem loading Game.fxml");
    		}    		
    	}
    	else {
    		ruleStage.show();
    	}
    }

    @FXML
    public void startTutorial(ActionEvent event) {
    	MainApp.changeScreen("src/view/Board.fxml");
    }

    @FXML
    void initialize() {
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'Main.fxml'.";
        assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Main.fxml'.";
        assert tutorialButton != null : "fx:id=\"tutorialButton\" was not injected: check your FXML file 'Main.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'Main.fxml'.";
    }
}