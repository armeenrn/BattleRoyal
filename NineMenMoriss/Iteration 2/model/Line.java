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

	public boolean isLineFilled(Player player) {
		if (endPoint1.getOccupiedPlayer() == player) {
			if (endPoint1.getOccupiedPlayer() == midPoint.getOccupiedPlayer()) {
				if (midPoint.getOccupiedPlayer() == endPoint2.getOccupiedPlayer()) {
					return true;
				}

				else {
					return false;
				}
			}

			else {
				return false;
			}
		}

		else {
			return false;
		}

	}

	public boolean doesLineContain(Stone stoneToCheck) {
		boolean contains = false;

		if (endPoint1.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}

		else if (midPoint.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}

		else if (endPoint2.getOccupiedStone() == stoneToCheck) {
			contains = true;
		}

		return contains;
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