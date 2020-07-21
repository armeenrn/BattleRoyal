
public class Square {
	private Line lineN;
	private Line lineW;
	private Line lineE;
	private Line lineS;
	private int sideLength;
	
	public Square (int sizeOfSquare, int startX, int startY) {
		Point origin = new Point(startX, startY);
		Point midSouth = new Point((startX + sizeOfSquare), startY);
		Point southEast = new Point((startX + sizeOfSquare * 2), startY);
		Point midWest = new Point(startX, (startY + sizeOfSquare));
		Point northWest = new Point(startX, (startY + sizeOfSquare * 2));
		Point midNorth = new Point((startX + sizeOfSquare), (startY + sizeOfSquare * 2));
		Point northEast = new Point((startX + sizeOfSquare * 2), (startY + sizeOfSquare * 2));
		Point midEast = new Point ((startX + sizeOfSquare * 2), (startY + sizeOfSquare));
		
		lineN = new Line(northWest, midNorth, northEast);
		lineW = new Line(origin, midWest, northWest);
		lineE = new Line(southEast, midEast, northEast);
		lineS = new Line(origin, midSouth, southEast);
		sideLength = sizeOfSquare;
	}
	
	/**
	 * Getter method to retrieve north Line of the Square object
	 * 
	 * @return North Line object
	 */
	public Line getNorthLine() {
		return lineN;
	}

	/**
	 * Getter method to retrieve west Line of the Square object
	 * 
	 * @return West Line object
	 */
	public Line getWestLine() {
		return lineW;
	}

	/**
	 * Getter method to retrieve east Line of the Square object
	 * 
	 * @return East Line object
	 */
	public Line getEastLine() {
		return lineE;
	}
	
	/**
	 * Getter method to retrieve south Line of the Square object
	 * 
	 * @return South Line object
	 */
	public Line getSouthLine() {
		return lineS;
	}
}
