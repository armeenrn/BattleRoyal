/*
 * GameShared runs the GUI version of the game 
 */

package model;

import java.util.ArrayList;
import java.util.Random;

/** 
 * public class GameShared runs the GUI program
 * 
 * 	10.00 6 August 2020
 * 	@author Daniel Kim, Armeen Rashidian
 * 	Team D
 */


public class GameShared {
	private double BOARDWIDTH = 30;
	private double CENTER_X = 0;
	private double CENTER_Y = 0;
	private HumanPlayer player1;
	private AIPlayer player2;
	private int goFirst;			// determines if player1 or player2 goes first
	private Player[] playerList;
	private Board gameBoard;
	
	private final int NO_ONE_NUM = 0;			// num representing no one, when game initializes
	private final int HUMAN_PLAYER_NUM = 1;		
	private final int COMP_PLAYER_NUM = 2;
	
	/**
	 * setGameConfig sets up the game. It creates the game board, creates the players, and determines who goes first
	 */
	public void setGameConfig() {
		gameBoard = new Board(BOARDWIDTH, CENTER_X, CENTER_Y);		
		Random rand = new Random();
		
		/* 
		 * goFirst will either be 1 or 2 
		 */
		
		goFirst = rand.nextInt(2) + 1; 		
		
		if (goFirst == HUMAN_PLAYER_NUM) {
			player1 = new HumanPlayer("YOU", 1, true, gameBoard);
			player2 = new AIPlayer("COMPUTER", 2, false, gameBoard);	
		}
		
		if (goFirst == COMP_PLAYER_NUM) {
			player1 = new HumanPlayer("YOU", 1, false, gameBoard);
			player2 = new AIPlayer("COMPUTER", 2, true, gameBoard);	
			
		}

		playerList = new Player[2];
		playerList[0] = player1;
		playerList[1] = player2;
	}
	
	/**
	 * getGameBoard returns the gameBoard
	 * 	@return gameBoard
	 */
	public Board getGameBoard() {
		return gameBoard;
	}

	/**
	 * selectFirstPlayer returns the human player, which is player1
	 * 	@return player1
	 */
	public HumanPlayer selectFirstPlayer() {
		return player1;
	}
	
	/**
	 * selectSecondPlayer returns the AI player, which is player2
	 * 	@return player2
	 */
	public AIPlayer selectSecondPlayer() {
		return player2;
	}
	
	/**
	 * getFirstPlayer returns who goes first
	 * 	@return goFirst, the int determing if player1 or player2 goes first
	 */
	public int getFirstPlayer() {
		return goFirst;
	}
	
	/**
	 * getPlayerNumOne returns the integer associated with the human player
	 * 	@return HUMAN_PLAYER_NUM
	 */
	public int getPlayerNumOne() {
		return HUMAN_PLAYER_NUM;
	}
	
	/**
	 * getPlayerNumTwo returns the integer associated with the AI player
	 *	 @return COMP_PLAYER_NUM
	 */
	public int getPlayerNumTwo() {
		return COMP_PLAYER_NUM;
	}

	/**
	 * getFilledLine returns the lines that are filled by three stones for each player
	 * 	@param player, the player who's lines we are checking
	 * 	@return filledLines, an ArrayList of the filled lines of that player
	 */
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
	
	/**
	 * setNewStoneInPlace places a new stone on the board for each player
	 * 	@param player, the player that is placing the new stone
	 * 	@param place, the point on the board which we are selecting
	 */
	public void setNewStoneInPlace(Player player, Point place) {
		player.getStones().add(new Stone(player, place));
		player.getStones().get(player.getNumberOfPlacedStones()).setLocation(place);
		place.setOccupiedPlayer(player.getPlayerNumber());
		place.setOccupiedStone(player.getStones().get(player.getNumberOfPlacedStones()));
		player.increaseNumberOfPlacedStones();
	}

	/**
	 * move changes the place of a stone already on the board for each player
	 * 	@param player, the player who is moving their stone
	 * 	@param stone, the stone which has been selected to move
	 * 	@param place, the new place on the board which to place the stone
	 */
	public void move(Player player, Stone stone, Point place) {
		stone.getLocation().setOccupiedPlayer(NO_ONE_NUM);
		stone.getLocation().setOccupiedStone(null);
		stone.setLocation(place);
		place.setOccupiedPlayer(player.getPlayerNumber());
		place.setOccupiedStone(stone);
	}
	
	/**
	 * getStonesOfOpponent returns the stones of the opponent player which can be moved if the other player has formed a line
	 * 	@param opponent, the opponent player who's stones are to be removed
	 * 	@return stonesOfOpponent, an ArrayList of the stones of the opponent player
	 */
	public ArrayList<Stone> getStonesOfOpponent(Player opponent) {
		ArrayList<Stone> stonesOfOpponent = new ArrayList<Stone>();
		
		for (Stone eachStone: opponent.getStones()) {
			if (eachStone.getDead() == false) {
				stonesOfOpponent.add(eachStone);
			}
		}
		return stonesOfOpponent;
	}
	
