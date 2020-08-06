/**
 * Contains logic for AI to play the game
 * 
 * @author Daniel Kim, Armeen Rashidian
 * @version 2.0
 */

package model;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {	
	private Stone bestMoveStone;
	private Point pointToMoveTo;
	
	public AIPlayer(String name, int num, boolean goFirst, Board gameBoard) {
		super(name, num, goFirst, gameBoard);
	}
	
	/**
	 * Getter method for the stone that will perform AI's best move possible
	 * 
	 * @return Stone to move
	 */
	public Stone getBestMoveStone() {
		return bestMoveStone;
	}
	
	/**
	 * Selects a random empty point to move to
	 * 
	 * @param pointsList Set of points
	 * @return Point to move to
	 */
	public Point getRandomPoint(ArrayList<Point> pointsList) {
		int index = 0;
		Random rand = new Random();
		index = rand.nextInt(pointsList.size());
		while (pointsList.get(index).getOccupiedPlayer() != 0) {
			index = rand.nextInt(pointsList.size());
		}
		
		return pointsList.get(index);
	}
	
	/**
	 * Selects a random alive stone to move to
	 * 
	 * @param stonesList Set of stones player owns
	 * @return Stone to move
	 */
	public Stone selectRandomStone(ArrayList<Stone> stonesList) {
		Random rand = new Random();
		int index;
		do {
			index = rand.nextInt(stonesList.size());
		} while (stonesList.get(index).getDead());
		
		return stonesList.get(index);
	}
	
	/**
	 * Selects a random alive opponent stone to remove
	 * 
	 * @param stonesOfOpponent Set of stones the opponent owns
	 * @return Stone to remove
	 */
	public Stone selectRandomStoneToRemove(ArrayList<Stone> stonesOfOpponent) {
		Random rand = new Random();
		int index;
		do {
			index = rand.nextInt(stonesOfOpponent.size());
		} while (stonesOfOpponent.get(index).getDead());
		
		return stonesOfOpponent.get(index);
	}
	
	/**
	 * The AI goes through a set of logic to select which opponent stone to remove
	 * 
	 * @param board Gameboard
	 * @param opponentPlayer
	 * @return Stone to remove
	 */
	public Stone lookforBestRemove(Board board, Player opponentPlayer) {
		Stone bestStoneToRemove;
		
		// check if there is a stone the opponent can move to make a mill next turn
		bestStoneToRemove = findStopMillByRemove(board, opponentPlayer);

		if (bestStoneToRemove == null) {
			// if not, remove a random stone as there is no priority
			bestStoneToRemove = selectRandomStoneToRemove(opponentPlayer.getStones());
		}
		
		return bestStoneToRemove;			
	}
	
	/**
	 * Logic that checks if the AI can prevent the opponent from forming a mill by removing a stone.
	 * 
	 * @param board Gameboard
	 * @param opponentPlayer
	 * @return Stone to remove
	 */
	public Stone findStopMillByRemove(Board board, Player opponentPlayer) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;

				// checks if the line has exactly 1 empty point and 2 points occupied by Human Player
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
						// the opponent can place or jump; remove any two of the stones to prevent a mill
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
		
		// couldn't find a logical stone to remove
		return null;
	}

	/**
	 * The AI goes through a set of logic to select which move to make
	 * 
	 * @param board Gameboard
	 * @return Point to move to
	 */
	public Point lookForBestMove(Board board) {
		boolean foundMove;
		
		// check if the AI can move a stone to any empty point
		if ((getNumberOfPlacedStones() < 9) || (getNumberOfTotalStones() == 3)) {
			// check if the AI can form a mill by moving
			foundMove = checkMillByPlace(board);
			
			if (!foundMove) {
				// couldn't find mill; check if the AI can prevent opponent mill by moving
				foundMove = checkStopMillByPlace(board);

				if (!foundMove) {
					// cannot stop opponent from forming a mill, or opponent cannot form a mill next turn
					// check if two turns can be taken to form a mill
					foundMove = checkTwoStepsFromMillByPlace(board);

					// no good answer for computer;
					if (!foundMove) {
						pointToMoveTo = null;
					}
				}
			}
		}
		// the AI can only move a stone to its adjacent point
		else {
			// Priority 1: check if the AI can form a mill by moving
			foundMove = checkMillByMove(board);

			if (!foundMove) {
				// Priority 2: check if the AI can prevent opponent mill by moving
				foundMove = checkStopMillByMove(board);

				if (!foundMove) {
					// cannot stop opponent from forming a mill, or opponent cannot form a mill next turn
					// Priority 3: check if two turns can be taken to form a mill
					foundMove = checkTwoStepsFromMillByMove(board);

					// no good answer for computer;
					if (!foundMove) {
						pointToMoveTo = null;
					}
				}
			}
		}
		
		return pointToMoveTo;
	}

	/**
	 * Logic that checks if the AI can form a mill by moving a stone
	 * The AI does not have restrictions on movement
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkMillByPlace(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;

				// check if the line has exactly 1 empty point and 2 points occupied by AI Player
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
					if (getNumberOfPlacedStones() < 9) {
						// placing stage; there is no need to check for moving stones on current line
						pointToMoveTo = pointToTest;
						return true;
					}
					else {
						// jumping stage; make sure the stone is alive
						boolean wasStoneOnLine;
						
						for (Stone currentStone : getStones()) {
							if (!currentStone.getDead()) {
								wasStoneOnLine = line.doesLineContain(currentStone);
								
								// if the live stone tested was not on the current line it will move to the point
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
		
		// all tests were done and the move was not found
		return false;
	}
	
	/**
	 * Logic that checks if the AI can prevent an opponent mill by moving a stone
	 * The AI does not have restrictions on movement
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkStopMillByPlace(Board board) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;
				
				// check if the line has exactly 1 empty point and 2 points occupied by Human Player
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
						// jumping stage; make sure the stone is alive
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
		
		// all tests were done and the move was not found	
		return false;
	}
	
	/**
	 * Logic that checks if the AI can move a stone to prepare for a mill the turn after
	 * The AI does not have restrictions on movement
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkTwoStepsFromMillByPlace(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;
				
				// check if the line has exactly 1 empty point and 2 points occupied by AI Player
				for (Point point : line.getPoints()) {
					if (point.getOccupiedPlayer() == 0) {
						pointToTest = point;
						numEmptyPoint++;
					}
					else if (point.getOccupiedPlayer() == 2) {
						numCompPoint++;
					}
				}
				
				if (numEmptyPoint == 2 && numCompPoint == 1) {
					if (getNumberOfPlacedStones() < 9) {
						// placing stage; place a stone on the same line, prefer midpoint
						if (line.getMidPoint().getOccupiedPlayer() == 0) {
							pointToMoveTo = line.getMidPoint();
							return true;
						}
						else {
							pointToMoveTo = pointToTest;
							return true;
						}
					}
					else {
						// jumping stage; make sure the stone is alive
						for (Stone currentStone : getStones()) {
							if (!currentStone.getDead()) {
								bestMoveStone = currentStone;

								if (line.getMidPoint().getOccupiedPlayer() == 0) {
									pointToMoveTo = line.getMidPoint();
									return true;
								}
								else {
									pointToMoveTo = pointToTest;
									return true;
								}
							}
						}					
					}
				}
			}
		}
		
		// all tests were done and the move was not found
		return false;
	}
	
	/**
	 * Logic that checks if the AI can form a mill by moving a stone
	 * The AI can only move a stone to its adjacent point
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkMillByMove(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		Point pointToTest = null;
		ArrayList<Point> adjacentPoints;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numCompPoint = 0;

				// check if the line has exactly 1 empty point and 2 points occupied by AI Player
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
					adjacentPoints = getAdjacentPoints(pointToTest);
					
					for (Point adjacentPoint : adjacentPoints) {
						// check if an adjacent point has a stone occupied by AI Player
						if (adjacentPoint.getOccupiedPlayer() == 2) {
							// check if the stone is not on the current line; if it is not, it moves to the empty point
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
		
		// all tests were done and the move was not found
		return false;
	}

	/**
	 * Logic that checks if the AI can prevent an opponent mill by moving a stone
	 * The AI can only move a stone to its adjacent point
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkStopMillByMove(Board board) {
		int numEmptyPoint;
		int numHumanPoint;
		Point pointToTest = null;
		ArrayList<Point> adjacentPoints;
		
		for (Square square : board.getSquares()) {
			for (Line line : square.getLines()) {
				numEmptyPoint = 0;
				numHumanPoint = 0;
				
				// check if the line has exactly 1 empty point and 2 points occupied by Human Player
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
					adjacentPoints = getAdjacentPoints(pointToTest);
					
					for (Point adjacentPoint : adjacentPoints) {						
						// check if an adjacent point has a stone occupied by AI Player
						if (adjacentPoint.getOccupiedPlayer() == 2) {
							// the stone can move to the empty point to stop mill
							bestMoveStone = adjacentPoint.getOccupiedStone();
							pointToMoveTo = pointToTest;
							return true;
						}
					}
				}
			}
		}
		
		// all tests were done and the move was not found
		return false;
	}

	/**
	 * Logic that checks if the AI can move a stone to prepare for a mill the turn after
	 * The AI can only move a stone to its adjacent point
	 * 
	 * @param board Gameboard
	 * @return Indicates whether a move was found
	 */
	public boolean checkTwoStepsFromMillByMove(Board board) {
		int numEmptyPoint;
		int numCompPoint;
		boolean moveWasFound = false;
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
					// Case 1; there are two computer stones and one empty point on the line
					moveWasFound = TwoStepsFromMillCaseOne(pointToTest, line);
				}
				else if (numEmptyPoint == 2 && numCompPoint == 1) {
					// case 2; there are one computer stone and two empty points on the line
					moveWasFound = TwoStepsFromMillCaseTwo(pointToTest, line);

				}
				else if (numCompPoint == 3) {
					// case 3; the computer has a mill
					moveWasFound = TwoStepsFromMillCaseThree(pointToTest, line);
				}
			}
		}
		
		// tests are done; return if move was found
		return moveWasFound;
	}
	
	/**
	 * Case 1 of "two steps from mill" when AI can only move adjacent
	 * When there are two computer stones and one empty point on the line, check adjacent points of the empty point

	 * Case 1-1: The adjacent point is empty, and the adjacent of that adjacent point is occupied by AI
	 * The AI Player can move that stone to the adjacent point to form a mill the turn after by moving the stone to the empty point.
	 * 
	 * Case 1-2: The adjacent point is occupied by AI, and the adjacent of the adjacent point is occupied by AI
	 * Case 1-2-1: The empty point was the midpoint of the line
	 * The stone in the adjacent point was in an endpoint of the line; move that to midpoint
	 * AI can form a mill the turn after by moving the stone in the adjacent to adjacent point to the now empty endpoint
	 * 
	 * Case 1-2-2: The empty point was an endpoint of the line
	 * Check if the stone in the adjacent to adjacent point was on the line.
	 * If not, the stone in the adjacent point can move to the empty endpoint.
	 * AI can form a mill the turn after by moving the stone in the adjacent to adjacent point to the now empty midpoint
	 * 
	 * @param pointToTest Point to check if a stone can make a logical choice to move here
	 * @param line Line that pointToTest belongs to and will be called to test
	 * @return Indicates whether a move was found
	 */
	public boolean TwoStepsFromMillCaseOne(Point pointToTest, Line line) {
		ArrayList<Point> adjacentPoints = getAdjacentPoints(pointToTest);
		ArrayList<Point> adjacentsToAdjacentPoint;
		
		for (Point adjacentPoint : adjacentPoints) {
			adjacentsToAdjacentPoint = getAdjacentPoints(adjacentPoint);
			
			if (adjacentPoint.getOccupiedPlayer() == 0) {
				// Case 1-1
				for (Point adjacentAdjacent : adjacentsToAdjacentPoint) {
					if (adjacentAdjacent.getOccupiedPlayer() == 2) {
						bestMoveStone = adjacentAdjacent.getOccupiedStone();
						pointToMoveTo = adjacentPoint;
						return true;
					}
				}	
			}
			
			else if (adjacentPoint.getOccupiedPlayer() == 2) {
				// Case 1-2
				for (Point adjacentAdjacent : adjacentsToAdjacentPoint) {
					if (adjacentAdjacent.getOccupiedPlayer() == 2) {
						if (pointToTest == line.getMidPoint()) {
							// Case 1-2-1
							bestMoveStone = adjacentPoint.getOccupiedStone();
							pointToMoveTo = pointToTest;
							return true;
						}
						else {
							// Case 1-2-2
							if (!(line.doesLineContain(adjacentAdjacent.getOccupiedStone()))) {
								bestMoveStone = adjacentPoint.getOccupiedStone();
								pointToMoveTo = pointToTest;
								return true;
							}
						}							
					}
				}
			}
		}

		// all tests were done and the move was not found
		return false;
	}
	
	/**
	 * Case 2 of "two steps from mill" when AI can only move adjacent
	 * When there are one computer stone and two empty points on the line,
	 * check adjacent points of the point occupied by AI
	 * 
	 * Test 1: An adjacent to adjacent point has a stone occupied by AI
	 * Test 2-1: There is an adjacent to adjacent point that is empty
	 * Test 2-2: The adjacent point to that empty adjacent to adjacent point has a stone occupied by AI
	 * When all tests are passed, any of the two AI stones found can move to an empty point on the line
	 * AI can form a mill the turn after by moving the other stone to the last empty point on the line
	 * 
	 * @param pointToTest Point to check if a stone can make a logical choice to move here
	 * @param line Line that pointToTest belongs to and will be called to test
	 * @return Indicates whether a move was found
	 */
	public boolean TwoStepsFromMillCaseTwo(Point pointToTest, Line line) {
		ArrayList<Point> adjacentPoints = getAdjacentPoints(pointToTest);
		ArrayList<Point> adjacentsToAdjacentPoint;
		boolean adjacentToAdjacentHasStone;
		ArrayList<Point> emptyDoubleAdjacent = new ArrayList<Point>();
		ArrayList<Point> tripleAdjacent;

		// start from the point with stone
		for (Point point : line.getPoints()) {
			if (point.getOccupiedPlayer() == 2) {
				pointToTest = point;
			}
		}
		
		for (Point adjacentPoint : adjacentPoints) {
			adjacentsToAdjacentPoint = getAdjacentPoints(adjacentPoint);
			
			// Test 1
			if (adjacentPoint.getOccupiedPlayer() == 0) {
				adjacentToAdjacentHasStone = false;

				for (Point adjacentAdjacent : adjacentsToAdjacentPoint) {
					if (adjacentAdjacent.getOccupiedPlayer() == 2) {
						// Test 1 passed here
						adjacentToAdjacentHasStone = true;
					}
					else if (adjacentAdjacent.getOccupiedPlayer() == 0) {
						// Add all points that passes Test 2-1
						emptyDoubleAdjacent.add(adjacentAdjacent);
					}
					
					if (adjacentToAdjacentHasStone) {
						for (Point doubleAdjacentPoint : emptyDoubleAdjacent) {
							// Test 2-2
							tripleAdjacent = getAdjacentPoints(doubleAdjacentPoint);
							for (Point tripleAdjacentPoint : tripleAdjacent) {
								if (tripleAdjacentPoint.getOccupiedPlayer() == 2) {
									// all tests are passed; a move was found
									Random rand = new Random();
									int random = rand.nextInt(2);
									
									if (random == 0) {
										bestMoveStone = adjacentAdjacent.getOccupiedStone();
										pointToMoveTo = adjacentPoint;
									}
									else {
										bestMoveStone = tripleAdjacentPoint.getOccupiedStone();
										pointToMoveTo = doubleAdjacentPoint;
									}
									
									return true;
								}
							}
						}
					}
				}	
			}
		}

		// all tests were done and the move was not found
		return false;
	}
	
	/**
	 * Case 3 of "two steps from mill" when AI can only move adjacent
	 * AI can move any stone on the mill to an adjacent empty point
	 * It can form a mill the turn after by moving the stone back to the line
	 * 
	 * @param pointToTest Point to check if a stone can make a logical choice to move here
	 * @param line Line that pointToTest belongs to and will be called to test
	 * @return Indicates whether a move was found
	 */
	public boolean TwoStepsFromMillCaseThree(Point pointToTest, Line line) {
		ArrayList<Point> adjacentPoints;
		
		for (Point point : line.getPoints()) {
			bestMoveStone = point.getOccupiedStone();

			adjacentPoints = getAdjacentPoints(bestMoveStone.getLocation());
			
			for (Point adjacentPoint : adjacentPoints) {
				if (adjacentPoint.getOccupiedPlayer() == 0) {
					pointToMoveTo = adjacentPoint;
					return true;
				}
			}
		}

		// all tests were done and the move was not found
		return false;
	}
}