/**
 * The Line class helps to get the position of all the points and keep track of
 * the positions of the stones on the lines for all the players. This class can
 * be used to consistently check if any of the players or the computer has three
 * stones in a row. This class helps other classes to take appropriate action or
 * print appropriate message based on the information gathered about the stones
 * and their relative position on the board.
 * 
 * @author srish
 *
 */
public class Line {
	private String playerName;// what does this variable represent?
	private Point endPoint1;
	private Point midPoint;
	private Point endPoint2;
	private boolean isFilled;
	private Point[] slotLocationOnBoard;

	public Line(String name, Point endPoint1, Point midPoint, Point endPoint2) {
		this.playerName = name;
		isFilled = false;
		slotLocationOnBoard = new Point[3];// meaning of integer in []?

		this.endPoint1 = endPoint1;
		slotLocationOnBoard[0] = this.endPoint1;
		this.midPoint = midPoint;
		slotLocationOnBoard[1] = this.midPoint;
		this.endPoint2 = endPoint2;
		slotLocationOnBoard[2] = this.endPoint2;
	}

	public String toString() {
		return playerName;// why is this method needed?, access player name from another class?
	}

	public Point[] getPoints() {// Why is this Point [] only whereas other getter methods use Point only?
		return slotLocationOnBoard;
	}

	public Point getEndPoint1() {
		return endPoint1;
	}

	public Point getEndPoint2() {
		return endPoint2;
	}

	public Point getMidPoint() {
		return midPoint;
	}
	
	public void setFilled(boolean filled) {//Where do you use this?
		isFilled = filled;
	}

//	public boolean isLineFilled(Player player) {// check if all three points of a line are occupied for player
//		if (endPoint1.getOccupiedStone() != null) {
//			if (endPoint1.getOccupiedStone() == midPoint.getOccupiedPlayer()) {
//				if (midPoint.getOccupiedStone() == endPoint2.getOccupiedPlayer()) {
//					return true;
//				}
//
//				else {
//					return false;
//				}
//			}
//
//			else {
//				return false;
//			}
//		}
//
//		else {
//			return false;
//		}
//
//	}

	public void isLineFilled (Stone playerStonePosition) {
		if (doesSlotContainStone(playerStonePosition)==3) {
			isFilled = true;
			System.out.println(playerName+"has three stones in a line.");
		}
		
	}
	public int doesSlotContainStone(Stone playerStonePosition) {// checks number of stones in a line
		boolean slotContainsAStone = false;//purpose to check this, where is it used?
		int numberOfStonesInLine = 0;

		if (endPoint1.getOccupiedStone() == playerStonePosition) {
			slotContainsAStone = true;
			numberOfStonesInLine += 1;
		}	
			if (midPoint.getOccupiedStone() == playerStonePosition) {
				slotContainsAStone = true;
				numberOfStonesInLine += 1;
			}
				if (endPoint2.getOccupiedStone() == playerStonePosition) {
					slotContainsAStone = true;
					numberOfStonesInLine += 1;
				}

		if (midPoint.getOccupiedStone() == playerStonePosition) {
			slotContainsAStone = true;
			numberOfStonesInLine += 1;
		}	
			if (endPoint2.getOccupiedStone() == playerStonePosition) {
			slotContainsAStone = true;
			numberOfStonesInLine += 1;
			}

		else if (endPoint2.getOccupiedStone() == playerStonePosition) {
			slotContainsAStone = true;
			numberOfStonesInLine += 1;
		}

		return numberOfStonesInLine;
	}

	public boolean doesLineContainPoint(Point pointToBeChecked) {
		boolean contains = false;

		if (endPoint1 == pointToBeChecked) {
			contains = true;
		}

		else if (midPoint == pointToBeChecked) {
			contains = true;
		}

		else if (endPoint2 == pointToBeChecked) {
			contains = true;
		}

		return contains;
	}
	
}