	/**
	 * moveStone is a genral function that either places a new stone on the board or moves a stone already on the board
	 * 	@param player, the player who's turn it is
	 * 	@param stone, the stone that potentially may be moved
	 * 	@param point, the destination to place the point
	 */
	public void moveStone(Player player, Stone stone, Point point) {
		
		/* 
		 * If the player has less than 9 stones placed on the board, a new stone is placed 
		 */
		
		if (player.getNumberOfPlacedStones() < 9) {
			setNewStoneInPlace(player, point);
		}	
		
		/* 
		 * if the player has 9 stones on the board, then we begin moving stones on the board
		 */
		
		else {
			move(player, stone, point);
		}
	}
	
	/**
	 * removeStone removes a selected stone from the opponent player
	 * 	@param opponentPlayer, the player who's stone is being removed
	 * 	@param stoneToBeRemoved, the stone selected to be removed
	 */
	public void removeStone(Player opponentPlayer, Stone stoneToBeRemoved) {
		int index;
		Point pointItIsRemoved = stoneToBeRemoved.getLocation();
		
		/*
		 *  checking to make sure that the stone selected is actually on the board
		 */
		
		for (index = 0; index < opponentPlayer.getStones().size(); index++) {
			if (opponentPlayer.getStones().get(index) == stoneToBeRemoved) {
				break;
			}
		}
		
		pointItIsRemoved.setOccupiedPlayer(NO_ONE_NUM);
		pointItIsRemoved.setOccupiedStone(null);
		opponentPlayer.getStones().get(index).setDead();
		opponentPlayer.decreaseNumberOfOwnedStones();			
	}
	
	/**
	 * getFreeStones returns the player's stones which they can move on the board after all 9 stones are placed on the board
	 * 	Player can only move free stones that are not part of a line, unless no free stones are left, 
	 * 	in which all stones can be removed
	 * 	@param player, the player who's free stones are being checked
	 * 	@return stonesAvailable, the stones on the board that the player can move
	 */
	public ArrayList<Stone> getFreeStones(Player player) {
		ArrayList<Stone> stonesAvailable = new ArrayList<Stone>();
		boolean shouldAdd = true;	// boolean determining if stone should be considered a free stone
		
		/* 
		 * Checking if each player's stone is part of a player's filled line on the board. 
		 * 	If so, it is not considered a free stone 
		 */
		
		for (Stone eachStone : player.getStones()) {
			for (Line eachLine : getFilledLine(player)) {
				if (eachLine.doesLineContain(eachStone) == true) {
					shouldAdd = false;
				}
			}
			
			/* 
			 * if the stone is not part of any filled lines, it can be considered a free stone 
			 */
			
			if (shouldAdd == true) {
				stonesAvailable.add(eachStone);
			}
		}
		
		/* 
		 * if no free stones are available, then the player can move any stone on the board
		 */
		
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

	/**
	 * checkIfPointIsUnOccupied checks if any player already has a stone on any selected point on the board
	 * 	@param point, the point on the board that is to be checked
	 * 	@return the number of the player on the stone (0 if no player)
	 */
	public int checkIfPointIsUnOccupied(Point point) {
		return point.getOccupiedPlayer();
	}
	
	/**
	 * checkIfAjdacent checks if a selected point is adjacent to the stone that is to be moved on the board
	 * 	@param stoneToSelect, the stone that has been selected to be removed
	 * 	@param pointToSelect, the point that the stone has been selected to be moved to
	 * 	@return boolean indicating if this point is both adjacent to the stone and available to be moved to
	 */
	public boolean checkIfAjdacent(Stone stoneToSelect, Point pointToSelect) {
		boolean answer = false;	
		
		/*
		 * if selected point is already occupied, method automatically returns false
		 */
		
		if (checkIfPointIsUnOccupied(pointToSelect) == 0) {
			for (Square eachSquare : gameBoard.getSquares()) {
				for (Line eachLine : eachSquare.getLines()) {
					if (eachLine.doesLineContain(stoneToSelect) == true) { 	/* checking if each line has the stone */
						if (eachLine.doesLineContainPoint(pointToSelect) == true) {
							
							/* 
							 * if the stone is on EndPoint1 of the line, 
							 * 	we check if the point selected is on the adjacent midPoint
							 */
							
							if (eachLine.getEndPoint1() == stoneToSelect.getLocation()) {
								if (eachLine.getMidPoint() == pointToSelect) {
									answer = true;
								}
							}
							
							/*
							 * if the stone is on a midPoint of the line, 
							 * 	we check if the point selected is on either adjacent end point
							 */
							
							else if (eachLine.getMidPoint() == stoneToSelect.getLocation()) {
								if ((eachLine.getEndPoint1() == pointToSelect) || (eachLine.getEndPoint2() == pointToSelect)) {
									answer = true;
								}	
							}
							
							/*
							 * if the stone selected is on EndPoint2 of the line, 
							 * 	we check if the point selected is on the adjacent midPoint
							 */
							
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

	/**
	 * getAdjacentPoints returns an Arraylist of the available adjacent points that a particular stone can be moved to
	 * 	@param stoneToMove, the stone that has been selected to be moved
	 * 	@return adjacentPoints, an ArrayList of the available points that are adjacent to that selected stone
	 */
	public ArrayList<Point> getAdjacentPoints(Stone stoneToMove) {
		ArrayList<Point> adjacentPoints = new ArrayList<Point>();
		
		for (Square eachSquare: gameBoard.getSquares()) {
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
	
	/**
	 * getPointsAsList generates a generic list of points on the board
	 * 	@return pointsAsList, a generic ArrayList of points
	 */
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
	
}
