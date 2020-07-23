
public class Board {

	private Square outerSquare;
	private Square midSquare;
	private Square innerSquare;
	private Line northMidLine;
	private Line westMidLine;
	private Line eastMidLine;
	private Line southMidLine;
	private Square[] squares = new Square[4];

//Add an extra element for pseudoSquares
	public Board(int range, int CentreXCoord, int CentreYCoord) {
		outerSquare = new Square(range, CentreXCoord - range / 2, CentreYCoord - range / 2);
		squares[0] = outerSquare;
		range *= 0.67;

		midSquare = new Square(range, CentreXCoord - range / 2, CentreYCoord - range / 2);
		squares[1] = midSquare;
		range *= 0.67;

		innerSquare = new Square(range, CentreXCoord - range / 2, CentreYCoord - range / 2);
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

	public Square[] getSquares() {
		return squares;
	}

	public void displayBoard() {
		for (int i = 0; i < squares.length; i++) {
			System.out.println("Points on the North Lines: " + squares[i].getNorthLine().getPoints());
			System.out.println("Points on the East Lines: " + squares[i].getEastLine().getPoints());
			System.out.println("Points on the West Lines: " + squares[i].getWestLine().getPoints());
			System.out.println("Points on the South Lines: " + squares[i].getSouthLine().getPoints());
		}
	}
}
