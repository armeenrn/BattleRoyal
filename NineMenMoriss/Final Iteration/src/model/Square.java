package model;

/**
 * This class is used to built one square on the bases of 4 lines constructing
 * 	it.
 * 
 * 	@author Srishti Pahel, Sadia Saad
 * 	@version 1.0 10.00 6 August 2020 
 * 	Team D
 */
public class Square {
	private String name;
	private Line[] lines;

	/**
	 * Constructor to to create a square from 4 lines
	 * 
	 * @param name   name of the square on the board as there are more than one
	 *               square
	 * @param size   size of square
	 * @param startX x coordinate starting point for each line that makes up the
	 *               square
	 * @param startY y coordinate starting point for each line that makes up the
	 *               square
	 */
	public Square(String name, double size, double startX, double startY) throws ClassNotFoundException {

		try {
			this.name = name;
			Point southWest = new Point(startX, startY);
			Point midSouth = new Point((startX + size / 2), startY);
			Point southEast = new Point((startX + size), startY);
			Point midWest = new Point(startX, (startY + size / 2));
			Point northWest = new Point(startX, startY + size);
			Point midNorth = new Point(startX + size / 2, startY + size);
			Point northEast = new Point(startX + size, startY + size);
			Point midEast = new Point(startX + size, startY + size / 2);
			
			lines = new Line[4];

			lines[3] = new Line("lineN", northWest, midNorth, northEast);
			lines[2] = new Line("lineE", southEast, midEast, northEast);
			lines[1] = new Line("lineW", southWest, midWest, northWest);
			lines[0] = new Line("lineS", southWest, midSouth, southEast);
			
		} catch (Exception cnf) {
			System.out.println("Classes used do not exist: " + cnf);
		}
	}

	/**
	 * Create a pseudo square to incorporate the lines connecting different squares
	 * together
	 * 
	 * @param northMidLine northMidLine
	 * @param westMidLine  westMidLine
	 * @param eastMidLine  eastMidLine
	 * @param southMidLine southMidLine
	 */
	public Square(Line northMidLine, Line westMidLine, Line eastMidLine, Line southMidLine)
			throws ClassNotFoundException {
		name = "pseudo-square";
		try {
			lines = new Line[4];
			
			lines[3] = northMidLine;
			lines[2] = eastMidLine;
			lines[1] = westMidLine;
			lines[0] = southMidLine;
			
		} catch (Exception cnf) {
			System.out.println("Classes used do not exist: " + cnf);
		}

	}

	
	/**
	 * name of square in type String
	 */
	public String toString() {
		return name;
	}

	/**
	 * Getter method to get all the lines that make up the square and pseudo square
	 * 
	 * @return array of all the lines on the boards
	 */
	public Line[] getLines() {
		return lines;
	}
}
