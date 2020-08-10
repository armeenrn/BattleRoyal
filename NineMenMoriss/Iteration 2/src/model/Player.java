package model;

import java.util.ArrayList;

/**
 * This class keeps track of the player's name, stones, first or second turn.
 * All the details about the player during the game can be extracted from here.
 * 
 * @author srish
 *
 */
public class Player {
	private ArrayList<Stone> stones;
	private int placedStones = 0;
	private int totalStones = 9;
	private String name;
	private int playerNumber;
	private Board board;
	private Point[] allPoints = new Point[24];

	/**
	 * 
	 * @param name      name of the player
	 * @param num       symbolizes player turn's ( 1 if plays first move, second
	 *                  otherwise)
	 * @param goFirst   true or false value based on player's turn chosen randomly
	 * @param gameBoard a new object created for type game every time a new game is
	 *                  started
	 */
	public Player(String name, int num, boolean goFirst, Board gameBoard)
			throws Exception {

		try {
			stones = new ArrayList<Stone>();
			this.name = name;
			playerNumber = num;
			board = gameBoard;

			allPoints[0] = board.getSquares()[0].getSouthLine().getEndPoint1();
			allPoints[1] = board.getSquares()[0].getSouthLine().getMidPoint();
			allPoints[2] = board.getSquares()[0].getSouthLine().getEndPoint2();
			allPoints[3] = board.getSquares()[0].getWestLine().getMidPoint();
			allPoints[4] = board.getSquares()[0].getEastLine().getMidPoint();
			allPoints[5] = board.getSquares()[0].getNorthLine().getEndPoint1();
			allPoints[6] = board.getSquares()[0].getNorthLine().getMidPoint();
			allPoints[7] = board.getSquares()[0].getNorthLine().getEndPoint2();
			allPoints[8] = board.getSquares()[1].getSouthLine().getEndPoint1();
			allPoints[9] = board.getSquares()[1].getSouthLine().getMidPoint();
			allPoints[10] = board.getSquares()[1].getSouthLine().getEndPoint2();
			allPoints[11] = board.getSquares()[1].getWestLine().getMidPoint();
			allPoints[12] = board.getSquares()[1].getEastLine().getMidPoint();
			allPoints[13] = board.getSquares()[1].getNorthLine().getEndPoint1();
			allPoints[14] = board.getSquares()[1].getNorthLine().getMidPoint();
			allPoints[15] = board.getSquares()[1].getNorthLine().getEndPoint2();
			allPoints[16] = board.getSquares()[2].getSouthLine().getEndPoint1();
			allPoints[17] = board.getSquares()[2].getSouthLine().getMidPoint();
			allPoints[18] = board.getSquares()[2].getSouthLine().getEndPoint2();
			allPoints[19] = board.getSquares()[2].getWestLine().getMidPoint();
			allPoints[20] = board.getSquares()[2].getEastLine().getMidPoint();
			allPoints[21] = board.getSquares()[2].getNorthLine().getEndPoint1();
			allPoints[22] = board.getSquares()[2].getNorthLine().getMidPoint();
			allPoints[23] = board.getSquares()[2].getNorthLine().getEndPoint2();
		} catch (Exception e) {
			System.out.println("Exception " + e + " is thrown.");

		}
	}

	/**
	 * Return the name of the human player
	 */
	public String toString() {
		return name;
	}

	/**
	 * Getter method to get all the stones of this player in an array list
	 * 
	 * @return
	 */
	public ArrayList<Stone> getStones() {
		return stones;
	}

	/**
	 * A getter method to get total number of placed stones at any given moment
	 * during the game.
	 * 
	 * @return number od stones placed on the board
	 */
	public int getNumberOfPlacedStones() {
		return placedStones;
	}

	/**
	 * A getter method to access total number of player stones.
	 * 
	 * @return total number of stones of the player
	 */
	public int getNumberOfTotalStones() {
		return totalStones;
	}

	/**
	 * Increased the values of placed stones once they the player places more stones
	 * on the board
	 */
	public void increaseNumberOfPlacedStones() {
		placedStones++;
	}

	/**
	 * These are the stones that have been removed by the opponent after forming a
	 * line of three stones.
	 */
	public void decreaseNumberOfOwnedStones() {
		totalStones--;
	}

	/**
	 * This is a getter method to get the player's turn number. 1 is first turn and
	 * 2nd is second turn.
	 * 
	 * @return
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * A getter method to get all the points with their x and y coordinates on the
	 * board.
	 * 
	 * @return all the points on the board in an Array
	 */
	public Point[] getPointList() {
		return allPoints;
	}

