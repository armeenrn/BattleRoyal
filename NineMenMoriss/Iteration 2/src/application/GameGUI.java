package application;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.AIPlayer;
import model.Board;
import model.HumanPlayer;
import model.Line;
import model.Player;
import model.Point;
import model.Square;
import model.Stone;

public class GameGUI {
	private double BOARDWIDTH = 30;
	private double CENTER_X = 0;
	private double CENTER_Y = 0;
	private HumanPlayer player1;
	private AIPlayer player2;
	private int goFirst;
	private Board gameBoard;
	private int winner = 0;
	private Player[] playerList;
	private GameController gameController;
	private Point selectedPoint;
	private boolean firstPlayerJustWent = false;
	private boolean gameStarted = false;
	
	public GameGUI(String[] playerNames, int mode, GameController controller) {
		gameController = controller;
		
		Random rand = new Random();
		goFirst = 0;
		
		while (goFirst == 0) {
			goFirst = rand.nextInt(3); 
		}
		
		if (goFirst == 1) {
			player1 = new HumanPlayer(playerNames[0], 1, gameController);
			player2 = new AIPlayer(playerNames[1], 2, gameController);	
		}
		
		if (goFirst == 2) {
			player1 = new HumanPlayer(playerNames[1], 1, gameController);
			player2 = new AIPlayer(playerNames[0], 2, gameController);	
			
			
		}
		
		gameBoard = new Board(BOARDWIDTH, CENTER_X, CENTER_Y);
		playerList = new Player[2];
		playerList[0] = player1;
		playerList[1] = player2;
		
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}
	
	public GameController getController() {
		return gameController;
	}
	
	public Player selectHumanPlayer() {
		return player1;
	}
	
	public Player selectAIPlayer() {
		return player2;
	}

	
	public void play() {
		if (gameStarted == false) {
			if (goFirst == 1) {
				gameController.getMyText().setText("Enter play. Human player goes first");
			}
			else {
				gameController.getMyText().setText("Enter play. AI player goes first");
			}
			gameStarted = true;
		}
		
		
		if (goFirst == 1) {
			if (firstPlayerJustWent == false) {
				firstPlayerJustWent = true;
				gameController.getMyText().setText("It is the Human Player's turn");
				humanPlayerTurn();
			}
			
			else {
				firstPlayerJustWent = false;
				gameController.getMyText().setText("It is the AI Player's turn");
				AIPlayerTurn();
			}
		}
		
		else if (goFirst == 2) {
			if (firstPlayerJustWent == false) {
				gameController.getMyText().setText("It is the AI Player's turn");
				firstPlayerJustWent = true;
				AIPlayerTurn();
			}
			
			else {
				firstPlayerJustWent = false;
				gameController.getMyText().setText("It is the Human Player's turn");
				humanPlayerTurn();
			}
		}	
	}
	
	public void humanPlayerTurn() {
			// if  stones placed more than 0
			gameController.setPreventClick(false);
			seSelectedPoint(null);
		
	}
	
	
	public void seSelectedPoint(Point point) {	
		if (point == null) {
			gameController.turn();
		}
		
		else if (point != null) {
			int initialLines = getFilledLine(player1).size();
			selectedPoint = point;
		
			
			if (checkIfPointIsUnOccupied(selectedPoint) == null) {
				gameController.setPreventClick(true);
				player1.moveStone(null, selectedPoint, 1);
				displayScore();
				displayPointsAsList();
				selectedPoint.getCircle().setFill(Color.BLUE);
				gameController.moveRockToPoint(selectedPoint.getOccupiedStone());
				checkPoint(player1, initialLines);
			}
			
			else {
				gameController.getMyText().setText("Point occupied. Try again");
				gameController.turn();
			}
		}
	}
	
	
	public void checkPoint(Player player, int initialLines) {
		gameController.getMyText().setText("enter checkpoint");
		if (getFilledLine(player).size() > initialLines) {
			
		}
		
		// if opponent has less than 3 stones
		
		
		else {
			play();
		}
	}
	
	
	public void AIPlayerTurn() {
		int filled_Lines_At_Start_Of_Turn = getFilledLine(player2).size();
		
		if (player2.getnumberOfNewStones() > 0) {
			Point randomPoint = player2.getRandomPoint(getPointsAsList());
			while (checkIfPointIsUnOccupied(randomPoint) != null) {
				randomPoint = player2.getRandomPoint(getPointsAsList());
			}
			
			player2.moveStone(null, randomPoint, 1);
			gameController.moveRockToPoint(randomPoint.getOccupiedStone());
			
		}
		
		else if (player2.getnumberOfNewStones() == 0) {
			Stone randomStone = player2.selectRandomStone(getFreeStones(player2));
			Point randomPoint = player2.getRandomPoint(getPointsAsList());
			while (checkIfAjdacent(randomStone, randomPoint) == false) {
				randomStone = player2.selectRandomStone(getFreeStones(player2));
				randomPoint = player2.getRandomPoint(getPointsAsList());
			}
			
			player2.moveStone(randomStone, randomPoint, 2);
		}
		
		int filled_Lines_At_End_Of_Turn = getFilledLine(player2).size();
		
		if (filled_Lines_At_End_Of_Turn > filled_Lines_At_Start_Of_Turn) {
			gameController.getMyText().setText("The computer formed a new line at: " );
			System.out.print(getFilledLine(player2).get(filled_Lines_At_End_Of_Turn-1));
			System.out.println("The computer removes one of your stones on the board." + "\n");
			Stone randomRemove = player2.selectRandomStoneToRemove(getStonesOfOpponent(player1));
			player1.removeStone(randomRemove);
		}
		
		// check AI's number of stones at the end to see if you win
		if (player1.getNumberOfTotalStones() <= 3) {
			winner = 2;
			System.out.println("The computer won.");
		}
		
		else {
			play();
		}
	}
	
