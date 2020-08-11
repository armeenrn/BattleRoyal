package model;

/**
 * The board class uses Square and Line classes to create the game board.
 * 
 * @author srish
 *
 */
public class Board {
/*
	private Square outerSquare;
	private Square midSquare;
	private Square innerSquare;
	*/
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

		squares[0] = new Square("Outer Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		//squares[0] = outerSquare;
		range = range * (2.0 / 3);

		squares[1] = new Square("Middle Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		//squares[1] = midSquare;
		range = range * 0.5;

		squares[2] = new Square("Inner Square", range, centreXCoord - range / 2, centreYCoord - range / 2);
		//squares[2] = innerSquare;

		northMidLine = new Line("North Mid Line", squares[2].getLines()[3].getPoints()[1],
				squares[1].getLines()[3].getPoints()[1], squares[0].getLines()[3].getPoints()[1]);
		westMidLine = new Line("West Mid Line", squares[2].getLines()[1].getPoints()[1],
				squares[1].getLines()[1].getPoints()[1], squares[0].getLines()[1].getPoints()[1]);
		eastMidLine = new Line("East Mid Line", squares[2].getLines()[2].getPoints()[1],
				squares[1].getLines()[2].getPoints()[1], squares[0].getLines()[2].getPoints()[1]);
		southMidLine = new Line("South Mid Line", squares[2].getLines()[0].getPoints()[1],
				squares[1].getLines()[0].getPoints()[1], squares[0].getLines()[0].getPoints()[1]);
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
			System.out.println("Points on the North Lines: " + squares[i].getLines()[3].getPoints());
			System.out.println("Points on the East Lines: " + squares[i].getLines()[2].getPoints());
			System.out.println("Points on the West Lines: " + squares[i].getLines()[1].getPoints());
			System.out.println("Points on the South Lines: " + squares[i].getLines()[0].getPoints());
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
