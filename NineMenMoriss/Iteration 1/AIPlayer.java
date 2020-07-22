import java.util.ArrayList;

public class AIPlayer extends Player {
	private int movePriority;
	private final static int NONE = 0;
	private final static int FILL_LINE_THISTURN = 1;
	private final static int STOP_OPPONENT_NEXTTURN = 2;
	private final static int TWO_STEPS_TO_FILL = 3;
	private Point pointToMoveTo;
	
	public AIPlayer() {
		super();
	}
	
	public void checkPriorityMove(Board board) {
		ArrayList<Stone> remainingStones = getPlayerStones();
		Point stoneLocation;
		int index = 0;
		int pointLocInLine = 0;
		int whichSquare = 0; // 0 Out, 1 Mid, 2 Inner
		int directionLine = 0; // 0 N, 1 W, 2 E, 3 S
		int x;
		int y;
		
		if (getStonesRemaining() > 3 && getStonesPlaced() == 9) {
			// move adjacent if all stones were placed and remaining stones are more than 3
			for (Stone currentStone : remainingStones) {
				stoneLocation = currentStone.getLocation();
				x = stoneLocation.getX();				

				if (x == 0) {
					// first line to test will always be west line of outerSquare
					whichSquare = 0;
					directionLine = 1;
					switch (stoneLocation.getY()) {
						case 0:
							// endpoint1 lineW
							pointLocInLine = 0;
							break;
						case 3:
							// midpoint lineW
							pointLocInLine = 1;
							break;
						case 6:
							// endpoint2 lineW
							pointLocInLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else if (x == 1) {
					// first line to test will always be west line of midSquare
					whichSquare = 1;
					directionLine = 1;
					switch (stoneLocation.getY()) {
						case 1:
							// endpoint1 lineW
							pointLocInLine = 0;
							break;
						case 3:
							// midpoint lineW
							pointLocInLine = 1;
							break;
						case 5:
							// endpoint2 lineW
							pointLocInLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else if (x == 2) {
					// first line to test will always be west line of innerSquare
					whichSquare = 2;
					directionLine = 1;
					switch (stoneLocation.getY()) {
						case 2:
							// endpoint1 lineW
							directionLine = 0;
							break;
						case 3:
							// midpoint lineW
							directionLine = 1;
							break;
						case 4:
							// endpoint2 lineW
							directionLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else if (x == 3) {
					// tests all midpoints of north line and south line of every square and north/south midpoint lines
					switch (stoneLocation.getY()) {
						case 0:
							// outerSquare lineS midpoint
							whichSquare = 0;
							directionLine = 3;
							pointLocInLine = 1;
							break;
						case 1:
							// midSquare lineS midpoint
							whichSquare = 1;
							directionLine = 3;
							pointLocInLine = 1;
							break;
						case 2:
							// innerSquare lineS midpoint
							whichSquare = 2;
							directionLine = 3;
							pointLocInLine = 1;
							break;
						case 4:
							// innerSquare lineN midpoint
							whichSquare = 2;
							directionLine = 0;
							pointLocInLine = 1;
							break;
						case 5:
							// midSquare lineN midpoint
							whichSquare = 1;
							directionLine = 0;
							pointLocInLine = 1;
							break;
						case 6:
							// outerSquare lineN midpoint
							whichSquare = 0;
							directionLine = 0;
							pointLocInLine = 1;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else if (x == 4) {
					// first line to test will always be east line of innerSquare
					whichSquare = 2;
					directionLine = 2;
					switch (stoneLocation.getY()) {
						case 2:
							// endpoint1 lineE
							pointLocInLine = 0;
							break;
						case 3:
							// midpoint lineE
							pointLocInLine = 1;
							break;
						case 4:
							// endpoint2 lineE
							pointLocInLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else if (x == 5) {
					// first line to test will always be east line of midSquare
					whichSquare = 1;
					directionLine = 2;
					switch (stoneLocation.getY()) {
						case 1:
							// endpoint1 lineE
							pointLocInLine = 0;
							break;
						case 3:
							// midpoint lineE
							pointLocInLine = 1;
							break;
						case 5:
							// endpoint2 lineE
							pointLocInLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				else {
					// x == 6; line to test will always be east line of outerSquare
					whichSquare = 0;
					directionLine = 2;
					switch (stoneLocation.getY()) {
						case 0:
							// endpoint1 lineE
							pointLocInLine = 0;
							break;
						case 3:
							// midpoint lineE
							pointLocInLine = 1;
							break;
						case 6:
							// endpoint2 lineE
							pointLocInLine = 2;
							break;
						default:
							// invalid location
							System.out.println("Stone location invalid; shouldn't happen.");
							break;					
					}
				}
				
				while (index < remainingStones.size()) {
					movePriority = checkMovement(board, whichSquare, directionLine, pointLocInLine);
					
					if (movePriority != 0) {
						if (pointLocInLine == 0) {
						}
						else if (pointLocInLine == 1) {
						}
						else {
						}
						// stop here
						break;
					}
				}
				
				if (movePriority != 0) {
					// found; end for loop
					break;
				}
			
				index++;
			}
			
			if (movePriority == 0) {
				// no good move found; do a random move
			}
		}
		else {
			// either place or jump phase; can move to any empty point
		}
		
	}
		
	public int checkMovement(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		// point LocInLine; value 0 = endpoint1, 1 = midpoint, 2 = endpoint2
		int moveToDo;
		
		if (checkFill(board, squareSize, lineDirection, pointLocInLine)) {
			moveToDo = FILL_LINE_THISTURN;
		}
		else if (checkStopFill(board, squareSize, lineDirection, pointLocInLine)) {
			moveToDo = STOP_OPPONENT_NEXTTURN;
		}
		else if (checkGoForFill(board, squareSize, lineDirection, pointLocInLine)) {
			moveToDo = TWO_STEPS_TO_FILL;
		}
		else {
			moveToDo = 0;
		}
		
		return moveToDo;
	}
	
	public boolean checkFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		return true;
/*
		boolean moveFound = false;
		int goFirst;
		Point currentPoint;
		Point possiblePoint;
		
		if (getFirst()) {
			goFirst = 0;
		}
		else {
			goFirst = 1;
		}

		if (squareSize == 0) { // outerSquare
			if (lineDirection == 1) { // westLine
				if (pointLocInLine == 0) {
					currentPoint = board.getOuterSquare().getWestLine().getEndPoint1();
					possiblePoint = board.getOuterSquare().getWestLine().getMidPoint();
					
					if ((board.getWestMidLine().getMidPoint().getOccupyingPlayer() == goFirst)  &&
					(board.getWestMidLine().getEndPoint2().getOccupyingPlayer() == goFirst)) {
						moveFound = true;
						pointToMoveTo = possiblePoint;						
					}
					
					if (!moveFound) {
						possiblePoint = board.getOuterSquare().getSouthLine().getMidPoint();
						
						if ((board.getSouthMidLine().getMidPoint().getOccupyingPlayer() == goFirst) &&
						(board.getSouthMidLine().getEndPoint2().getOccupyingPlayer() == goFirst)) {
							moveFound = true;
							pointToMoveTo = possiblePoint;													
						}
					}
				else if (pointLocInLine == 1) {
					currentPoint = board.getOuterSquare().getWestLine().getMidPoint();
					possiblePoint = board.getOuterSquare().getWestLine().getEndPoint1();
					
					if ((board.getOuterSquare().getSouthLine().getMidPoint().getOccupyingPlayer() == goFirst) &&
					(board.getOuterSquare().getSouthLine().getEndPoint2().getOccupyingPlayer() == goFirst)) {
						moveFound = true;
						pointToMoveTo = possiblePoint;						
					}
					
					if (!moveFound) {
						possiblePoint = board.getOuterSquare().getWestLine().getEndPoint2();
						
						if ((board.getOuterSquare().getNorthLine().getMidPoint().getOccupyingPlayer() == goFirst) &&
						(board.getOuterSquare().getNorthLine().getEndPoint2().getOccupyingPlayer() == goFirst)) {
							moveFound = true;
							pointToMoveTo = possiblePoint;
						}
					}
					
					if (!moveFound) {
						possiblePoint = board.getWestMidLine().getMidPoint();
						
						if ((board.getMidSquare().getWestLine().getEndPoint1().getOccupyingPlayer() == goFirst) &&
						(board.getMidSquare().getWestLine().getEndPoint2().getOccupyingPlayer() == goFirst)) {
					}
					
					possiblePoints[2] = board.getWestMidLine().getMidPoint();
				}
				else {
					currentPoint = board.getOuterSquare().getWestLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getOuterSquare().getNorthLine().getMidPoint();
					possiblePoints[1] = board.getOuterSquare().getWestLine().getMidPoint();
				}				
			}
			else if (lineDirection == 2) { // eastLine
				if (pointLocInLine == 0) {
					currentPoint = board.getOuterSquare().getEastLine().getEndPoint1();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getOuterSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getOuterSquare().getSouthLine().getMidPoint();
				}
				else if (pointLocInLine == 1) {
					currentPoint = board.getOuterSquare().getEastLine().getMidPoint();
					possiblePoints = new Point[3];
					possiblePoints[0] = board.getOuterSquare().getEastLine().getEndPoint1();
					possiblePoints[1] = board.getOuterSquare().getEastLine().getEndPoint2();
					possiblePoints[2] = board.getEastMidLine().getMidPoint();
				}
				else {
					currentPoint = board.getOuterSquare().getEastLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getOuterSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getOuterSquare().getNorthLine().getMidPoint();
				}				
			}
			else if (lineDirection == 0) { // northLine
				// northLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getOuterSquare().getNorthLine().getMidPoint();
				possiblePoints = new Point[3];
				possiblePoints[0] = board.getOuterSquare().getNorthLine().getEndPoint1();
				possiblePoints[1] = board.getOuterSquare().getNorthLine().getEndPoint2();
				possiblePoints[2] = board.getNorthMidLine().getMidPoint();
			}
			else { // southLine
				// southLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getOuterSquare().getSouthLine().getMidPoint();
				possiblePoints = new Point[3];
				possiblePoints[0] = board.getOuterSquare().getSouthLine().getEndPoint1();
				possiblePoints[1] = board.getOuterSquare().getSouthLine().getEndPoint2();
				possiblePoints[2] = board.getSouthMidLine().getMidPoint();
			}
		}
		else if (squareSize == 1) { // midSquare
			if (lineDirection == 1) { // westLine
				if (pointLocInLine == 0) {
					currentPoint = board.getMidSquare().getWestLine().getEndPoint1();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getMidSquare().getWestLine().getMidPoint();
					possiblePoints[1] = board.getMidSquare().getSouthLine().getMidPoint();
				}
				else if (pointLocInLine == 1) {
					currentPoint = board.getMidSquare().getWestLine().getMidPoint();
					possiblePoints = new Point[4];
					possiblePoints[0] = board.getMidSquare().getWestLine().getEndPoint1();
					possiblePoints[1] = board.getMidSquare().getWestLine().getEndPoint2();
					possiblePoints[2] = board.getWestMidLine().getEndPoint1();
					possiblePoints[3] = board.getWestMidLine().getEndPoint2();
				}
				else {
					currentPoint = board.getMidSquare().getWestLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getMidSquare().getNorthLine().getMidPoint();
					possiblePoints[1] = board.getMidSquare().getWestLine().getMidPoint();
				}				
			}
			else if (lineDirection == 2) { // eastLine
				if (pointLocInLine == 0) {
					currentPoint = board.getMidSquare().getEastLine().getEndPoint1();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getMidSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getMidSquare().getSouthLine().getMidPoint();
				}
				else if (pointLocInLine == 1) {
					currentPoint = board.getMidSquare().getEastLine().getMidPoint();
					possiblePoints = new Point[4];
					possiblePoints[0] = board.getMidSquare().getEastLine().getEndPoint1();
					possiblePoints[1] = board.getMidSquare().getEastLine().getEndPoint2();
					possiblePoints[2] = board.getEastMidLine().getEndPoint1();
					possiblePoints[3] = board.getEastMidLine().getEndPoint2();
				}
				else {
					currentPoint = board.getMidSquare().getEastLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getMidSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getMidSquare().getNorthLine().getMidPoint();
				}				
			}
			else if (lineDirection == 0) { // northLine
				// northLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getMidSquare().getNorthLine().getMidPoint();
				possiblePoints = new Point[4];
				possiblePoints[0] = board.getMidSquare().getNorthLine().getEndPoint1();
				possiblePoints[1] = board.getMidSquare().getNorthLine().getEndPoint2();
				possiblePoints[2] = board.getNorthMidLine().getEndPoint1();
				possiblePoints[3] = board.getNorthMidLine().getEndPoint2();
			}
			else { // southLine
				// southLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getMidSquare().getSouthLine().getMidPoint();
				possiblePoints = new Point[4];
				possiblePoints[0] = board.getMidSquare().getSouthLine().getEndPoint1();
				possiblePoints[1] = board.getMidSquare().getSouthLine().getEndPoint2();
				possiblePoints[2] = board.getSouthMidLine().getEndPoint1();
				possiblePoints[3] = board.getSouthMidLine().getEndPoint2();
			}
		}
		else { // innerSquare
			if (lineDirection == 1) { // westLine
				if (pointLocInLine == 0) {
					currentPoint = board.getInnerSquare().getWestLine().getEndPoint1();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getInnerSquare().getWestLine().getMidPoint();
					possiblePoints[1] = board.getInnerSquare().getSouthLine().getMidPoint();
				}
				else if (pointLocInLine == 1) {
					currentPoint = board.getInnerSquare().getWestLine().getMidPoint();
					possiblePoints = new Point[3];
					possiblePoints[0] = board.getInnerSquare().getWestLine().getEndPoint1();
					possiblePoints[1] = board.getInnerSquare().getWestLine().getEndPoint2();
					possiblePoints[2] = board.getWestMidLine().getMidPoint();
				}
				else {
					currentPoint = board.getInnerSquare().getWestLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getInnerSquare().getNorthLine().getMidPoint();
					possiblePoints[1] = board.getInnerSquare().getWestLine().getMidPoint();
				}				
			}
			else if (lineDirection == 2) { // eastLine
				if (pointLocInLine == 0) {
					currentPoint = board.getInnerSquare().getEastLine().getEndPoint1();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getInnerSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getInnerSquare().getSouthLine().getMidPoint();
				}
				else if (pointLocInLine == 1) {
					currentPoint = board.getInnerSquare().getEastLine().getMidPoint();
					possiblePoints = new Point[3];
					possiblePoints[0] = board.getInnerSquare().getEastLine().getEndPoint1();
					possiblePoints[1] = board.getInnerSquare().getEastLine().getEndPoint2();
					possiblePoints[2] = board.getEastMidLine().getMidPoint();
				}
				else {
					currentPoint = board.getInnerSquare().getEastLine().getEndPoint2();
					possiblePoints = new Point[2];
					possiblePoints[0] = board.getInnerSquare().getEastLine().getMidPoint();
					possiblePoints[1] = board.getInnerSquare().getNorthLine().getMidPoint();
				}				
			}
			else if (lineDirection == 0) { // northLine
				// northLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getInnerSquare().getNorthLine().getMidPoint();
				possiblePoints = new Point[3];
				possiblePoints[0] = board.getInnerSquare().getNorthLine().getEndPoint1();
				possiblePoints[1] = board.getInnerSquare().getNorthLine().getEndPoint2();
				possiblePoints[2] = board.getNorthMidLine().getMidPoint();
			}
			else { // southLine
				// southLine will only have midPoint as others overlap with west/eastLine
				currentPoint = board.getInnerSquare().getSouthLine().getMidPoint();
				possiblePoints = new Point[3];
				possiblePoints[0] = board.getInnerSquare().getSouthLine().getEndPoint1();
				possiblePoints[1] = board.getInnerSquare().getSouthLine().getEndPoint2();
				possiblePoints[2] = board.getSouthMidLine().getMidPoint();
			}
		}
		
		if (pointToMoveTo == null) {
			moveFound = false;
		}
			
		return moveFound;
		*/
	}
	
	public boolean checkStopFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		boolean moveFound;
		
		if (pointLocInLine == 0) {
			
		}
		else if (pointLocInLine == 1) {
			
		}
		else {
			
		}

		return true;
	}
	
	public boolean checkGoForFill(Board board, int squareSize, int lineDirection, int pointLocInLine) {
		boolean moveFound;
		
		if (pointLocInLine == 0) {
			
		}
		else if (pointLocInLine == 1) {
			
		}
		else {
			
		}

		return true;
	}
	
	public void checkRemove() {
		
	}
}
