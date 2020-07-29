package application;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GameController extends MainController {

    @FXML
    private Button exitButton;

    @FXML
    private Button rulesButton;

    @FXML
    public void exitCurrentGame(ActionEvent event) {
    	MainApp.changeScreen("src/view/Main.fxml");
    }

    @FXML
    public void rulesPressed(ActionEvent event) {
    	displayRules(event);
    }

    @FXML
    void initialize() {
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'Board.fxml'.";
        assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Board.fxml'.";
    }
}
