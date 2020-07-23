import java.util.ArrayList;

public class AIPlayer extends Player {
	private int movePriority;
	private final static int NONE = 0;
	private final static int FILL_LINE_THISTURN = 1;
	private final static int STOP_OPPONENT_NEXTTURN = 2;
	private final static int TWO_STEPS_TO_FILL = 3;
	private Point pointToMoveTo;
	
	public AIPlayer(String name, int num) {
		super(name, num);
	}
	
	public void placeStone(Stone stone, Board board) {
		boolean placeSuccessful = false;

		while(!placeSuccessful) {
			switch ((int)(Math.random() * 24)) {
				case 0:
					if (board.getSquares()[0].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getSouthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 1:
					if (board.getSquares()[0].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getSouthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 2:
					if (board.getSquares()[0].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getSouthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					}
				case 3:
					if (board.getSquares()[0].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getWestLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 4:
					if (board.getSquares()[0].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getEastLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 5:
					if (board.getSquares()[0].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getNorthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 6:
					if (board.getSquares()[0].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getNorthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 7:
					if (board.getSquares()[0].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[0].getNorthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					} // end of outersquare
				case 8:
					if (board.getSquares()[1].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getSouthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 9:
					if (board.getSquares()[1].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getSouthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 10:
					if (board.getSquares()[1].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getSouthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					}
				case 11:
					if (board.getSquares()[1].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getWestLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 12:
					if (board.getSquares()[1].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getEastLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 13:
					if (board.getSquares()[1].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getNorthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 14:
					if (board.getSquares()[1].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getNorthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 15:
					if (board.getSquares()[1].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[1].getNorthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					} // end of midsquare
				case 16:
					if (board.getSquares()[2].getSouthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getSouthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 17:
					if (board.getSquares()[2].getSouthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getSouthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 18:
					if (board.getSquares()[2].getSouthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getSouthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					}
				case 19:
					if (board.getSquares()[2].getWestLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getWestLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 20:
					if (board.getSquares()[2].getEastLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getEastLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 21:
					if (board.getSquares()[2].getNorthLine().getEndPoint1().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getNorthLine().getEndPoint1(), 1);
						placeSuccessful = true;
					}
					break;
				case 22:
					if (board.getSquares()[2].getNorthLine().getMidPoint().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getNorthLine().getMidPoint(), 1);
						placeSuccessful = true;
					}
				case 23:
					if (board.getSquares()[2].getNorthLine().getEndPoint2().getOccupyingPlayerNumber() == 0) {
						moveStone(stone, board.getSquares()[2].getNorthLine().getEndPoint2(), 1);
						placeSuccessful = true;
					} // end of innersquare
				default:
					// nothing happens
					break;
			}			
		}	
	}
	
	public void moveAdjacentStone(ArrayList<Stone> stone) {
		
	}
	
	public void checkPriorityMove(Board board) {
	}
		
	public int checkMovement(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return 0;
	}
	
	public boolean checkFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public boolean checkStopFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public boolean checkGoForFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
	}
	
	public void checkRemove() {
		
	}
}
