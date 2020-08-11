/**
 * Controller for GUI of the Game
 * 
 * @author Daniel Kim, Armeen Rashidian
 * @version 2.0
 * 10.00 6 August 2020
 * Team D
 */
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

import javafx.animation.PauseTransition;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;

import javafx.util.Duration;

import java.util.ArrayList;

import model.Point;
import model.Line;
import model.Stone;
import model.GameShared;
import model.AIPlayer;

public class GameController extends GameShared {
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
    
    // button specifically set for animation
    // idea of FadeTransition retrieved from https://docs.oracle.com/javase/9/docs/api/javafx/animation/FadeTransition.html
    @FXML
    private Button animationButton;

	private int winner = 0;
	
	private final double MARGIN_LAYOUT_X_AND_Y = 22.5;
	
	private PauseTransition pauseTimer = new PauseTransition(Duration.millis(2000));
	private PauseTransition pauseTimer2 = new PauseTransition(Duration.millis(1500));
	private PauseTransition pauseTimer3 = new PauseTransition(Duration.millis(1500));
	private FadeTransition fadeTransition = new FadeTransition(Duration.millis(1200));
	private TranslateTransition moveTransition = new TranslateTransition();
	
	private Stone chosenStone;
	private Point destination;
	private double clickedX;
	private double clickedY;
	
	private Button[] allPoints = new Button[24];

	private Circle[] humanStones = new Circle[9];
	private Circle[] compStones = new Circle[9];
	
    /**
     * Quits the game with confirmation window
     * Base idea https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
     * 
     * @param event QUIT button clicked
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

    /**
     * Displays rules
     * 
     * @param event RULES button clicked
     */
    @FXML
    public void rulesPressed(ActionEvent event) {
    }

    /**
     * Clicking on any of nine human stones will select its corresponding Stone object and invoke move method
     * 
     * @param event Human stone clicked
     */
    @FXML
    public void humanStone1Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(0);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone2Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(1);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone3Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(2);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone4Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(3);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone5Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(4);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone6Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(5);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone7Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(6);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone8Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(7);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    @FXML
    public void humanStone9Clicked(MouseEvent event) {
    	chosenStone = selectFirstPlayer().getStones().get(8);
    	moveFromChosenStone(chosenStone.getLocation());
    }

