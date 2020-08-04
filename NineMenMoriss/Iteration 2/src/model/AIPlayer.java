package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class AIPlayer extends Player {
	String name;
	private int movePriority;
	private final static int NONE = 0;
	private final static int FILL_LINE_THISTURN = 1;
	private final static int STOP_OPPONENT_NEXTTURN = 2;
	private final static int TWO_STEPS_TO_FILL = 3;
	private Point pointToMoveTo;
	
	public AIPlayer(String name, int num, boolean goFirst) {
		super(name, num, goFirst);
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	
	public Point getRandomPoint(ArrayList<Point> pointsList) {
		int index = 0;
		Random rand = new Random();
		index = rand.nextInt(pointsList.size());
		while (pointsList.get(index).getOccupiedPlayer() != 0) {
			index = rand.nextInt(pointsList.size());
		}
		
		return pointsList.get(index);
	}
	
	
	public Stone selectRandomStone(ArrayList<Stone> stonesList) {
		int index = 0;
		Random rand = new Random();
		index = rand.nextInt(stonesList.size());
		return stonesList.get(index);
	}
	
	
	public Stone selectRandomStoneToRemove(ArrayList<Stone> stonesOfOpponent) {
		int index;
		Random rand = new Random();
		index = rand.nextInt(stonesOfOpponent.size());
		return stonesOfOpponent.get(index);
	}
	
	public void lookForBestMove(Board board) {
		ArrayList<Stone> stones = getStones();
	}
	
	public void performBestMove(Stone stone, Point location, int moveType) {
		
	}
	
	public Point[] getPointList(Stone stone, Board board) {
		return new Point[23];
	}
		
	/**
	 * Check and see which move the computer should make.
	 * 
	 * @param board Board
	 * @return Point the computer's stone should move to
	 */
	public Point checkPriorityMove(Board board) {
		// get stones that the player has
		ArrayList<Stone> stonesOnBoard = getStones();
		ArrayList<Point> adjacentPoints;
		ArrayList<Point> adjacentToAdjacents;
		// go through each stone to see if something can be done
		int currentPriority = FILL_LINE_THISTURN;

		for (Stone currentStone : stonesOnBoard) {
			adjacentPoints = getAdjacentPoints(currentStone, board);
			
			for (Point pointToTest : adjacentPoints) {
				if (pointToTest.getOccupiedPlayer() == 0) {
					// can move; check if moving there will fill the line
					
				}
			}
		}		
		
		return pointToMoveTo;
	}
		
	/**
	 * Check which movement options are available and should be carried out by the computer
	 * 
	 * @param stones List of stones the player owns
	 * @param board Board
	 * @return Indication of which move the computer should make
	 */
	public int checkMovement(ArrayList<Stone> stones, Board board) {
		return 0;
	}

	/**
	 * Check if the computer can fill a line to form a mill
	 * 
	 * @param stones List of stones the player owns
	 * @param board Board
	 * @return Can/cannot fill a line
	 */
	public boolean checkFill(ArrayList<Stone> stones, Board board) {
		return true;
	}

	/**
	 * Check if the computer should stop the opponent from forming a mill
	 * 
	 * @param stones List of stones the player owns
	 * @param board Board
	 * @return Can/cannot stop the opponent
	 */
	public boolean checkStopFill(ArrayList<Stone> stones, Board board) {
		return true;
	}

	/**
	 * Check if the computer can take 2 moves to form a mill
	 * 
	 * @param stones List of stones the player owns
	 * @param board Board
	 * @return Two turns away from filling a line and forming a mill
	 */
	public boolean checkGoForFill(ArrayList<Stone> stones, Board board) {
		return true;
	}
	/**
	 * Decide which stone the computer should remove after forming a mill
	 */
	public void checkRemove() {
		
	}
	
	

	
}