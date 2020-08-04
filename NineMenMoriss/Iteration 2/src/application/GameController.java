package application;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

import model.Point;
import model.Stone;
import model.GameShared;
import model.AIPlayer;

public class GameController extends MainController {
	@FXML
	private Pane boardPane;
	
    @FXML
    private Button exitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Label namePlayer2;

    @FXML
    private Label namePlayer1;

    @FXML
    private Label statusLabel;

    @FXML
    private Button outSqLineSEnd1;

    @FXML
    private Button outSqLineSMid;

    @FXML
    private Button outSqLineSEnd2;

    @FXML
    private Button outSqLineWMid;

    @FXML
    private Button outSqLineEMid;

    @FXML
    private Button outSqLineNEnd1;

    @FXML
    private Button outSqLineNMid;

    @FXML
    private Button outSqLineNEnd2;

    @FXML
    private Button midSqLineSEnd1;

    @FXML
    private Button midSqLineSMid;

    @FXML
    private Button midSqLineSEnd2;

    @FXML
    private Button midSqLineWMid;

    @FXML
    private Button midSqLineEMid;

    @FXML
    private Button midSqLineNEnd1;

    @FXML
    private Button midSqLineNMid;

    @FXML
    private Button midSqLineNEnd2;

    @FXML
    private Button innSqLineSEnd1;

    @FXML
    private Button innSqLineSMid;

    @FXML
    private Button innSqLineSEnd2;

    @FXML
    private Button innSqLineWMid;

    @FXML
    private Button innSqLineEMid;

    @FXML
    private Button innSqLineNEnd1;

    @FXML
    private Button innSqLineNMid;

    @FXML
    private Button innSqLineNEnd2;
    
    @FXML
    private Circle humanStone1;

    @FXML
    private Circle humanStone2;

    @FXML
    private Circle humanStone3;

    @FXML
    private Circle humanStone4;

    @FXML
    private Circle humanStone5;

    @FXML
    private Circle humanStone6;

    @FXML
    private Circle humanStone7;

    @FXML
    private Circle humanStone8;

    @FXML
    private Circle humanStone9;

    @FXML
    private Circle compStone1;

    @FXML
    private Circle compStone2;

    @FXML
    private Circle compStone3;

    @FXML
    private Circle compStone4;

    @FXML
    private Circle compStone5;

    @FXML
    private Circle compStone6;

    @FXML
    private Circle compStone7;

    @FXML
    private Circle compStone8;

    @FXML
    private Circle compStone9;

	private int winner = 0;
	private final int STEP1_PLACE = 1;
	private final int STEP2_MOVEADJACENT = 2;
	private final int STEP3_JUMP = 3;
	
	private final double MARGIN_LAYOUT_X_AND_Y = 22.5;
	
	private Stone chosenStone;
	private Point destination;
	private GameShared configGUI;
	private double clickedX;
	private double clickedY;
	private int filledLines;
	
	private Button[] allButtons = new Button[24];

