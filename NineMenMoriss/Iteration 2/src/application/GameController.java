package application;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;

public class GameController extends MainController {
	
	@FXML
    private Circle AIPlayerStone1;

    @FXML
    private Circle AIPlayerStone2;

    @FXML
    private Circle AIPlayerStone3;

    @FXML
    private Circle AIPlayerStone4;

    @FXML
    private Circle AIPlayerStone5;

    @FXML
    private Circle AIPlayerStone6;

    @FXML
    private Circle AIPlayerStone7;

    @FXML
    private Circle AIPlayerStone8;

    @FXML
    private Circle AIPlayerStone9;

    @FXML
    private Circle BaordPoint21;

    @FXML
    private Circle BoardPoint1;

    @FXML
    private Circle BoardPoint10;

    @FXML
    private Circle BoardPoint11;

    @FXML
    private Circle BoardPoint12;

    @FXML
    private Circle BoardPoint13;

    @FXML
    private Circle BoardPoint14;

    @FXML
    private Circle BoardPoint15;

    @FXML
    private Circle BoardPoint16;

    @FXML
    private Circle BoardPoint17;

    @FXML
    private Circle BoardPoint18;

    @FXML
    private Circle BoardPoint19;

    @FXML
    private Circle BoardPoint2;

    @FXML
    private Circle BoardPoint20;

    @FXML
    private Circle BoardPoint22;

    @FXML
    private Circle BoardPoint23;

    @FXML
    private Circle BoardPoint24;

    @FXML
    private Circle BoardPoint3;

    @FXML
    private Circle BoardPoint4;

    @FXML
    private Circle BoardPoint5;

    @FXML
    private Circle BoardPoint6;

    @FXML
    private Circle BoardPoint7;

    @FXML
    private Circle BoardPoint8;

    @FXML
    private Circle BoardPoint9;

    @FXML
    private Circle HumanPlayerStone1;

    @FXML
    private Circle HumanPlayerStone2;

    @FXML
    private Circle HumanPlayerStone3;

    @FXML
    private Circle HumanPlayerStone4;

    @FXML
    private Circle HumanPlayerStone5;

    @FXML
    private Circle HumanPlayerStone6;

    @FXML
    private Circle HumanPlayerStone7;

    @FXML
    private Circle HumanPlayerStone8;

    @FXML
    private Circle HumanPlayerStone9;

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
    void point10Clicked(MouseEvent event) {
    }

    @FXML
    void point11Clicked(MouseEvent event) {
    }

    @FXML
    void point12Clicked(MouseEvent event) {
    }

    @FXML
    void point13Clicked(MouseEvent event) {
    }

    @FXML
    void point14Clicked(MouseEvent event) {
    }

    @FXML
    void point15clicked(MouseEvent event) {
    }

    @FXML
    void point16Clicked(MouseEvent event) {
    }

    @FXML
    void point17Clicked(MouseEvent event) {
    }

    @FXML
    void point18Clicked(MouseEvent event) {
    }

    @FXML
    void point19Clicked(MouseEvent event) {
    }

    @FXML
    void point1Clicked(MouseEvent event) {
    }

    @FXML
    void point20Clicked(MouseEvent event) {
    }

    @FXML
    void point21Clicked(MouseEvent event) {
    }

    @FXML
    void point22Clicked(MouseEvent event) {
    }

    @FXML
    void point23Clicked(MouseEvent event) {
    }

    @FXML
    void point24Clicked(MouseEvent event) {
    }

    @FXML
    void point2Clicked(MouseEvent event) {
    }

    @FXML
    void point3Clicked(MouseEvent event) {
    }

    @FXML
    void point4Clicked(MouseEvent event) {
    }

    @FXML
    void point5Clicked(MouseEvent event) {
    }

    @FXML
    void point6Clicked(MouseEvent event) {
    }

    @FXML
    void point7Clicked(MouseEvent event) {
    }

    @FXML
    void point8Clicked(MouseEvent event) {
    }

    @FXML
    void point9Clicked(MouseEvent event) {
    }

    @FXML
    void pointClicked(MouseEvent event) {
    }

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
