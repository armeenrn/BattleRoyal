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
package model;

public class Line {
	private String name;
	private Point endPoint1;
	private Point midPoint;
	private Point endPoint2;
	private Point[] points;

	public Line(String name, Point endPoint1, Point midPoint, Point endPoint2) {
		this.name = name;
		points = new Point[3];

		this.endPoint1 = endPoint1;
		points[0] = this.endPoint1;
		this.midPoint = midPoint;
		points[1] = this.midPoint;
		this.endPoint2 = endPoint2;
		points[2] = this.endPoint2;
	}

	public String toString() {
		return name;
	}

	public Point[] getPoints() {
		return points;
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

	/**
	 * Checks if the line is filled with same player. This method can be used to
	 * determine if the player has won.
	 * 
	 * @param player is one of the players playing in this turn and will be used to
	 *               compare if this player occupies each point in a line or not.
	 * @return true if a line is filled by the same player (a wining move for the
	 *         game), false otherwise
	 */
	public boolean isLineFilled(Player player) {
		boolean lineIsFilledByPlayer = false;
		if (endPoint1.getOccupiedPlayer() == player) {
			if (endPoint2.getOccupiedPlayer() == player) {
				if (midPoint.getOccupiedPlayer() == player) {
					lineIsFilledByPlayer = true;
				}
			}
		}
		return lineIsFilledByPlayer;
	}

	/**
	 * Check if the line contains the stone entered in the parameter
	 * 
	 * @param stoneToCheck a stone with x and y coordinate
	 * @return true if the any of the points on the line contains stone entered in
	 *         the parameter, false otherwise
	 */
	public boolean doesLineContainStone(Stone stoneToCheck) {
		boolean containsStones = false;

		if (endPoint1.getOccupiedStone() == stoneToCheck) {
			containsStones = true;
		}

		else if (midPoint.getOccupiedStone() == stoneToCheck) {
			containsStones = true;
		}

		else if (endPoint2.getOccupiedStone() == stoneToCheck) {
			containsStones = true;
		}

		return containsStones;
	}

	/**
	 * Check if the line contains the point entered in the parameter
	 * 
	 * @param pointToBeChecked a point with x and y coordinate
	 * @return true if the line contains any of the point entered in the parameter,
	 *         false otherwise
	 */
	public boolean doesLineContainPoint(Point pointToBeChecked) {
		boolean containsPoints = false;

		if (endPoint1 == pointToBeChecked) {
			containsPoints = true;
		}

		else if (midPoint == pointToBeChecked) {
			containsPoints = true;
		}

		else if (endPoint2 == pointToBeChecked) {
			containsPoints = true;
		}

		return containsPoints;
	}

}