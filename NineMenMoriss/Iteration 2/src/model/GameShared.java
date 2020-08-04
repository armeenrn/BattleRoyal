package model;

import java.util.ArrayList;
import java.util.Random;

public class GameShared {
	private double BOARDWIDTH = 30;
	private double CENTER_X = 0;
	private double CENTER_Y = 0;
	private HumanPlayer player1;
	private AIPlayer player2;
	private int goFirst;
	private Player[] playerList;
	private Board gameBoard;
	
	private final int PLACE = 1;
	private final int MOVE_ADJACENT = 2;
	private final int JUMP = 3;
	
	private final int NO_ONE_NUM = 0;
	private final int HUMAN_PLAYER_NUM = 1;
	private final int COMP_PLAYER_NUM = 2;
	
	public void setGameConfig() {		
		gameBoard = new Board(BOARDWIDTH, CENTER_X, CENTER_Y);		
		Random rand = new Random();
		
		goFirst = rand.nextInt(2) + 1; 
		
		if (goFirst == HUMAN_PLAYER_NUM) {
			player1 = new HumanPlayer("YOU", 1, true);
			player2 = new AIPlayer("COMPUTER", 2, false);	
		}
		
		if (goFirst == COMP_PLAYER_NUM) {
			player1 = new HumanPlayer("YOU", 1, false);
			player2 = new AIPlayer("COMPUTER", 2, true);	
			
		}

		playerList = new Player[2];
		playerList[0] = player1;
		playerList[1] = player2;
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}

	public HumanPlayer selectFirstPlayer() {
		return player1;
	}
	
	public AIPlayer selectSecondPlayer() {
		return player2;
	}
	
	public int getFirstPlayer() {
		return goFirst;
	}
	
	public int getPlayerNumOne() {
		return HUMAN_PLAYER_NUM;
	}

	public int getPlayerNumTwo() {
		return COMP_PLAYER_NUM;
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
	
	public void setNewStoneInPlace(Player player, Point place) {
		player.getStones().add(new Stone(player, place));
		move(player, player.getStones().get(player.getNumberOfPlacedStones()), place);
		place.setOccupiedPlayer(player.getPlayerNumber());
		player.increaseNumberOfPlacedStones();
	}

	public void move(Player player, Stone stone, Point place) {
		if (player.getNumberOfPlacedStones() == 9) {
			if (stone.getLocation().getOccupiedPlayer() != NO_ONE_NUM) {
				stone.getLocation().setOccupiedPlayer(NO_ONE_NUM);
			}			
		}

		stone.setLocation(place);
		place.setOccupiedPlayer(player.getPlayerNumber());
	}
	
	public ArrayList<Stone> getStonesOfOpponent(Player opponent) {
		ArrayList<Stone> stonesOfOpponent = new ArrayList<Stone>();
		
		for (Stone eachStone: opponent.getStones()) {
			if (eachStone.getDead() == false) {
				stonesOfOpponent.add(eachStone);
			}
		}
		return stonesOfOpponent;
	}
	
	public void moveStone(Player player, Stone stone, Point point, int moveType) {
		if (moveType == PLACE) {
			setNewStoneInPlace(player, point);
		}
		
		else {
			move(player, stone, point);
		}
	}
	
	public void removeStone(Player opponentPlayer, Stone stoneToBeRemoved) {
		int index;
		Point pointItIsRemoved = stoneToBeRemoved.getLocation();
		
		for (index = 0; index < opponentPlayer.getStones().size(); index++) {
			if (opponentPlayer.getStones().get(index).equals(stoneToBeRemoved)) {
				opponentPlayer.getStones().get(index).setDead();
				pointItIsRemoved.setOccupiedPlayer(NO_ONE_NUM);					
				break;
			}
		}

		opponentPlayer.decreaseNumberOfOwnedStones();
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

	public int checkIfPointIsUnOccupied(Point point) {
		return point.getOccupiedPlayer();
	}
	
	public boolean checkIfAjdacent(Stone stoneToSelect, Point pointToSelect) {
		boolean answer = false;
		
		if (checkIfPointIsUnOccupied(pointToSelect) == 0) {
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
