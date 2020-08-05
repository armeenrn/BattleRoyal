package model;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
	String name;
	
	private Stone bestMoveStone;
	private Point pointToMoveTo;
	
	public AIPlayer(String name, int num, boolean goFirst, Board gameBoard) {
		super(name, num, goFirst, gameBoard);
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public Stone getBestMoveStone() {
		return bestMoveStone;
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
		Random rand = new Random();
		int index;
		do {
			index = rand.nextInt(stonesList.size());
		} while (stonesList.get(index).getDead());
		
		return stonesList.get(index);
	}
	
	
	public Stone selectRandomStoneToRemove(ArrayList<Stone> stonesOfOpponent) {
		Random rand = new Random();
		int index;
		do {
			index = rand.nextInt(stonesOfOpponent.size());
		} while (stonesOfOpponent.get(index).getDead());
		
		return stonesOfOpponent.get(index);
	}
	
	public Stone lookforBestRemove(Board board, Player opponentPlayer) {
		Stone bestStone;
		
		// check if there is a stone the opponent can move to make a mill		
		bestStone = findStopMillByRemove(board, opponentPlayer);

		if (bestStone == null) {
			// if not, random is fine
			bestStone = selectRandomStoneToRemove(opponentPlayer.getStones());
		}
		
		return bestStone;			
	}
	
	public Stone findStopMillByRemove(Board board, Player opponentPlayer) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 1) {
						numHumanPoint++;
					}
				}
				
				if (numEmptyPoint == 1 && numHumanPoint == 2) {
					if ((opponentPlayer.getNumberOfPlacedStones() < 9) || opponentPlayer.getNumberOfTotalStones() == 3) {
						// the opponent can place or teleport; remove any two of the stones
						for (Point point : line.getPoints()) {
							if (point.getOccupiedPlayer() == 1) {
								return point.getOccupiedStone();
							}
						}
					}
					else {
						// the opponent can only move adjacent; remove only if the opponent can form a mill in one turn
						ArrayList<Point> adjacentPoints = getAdjacentPoints(pointToTest);
						
						for (Point adjacentPoint : adjacentPoints) {						
							if (adjacentPoint.getOccupiedPlayer() == 1) {
								if(line.doesLineContain(adjacentPoint.getOccupiedStone())) {								
									return adjacentPoint.getOccupiedStone();
								}
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	public Point lookForBestMove(Board board) {
		boolean foundMove;
		
		if ((getNumberOfPlacedStones() < 9) || (getNumberOfTotalStones() == 3)) {
			// teleport
			// check mill first
			foundMove = checkMillByPlace(board);
			
			if (!foundMove) {
				// couldn't find mill; check to stop opponent's mill
				foundMove = checkStopMillByPlace(board);
				if (!foundMove) {
					// cannot stop opponent from forming a mill, or opponent cannot form a mill next turn
					// check if two turns can be taken to form a mill
					foundMove = checkTwoStepsFromMillByPlace(board);
					if (!foundMove) {
						// no good answer for computer;
						pointToMoveTo = null;
					}
				}
			}
		}
		else {
			// move adjacent
			// check mill first
			foundMove = checkMillByMove(board);

			if (!foundMove) {
				// couldn't find mill; check to stop opponent's mill
				foundMove = checkStopMillByMove(board);
				if (!foundMove) {
					// cannot stop opponent from forming a mill, or opponent cannot form a mill next turn
					// check if two turns can be taken to form a mill
					foundMove = checkTwoStepsFromMillByMove(board);
					if (!foundMove) {
						// no good answer for computer;
						pointToMoveTo = null;
					}
				}
			}
		}
		
		return pointToMoveTo;
	}

	public boolean checkMillByPlace(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 2) {
						numCompPoint++;
					}
				}
				
				if (numEmptyPoint == 1 && numCompPoint == 2) {
					// if this is placing stage there is no need to check for moving stones on current line
					if (getNumberOfPlacedStones() < 9) {
						pointToMoveTo = pointToTest;
						return true;
					}
					else {
						// since this is jumping stage it needs to check to prevent moving a stone on the line
						boolean wasStoneOnLine;
						
						for (Stone currentStone : getStones()) {
							if (!currentStone.getDead()) {
								wasStoneOnLine = line.doesLineContain(currentStone);
								
								// if stone not on line was found that stone will move to the point
								if (!wasStoneOnLine) {
									bestMoveStone = currentStone;
									pointToMoveTo = pointToTest;
									return true;
								}								
							}
						}					
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean checkStopMillByPlace(Board board) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 1) {
						numHumanPoint++;
					}
				}
				
				if (numEmptyPoint == 1 && numHumanPoint == 2) {
					// if this is placing stage there is no need to check for moving stones on current line
					if (getNumberOfPlacedStones() < 9) {
						pointToMoveTo = pointToTest;
						return true;
					}
					else {
						// since this is jumping stage it needs to check to prevent moving a stone on the line
						for (Stone currentStone : getStones()) {
							if (!currentStone.getDead()) {
								bestMoveStone = currentStone;
								pointToMoveTo = pointToTest;
								return true;
							}
						}					
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean checkTwoStepsFromMillByPlace(Board board) {
		return false;
	}
	
	public boolean checkMillByMove(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		ArrayList<Point> adjacentPoints;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 2) {
						numCompPoint++;
					}
				}
				
				// only perform further check if the conditions are right
				if (numEmptyPoint == 1 && numCompPoint == 2) {
					adjacentPoints = getAdjacentPoints(pointToTest);
					
					for (Point adjacentPoint : adjacentPoints) {						
						if (adjacentPoint.getOccupiedPlayer() == 2) {
							if(!(line.doesLineContain(adjacentPoint.getOccupiedStone()))) {								
								bestMoveStone = adjacentPoint.getOccupiedStone();
								pointToMoveTo = pointToTest;
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	public boolean checkStopMillByMove(Board board) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		ArrayList<Point> adjacentPoints;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 1) {
						numHumanPoint++;
					}
				}
				
				// only perform further check if the conditions are right
				if (numEmptyPoint == 1 && numHumanPoint == 2) {
					adjacentPoints = getAdjacentPoints(pointToTest);
					
					for (Point adjacentPoint : adjacentPoints) {						
						if (adjacentPoint.getOccupiedPlayer() == 2) {
							bestMoveStone = adjacentPoint.getOccupiedStone();
							pointToMoveTo = pointToTest;
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean checkTwoStepsFromMillByMove(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 2) {
						numCompPoint++;
					}
				}
				
				// only perform further check if the conditions are right
				if (numEmptyPoint == 1 && numCompPoint == 2) {
					ArrayList<Point> adjacentPoints = getAdjacentPoints(pointToTest);
					ArrayList<Point> adjacentToAdjacentPoint;
					
					
					if (pointToTest != line.getMidPoint()) {
						// midpoint test
						pointToMoveTo = pointToTest;
						return true;
					}
					else {
						// endpoint test
						/*
						if () {
							pointToMoveTo = pointToTest;
							return true;							
						}*/
					}
					
					for (Point adjacentPoint : adjacentPoints) {						
						if (adjacentPoint.getOccupiedPlayer() == 0) {
							adjacentToAdjacentPoint = getAdjacentPoints(adjacentPoint);
								bestMoveStone = adjacentPoint.getOccupiedStone();
								pointToMoveTo = pointToTest;
								return true;
						}
						else if (adjacentPoint.getOccupiedPlayer() == 2) {
							adjacentToAdjacentPoint = getAdjacentPoints(adjacentPoint);							
						}
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * Check two spaces from adjacent point stone can move to
	 * 
	 * @param stone Current stone to move
	 * @param board Game board
	 * @return List of all two spaces from adjacent point
	 */
	public ArrayList<Point[]> checkAllTwoSpaces(Stone stone, Board board) {
		ArrayList<Point[]> pointLists = new ArrayList<Point[]>();
		Point[] twoSpacePoints = new Point[2];
		
		if (stone.getLocation().equals(board.getSquares()[0].getSouthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getSouthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getSouthLine().getEndPoint2())) {
			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getWestLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getEastLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getNorthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			

			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getNorthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getWestLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getEastLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[0].getNorthLine().getEndPoint2())) {
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getSouthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[0].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getSouthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getSouthLine().getEndPoint2())) {
			twoSpacePoints[0] = board.getSquares()[0].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[0].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getWestLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getWestLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[2].getWestLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getEastLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getEastLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getEastLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getNorthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[0].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			

			twoSpacePoints[0] = board.getSquares()[0].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getNorthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[0].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[0].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getWestLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[1].getEastLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[1].getNorthLine().getEndPoint2())) {
			twoSpacePoints[0] = board.getSquares()[0].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[0].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getSouthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getSouthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[2].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getSouthLine().getEndPoint2())) {
			twoSpacePoints[0] = board.getSquares()[1].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getWestLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getWestLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getSouthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[2].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getEastLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getEastLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getSouthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getSouthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[2].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[2].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getNorthLine().getEndPoint1())) {
			twoSpacePoints[0] = board.getSquares()[1].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getWestLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);			

			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		else if (stone.getLocation().equals(board.getSquares()[2].getNorthLine().getMidPoint())) {
			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getEndPoint1();
			twoSpacePoints[1] = board.getSquares()[1].getNorthLine().getEndPoint2();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[2].getWestLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getWestLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);
			
			twoSpacePoints[0] = board.getSquares()[2].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[2].getEastLine().getEndPoint1();
			
			pointLists.add(twoSpacePoints);
		}
		else {
			twoSpacePoints[0] = board.getSquares()[1].getEastLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getEastLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);

			twoSpacePoints[0] = board.getSquares()[1].getNorthLine().getMidPoint();
			twoSpacePoints[1] = board.getSquares()[0].getNorthLine().getMidPoint();
			
			pointLists.add(twoSpacePoints);
		}
		
		return pointLists;
	}		
}