    /**
     * Clicking on any of nine AI stones will select its corresponding Stone object and invoke remove method
     * 
     * @param event Human stone clicked
     */
    @FXML
    public void compStone1Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(0);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone2Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(1);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone3Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(2);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone4Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(3);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone5Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(4);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone6Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(5);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone7Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(6);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone8Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(7);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    }

    @FXML
    public void compStone9Clicked(MouseEvent event) {
    	chosenStone = selectSecondPlayer().getStones().get(8);
    	removeChosenStone(chosenStone, getPlayerNumTwo());
    } 

    /**
     * Performs first turn; if the AI goes first it will call the AI to take its turn
     */
    public void firstTurnPlay() {
        if (getFirstPlayer() == 2) {
        	pauseAndPlayForAI();
    	}
        else {
        	promptEachTurn();
        }
    }
    
    /**
     * Starts human player turn
     */
    public void promptEachTurn() {
    	if (selectFirstPlayer().getNumberOfPlacedStones() == 9) {
    		// stage 2 or 3; select stone first; enable player stones for selection
    		statusLabel.setText("YOUR TURN: Choose a stone to move");

    		for (Circle humanStone : humanStones) {
    			humanStone.setDisable(false);
    		}

    	}
    	else {
    		statusLabel.setText("YOUR TURN: Choose a point to place a stone");
    		for (Button point : allPoints) {
    			point.setDisable(false);
    		}
    	}
    }    
    
    /**
     * Ends the game and displays message
     */
    public void endGame() {
    	if (winner == 1) {
    		statusLabel.setText("You win!");
    	}
    	else {
    		statusLabel.setText("Computer wins!");
    	}

    	// disables all buttons and stones; the game will no longer be accessible
    	disablePointsAndStones();
	}

    /**
     * Performs a turn with AI
     * 
     * @param compPlayer
     */
    @Override
	public void turnComputer(AIPlayer compPlayer) {
    	ArrayList<Line> filledLines_StartTurn = getFilledLine(compPlayer);
    	
		if (compPlayer.getNumberOfPlacedStones() < 9) {
			// placing stage
			compTurnPlaceStage(compPlayer);
		}
    	else if (compPlayer.getNumberOfTotalStones() > 3) {
			// can only move adjacent
    		compTurnMoveAdjacentStage(compPlayer);
    	}
		else {
			// jumping stage
			compTurnJumpStage(compPlayer);
		}
		
		// ending stage
		compTurnCheckMillAndEndTurn(compPlayer, filledLines_StartTurn);
	}
	
	/**
	 * Performs the placing stage part with AI
	 * 
	 * @param compPlayer
	 */
	private void compTurnPlaceStage(AIPlayer compPlayer) {
		// look for the best move in placing stage
		destination = compPlayer.lookForBestMove(getGameBoard());

		if (destination == null) {
			// the AI had no good move to make; make a random point to move to
			Point randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			while (randomPoint.getOccupiedPlayer() != 0) {
				randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			}
			
			destination = randomPoint;			
		}

		setCoordinatesStone(destination);
		stonePlacedNewVisually(getPlayerNumTwo());
		moveStone(compPlayer, null, destination);
	}
	
	/**
	 * Performs a moving adjacent part of the turn with AI
	 * 
	 * @param compPlayer
	 */
	private void compTurnMoveAdjacentStage(AIPlayer compPlayer) {
		// look for the best move in placing stage
		destination = compPlayer.lookForBestMove(getGameBoard());
		
		if (destination == null) {
			// the AI had no good move to make; make a move adjacent with a random stone
			boolean isEmpty = false;
			Stone randomStone;
			ArrayList<Point> adjacentPoints;
			
			do {
				randomStone = compPlayer.selectRandomStone(compPlayer.getStones());
				adjacentPoints = compPlayer.getAdjacentPoints(randomStone.getLocation());				

				for (Point point : adjacentPoints) {
					if (point.getOccupiedPlayer() == 0) {
						destination = point;
						isEmpty = true;
					}
				}
			} while (!isEmpty);
				
			chosenStone = randomStone;
		}
		else {
			chosenStone = compPlayer.getBestMoveStone();			
		}
		
		setCoordinatesStone(destination);
		stoneMovedVisually(getPlayerNumTwo());
		moveStone(compPlayer, chosenStone, destination);
	}
	
	/**
	 * Performs a jumping stage with AI
	 * 
	 * @param compPlayer
	 */
	private void compTurnJumpStage(AIPlayer compPlayer) {
		// look for the best move in jumping stage
    	destination = compPlayer.lookForBestMove(getGameBoard());
    	
    	if (destination == null) {
			// the AI had no good move to make; make a random move with a random stone
    		Stone randomStone = compPlayer.selectRandomStone(getFreeStones(compPlayer));
    		Point randomPoint = compPlayer.getRandomPoint(getPointsAsList());
    		while (randomPoint.getOccupiedPlayer() != 0) {
    			randomPoint = compPlayer.getRandomPoint(getPointsAsList());
    		}
    	
    		chosenStone = randomStone;
    		destination = randomPoint;
    	}
    	else {
    		chosenStone = compPlayer.getBestMoveStone();
    	}

    	setCoordinatesStone(destination);
		stoneMovedVisually(getPlayerNumTwo());
		moveStone(compPlayer, chosenStone, destination);
	}
	
	/**
	 * Checks if the AI performed a new mill this turn and perform accordingly.
	 * 
	 * @param compPlayer
	 * @param filledLines_StartTurn List of mills at the start of the turn
	 */
	private void compTurnCheckMillAndEndTurn(AIPlayer compPlayer, ArrayList<Line> filledLines_StartTurn) {
		ArrayList<Line> filled_Lines_At_End_Of_Turn = getFilledLine(compPlayer);
		
		if ((!(filled_Lines_At_End_Of_Turn.equals(filledLines_StartTurn))) && filled_Lines_At_End_Of_Turn.size() >= filledLines_StartTurn.size()) {
			// A new mill was found; look for the best stone to remove and perform
			chosenStone = compPlayer.lookforBestRemove(getGameBoard(), selectFirstPlayer());
			pauseTimer.setOnFinished(event -> removeChosenStone(chosenStone, getPlayerNumOne()));
			statusLabel.setText("The Computer is thinking...");
	    	pauseTimer.play();
	    	pauseTimer3.setDuration(Duration.millis(3500));
	    	pauseTimer2.setDuration(Duration.millis(3500));
		}

		if (selectFirstPlayer().getNumberOfPlacedStones() < 9) {
			pauseTimer3.setOnFinished(event -> setAnimationAllPoints());
			pauseTimer3.play();
		}
		pauseTimer3.setDuration(Duration.millis(1500));
		pauseTimer2.setOnFinished(event -> promptEachTurn());
		pauseTimer2.play();
		pauseTimer2.setDuration(Duration.millis(1500));
	}

    /**
	 * Activates when any point is clicked
	 * 
	 * @param event Mouse click on a point
	 */
    @FXML
    public void pointClicked(ActionEvent event) {
    	disablePointsAndStones();
    	stopAnimationPoints();
    	
    	Button button = (Button)event.getSource();
    	Boolean moveWasDone;
    	ArrayList<Line> filledLines_StartTurn = getFilledLine(selectFirstPlayer());
    	
    	// If phase1 -> placing stones
    	if (selectFirstPlayer().getNumberOfPlacedStones() < 9) {
    		moveWasDone = humanTurnPlaceStage(button);
    	}
    	// If phase2 -> moving stones; stone is already selected
    	else if (selectFirstPlayer().getNumberOfTotalStones() > 3) {
    		moveWasDone = humanTurnMoveAdjacentStage(button);
    	}
    	else {
    		// If phase 3 -> jumping stones
    		moveWasDone = humanTurnJumpStage(button);
    	}
    	
		// ending stage if a move was done properly; otherwise reset the turn
    	if (moveWasDone) {
        	humanTurnCheckMillAndEndTurn(filledLines_StartTurn);
    	}
    }
    
	/**
	 * Performs the placing stage part with human player
	 * 
	 * @param compPlayer
	 */
    private boolean humanTurnPlaceStage(Button button) {
    	boolean moveWasValid;
		destination = chooseLocation(button);
		
		// Check if player chooses an empty location		
		if (destination.getOccupiedPlayer() != 0) {
			statusLabel.setText("Invalid location; please try again");

	   		for (Button point : allPoints) {
	   			point.setDisable(false);
	   		}
	   		
            setAnimationAllPoints();        	
            moveWasValid = false;
		}
		else {
    		stonePlacedNewVisually(getPlayerNumOne());
    		moveStone(selectFirstPlayer(), null, destination);
    		moveWasValid = true;
		}	
		
		return moveWasValid;
    }
    
	/**
	 * Human player performs moving adjacent
	 * 
	 * @param compPlayer
	 */
    private boolean humanTurnMoveAdjacentStage(Button button) {
		boolean validAdjacent = false;
		boolean moveWasValid;

		// the player will choose a point
		destination = chooseLocation(button);
		
    	// Check if the point chosen was an adjacent point
        ArrayList<Point> adjacentPoints = selectFirstPlayer().getAdjacentPoints(chosenStone.getLocation());

        for (Point adjacentPoint : adjacentPoints) {
        	if (destination.equals(adjacentPoint)) {
        		validAdjacent = true;
        	}
        }
        
        // Check if the point chosen is empty
        if (!validAdjacent || destination.getOccupiedPlayer() != 0) {
        	statusLabel.setText("Invalid destination. Try again");

        	// reset to the beginning; make human stones clickable
    		for (Circle humanStone : humanStones) {
    			humanStone.setDisable(false);
    		}

    		moveWasValid = false;
        }
        else {
    		stoneMovedVisually(getPlayerNumOne());
    		moveStone(selectFirstPlayer(), chosenStone, destination);

    		moveWasValid = true;        	
        }
        
        return moveWasValid;
    }
    
	/**
	 * Human player performs a jump
	 * 
	 * @param compPlayer
	 */  
    private boolean humanTurnJumpStage(Button button) {
    	boolean moveWasValid;
		destination = chooseLocation(button);
		
		// Check if player chooses an empty location				
		if (destination.getOccupiedPlayer() != 0) {
			statusLabel.setText("Invalid location; please try again");    				

        	// reset to the beginning; make human stones clickable
    		for (Circle humanStone : humanStones) {
    			humanStone.setDisable(false);
    		}

    		moveWasValid = false;;
		}
		else {			
			stoneMovedVisually(getPlayerNumOne());
			moveStone(selectFirstPlayer(), chosenStone, destination);

			moveWasValid = true;
		}

		return moveWasValid;
    }
    
	/**
	 * Checks if the human player performed a new mill this turn and perform accordingly.
	 * 
	 * @param filledLines_StartTurn List of mills at the start of the turn
	 */
    private void humanTurnCheckMillAndEndTurn(ArrayList<Line> filledLines_StartTurn) {
    	// check if you formed line
    	
		ArrayList<Line> filled_Lines_At_End_Of_Turn = getFilledLine(selectFirstPlayer());
		
		if ((!filled_Lines_At_End_Of_Turn.equals(filledLines_StartTurn)) && filled_Lines_At_End_Of_Turn.size() >= filledLines_StartTurn.size()) {
	    	statusLabel.setText("You formed a line!\nSelect a stone to remove");
	    	
			// A new mill was found; only computer stones will be selectable for removal
    		for (Circle compStone : compStones) {
    			compStone.setDisable(false);
    		}
		}
		else {
			// A mill was not found. It is the computer's turn
			pauseAndPlayForAI();
		}
    }
    
    /**
     * Sets coordinates of a stone in relation to the right point to move visually
     * Used with AI only
     * 
     * @param point Clicked point
     */
    private void setCoordinatesStone(Point point) {
    	if (point.equals(getGameBoard().getSquares()[0].getLines()[0].getPoints()[0])) {
    		clickedX = outSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;    		
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[0].getPoints()[1])) {
    		clickedX = outSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[0].getPoints()[2])) {
    		clickedX = outSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[1].getPoints()[1])) {
    		clickedX = outSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[2].getPoints()[1])) {
    		clickedX = outSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[3].getPoints()[0])) {
    		clickedX = outSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[3].getPoints()[1])) {
    		clickedX = outSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[0].getLines()[3].getPoints()[2])) {
    		clickedX = outSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[0].getPoints()[0])) {
    		clickedX = midSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[0].getPoints()[1])) {
    		clickedX = midSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[0].getPoints()[2])) {
    		clickedX = midSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[1].getPoints()[1])) {
    		clickedX = midSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[2].getPoints()[1])) {
    		clickedX = midSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[3].getPoints()[0])) {
    		clickedX = midSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[3].getPoints()[1])) {
    		clickedX = midSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[1].getLines()[3].getPoints()[2])) {
    		clickedX = midSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[0].getPoints()[0])) {
    		clickedX = innSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[0].getPoints()[1])) {
    		clickedX = innSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[0].getPoints()[2])) {
    		clickedX = innSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[1].getPoints()[1])) {
    		clickedX = innSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[2].getPoints()[1])) {
    		clickedX = innSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[3].getPoints()[0])) {
    		clickedX = innSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (point.equals(getGameBoard().getSquares()[2].getLines()[3].getPoints()[1])) {
    		clickedX = innSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else {
    		clickedX = innSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    }
    
    /**
     * Sets coordinates of an actual point according to which point was visually clicked
     * 
     * @param button Point clicked
     * @return Point retrieved
     */
    private Point chooseLocation(Button button) {
    	Point pointSelected;
    	
    	if (button.equals(outSqLineSEnd1)) {
        	// get outerSquare().getLines()[0].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[0].getPoints()[0];
    		clickedX = outSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineSMid)) {
        	// get outerSquare().getLines()[0].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[0].getPoints()[1];
    		clickedX = outSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineSEnd2)) {
        	// get outerSquare().getLines()[0].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[0].getPoints()[2];
    		clickedX = outSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineWMid)) {
        	// get outerSquare().getLines()[1].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[1].getPoints()[1];
    		clickedX = outSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineEMid)) {
        	// get outerSquare().getLines()[2].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[2].getPoints()[1];
    		clickedX = outSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNEnd1)) {
        	// get outerSquare().getLines()[3].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[3].getPoints()[0];
    		clickedX = outSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNMid)) {
        	// get outerSquare().getLines()[3].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[3].getPoints()[1];
    		clickedX = outSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(outSqLineNEnd2)) {
        	// get outerSquare().getLines()[3].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[0].getLines()[3].getPoints()[2];
    		clickedX = outSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = outSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSEnd1)) {
        	// get midSquare().getLines()[0].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[0].getPoints()[0];
    		clickedX = midSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSMid)) {
        	// get midSquare().getLines()[0].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[0].getPoints()[1];
    		clickedX = midSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineSEnd2)) {
        	// get midSquare().getLines()[0].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[0].getPoints()[2];
    		clickedX = midSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineWMid)) {
        	// get midSquare().getLines()[1].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[1].getPoints()[1];
    		clickedX = midSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineEMid)) {
        	// get midSquare().getLines()[2].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[2].getPoints()[1];
    		clickedX = midSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNEnd1)) {
        	// get midSquare().getLines()[3].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[3].getPoints()[0];
    		clickedX = midSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNMid)) {
        	// get midSquare().getLines()[3].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[3].getPoints()[1];
    		clickedX = midSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(midSqLineNEnd2)) {
        	// get midSquare().getLines()[3].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[1].getLines()[3].getPoints()[2];
    		clickedX = midSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = midSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSEnd1)) {
        	// get innerSquare().getLines()[0].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[0].getPoints()[0];
    		clickedX = innSqLineSEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSMid)) {
        	// get innerSquare().getLines()[0].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[0].getPoints()[1];
    		clickedX = innSqLineSMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineSEnd2)) {
        	// get innerSquare().getLines()[0].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[0].getPoints()[2];
    		clickedX = innSqLineSEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineSEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineWMid)) {
        	// get innerSquare().getLines()[1].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[1].getPoints()[1];
    		clickedX = innSqLineWMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineWMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineEMid)) {
        	// get innerSquare().getLines()[2].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[2].getPoints()[1];
    		clickedX = innSqLineEMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineEMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNEnd1)) {
        	// get innerSquare().getLines()[3].getPoints()[0];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[3].getPoints()[0];
    		clickedX = innSqLineNEnd1.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd1.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNMid)) {
        	// get innerSquare().getLines()[3].getPoints()[1];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[3].getPoints()[1];
    		clickedX = innSqLineNMid.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNMid.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else if (button.equals(innSqLineNEnd2)) {
        	// get innerSquare().getLines()[3].getPoints()[2];
    		pointSelected = getGameBoard().getSquares()[2].getLines()[3].getPoints()[2];
    		clickedX = innSqLineNEnd2.getTranslateX() + MARGIN_LAYOUT_X_AND_Y;
    		clickedY = innSqLineNEnd2.getTranslateY() + MARGIN_LAYOUT_X_AND_Y;
    	}
    	else {
    		// shouldn't happen
    		return null;
    	}
    	
    	return pointSelected;
    }
    
    /**
     * Activates when a human stone is clicked; prompt to click a point to move to.
     */
    private void moveFromChosenStone(Point stoneLocation) {
		for (Button point : allPoints) {
			point.setDisable(false);
		}

		stopAnimationPoints();
    	statusLabel.setText("YOUR TURN: Choose a destination point");

		if (selectFirstPlayer().getNumberOfTotalStones() > 3) {
	    	setAnimationAvailablePoints(stoneLocation);			
		}
		else {
			setAnimationAllPoints();
		}
    }

    /**
     * Removes a chosen stone from a chosen player
     * 
     * @param stone Stone to remove
     * @param playerNum Player to remove the stone from
     */
    private void removeChosenStone(Stone stone, int playerNum) {
    	try {
    		if (playerNum == getPlayerNumOne()) {
    			// remove Human player's stone
            	stoneRemovedVisually(getPlayerNumOne());
        		removeStone(selectFirstPlayer(), stone);
        		    		
    			// check Human player's number of stones at the end to see if the AI wins
    			if (selectFirstPlayer().getNumberOfTotalStones() < 3) {
    				winner = 2;
    				endGame();
    			}
    			else {
    				promptEachTurn();
    			}
    		}
    		else {
    			// remove AI player's stone
            	stoneRemovedVisually(getPlayerNumTwo());
        		removeStone(selectSecondPlayer(), stone);

        		// check AI's number of stones at the end to see if the Human player wins
        		if (selectSecondPlayer().getNumberOfTotalStones() < 3) {
        			winner = 1;
        			endGame();
        		}
        		else {
            		for (Circle compStone : compStones) {
            			compStone.setDisable(true);
            		}

            		pauseAndPlayForAI();
        		}
    		}
    	}
    	catch (NullPointerException NPE) {
    		// tried to remove a stone that is not on the field
    		statusLabel.setText("You can only remove a stone on the field");
    	}
	}    	

    /**
     * Visually places a new circle associated with the stone on the board
     * 
     * @param playerNum Player number that indicates whether it is Human or AI
     */
    private void stonePlacedNewVisually(int playerNum) {
    	Circle circle;
    	
    	if (playerNum == getPlayerNumOne()) {
    		if (selectFirstPlayer().getNumberOfPlacedStones() == 0) {
				circle = humanStone1;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 1) {
				circle = humanStone2;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 2) {
				circle = humanStone3;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 3) {
				circle = humanStone4;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 4) {
				circle = humanStone5;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 5) {
				circle = humanStone6;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 6) {
				circle = humanStone7;
    		}
    		else if (selectFirstPlayer().getNumberOfPlacedStones() == 7) {
				circle = humanStone8;
    		}
    		else {			
				circle = humanStone9;
    		}    		
    	}
    	else {
    		if (selectSecondPlayer().getNumberOfPlacedStones() == 0) {
				circle = compStone1;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 1) {
				circle = compStone2;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 2) {
				circle = compStone3;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 3) {
				circle = compStone4;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 4) {
				circle = compStone5;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 5) {
				circle = compStone6;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 6) {
				circle = compStone7;
    		}
    		else if (selectSecondPlayer().getNumberOfPlacedStones() == 7) {
				circle = compStone8;
    		}
    		else {			
				circle = compStone9;
    		}    		    		
    	}
    	
		setStoneLayoutVisual(circle);
	}

    /**
     * Visually moves an existing stone on GUI
     * 
     * @param playerNum Player number that indicates whether it is Human or AI
     */
	private void stoneMovedVisually(int playerNum) {
		Circle circle;
		
		if (playerNum == getPlayerNumOne()) {
			if (chosenStone.equals(selectFirstPlayer().getStones().get(0))) {
				circle = humanStone1;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(1))) {
				circle = humanStone2;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(2))) {
				circle = humanStone3;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(3))) {
				circle = humanStone4;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(4))) {
				circle = humanStone5;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(5))) {
				circle = humanStone6;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(6))) {
				circle = humanStone7;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(7))) {
				circle = humanStone8;
			}
			else {
				circle = humanStone9;
			}			
		}
		else {
			if (chosenStone.equals(selectSecondPlayer().getStones().get(0))) {
				circle = compStone1;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(1))) {
				circle = compStone2;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(2))) {
				circle = compStone3;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(3))) {
				circle = compStone4;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(4))) {
				circle = compStone5;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(5))) {
				circle = compStone6;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(6))) {
				circle = compStone7;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(7))) {
				circle = compStone8;
			}
			else {
				circle = compStone9;
			}						
		}
		
		setStoneLayoutVisual(circle);
	}

    /**
     * Visually remove a circle associated with the stone from the board
     * 
     * @param playerNum Player number that indicates whether it is Human or AI
     */
	private void stoneRemovedVisually(int playerNum) {
		Circle stone;
		
		if (playerNum == getPlayerNumOne()) {
			if (chosenStone.equals(selectFirstPlayer().getStones().get(0))) {
				stone = humanStone1;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(1))) {
				stone = humanStone2;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(2))) {
				stone = humanStone3;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(3))) {
				stone = humanStone4;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(4))) {
				stone = humanStone5;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(5))) {
				stone = humanStone6;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(6))) {
				stone = humanStone7;
			}
			else if (chosenStone.equals(selectFirstPlayer().getStones().get(7))) {
				stone = humanStone8;
			}
			else {
				stone = humanStone9;
			}				
			
			clickedX = -stone.getTranslateX();
			clickedY = -stone.getTranslateY();
		}
		else {
			if (chosenStone.equals(selectSecondPlayer().getStones().get(0))) {
				stone = compStone1;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(1))) {
				stone = compStone2;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(2))) {
				stone = compStone3;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(3))) {
				stone = compStone4;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(4))) {
				stone = compStone5;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(5))) {
				stone = compStone6;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(6))) {
				stone = compStone7;
			}
			else if (chosenStone.equals(selectSecondPlayer().getStones().get(7))) {
				stone = compStone8;
			}
			else {
				stone = compStone9;
			}				
			
			clickedX = 850 - stone.getTranslateX();
			clickedY = -stone.getTranslateY();			
		}

		moveTransition.setDuration(Duration.millis(500));
		moveTransition.setNode(stone);
		moveTransition.setByX(clickedX);
		moveTransition.setByY(clickedY);
		pauseTimer.setOnFinished(event -> boardPane.getChildren().remove(stone));
		pauseTimer.setDuration(Duration.millis(500));

		moveTransition.play();
		pauseTimer.play();
		pauseTimer.setDuration(Duration.millis(2000));
	}	
	
	/**
	 * The part where it actively moves the stone visually in GUI
	 * 
	 * @param stone
	 */
	private void setStoneLayoutVisual(Circle stone) {
		moveTransition.setDuration(Duration.millis(1200));
		moveTransition.setNode(stone);
		moveTransition.setByX(clickedX - stone.getTranslateX());
		moveTransition.setByY(clickedY - stone.getTranslateY());
		moveTransition.play();
	}
	
	/**
	 * Disable all points and stones clickable, for work to be done. Only rules and quit button can be clicked
	 */
	private void disablePointsAndStones() {
    	for (Button point : allPoints) {
    		point.setDisable(true);
		}
		
		for (Circle humanStone : humanStones) {
			humanStone.setDisable(true);
		}

		for (Circle compStone : compStones) {
			compStone.setDisable(true);
		}
	}
		
	/**
	 * Get a button from location of the point
	 * 
	 * @param Point to retrieve button from
	 */
	private Button getButtonFromPoint(Point point) {
		Button selectedPoint;
		
    	if (point == getGameBoard().getSquares()[0].getLines()[0].getPoints()[0]) {
    		selectedPoint = outSqLineSEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[0].getPoints()[1]) {
    		selectedPoint = outSqLineSMid;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[0].getPoints()[2]) {
    		selectedPoint = outSqLineSEnd2;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[1].getPoints()[1]) {
    		selectedPoint = outSqLineWMid;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[2].getPoints()[1]) {
    		selectedPoint = outSqLineEMid;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[3].getPoints()[0]) {
    		selectedPoint = outSqLineNEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[3].getPoints()[1]) {
    		selectedPoint = outSqLineNMid;
    	}
    	else if (point == getGameBoard().getSquares()[0].getLines()[3].getPoints()[2]) {
    		selectedPoint = outSqLineNEnd2;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[0].getPoints()[0]) {
    		selectedPoint = midSqLineSEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[0].getPoints()[1]) {
    		selectedPoint = midSqLineSMid;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[0].getPoints()[2]) {
    		selectedPoint = midSqLineSEnd2;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[1].getPoints()[1]) {
    		selectedPoint = midSqLineWMid;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[2].getPoints()[1]) {
    		selectedPoint = midSqLineEMid;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[3].getPoints()[0]) {
    		selectedPoint = midSqLineNEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[3].getPoints()[1]) {
    		selectedPoint = midSqLineNMid;
    	}
    	else if (point == getGameBoard().getSquares()[1].getLines()[3].getPoints()[2]) {
    		selectedPoint = midSqLineNEnd2;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[0].getPoints()[0]) {
    		selectedPoint = innSqLineSEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[0].getPoints()[1]) {
    		selectedPoint = innSqLineSMid;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[0].getPoints()[2]) {
    		selectedPoint = innSqLineSEnd2;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[1].getPoints()[1]) {
    		selectedPoint = innSqLineWMid;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[2].getPoints()[1]) {
    		selectedPoint = innSqLineEMid;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[3].getPoints()[0]) {
    		selectedPoint = innSqLineNEnd1;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[3].getPoints()[1]) {
    		selectedPoint = innSqLineNMid;
    	}
    	else if (point == getGameBoard().getSquares()[2].getLines()[3].getPoints()[2]) {
    		selectedPoint = innSqLineNEnd2;
    	}
    	else {
    		// shouldn't happen
    		// throw GameException here
    		return null;
    	}
    	
    	return selectedPoint;
	}
	
	/**
	 * Sets animation for available points to move to; only used for moving adjacent
	 * 
	 * @param point Current point
	 */
	private void setAnimationAvailablePoints(Point point) {
		ArrayList<Point> availablePoints;
		Button chosenPoint;
		
		availablePoints = selectFirstPlayer().getAdjacentPoints(point);
		
		for (Point currentPoint : availablePoints) {
			if (currentPoint.getOccupiedPlayer() == 0) {
				chosenPoint = getButtonFromPoint(currentPoint);
				chosenPoint.opacityProperty().bind(animationButton.opacityProperty());
			}
		}
		
		playAnimationPoints();
	}
	
	/**
	 * Sets animation for available points to move to; used for placing and jumping stage
	 */
	private void setAnimationAllPoints() {
		Point chosenPoint;
		
		for (Button point : allPoints) {
			chosenPoint = chooseLocation(point);
			
			if (chosenPoint.getOccupiedPlayer() == 0) {
				point.opacityProperty().bind(animationButton.opacityProperty());
			}
		}
		
		playAnimationPoints();
	}
	
	/**
	 * 
	 */
	private void playAnimationPoints() {
		fadeTransition.setNode(animationButton);
		fadeTransition.play();
	}
	
	private void stopAnimationPoints() {
		fadeTransition.stop();
		animationButton.setOpacity(0.0);
		animationButton = new Button();
	}
	
	private void pauseAndPlayForAI() {
    	disablePointsAndStones();
		pauseTimer.setOnFinished(event -> turnComputer(selectSecondPlayer()));
		statusLabel.setText("The Computer is thinking...");
		stopAnimationPoints();
    	pauseTimer.play();
	}
	
    /**
     * Initializes the game and performs the first turn
     * The method also includes all stones and points in the corresponding arrays for later use
     */
    @FXML
    void initialize() {
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'Board.fxml'.";
        assert rulesButton != null : "fx:id=\"rulesButton\" was not injected: check your FXML file 'Board.fxml'.";
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
        allPoints[0] = outSqLineSEnd1;
        allPoints[1] = outSqLineSMid;
        allPoints[2] = outSqLineSEnd2;
        allPoints[3] = outSqLineWMid;
        allPoints[4] = outSqLineEMid;
        allPoints[5] = outSqLineNEnd1;
        allPoints[6] = outSqLineNMid;
        allPoints[7] = outSqLineNEnd2;
        allPoints[8] = midSqLineSEnd1;
        allPoints[9] = midSqLineSMid;
        allPoints[10] = midSqLineSEnd2;
        allPoints[11] = midSqLineWMid;
        allPoints[12] = midSqLineEMid;
        allPoints[13] = midSqLineNEnd1;
        allPoints[14] = midSqLineNMid;
        allPoints[15] = midSqLineNEnd2;
        allPoints[16] = innSqLineSEnd1;
        allPoints[17] = innSqLineSMid;
        allPoints[18] = innSqLineSEnd2;
        allPoints[19] = innSqLineWMid;
        allPoints[20] = innSqLineEMid;
        allPoints[21] = innSqLineNEnd1;
        allPoints[22] = innSqLineNMid;
        allPoints[23] = innSqLineNEnd2;
    	fadeTransition.setFromValue(1.0);
    	fadeTransition.setToValue(0.0);
    	fadeTransition.setCycleCount(Animation.INDEFINITE);
        setGameConfig();
        firstTurnPlay();
    }
}