	private Circle[] humanStones = new Circle[9];
	private Circle[] compStones = new Circle[9];
	
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
    void initialize() {
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'Board.fxml'.";
        assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Board.fxml'.";
        configGUI = new GameShared();
        configGUI.setGameConfig();
        firstTurnPlay();
        humanStones[0] = humanStone1;
        humanStones[1] = humanStone2;
        humanStones[2] = humanStone3;
        humanStones[3] = humanStone4;
        humanStones[4] = humanStone5;
        humanStones[5] = humanStone6;
        humanStones[6] = humanStone7;
        humanStones[7] = humanStone8;
        humanStones[8] = humanStone9;
        compStones[0] = compStone1;
        compStones[1] = compStone2;
        compStones[2] = compStone3;
        compStones[3] = compStone4;
        compStones[4] = compStone5;
        compStones[5] = compStone6;
        compStones[6] = compStone7;
        compStones[7] = compStone8;
        compStones[8] = compStone9;
        allButtons[0] = outSqLineSEnd1;
        allButtons[1] = outSqLineSMid;
        allButtons[2] = outSqLineSEnd2;
        allButtons[3] = outSqLineWMid;
        allButtons[4] = outSqLineEMid;
        allButtons[5] = outSqLineNEnd1;
        allButtons[6] = outSqLineNMid;
        allButtons[7] = outSqLineNEnd2;
        allButtons[8] = midSqLineSEnd1;
        allButtons[9] = midSqLineSMid;
        allButtons[10] = midSqLineSEnd2;
        allButtons[11] = midSqLineWMid;
        allButtons[12] = midSqLineEMid;
        allButtons[13] = midSqLineNEnd1;
        allButtons[14] = midSqLineNMid;
        allButtons[15] = midSqLineNEnd2;
        allButtons[16] = innSqLineSEnd1;
        allButtons[17] = innSqLineSMid;
        allButtons[18] = innSqLineSEnd2;
        allButtons[19] = innSqLineWMid;
        allButtons[20] = innSqLineEMid;
        allButtons[21] = innSqLineNEnd1;
        allButtons[22] = innSqLineNMid;
        allButtons[23] = innSqLineNEnd2;
    }


    public void firstTurnPlay() {
        int firstTurn = configGUI.getFirstPlayer();
		AIPlayer player2 = configGUI.selectSecondPlayer();
		
        if (firstTurn == 2) {
			turnComputer(player2);    		
    	}
    }
    
