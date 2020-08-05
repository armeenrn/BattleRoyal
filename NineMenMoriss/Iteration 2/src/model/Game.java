package model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private double BOARDWIDTH = 30;
	private double CENTER_X = 0;
	private double CENTER_Y = 0;
	private HumanPlayer player1;
	private AIPlayer player2;
	private int goFirst;
	private Board gameBoard;
	private int winner = 0;

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	private Player[] playerList;

	public Game(String[] playerNames, int mode) {
		Random rand = new Random();
		int goFirst = 0;

		while (goFirst == 0) {
			goFirst = rand.nextInt(3);
		}

		if (goFirst == 1) {
			player1 = new HumanPlayer(playerNames[0], 1);
			player2 = new AIPlayer(playerNames[1], 2);
		}

		if (goFirst == 2) {
			player1 = new HumanPlayer(playerNames[1], 1);
			player2 = new AIPlayer(playerNames[0], 2);

		}

		gameBoard = new Board(BOARDWIDTH, CENTER_X, CENTER_Y);
		playerList = new Player[2];
		playerList[0] = player1;
		playerList[1] = player2;

	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public Player selectFirstPlayer() {
		return player1;
	}

	public Player selectSecondPlayer() {
		return player2;
	}

	/*
	
	
	
	
	
	*/

	public void play() {
		if (goFirst == 1) {
			System.out.println("You go first." + "\n");
		}

		else if (goFirst == 2) {
			System.out.println("The computer goes first" + "\n");
		}

		while (winner == 0) {
			if (goFirst == 1) {
				System.out.println("Your turn" + "\n");
				turnHumanPlayer(player1);
				if (winner == 0) {
					System.out.println("The computer's turn" + "\n");
					turnComputer(player2);
					displayScore();
				}

				else {
					break;
				}
			} else {
				System.out.println("The computer's turn" + "\n");
				turnComputer(player2);

				if (winner == 0) {
					System.out.println("Your turn" + "\n");
					turnHumanPlayer(player1);
					displayScore();
				}

				else {
					break;
				}
			}
		}
	}

	public void displayScore() {
		System.out.println("Your score: " + "\n" + player1.getNumberOfStonesPlaced() + " stones on board");
		System.out.println(getFilledLine(player1).size() + " lines formed");
		System.out.println(player1.getNumberOfTotalStones() + " stones remaining." + "\n");
		System.out.println("Computer score: " + "\n" + player2.getNumberOfStonesPlaced() + " stones on board");
		System.out.println(getFilledLine(player2).size() + " lines formed");
		System.out.println(player2.getNumberOfTotalStones() + " stones remaining." + "\n");
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

	public Stone checkIfPointIsUnOccupied(Point point) {
		return point.getOccupiedStone();
	}

	public void turnHumanPlayer(HumanPlayer humanPlayer) {
		displayPointsAsList();
		int filled_Lines_At_Start_Of_Turn = getFilledLine(humanPlayer).size();

		if (humanPlayer.getnumberOfNewStones() > 0) {
			Point userPoint = humanPlayer.selectDestination(getPointsAsList());
			while (checkIfPointIsUnOccupied(userPoint) != null) {
				System.out.println("Another user's stone is on this point. Choose another point." + "\n");
				userPoint = humanPlayer.selectDestination(getPointsAsList());
			}

			humanPlayer.moveStone(null, userPoint, 1);

		}

		else if (humanPlayer.getnumberOfNewStones() == 0) {
			displayFreeStones(humanPlayer);
			Stone selectedStone = humanPlayer.selectStoneToMove(getFreeStones(humanPlayer));
			displayAdjacentPoints(selectedStone);
			Point userPoint = humanPlayer.selectDestination(getAdjacentPoints(selectedStone));
			while (checkIfAjdacent(selectedStone, userPoint) == false) {
				System.out.println("Invalid Point. Choose adjcaent point." + "\n");
				userPoint = humanPlayer.selectDestination(getPointsAsList());
			}

			humanPlayer.moveStone(selectedStone, userPoint, 2);
		}

		int filled_Lines_At_End_Of_Turn = getFilledLine(humanPlayer).size();

		if (filled_Lines_At_End_Of_Turn > filled_Lines_At_Start_Of_Turn) {
			System.out.println("You formed a new line at: ");
			System.out.print(getFilledLine(humanPlayer).get(filled_Lines_At_End_Of_Turn - 1));
			System.out.println("You get to remove a stone on the board from the computer." + "\n");
			displayStonesOfOpponent(player2);
			Stone removeStone = humanPlayer.selectStoneToRemove(getStonesOfOpponent(player2));
			player2.removeStone(removeStone);
		}

		// check AI's number of stones at the end to see if you win
		if (player2.getNumberOfTotalStones() <= 3) {
			winner = 1;
			System.out.println("You won");
		}
	}

//	public void turnAIPlayer(AIPlayer compPlayer) {
//		if (compPlayer.getnumberOfNewStones() > 0) {
//			compPlayer.placeStone(player2.getStones().get(player2.getStonesPlaced()), gameBoard);
//		}
//		else {
//			if (compPlayer.getNumberOfStonesRemaining() <= 3) {
//				// jump
//				compPlayer.placeStone(player2.getStones().get(player2.getStonesPlaced()), gameBoard);
//			}
//			else {
//				// move adjacent
//			}
//		}
//		// check human player's number of stones at the end to see if you win
//		if (player1.getNumberOfStonesRemaining() < 3) {
//			winner = 2;
//		}
//	}

	public void turnComputer(AIPlayer compPlayer) {
		displayPointsAsList();
		int filled_Lines_At_Start_Of_Turn = getFilledLine(compPlayer).size();

		if (compPlayer.getnumberOfNewStones() > 0) {
			Point randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			while (checkIfPointIsUnOccupied(randomPoint) != null) {
				randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			}

			compPlayer.moveStone(null, randomPoint, 1);

		}

		else if (compPlayer.getnumberOfNewStones() == 0) {
			Stone randomStone = compPlayer.selectRandomStone(getFreeStones(compPlayer));
			Point randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			while (checkIfAjdacent(randomStone, randomPoint) == false) {
				randomStone = compPlayer.selectRandomStone(getFreeStones(compPlayer));
				randomPoint = compPlayer.getRandomPoint(getPointsAsList());
			}

			compPlayer.moveStone(randomStone, randomPoint, 2);
		}

		int filled_Lines_At_End_Of_Turn = getFilledLine(compPlayer).size();

		if (filled_Lines_At_End_Of_Turn > filled_Lines_At_Start_Of_Turn) {
			System.out.println("The computer formed a new line at: ");
			System.out.print(getFilledLine(compPlayer).get(filled_Lines_At_End_Of_Turn - 1));
			System.out.println("The computer removes one of your stones on the board." + "\n");
			Stone randomRemove = compPlayer.selectRandomStoneToRemove(getStonesOfOpponent(player1));
			player1.removeStone(randomRemove);
		}

		// check AI's number of stones at the end to see if you win
		if (player1.getNumberOfTotalStones() <= 3) {
			winner = 2;
			System.out.println("The computer won.");
		}
	}

	public ArrayList<Stone> getStonesOfOpponent(Player opponent) {
		ArrayList<Stone> stonesOfOpponent = new ArrayList<Stone>();

		for (Stone eachStone : opponent.getStones()) {
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
								if ((eachLine.getEndPoint1() == pointToSelect)
										|| (eachLine.getEndPoint2() == pointToSelect)) {
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

		for (int index = 0; index < (gameBoard.getSquares().length - 1); index++) {
			for (Line eachLine : gameBoard.getSquares()[index].getLines()) {
				for (Point eachPoint : eachLine.getPoints()) {
					if (pointsAsList.contains(eachPoint) == false) {
						pointsAsList.add(eachPoint);
					}
				}
			}
		}

		return pointsAsList;
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

	public ArrayList<Point> getAdjacentPoints(Stone stoneToMove) {
		ArrayList<Point> adjacentPoints = new ArrayList<Point>();
		for (Square eachSquare : gameBoard.getSquares()) {
			for (Line eachLine : eachSquare.getLines()) {
				for (Point eachPoint : eachLine.getPoints()) {
					if (checkIfAjdacent(stoneToMove, eachPoint) == true) {
						if (adjacentPoints.contains(eachPoint) == false) {
							adjacentPoints.add(eachPoint);
						}
					}
				}
			}
		}

		return adjacentPoints;
	}

	public void displayAdjacentPoints(Stone stoneToMove) {
		System.out.println("Adjacent Points Available");
		int counter = 1;
		for (Point eachPoint : getAdjacentPoints(stoneToMove)) {
			System.out.println(counter + ". " + eachPoint.toString());
			counter++;
		}

		System.out.println("");
	}

	public void displayStonesOfOpponent(Player opponent) {
		int counter = 0;
		System.out.println("Opponent Stones that can be removed");
		for (Stone eachStone : getStonesOfOpponent(opponent)) {
			System.out.println(counter + ". stone at: " + eachStone.getLocation().toString());
			counter++;
		}
		System.out.println("");
	}

	public void displayStones() {
		for (Stone eachStone : player1.getStones()) {
			if (eachStone.getLocation() != null) {
				System.out.println(eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			}
		}
	}

	public void displayFreeStones(Player player) {
		System.out.println("Stones that can be removed");
		int counter = 0;

		for (Stone eachStone : getFreeStones(player)) {
			System.out.println(
					counter + ". " + eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			counter++;
		}

		System.out.println("");
	}
}
