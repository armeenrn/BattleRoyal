/*
 * Game runs the overall game 
 */

package model;

import java.util.ArrayList;

/**
 * public class Game runs the game program
 * 
 * 10.00 6 August 2020
 * 
 * @author Armeen Rashidian, Daniel Kim Team D
 */

public class Game extends GameShared {
	private int winner = 0; // initialized to 0, set to 1 or 2 depending on if player1 or player2 wins,
							// respectively
	
	/**
	 * the constructor creates a new object of Game
	 */
	public Game() {
	}

	/**
	 * play runs the game
	 */
	public void play() {
		setGameConfig();
		int firstTurn = getFirstPlayer();
		HumanPlayer player1 = selectFirstPlayer();
		AIPlayer player2 = selectSecondPlayer();
		
		setGameConfig();

		if (firstTurn == 1) {
			System.out.println("You go first." + "\n");
		}

		else {
			System.out.println("The computer goes first" + "\n");
		}

		while (winner == 0) {

			if (firstTurn == 1) {
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

	/**
	 * displayScore displays as text the score of each player after a turn
	 */
	public void displayScore() {
		System.out.println("Your score: " + "\n" + selectFirstPlayer().getNumberOfTotalStones()
				+ " stones on board");
		System.out.println(getFilledLine(selectFirstPlayer()).size() + " lines formed");
		System.out.println(selectFirstPlayer().getNumberOfTotalStones() + " stones remaining." + "\n");
		System.out.println("Computer score: " + "\n" + selectSecondPlayer().getNumberOfTotalStones()
				+ " stones on board");
		System.out.println(getFilledLine(selectSecondPlayer()).size() + " lines formed");
		System.out.println(selectSecondPlayer().getNumberOfTotalStones() + " stones remaining." + "\n");
	}

	/**
	 * turnHumanPlayer runs the move for the human player
	 * 
	 * @param humanPlayer
	 */
	public void turnHumanPlayer(HumanPlayer humanPlayer) {
		displayPointsAsList();
		ArrayList<Line> filled_Lines_At_Start_Of_Turn = getFilledLine(humanPlayer);

		if (humanPlayer.getNumberOfPlacedStones() < 9) {
			// can place a stone
			Point userPoint = humanPlayer.selectDestination(getPointsAsList());

			while (checkIfPointIsUnOccupied(userPoint) != 0) {
				System.out.println("Another user's stone is on this point. Choose another point." + "\n");
				userPoint = humanPlayer.selectDestination(getPointsAsList());
			}

			moveStone(selectFirstPlayer(), null, userPoint);
			System.out.println("Player " + selectFirstPlayer().getPlayerNumber()
					+ ": New stone placed at: " + userPoint.toString());
			System.out.println("");
		}

		else if (humanPlayer.getNumberOfPlacedStones() == 0) {
			// can only move adjacent
			displayFreeStones(humanPlayer);
			Stone selectedStone = humanPlayer.selectStoneToMove(getFreeStones(humanPlayer));
			displayAdjacentPoints(selectedStone);
			Point userPoint = humanPlayer.selectDestination(getAdjacentPoints(selectedStone));

			while (checkIfAjdacent(selectedStone, userPoint) == false) {
				System.out.println("Invalid Point. Choose adjcaent point." + "\n");
				userPoint = humanPlayer.selectDestination(getPointsAsList());
			}

			moveStone(humanPlayer, selectedStone, userPoint);
			System.out.println(this.toString() + ": stone moved to: " + userPoint.toString());
			System.out.println("");
		}

		ArrayList<Line> filled_Lines_At_End_Of_Turn = getFilledLine(humanPlayer);

		/*
		 * checking to see if the player formed a new line during their turn
		 */

		if ((!(filled_Lines_At_End_Of_Turn.equals(filled_Lines_At_Start_Of_Turn)))
				&& filled_Lines_At_End_Of_Turn.size() >= filled_Lines_At_Start_Of_Turn.size()) {
			System.out.println("You formed a new line!");
			System.out.println("You get to remove a stone on the board from the computer." + "\n");
			displayStonesOfOpponent(selectSecondPlayer());
			Stone removeStone = humanPlayer
					.selectStoneToRemove(getStonesOfOpponent(selectSecondPlayer()));
			removeStone(selectSecondPlayer(), removeStone);
			System.out.println(selectSecondPlayer().toString() + "'s stone removed");
			System.out.println("");
		}

		/*
		 * check AI's number of stones at the end to see if you win
		 */

		if (selectSecondPlayer().getNumberOfTotalStones() <= 3) {
			winner = 1;
			System.out.println("You won");
		}
	}

	/**
	 * turnComputer runs the AIPlayer's turn
	 * 
	 * @param compPlayer
	 */
	public void turnComputer(AIPlayer compPlayer) {
		displayPointsAsList();
		ArrayList<Line> filled_Lines_At_Start_Of_Turn = getFilledLine(compPlayer);
		Point movePoint;
		Stone moveStone;

		// calls AI Player to look for the best move it can make; it will return the
		// point the player will move to
		// if it has no good answer it will return null, and the AI will make a random
		// move
		movePoint = compPlayer.lookForBestMove(getGameBoard());
		if (compPlayer.getNumberOfPlacedStones() < 9) {
			// can place a stone
			if (movePoint == null) {
				do {
					movePoint = compPlayer.getRandomPoint(getPointsAsList());
				} while (checkIfPointIsUnOccupied(movePoint) != 0);
			}
			moveStone(compPlayer, null, movePoint);
		}

		else if (compPlayer.getNumberOfTotalStones() > 3) {
			// can move adjacent

			if (movePoint == null) {
				Stone randomStone;

				do {
					randomStone = compPlayer.selectRandomStone(getFreeStones(compPlayer));
					movePoint = compPlayer.getRandomPoint(getPointsAsList());
				} while (checkIfAjdacent(randomStone, movePoint) == false);

				moveStone = randomStone;
			}

			else {
				moveStone = compPlayer.getBestMoveStone();
			}

			moveStone(compPlayer, moveStone, movePoint);
		}

		else {
			// can jump around with existing stones
			if (movePoint == null) {
				Stone randomStone;

				do {
					randomStone = compPlayer.selectRandomStone(getFreeStones(compPlayer));
					movePoint = compPlayer.getRandomPoint(getPointsAsList());
				} while (movePoint.getOccupiedPlayer() != 0);

				moveStone = randomStone;
			} else {
				moveStone = compPlayer.getBestMoveStone();
			}

			moveStone(compPlayer, moveStone, movePoint);
		}

		ArrayList<Line> filled_Lines_At_End_Of_Turn = getFilledLine(compPlayer);

		/*
		 * check if the AI player formed a new line during their turn
		 */

		if ((!(filled_Lines_At_End_Of_Turn.equals(filled_Lines_At_Start_Of_Turn)))
				&& filled_Lines_At_End_Of_Turn.size() >= filled_Lines_At_Start_Of_Turn.size()) {
			System.out.println("The computer formed a new line at: ");
			System.out.print(getFilledLine(compPlayer).get(filled_Lines_At_End_Of_Turn.size() - 1));
			System.out.println("The computer removes one of your stones on the board." + "\n");
			Stone removeStone = compPlayer.lookforBestRemove(getGameBoard(),
					selectFirstPlayer());
			removeStone(selectFirstPlayer(), removeStone);
		}

		/*
		 * check human player's number of stones at the end to see if you win
		 */

		if (selectFirstPlayer().getNumberOfTotalStones() <= 3) {
			winner = 2;
			System.out.println("The computer won.");
		}
	}

	/**
	 * displayPointsAsList displays the list of points on the board
	 */
	public void displayPointsAsList() {
		System.out.println("GAME BOARD");
		int counter = 0;

		for (Point eachPoint : getPointsAsList()) {
			String write = "";
			write = write + counter + eachPoint.toString() + " ";
			if (eachPoint.getOccupiedPlayer() != 0) {
				write = write + "Player " + eachPoint.getOccupiedPlayer() + " X";
			}

			System.out.println(write);
			counter = counter + 1;
		}

		System.out.println("");
	}

	/**
	 * displayAdjacentPoints displays as a list the available, adjacent points of a
	 * stone selected to be moved
	 * 
	 * @param stoneToMove, the stone selected to be moved
	 */
	public void displayAdjacentPoints(Stone stoneToMove) {
		System.out.println("Adjacent Points Available");
		int counter = 1;

		for (Point eachPoint : getAdjacentPoints(stoneToMove)) {
			System.out.println(counter + ". " + eachPoint.toString());
			counter++;
		}

		System.out.println("");
	}

	/**
	 * displayStonesOfOpponent displays as a list the stones of an opponent player
	 * once the other player forms a line
	 * 
	 * @param opponent, the opponent player who's stones are to be removed from the
	 *                  board
	 */
	public void displayStonesOfOpponent(Player opponent) {
		int counter = 0;
		System.out.println("Opponent Stones that can be removed");

		for (Stone eachStone : getStonesOfOpponent(opponent)) {
			if (!eachStone.getDead()) {
				counter++;
				System.out.println(counter + ". stone at: " + eachStone.getLocation().toString());
			}
		}

		System.out.println("");
	}

	/**
	 * displays a generic list of stones of the first player
	 */
	public void displayStones() {
		for (Stone eachStone : selectFirstPlayer().getStones()) {
			if (!eachStone.getDead()) {
				System.out.println(eachStone.getOwner().toString() + ": " + eachStone.getLocation().toString());
			}
		}
	}

	/**
	 * displayFreeStones displays the free stones of a player that can be moved on
	 * the board
	 * 
	 * @param player, the player who is about to move their stones along the board
	 */
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
