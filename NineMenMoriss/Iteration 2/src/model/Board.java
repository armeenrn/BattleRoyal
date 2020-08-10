package model;

/**
 * The board class uses Square and Line classes to create the game board.
 * 
 * @author Srishti
 *
 */
public class Board {

	//private Square outerSquare;
	//private Square midSquare;
	//private Square innerSquare;
	private Line northMidLine;
	private Line westMidLine;
	private Line eastMidLine;
	private Line southMidLine;
	private Square[] squares;

//Add an extra element for pseudoSquares
	/**
	 * This constructor allows to create and set the value of each index of the
	 * squares array
	 * 
	 * @param range        is the length of the line used to construct each square
	 *                     on the board
	 * @param centreXCoord is the centre x coordinate point of the square
	 * @param centreYCoord is the centre y coordinate point of the square
	 */
	public Board(double range, double centreXCoord, double centreYCoord) {
		squares = new Square[4];

		try {
			squares[0] = new Square("Outer Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//squares[0] = outerSquare;
		range = range * (2.0 / 3);

		try {
			squares[1] = new Square("Middle Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//squares[1] = midSquare;
		range = range * 0.5;

		try {
			squares[2] = new Square("Inner Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//squares[2] = innerSquare;

		try {
			northMidLine = new Line("North Mid Line", squares[2].getNorthLine().getMidPoint(),
					squares[1].getNorthLine().getMidPoint(), squares[0].getNorthLine().getMidPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			westMidLine = new Line("West Mid Line", squares[2].getWestLine().getMidPoint(),
					squares[1].getWestLine().getMidPoint(), squares[0].getWestLine().getMidPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			eastMidLine = new Line("East Mid Line", squares[2].getEastLine().getMidPoint(),
					squares[1].getEastLine().getMidPoint(), squares[0].getEastLine().getMidPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			southMidLine = new Line("South Mid Line", squares[2].getSouthLine().getMidPoint(),
					squares[1].getSouthLine().getMidPoint(), squares[0].getSouthLine().getMidPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Square linesOnBoard = null;
		try {
			squares[3] = new Square(northMidLine, westMidLine, eastMidLine, southMidLine);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//squares[3] = linesOnBoard;
	}

	/**
	 * Getter method to access all the squares on the game board
	 * 
	 * @return an array of squares that are used to build the game board.
	 */
	public Square[] getSquares() {
		return squares;
	}

	/**
	 * This method is used in the TextApplication controller to display all the
	 * points on any specific line
	 */
	public void displayBoard() {
		for (int i = 0; i < squares.length; i++) {
			System.out.println("Points on the North Lines: " + squares[i].getNorthLine().getPoints());
			System.out.println("Points on the East Lines: " + squares[i].getEastLine().getPoints());
			System.out.println("Points on the West Lines: " + squares[i].getWestLine().getPoints());
			System.out.println("Points on the South Lines: " + squares[i].getSouthLine().getPoints());
		}
	}

	/**
	 * This class can be used to locate the point on the board based on the x and y
	 * coordinates of the point.
	 * 
	 * @param xcor x coordinate of any of the points on the board
	 * @param ycor y coordinate of any of the points on the board
	 * @return
	 */
	public Point getPointByCoordinates(double xcor, double ycor) {
		Point answer = null;
		for (Square eachSquare : squares) {

			for (Line eachLine : eachSquare.getLines()) {

				for (Point eachPoint : eachLine.getPoints()) {

					if (eachPoint.getX() == xcor && eachPoint.getY() == ycor) {
						answer = eachPoint;
					}
				}
			}
		}

		return answer;
	}
}
