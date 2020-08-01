package application;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;

public class GameController extends MainController {

    @FXML
    private Button exitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Label namePlayer2;

    @FXML
    private Label namePlayer1;

    @FXML
    private Circle outSqSouthLiEndP1;
    
    /**
     * Base idea https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
     * 
     * @param event
     */
    @FXML
    public void exitCurrentGame(ActionEvent event) {
    	Alert gameQuitAlert = new Alert(AlertType.CONFIRMATION);
    	gameQuitAlert.setTitle("Quit");
    	gameQuitAlert.setHeaderText("Quitting");
    	gameQuitAlert.setContentText("Are you sure you want to quit the game?\nAll progress will be lost.");
    	
    	if (gameQuitAlert.showAndWait().get() == ButtonType.OK) {
        	MainApp.changeScreen("src/view/Main.fxml");    		
    	}
    }

    @FXML
    public void rulesPressed(ActionEvent event) {
    	displayRules(event);
    }

    @FXML
    public void outSqSouthLiEndP1Selected(ActionEvent event) {
    	displayRules(event);
    }

    @FXML
    void initialize() {
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'Board.fxml'.";
        assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Board.fxml'.";
    }
}