	public Stone checkIfPointIsUnOccupied(Point point) {
		return point.getOccupiedStone();
	}
	
	
	
	public ArrayList<Line> getFilledLine(Player player) {
		ArrayList<Line> filledLines = new ArrayList<Line>();
		for (Square eachSquare : gameBoard.getSquares()) {
			for (Line eachLine : eachSquare.getLines()) {
				if (eachLine.isLineFilled(player) == true) {
					filledLines.add(eachLine);
				}
					
			}
		}
		
		return filledLines;
	}

	public ArrayList<Stone> getStonesOfOpponent(Player opponent) {
		ArrayList<Stone> stonesOfOpponent = new ArrayList<Stone>();
		
		for (Stone eachStone: opponent.getStones()) {
			if (eachStone.getLocation() != null) {
				stonesOfOpponent.add(eachStone);
			}
		}
		return stonesOfOpponent;
	}
	
	public boolean checkIfAjdacent(Stone stoneToSelect, Point pointToSelect) {
		boolean answer = false;
		
		if (checkIfPointIsUnOccupied(pointToSelect) == null) {
			for (Square eachSquare : gameBoard.getSquares()) {
				for (Line eachLine : eachSquare.getLines()) {
					if (eachLine.doesLineContain(stoneToSelect) == true) {
						if (eachLine.doesLineContainPoint(pointToSelect) == true) {
							if (eachLine.getEndPoint1() == stoneToSelect.getLocation()) {
								if (eachLine.getMidPoint() == pointToSelect) {
									answer = true;
								}
							}
							
							else if (eachLine.getMidPoint() == stoneToSelect.getLocation()) {
								if ((eachLine.getEndPoint1() == pointToSelect) || (eachLine.getEndPoint2() == pointToSelect)) {
									answer = true;
								}	
							}
							
							else if (eachLine.getEndPoint2() == stoneToSelect.getLocation()) {
								if (eachLine.getMidPoint() == pointToSelect) {
									answer = true;
								}	
							}
						}
					}
				}
			}
		}
		
		return answer; 
	}
	
	
	public ArrayList<Point> getPointsAsList() {
		ArrayList<Point> pointsAsList = new ArrayList<Point>();
		for (Point eachPoint : gameBoard.getSquares()[0].getSouthLine().getPoints()) {
				pointsAsList.add(eachPoint);	
			}
		
		for (Point eachPoint : gameBoard.getSquares()[1].getSouthLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		
		for (Point eachPoint : gameBoard.getSquares()[2].getSouthLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		
		for (int index = 2; index > -1; index--) {
			pointsAsList.add(gameBoard.getSquares()[3].getWestLine().getPoints()[index]);	
		}
	
		for (Point eachPoint : gameBoard.getSquares()[3].getEastLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		
		for (Point eachPoint : gameBoard.getSquares()[2].getNorthLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		
		for (Point eachPoint : gameBoard.getSquares()[1].getNorthLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		
		for (Point eachPoint : gameBoard.getSquares()[0].getNorthLine().getPoints()) {
			pointsAsList.add(eachPoint);	
		}
		return pointsAsList;
	}
		

	public ArrayList<Stone> getFreeStones(Player player) {
		ArrayList<Stone> stonesAvailable = new ArrayList<Stone>();
		boolean shouldAdd = true;
		
		for (Stone eachStone : player.getStones()) {
			for (Line eachLine : getFilledLine(player)) {
				if (eachLine.doesLineContain(eachStone) == true) {
					shouldAdd = false;
				}
			}
			
			if (shouldAdd == true) {
				stonesAvailable.add(eachStone);
			}
		}
		
		if (stonesAvailable.size() == 0) {
			stonesAvailable = new ArrayList<Stone>();
			for (Stone eachStone : player.getStones()) {
				if (eachStone.getLocation() != null) {
					stonesAvailable.add(eachStone);
				}
			}
		}
		
		return stonesAvailable;
	}
	
	public void displayPointsAsList() {
		System.out.println("GAME BOARD");
		int counter = 0;
		for (Point eachPoint : getPointsAsList()) {
			String write = "";
			write = write + counter + eachPoint.toString() + " ";
			if (eachPoint.getOccupiedPlayer() != null) {
				write = write + eachPoint.getOccupiedPlayer().toString() + " X";
			}
			System.out.println(write);
 			counter = counter + 1;
		}
		
		System.out.println("");
	}
	
	
	public void displayScore() {
		System.out.println("Your score: " + "\n" + player1.getNumberOfStonesPlaced() + " stones on board");
		System.out.println(getFilledLine(player1).size() + " lines formed");
		System.out.println(player1.getNumberOfTotalStones() + " stones remaining." + "\n");
		System.out.println("Computer score: " + "\n" + player2.getNumberOfStonesPlaced() + " stones on board");
		System.out.println(getFilledLine(player2).size() + " lines formed");
		System.out.println(player2.getNumberOfTotalStones() + " stones remaining." + "\n");
	}
}




