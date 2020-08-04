package model;

public class Square {
	private String name;
	private Line lineN;
	private Line lineW;
	private Line lineE;
	private Line lineS;
	private Line[] lines;

	public Square (String name, double size, double startX, double startY) {
		this.name = name;
		Point southWest = new Point(startX, startY);
		Point midSouth = new Point((startX + size/2), startY);
		Point southEast = new Point((startX + size), startY);
		Point midWest = new Point(startX, (startY + size/2));
		Point northWest = new Point(startX, startY + size);
		Point midNorth = new Point(startX + size/2, startY + size);				
		Point northEast = new Point(startX + size, startY + size);
		Point midEast = new Point(startX + size, startY + size/2);	
	
		lineN = new Line("lineN", northWest, midNorth, northEast);
		lineW = new Line("lineW", southWest, midWest, northWest);
		lineE = new Line("lineE", southEast, midEast, northEast);
		lineS = new Line("lineS", southWest, midSouth, southEast);

		lines = new Line[4];
		
		lines[0] = lineS;
		lines[1] = lineW;
		lines[2] = lineE;
		lines[3] = lineN;
		
	}


	public Square(Line northMidLine, Line westMidLine, Line eastMidLine, Line southMidLine) {
		name = "pseudo-square";
		lineN = northMidLine;
		lineW = westMidLine;
		lineE = eastMidLine;
		lineS = southMidLine;

		lines = new Line[4];

		lines[0] = lineN;
		lines[1] = lineW;
		lines[2] = lineE;
		lines[3] = lineS;
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

	
	public String toString() {
		return name;
	}


	public Line[] getLines() {
		return lines;
	}
}
