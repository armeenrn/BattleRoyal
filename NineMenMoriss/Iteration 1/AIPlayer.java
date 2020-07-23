import java.util.ArrayList;
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
	
	public AIPlayer(String name, int num) {
		super(name, num);
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	
	public Point getRandomPoint(ArrayList<Point> pointsList) {
		int index = 0;
		Random rand = new Random();
		index = rand.nextInt(pointsList.size());
		while (pointsList.get(index).getOccupiedStone() != null) {
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
	
	
	/**
	 * Called by Game class to place stone on the board. Can be used in phase 1 (place) or phase 3 (jump)
	 * 
	 * @param stone stone to place
	 * @param board board
	 */
//	public void placeStone(Stone stone, Board board) {
//		boolean placeSuccessful = false;
//
//		while(!placeSuccessful) {
//			switch ((int)(Math.random() * 24)) {
//				case 0:
//					if (board.getSquares()[0].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getSouthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 1:
//					if (board.getSquares()[0].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getSouthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 2:
//					if (board.getSquares()[0].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getSouthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					}
//				case 3:
//					if (board.getSquares()[0].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getWestLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 4:
//					if (board.getSquares()[0].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getEastLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 5:
//					if (board.getSquares()[0].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getNorthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 6:
//					if (board.getSquares()[0].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getNorthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 7:
//					if (board.getSquares()[0].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[0].getNorthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					} // end of outersquare
//				case 8:
//					if (board.getSquares()[1].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getSouthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 9:
//					if (board.getSquares()[1].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getSouthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 10:
//					if (board.getSquares()[1].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getSouthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					}
//				case 11:
//					if (board.getSquares()[1].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getWestLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 12:
//					if (board.getSquares()[1].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getEastLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 13:
//					if (board.getSquares()[1].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getNorthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 14:
//					if (board.getSquares()[1].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getNorthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 15:
//					if (board.getSquares()[1].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[1].getNorthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					} // end of midsquare
//				case 16:
//					if (board.getSquares()[2].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getSouthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 17:
//					if (board.getSquares()[2].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getSouthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 18:
//					if (board.getSquares()[2].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getSouthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					}
//				case 19:
//					if (board.getSquares()[2].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getWestLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 20:
//					if (board.getSquares()[2].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getEastLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 21:
//					if (board.getSquares()[2].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getNorthLine().getEndPoint1(), 1);
//						placeSuccessful = true;
//					}
//					break;
//				case 22:
//					if (board.getSquares()[2].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getNorthLine().getMidPoint(), 1);
//						placeSuccessful = true;
//					}
//				case 23:
//					if (board.getSquares()[2].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
//						moveStone(stone, board.getSquares()[2].getNorthLine().getEndPoint2(), 1);
//						placeSuccessful = true;
//					} // end of innersquare
//				default:
//					// nothing happens
//					break;
//			}			
//		}	
//	}
//	
//	/**
//	 * Called by Game class to move one Stone to an adjacent empty point. Can be used in phase 2
//	 * 
//	 * @param stone stone to move
//	 * @param board board
//	 */
//	public boolean moveStoneAdjacent(Stone stone, Board board) {
//		boolean moveSuccessful = true;
//		Point currentPoint;
//		Point[] allPoints = new Point[24];
//		
//		allPoints[0] = board.getSquares()[0].getSouthLine().getEndPoint1();
//		allPoints[1] = board.getSquares()[0].getSouthLine().getMidPoint();
//		allPoints[2] = board.getSquares()[0].getSouthLine().getEndPoint2();
//		allPoints[3] = board.getSquares()[0].getWestLine().getMidPoint();
//		allPoints[4] = board.getSquares()[0].getEastLine().getMidPoint();
//		allPoints[5] = board.getSquares()[0].getNorthLine().getEndPoint1();
//		allPoints[6] = board.getSquares()[0].getNorthLine().getMidPoint();
//		allPoints[7] = board.getSquares()[0].getNorthLine().getEndPoint2();
//		allPoints[8] = board.getSquares()[1].getSouthLine().getEndPoint1();
//		allPoints[9] = board.getSquares()[1].getSouthLine().getMidPoint();
//		allPoints[10] = board.getSquares()[1].getSouthLine().getEndPoint2();
//		allPoints[11] = board.getSquares()[1].getWestLine().getMidPoint();
//		allPoints[12] = board.getSquares()[1].getEastLine().getMidPoint();
//		allPoints[13] = board.getSquares()[1].getNorthLine().getEndPoint1();
//		allPoints[14] = board.getSquares()[1].getNorthLine().getMidPoint();
//		allPoints[15] = board.getSquares()[1].getNorthLine().getEndPoint2();
//		allPoints[16] = board.getSquares()[2].getSouthLine().getEndPoint1();
//		allPoints[17] = board.getSquares()[2].getSouthLine().getMidPoint();
//		allPoints[18] = board.getSquares()[2].getSouthLine().getEndPoint2();
//		allPoints[19] = board.getSquares()[2].getWestLine().getMidPoint();
//		allPoints[20] = board.getSquares()[2].getEastLine().getMidPoint();
//		allPoints[21] = board.getSquares()[2].getNorthLine().getEndPoint1();
//		allPoints[22] = board.getSquares()[2].getNorthLine().getMidPoint();
//		allPoints[23] = board.getSquares()[2].getNorthLine().getEndPoint2();
//		
//		currentPoint = stone.getLocation();
//		
//		if (currentPoint == allPoints[0]) {
//			if (allPoints[1].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[1];
//			}
//			else if (allPoints[3].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[3];
//			}
//			else {
//				moveSuccessful = false;
//			}
//		}
//		else if (currentPoint == allPoints[1]) {
//			if (allPoints[0].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[0];
//			}
//			else if (allPoints[2].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[2];
//			}
//			else if (allPoints[9].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[9];
//			}
//			else {
//				moveSuccessful = false;
//			}			
//		}
//		else if (currentPoint == allPoints[2]) {
//			if (allPoints[1].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[1];
//			}
//			else if (allPoints[4].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[4];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[3]) {
//			if (allPoints[0].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[0];
//			}
//			else if (allPoints[5].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[5];
//			}
//			else if (allPoints[11].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[11];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[4]) {
//			if (allPoints[2].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[2];
//			}
//			else if (allPoints[7].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[7];
//			}
//			else if (allPoints[12].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[12];
//			}			
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[5]) {
//			if (allPoints[3].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[3];
//			}
//			else if (allPoints[6].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[6];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[6]) {
//			if (allPoints[5].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[5];
//			}
//			else if (allPoints[7].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[7];
//			}
//			else if (allPoints[14].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[14];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[7]) {
//			if (allPoints[4].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[4];
//			}
//			else if (allPoints[6].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[6];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[8]) {
//			if (allPoints[9].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[9];
//			}
//			else if (allPoints[11].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[11];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[9]) {
//			if (allPoints[1].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[1];
//			}
//			else if (allPoints[8].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[8];
//			}
//			else if (allPoints[10].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[10];
//			}						
//			else if (allPoints[17].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[17];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[10]) {
//			if (allPoints[9].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[9];
//			}
//			else if (allPoints[12].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[12];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[11]) {
//			if (allPoints[3].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[3];
//			}
//			else if (allPoints[8].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[8];
//			}
//			else if (allPoints[13].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[13];
//			}						
//			else if (allPoints[19].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[19];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[12]) {
//			if (allPoints[4].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[4];
//			}
//			else if (allPoints[10].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[10];
//			}
//			else if (allPoints[13].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[13];
//			}						
//			else if (allPoints[20].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[20];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[13]) {
//			if (allPoints[11].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[11];
//			}
//			else if (allPoints[14].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[14];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[14]) {
//			if (allPoints[6].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[6];
//			}
//			else if (allPoints[13].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[13];
//			}
//			else if (allPoints[15].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[15];
//			}						
//			else if (allPoints[22].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[22];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[15]) {
//			if (allPoints[12].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[12];
//			}
//			else if (allPoints[14].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[14];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[16]) {
//			if (allPoints[17].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[17];
//			}
//			else if (allPoints[19].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[19];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[17]) {
//			if (allPoints[9].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[9];
//			}
//			else if (allPoints[16].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[16];
//			}
//			else if (allPoints[18].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[18];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[18]) {
//			if (allPoints[17].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[17];
//			}
//			else if (allPoints[20].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[20];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[19]) {
//			if (allPoints[11].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[11];
//			}
//			else if (allPoints[16].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[16];
//			}
//			else if (allPoints[21].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[21];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[20]) {
//			if (allPoints[12].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[12];
//			}
//			else if (allPoints[18].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[18];
//			}
//			else if (allPoints[23].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[23];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[21]) {
//			if (allPoints[19].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[19];
//			}
//			else if (allPoints[22].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[22];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[22]) {
//			if (allPoints[14].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[14];
//			}
//			else if (allPoints[21].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[21];
//			}
//			else if (allPoints[23].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[23];
//			}						
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else if (currentPoint == allPoints[23]) {
//			if (allPoints[20].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[20];
//			}
//			else if (allPoints[22].getOccupyingPlayerNumber() == 0) {
//				moveSuccessful = true;
//				pointToMoveTo = allPoints[22];
//			}
//			else {
//				moveSuccessful = false;
//			}						
//		}
//		else {
//			// Invalid point
//			System.out.println("Invalid move.");
//			moveSuccessful = false;
//		}
//		
//		
//		if (moveSuccessful) {
//			moveStone(stone, pointToMoveTo, 2);
//		}
//		
//		return moveSuccessful;
//	}
//	
//	/**
//	 * Check and see which move the computer should make.
//	 * 
//	 * @param board Board
//	 * @return Point the computer's stone should move to
//	 */
//	public Point checkPriorityMove(Board board) {
//		return pointToMoveTo;
//	}
//		
//	/**
//	 * Check which movement options are available and should be carried out by the computer
//	 * 
//	 * @param stones List of stones the player owns
//	 * @param board Board
//	 * @return Indication of which move the computer should make
//	 */
//	public int checkMovement(ArrayList<Stone> stones, Board board) {
//		return 0;
//	}
//
//	/**
//	 * Check if the computer can fill a line to form a mill
//	 * 
//	 * @param stones List of stones the player owns
//	 * @param board Board
//	 * @return Can/cannot fill a line
//	 */
//	public boolean checkFill(ArrayList<Stone> stones, Board board) {
//		return true;
//	}
//
//	/**
//	 * Check if the computer should stop the opponent from forming a mill
//	 * 
//	 * @param stones List of stones the player owns
//	 * @param board Board
//	 * @return Can/cannot stop the opponent
//	 */
//	public boolean checkStopFill(ArrayList<Stone> stones, Board board) {
//		return true;
//	}
//
//	/**
//	 * Check if the computer can take 2 moves to form a mill
//	 * 
//	 * @param stones List of stones the player owns
//	 * @param board Board
//	 * @return Two turns away from filling a line and forming a mill
//	 */
//	public boolean checkGoForFill(ArrayList<Stone> stones, Board board) {
//		return true;
//	}
//	
//	/**
//	 * Decide which stone the computer should remove after forming a mill
//	 */
//	public void checkRemove() {
//		
//	}
//	
//	
//
	
}