	/**
	 * Called by Game class to move one Stone to an adjacent empty point. Can be
	 * used in phase 2
	 * 
	 * @param stone stone to move
	 * @param board game board
	 */
	public ArrayList<Point> getAdjacentPoints(Point currentPoint) {
		ArrayList<Point> adjacentPoints = new ArrayList<Point>();

		if (currentPoint.equals(allPoints[0])) {
			adjacentPoints.add(allPoints[1]);
			adjacentPoints.add(allPoints[3]);
		} else if (currentPoint.equals(allPoints[1])) {
			adjacentPoints.add(allPoints[0]);
			adjacentPoints.add(allPoints[2]);
			adjacentPoints.add(allPoints[9]);
		} else if (currentPoint.equals(allPoints[2])) {
			adjacentPoints.add(allPoints[1]);
			adjacentPoints.add(allPoints[4]);
		} else if (currentPoint.equals(allPoints[3])) {
			adjacentPoints.add(allPoints[0]);
			adjacentPoints.add(allPoints[5]);
			adjacentPoints.add(allPoints[11]);
		} else if (currentPoint.equals(allPoints[4])) {
			adjacentPoints.add(allPoints[2]);
			adjacentPoints.add(allPoints[7]);
			adjacentPoints.add(allPoints[12]);
		} else if (currentPoint.equals(allPoints[5])) {
			adjacentPoints.add(allPoints[3]);
			adjacentPoints.add(allPoints[6]);
		} else if (currentPoint.equals(allPoints[6])) {
			adjacentPoints.add(allPoints[5]);
			adjacentPoints.add(allPoints[7]);
			adjacentPoints.add(allPoints[14]);
		} else if (currentPoint.equals(allPoints[7])) {
			adjacentPoints.add(allPoints[4]);
			adjacentPoints.add(allPoints[6]);
		} else if (currentPoint.equals(allPoints[8])) {
			adjacentPoints.add(allPoints[9]);
			adjacentPoints.add(allPoints[11]);
		} else if (currentPoint.equals(allPoints[9])) {
			adjacentPoints.add(allPoints[1]);
			adjacentPoints.add(allPoints[8]);
			adjacentPoints.add(allPoints[10]);
			adjacentPoints.add(allPoints[17]);
		} else if (currentPoint.equals(allPoints[10])) {
			adjacentPoints.add(allPoints[9]);
			adjacentPoints.add(allPoints[12]);
		} else if (currentPoint.equals(allPoints[11])) {
			adjacentPoints.add(allPoints[3]);
			adjacentPoints.add(allPoints[8]);
			adjacentPoints.add(allPoints[13]);
			adjacentPoints.add(allPoints[19]);
		} else if (currentPoint.equals(allPoints[12])) {
			adjacentPoints.add(allPoints[4]);
			adjacentPoints.add(allPoints[10]);
			adjacentPoints.add(allPoints[15]);
			adjacentPoints.add(allPoints[20]);
		} else if (currentPoint.equals(allPoints[13])) {
			adjacentPoints.add(allPoints[11]);
			adjacentPoints.add(allPoints[14]);
		} else if (currentPoint.equals(allPoints[14])) {
			adjacentPoints.add(allPoints[6]);
			adjacentPoints.add(allPoints[13]);
			adjacentPoints.add(allPoints[15]);
			adjacentPoints.add(allPoints[22]);
		} else if (currentPoint.equals(allPoints[15])) {
			adjacentPoints.add(allPoints[12]);
			adjacentPoints.add(allPoints[14]);
		} else if (currentPoint.equals(allPoints[16])) {
			adjacentPoints.add(allPoints[17]);
			adjacentPoints.add(allPoints[19]);
		} else if (currentPoint.equals(allPoints[17])) {
			adjacentPoints.add(allPoints[9]);
			adjacentPoints.add(allPoints[16]);
			adjacentPoints.add(allPoints[18]);
		} else if (currentPoint.equals(allPoints[18])) {
			adjacentPoints.add(allPoints[17]);
			adjacentPoints.add(allPoints[20]);
		} else if (currentPoint.equals(allPoints[19])) {
			adjacentPoints.add(allPoints[11]);
			adjacentPoints.add(allPoints[16]);
			adjacentPoints.add(allPoints[21]);
		} else if (currentPoint.equals(allPoints[20])) {
			adjacentPoints.add(allPoints[12]);
			adjacentPoints.add(allPoints[18]);
			adjacentPoints.add(allPoints[23]);
		} else if (currentPoint.equals(allPoints[21])) {
			adjacentPoints.add(allPoints[19]);
			adjacentPoints.add(allPoints[22]);
		} else if (currentPoint.equals(allPoints[22])) {
			adjacentPoints.add(allPoints[14]);
			adjacentPoints.add(allPoints[21]);
			adjacentPoints.add(allPoints[23]);
		} else if (currentPoint.equals(allPoints[23])) {
			adjacentPoints.add(allPoints[20]);
			adjacentPoints.add(allPoints[22]);
		} else {
			// shouldn't happen.
			System.out.println(
					"ArrayList currentPoint contains an error. It is either empty or some index values do not exist.");
		}

		return adjacentPoints;
	}
}

//MY OLD code
//import java.util.Scanner;
//public class Player {
//	
//	private String name;
//	
//	private Stone[] stonesRemaining;
//	
//	private Stone[] stonesOnBoard;
//
//	public Player(String name) {
//		this.name = name;
//		stonesRemaining = new Stone[9];
//		
//		for (int index = 0; index < stonesRemaining.length; index++) {
//			stonesRemaining[index] = new Stone();
//		}
//		
//		stonesOnBoard = new Stone[9];
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public Stone[] getStonesRemaining() {
//		
//		return stonesRemaining;
//	}
//	
//	public Stone[] getStonesOnBoard() {
//		
//		return stonesOnBoard;
//	}
//	
//	public Stone chooseANewStone() {
//		Stone result = null;
//	
//		
//		for (int index = 0; index < stonesRemaining.length; index++) {
//			if (stonesRemaining[index] != null) {
//				result = stonesRemaining[index];
//				stonesRemaining[index] = null;
//				index = stonesRemaining.length;
//			}
//		}
//		
//		if (result != null) {
//			addStoneToBoardList(result);
//		}
//		
//		return result;
//	}
//	
//	
//	public Stone chooseExistingStone(int whatToChoose) {
//		return getStonesOnBoard()[whatToChoose];
//	}
// 	
//	
//	public void addStoneToBoardList(Stone newStone) {
//		
//		for (int index = 0; index < stonesOnBoard.length; index++) {
//			if (stonesOnBoard[index] == null) {
//				stonesOnBoard[index] = newStone;
//				index = stonesOnBoard.length;
//			}
//		}
//	}
//	
//	
//}
