package model;

/**
 * The board class uses Square and Line classes to create the game board.
 * 
 * @author srish
 *
 */
public class Board {

	private Square outerSquare;
	private Square midSquare;
	private Square innerSquare;
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

		outerSquare = new Square("Outer Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		squares[0] = outerSquare;
		range = range * (2.0 / 3);

		midSquare = new Square("Middle Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		squares[1] = midSquare;
		range = range * 0.5;

		innerSquare = new Square("Inner Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		squares[2] = innerSquare;

		northMidLine = new Line("North Mid Line", innerSquare.getNorthLine().getMidPoint(),
				midSquare.getNorthLine().getMidPoint(), outerSquare.getNorthLine().getMidPoint());
		westMidLine = new Line("West Mid Line", innerSquare.getWestLine().getMidPoint(),
				midSquare.getWestLine().getMidPoint(), outerSquare.getWestLine().getMidPoint());
		eastMidLine = new Line("East Mid Line", innerSquare.getEastLine().getMidPoint(),
				midSquare.getEastLine().getMidPoint(), outerSquare.getEastLine().getMidPoint());
		southMidLine = new Line("South Mid Line", innerSquare.getSouthLine().getMidPoint(),
				midSquare.getSouthLine().getMidPoint(), outerSquare.getSouthLine().getMidPoint());
		Square linesOnBoard = new Square(northMidLine, westMidLine, eastMidLine, southMidLine);
		squares[3] = linesOnBoard;
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
