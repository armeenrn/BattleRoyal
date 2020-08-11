/**
 * The Line class helps to get the position of all the points and keep track of
 * the positions of the stones on the lines for all the players. This class can
 * be used to consistently check if any of the players or the computer has three
 * stones in a row. This class helps other classes to take appropriate action or
 * print appropriate message based on the information gathered about the stones
 * and their relative position on the board.
 * 
 * @author Srishti
 * @version 1.1
 * 10.00 6 August 2020
 * Team D
 */
package model;

public class Line {
	private String name;
	private Point endPoint1;
	private Point midPoint;
	private Point endPoint2;
	private Point[] points;

	/**
	 * Constructor for a Line object, with its name and points on the line
	 * 
	 * @param name      Name of the Line object
	 * @param endPoint1 An endpoint of the line
	 * @param midPoint  A midpoint of the line
	 * @param endPoint2 The other endpoint of the line
	 */
	public Line(String name, Point endPoint1, Point midPoint, Point endPoint2) throws NullPointerException, Exception {

		try {
			this.name = name;
			points = new Point[3];

			this.endPoint1 = endPoint1;
			points[0] = this.endPoint1;
			this.midPoint = midPoint;
			points[1] = this.midPoint;
			this.endPoint2 = endPoint2;
			points[2] = this.endPoint2;
		} catch (NullPointerException np) {
			System.out.println(np + " One or more parameters are empty.");
		} catch (Exception e) {
			System.out.println(e + "Exception is caught.");
		}
	}

	public Point[] getPoints() {// Why is this Point [] only whereas other getter methods use Point only?
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
	 * Checks if all three points of the line is filled by the same player's stones
	 * 
	 * @param player Player in which stones will be checked
	 * @return Indicates if the line is filled
	 */
	public boolean isLineFilled(Player player) {// check if all three points of a line are occupied for player
		if (endPoint1.getOccupiedPlayer() == player.getPlayerNumber()) {
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

	/**
	 * Checks if any point of the line contains the stone the game seeks
	 * 
	 * @param stoneToCheck Stone object to check its existence
	 * @return Indicates if the line contains the stone
	 */
	public boolean doesLineContain(Stone stoneToCheck) throws NullPointerException, Exception {
		boolean contains = false;
		try {
			if (endPoint1.getOccupiedStone() == stoneToCheck) {
				contains = true;
			}

			else if (midPoint.getOccupiedStone() == stoneToCheck) {
				contains = true;
			}

			else if (endPoint2.getOccupiedStone() == stoneToCheck) {
				contains = true;
			}
		} catch (NullPointerException np) {
			System.out.println(np + " The parameter stoneToCheck of type Stone is empty.");
		} catch (Exception e) {
			System.out.println(e + "Exception is caught.");
		}

		return contains;
	}

	/**
	 * Checks if the line contains the point given
	 * 
	 * @param pointToBeChecked Point object to check its existence
	 * @return Indicates if the line contains the point
	 */
	public boolean doesLineContainPoint(Point pointToBeChecked) throws NullPointerException, Exception {
		boolean contains = false;
		try {

			if (endPoint1 == pointToBeChecked) {
				contains = true;
			}

			else if (midPoint == pointToBeChecked) {
				contains = true;
			}

			else if (endPoint2 == pointToBeChecked) {
				contains = true;
			}
		} catch (NullPointerException np) {
			System.out.println(np + " The parameter pointToBeChecked of type Point is empty.");
		} catch (Exception e) {
			System.out.println(e + "Exception is caught.");
		}
		return contains;

	}

}