    public void promptEachTurn() {
    	if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 9) {
    		// stage 2 or 3; select stone first; disable all buttons; enable all circles
    		statusLabel.setText("Choose a stone to move");

    		for (Button button : allButtons) {
    			button.setDisable(true);
    		}
    		
    		for (Circle circle : humanStones) {
    			circle.setDisable(false);
    		}
    	}
    	else {
    		statusLabel.setText("Choose a point to place a stone");
    	}
    }    

    @FXML
    public void humanStone1Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(0);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone2Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(1);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone3Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(2);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone4Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(3);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone5Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(4);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone6Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(5);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone7Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(6);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone8Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(7);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void humanStone9Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectFirstPlayer().getStones().get(8);
    	moveFromChosenStone(chosenStone);
    }

    @FXML
    public void compStone1Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(0);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone2Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(1);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone3Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(2);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone4Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(3);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone5Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(4);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone6Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(5);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone7Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(6);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone8Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(7);
    	removeChosenStone(chosenStone);
    }

    @FXML
    public void compStone9Clicked(MouseEvent event) {
    	chosenStone = configGUI.selectSecondPlayer().getStones().get(8);
    	removeChosenStone(chosenStone);
    }
    
	public void turnComputer(AIPlayer compPlayer) {
		filledLines = configGUI.getFilledLine(compPlayer).size();
    	
		if (compPlayer.getNumberOfPlacedStones() < 9) {
			Point randomPoint = compPlayer.getRandomPoint(configGUI.getPointsAsList());
			while (randomPoint.getOccupiedPlayer() != 0) {
				randomPoint = compPlayer.getRandomPoint(configGUI.getPointsAsList());
			}
			
			destination = randomPoint;
			setCoordinatesCircle(destination);
			stonePlacedNewVisually(configGUI.getPlayerNumTwo());
			configGUI.moveStone(compPlayer, null, destination, STEP1_PLACE);
		}
    	else if (compPlayer.getNumberOfTotalStones() > 3) {
			boolean isEmpty = false;
			Stone randomStone;
			ArrayList<Point> adjacentPoints;
			
			System.out.println("it reached here comp");

			do {
				randomStone = compPlayer.selectRandomStone(configGUI.selectSecondPlayer().getStones());
				adjacentPoints = configGUI.getAdjacentPoints(randomStone);
				
				System.out.println("it reached here comp loop");
				System.out.println("Adjacent point Size:" + adjacentPoints.size());


				for (Point point : adjacentPoints) {
					if (point.getOccupiedPlayer() == 0) {
						destination = point;
						isEmpty = true;
					}
				}
			} while (!isEmpty);
				
			System.out.println("did it reach here comp");
			
			chosenStone = randomStone;
			setCoordinatesCircle(destination);
    		stoneMovedVisually(configGUI.getPlayerNumTwo());
			configGUI.moveStone(compPlayer, chosenStone, destination, STEP2_MOVEADJACENT);
    	}
		else {
			Stone randomStone = compPlayer.selectRandomStone(configGUI.getFreeStones(compPlayer));
			Point randomPoint = compPlayer.getRandomPoint(configGUI.getPointsAsList());
			while (randomPoint.getOccupiedPlayer() != 0) {
				randomPoint = compPlayer.getRandomPoint(configGUI.getPointsAsList());
			}

			chosenStone = randomStone;
			destination = randomPoint;
			setCoordinatesCircle(destination);
			stoneMovedVisually(configGUI.getPlayerNumTwo());
			configGUI.moveStone(compPlayer, chosenStone, destination, STEP3_JUMP);
		}
		
		int filled_Lines_At_End_Of_Turn = configGUI.getFilledLine(compPlayer).size();
		
		if (filled_Lines_At_End_Of_Turn > filledLines) {
			Stone randomRemove = compPlayer.selectRandomStoneToRemove(configGUI.getStonesOfOpponent(configGUI.selectFirstPlayer()));
			chosenStone = randomRemove;
			removeChosenStone(chosenStone);
			configGUI.removeStone(configGUI.selectFirstPlayer(), chosenStone);
		}
		
		// check AI's number of stones at the end to see if you win
		if (configGUI.selectFirstPlayer().getNumberOfTotalStones() <= 3) {
			winner = 2;
			System.out.println("The computer won.");
		}
	}
	
    @FXML
    public void pointClicked(ActionEvent event) {
    	Button button = (Button)event.getSource();
		filledLines = configGUI.getFilledLine(configGUI.selectFirstPlayer()).size();
    	
    	// If phase1 -> placing stones
    	if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() < 9) {
    		// Check if player chooses an empty location    		
    		destination = chooseLocation(button);
    		
    		stonePlacedNewVisually(configGUI.getPlayerNumOne());
    		configGUI.moveStone(configGUI.selectFirstPlayer(), null, destination, STEP1_PLACE);
        	
        	button.setDisable(true);
    	}
    	// If phase2 -> moving stones; stone is already selected
    	else if (configGUI.selectFirstPlayer().getNumberOfTotalStones() > 3) {
    		// the player will choose an empty location
    		destination = chooseLocation(button);
    		
        	// Check if the empty point chosen was an adjacent point
    		boolean validAdjacent = false;
            ArrayList<Point> adjacentPoints = configGUI.selectFirstPlayer().getAdjacentPoints(chosenStone, configGUI.getGameBoard());

            System.out.println("it reached here");
            
            for (Point adjacentPoint : adjacentPoints) {
            	if (destination.equals(adjacentPoint)) {
            		validAdjacent = true;
            	}
            }
            
            System.out.println("did it reach here");

            if (!validAdjacent) {
            	statusLabel.setText("Invalid destination. Try again");
        		for (Button pointButton : allButtons) {
        			pointButton.setDisable(true);
        		}
        		
        		for (Circle circle : humanStones) {
        			circle.setDisable(false);
        		}

        		return;
            }
    		
    		stoneMovedVisually(configGUI.getPlayerNumOne());
    		configGUI.moveStone(configGUI.selectFirstPlayer(), chosenStone, destination, STEP2_MOVEADJACENT);

    		for (Button pointButton : allButtons) {
    			pointButton.setDisable(true);
    		}
    		
    		for (Circle circle : humanStones) {
    			circle.setDisable(false);
    		}
    	}
    	else {
    		// If phase 3 -> jumping stones
    		// the player will choose an empty location
    		destination = chooseLocation(button);
    		
    		while (destination.getOccupiedPlayer() != 0) {
    			statusLabel.setText("Invalid location; please try again");    				
        		for (Button pointButton : allButtons) {
        			pointButton.setDisable(true);
        		}
        		
        		for (Circle circle : humanStones) {
        			circle.setDisable(false);
        		}

        		return;
    		}
    		
    		stoneMovedVisually(configGUI.getPlayerNumOne());
    		configGUI.moveStone(configGUI.selectFirstPlayer(), chosenStone, destination, STEP3_JUMP);

    		for (Button pointButton : allButtons) {
    			pointButton.setDisable(true);
    		}
    		
    		for (Circle circle : humanStones) {
    			circle.setDisable(false);
    		}
    	}
    	
    	// check if you formed line
    	
		int filled_Lines_At_End_Of_Turn = configGUI.getFilledLine(configGUI.selectFirstPlayer()).size();
		
		if (filled_Lines_At_End_Of_Turn > filledLines) {
			// will wait to choose white stone
	    	statusLabel.setText("You formed a line!\nSelect a stone to remove");
	    	
    		for (Button pointButton : allButtons) {
    			pointButton.setDisable(true);
    		}

    		for (Circle circle : compStones) {
    			circle.setDisable(false);
    		}
    		
    		for (Circle circle : humanStones) {
    			circle.setDisable(true);
    		}
		}
		else {
			turnComputer(configGUI.selectSecondPlayer());
			promptEachTurn();
		}		    	
    }
    
    public void setCoordinatesCircle(Point point) {
    	if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[0].getEndPoint1())) {
    		clickedX = outSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;    		
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[0].getMidPoint())) {
    		clickedX = outSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[0].getEndPoint2())) {
    		clickedX = outSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[1].getMidPoint())) {
    		clickedX = outSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[2].getMidPoint())) {
    		clickedX = outSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[3].getEndPoint1())) {
    		clickedX = outSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[3].getMidPoint())) {
    		clickedX = outSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[0].getLines()[3].getEndPoint2())) {
    		clickedX = outSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[0].getEndPoint1())) {
    		clickedX = midSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[0].getMidPoint())) {
    		clickedX = midSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[0].getEndPoint2())) {
    		clickedX = midSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[1].getMidPoint())) {
    		clickedX = midSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[2].getMidPoint())) {
    		clickedX = midSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[3].getEndPoint1())) {
    		clickedX = midSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[3].getMidPoint())) {
    		clickedX = midSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[1].getLines()[3].getEndPoint2())) {
    		clickedX = midSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[0].getEndPoint1())) {
    		clickedX = innSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[0].getMidPoint())) {
    		clickedX = innSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[0].getEndPoint2())) {
    		clickedX = innSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[1].getMidPoint())) {
    		clickedX = innSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[2].getMidPoint())) {
    		clickedX = innSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[3].getEndPoint1())) {
    		clickedX = innSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(configGUI.getGameBoard().getSquares()[2].getLines()[3].getMidPoint())) {
    		clickedX = innSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else {
    		clickedX = innSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    }
    
    public Point chooseLocation(Button button) {
    	Point pointSelected;
    	
    	if (button.equals(outSqLineSEnd1)) {
        	// get outerSquare().getSouthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[0].getEndPoint1();
    		clickedX = outSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineSMid)) {
        	// get outerSquare().getSouthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[0].getMidPoint();
    		clickedX = outSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineSEnd2)) {
        	// get outerSquare().getSouthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[0].getEndPoint2();
    		clickedX = outSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineWMid)) {
        	// get outerSquare().getWestLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[1].getMidPoint();
    		clickedX = outSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineEMid)) {
        	// get outerSquare().getEastLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[2].getMidPoint();
    		clickedX = outSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNEnd1)) {
        	// get outerSquare().getNorthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[3].getEndPoint1();
    		clickedX = outSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNMid)) {
        	// get outerSquare().getNorthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[3].getMidPoint();
    		clickedX = outSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNEnd2)) {
        	// get outerSquare().getNorthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[0].getLines()[3].getEndPoint2();
    		clickedX = outSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSEnd1)) {
        	// get midSquare().getSouthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[0].getEndPoint1();
    		clickedX = midSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSMid)) {
        	// get midSquare().getSouthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[0].getMidPoint();
    		clickedX = midSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSEnd2)) {
        	// get midSquare().getSouthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[0].getEndPoint2();
    		clickedX = midSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineWMid)) {
        	// get midSquare().getWestLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[1].getMidPoint();
    		clickedX = midSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineEMid)) {
        	// get midSquare().getEastLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[2].getMidPoint();
    		clickedX = midSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNEnd1)) {
        	// get midSquare().getNorthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[3].getEndPoint1();
    		clickedX = midSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNMid)) {
        	// get midSquare().getNorthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[3].getMidPoint();
    		clickedX = midSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNEnd2)) {
        	// get midSquare().getNorthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[1].getLines()[3].getEndPoint2();
    		clickedX = midSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSEnd1)) {
        	// get innerSquare().getSouthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[0].getEndPoint1();
    		clickedX = innSqLineSEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSMid)) {
        	// get innerSquare().getSouthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[0].getMidPoint();
    		clickedX = innSqLineSMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSEnd2)) {
        	// get innerSquare().getSouthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[0].getEndPoint2();
    		clickedX = innSqLineSEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineWMid)) {
        	// get innerSquare().getWestLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[1].getMidPoint();
    		clickedX = innSqLineWMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineWMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineEMid)) {
        	// get innerSquare().getEastLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[2].getMidPoint();
    		clickedX = innSqLineEMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineEMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNEnd1)) {
        	// get innerSquare().getNorthLine().getEndPoint1();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[3].getEndPoint1();
    		clickedX = innSqLineNEnd1.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd1.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNMid)) {
        	// get innerSquare().getNorthLine().getMidPoint();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[3].getMidPoint();
    		clickedX = innSqLineNMid.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNMid.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNEnd2)) {
        	// get innerSquare().getNorthLine().getEndPoint2();
    		pointSelected = configGUI.getGameBoard().getSquares()[2].getLines()[3].getEndPoint2();
    		clickedX = innSqLineNEnd2.getLayoutX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd2.getLayoutY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else {
    		// shouldn't happen
    		return null;
    	}
    	
    	return pointSelected;
    }
    
    public void moveFromChosenStone(Stone stone) {
    	statusLabel.setText("Choose a destination point");

		for (Button pointButton : allButtons) {
			pointButton.setDisable(false);
		}
    }

    public void removeChosenStone(Stone stone) {
    	try {
        	stoneRemovedVisually(configGUI.getPlayerNumTwo());
    		configGUI.removeStone(configGUI.selectSecondPlayer(), chosenStone);
    		    		
        	if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() < 9) {
        		for (Button button : allButtons) {
        			button.setDisable(false);
        		}
        	}
        	else {
        		for (Circle circle : humanStones) {
        			circle.setDisable(false);
        		}
        	}

        	for (Circle eachStone : compStones) {
        		eachStone.setDisable(true);
        	}

    		// check AI's number of stones at the end to see if you win
    		if (configGUI.selectSecondPlayer().getNumberOfTotalStones() < 3) {
    			winner = 1;
    		}
    		
        	// check if you win
        	if (winner == 0) {
            	turnComputer(configGUI.selectSecondPlayer());
        		promptEachTurn();
        	}
        	else {
        		statusLabel.setText("You are the winner!");        		
        	}
    	}
    	catch (NullPointerException NPE) {
    		// tried to remove a stone that is not on the field
    		statusLabel.setText("You can only remove a stone on the field");
    	}
	}    	

    private void stonePlacedNewVisually(int playerNum) {
    	Circle circle;
    	
    	if (playerNum == configGUI.getPlayerNumOne()) {
    		if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 0) {
				circle = humanStone1;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 1) {
				circle = humanStone2;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 2) {
				circle = humanStone3;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 3) {
				circle = humanStone4;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 4) {
				circle = humanStone5;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 5) {
				circle = humanStone6;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 6) {
				circle = humanStone7;
    		}
    		else if (configGUI.selectFirstPlayer().getNumberOfPlacedStones() == 7) {
				circle = humanStone8;
    		}
    		else {			
				circle = humanStone9;
    		}    		
    	}
    	else {
    		if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 0) {
				circle = compStone1;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 1) {
				circle = compStone2;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 2) {
				circle = compStone3;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 3) {
				circle = compStone4;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 4) {
				circle = compStone5;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 5) {
				circle = compStone6;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 6) {
				circle = compStone7;
    		}
    		else if (configGUI.selectSecondPlayer().getNumberOfPlacedStones() == 7) {
				circle = compStone8;
    		}
    		else {			
				circle = compStone9;
    		}    		    		
    	}
		setStoneLayoutVisual(circle);
	}

	private void stoneMovedVisually(int playerNum) {
		Circle circle;
		
		if (playerNum == configGUI.getPlayerNumOne()) {
			if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(0))) {
				circle = humanStone1;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(1))) {
				circle = humanStone2;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(2))) {
				circle = humanStone3;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(3))) {
				circle = humanStone4;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(4))) {
				circle = humanStone5;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(5))) {
				circle = humanStone6;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(6))) {
				circle = humanStone7;
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(7))) {
				circle = humanStone8;
			}
			else {
				circle = humanStone9;
			}			
		}
		else {
			if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(0))) {
				circle = compStone1;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(1))) {
				circle = compStone2;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(2))) {
				circle = compStone3;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(3))) {
				circle = compStone4;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(4))) {
				circle = compStone5;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(5))) {
				circle = compStone6;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(6))) {
				circle = compStone7;
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(7))) {
				circle = compStone8;
			}
			else {
				circle = compStone9;
			}						
		}
		setStoneLayoutVisual(circle);
	}

	private void stoneRemovedVisually(int playerNum) {
		if (playerNum == 1) {
			if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(0))) {
				boardPane.getChildren().remove(humanStone1);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(1))) {
				boardPane.getChildren().remove(humanStone2);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(2))) {
				boardPane.getChildren().remove(humanStone3);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(3))) {
				boardPane.getChildren().remove(humanStone4);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(4))) {
				boardPane.getChildren().remove(humanStone5);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(5))) {
				boardPane.getChildren().remove(humanStone6);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(6))) {
				boardPane.getChildren().remove(humanStone7);
			}
			else if (chosenStone.equals(configGUI.selectFirstPlayer().getStones().get(7))) {
				boardPane.getChildren().remove(humanStone8);
			}
			else {
				boardPane.getChildren().remove(humanStone9);
			}								
		}
		else {
			if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(0))) {
				boardPane.getChildren().remove(compStone1);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(1))) {
				boardPane.getChildren().remove(compStone2);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(2))) {
				boardPane.getChildren().remove(compStone3);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(3))) {
				boardPane.getChildren().remove(compStone4);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(4))) {
				boardPane.getChildren().remove(compStone5);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(5))) {
				boardPane.getChildren().remove(compStone6);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(6))) {
				boardPane.getChildren().remove(compStone7);
			}
			else if (chosenStone.equals(configGUI.selectSecondPlayer().getStones().get(7))) {
				boardPane.getChildren().remove(compStone8);
			}
			else {
				boardPane.getChildren().remove(compStone9);
			}					
		}
	}	
	
	private void setStoneLayoutVisual(Circle circle) {
		circle.setLayoutX(clickedX);
		circle.setLayoutY(clickedY);
	}
}
