package model;

public class Point {

	private double x;
	private double y;
	private Stone occupiedStone;
	private Player occupiedPlayer;

	/**
	 * Constructor to define each point with an x-coordinate and y-coordinate
	 * 
	 * @param xCoord x coordinate location of point
	 * @param yCoord y coordinate location of point
	 */
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}

	/**
	 * Get the private instance variable for x coordinate
	 * 
	 * @return x coordinate of point
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the private instance variable for y coordinate
	 * 
	 * @return y coordinate of point
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get an instance of stone called occupied stone
	 * 
	 * @return
	 */
	public Stone getOccupiedStone() {
		return occupiedStone;
	}

	/**
	 * Set the instance of stone called stoneToOccupy (new object), if no value
	 * entered for this object then object gets a value of null
	 * 
	 * @param stoneToOccupy The stone that will occupy the point on the board
	 */
	public void setOccupiedStone(Stone stoneToOccupy) {
		if (stoneToOccupy == null) {
			occupiedStone = null;
		}

		else {
			occupiedStone = stoneToOccupy;
		}
	}

	/**
	 * Get the occupied player of type player
	 * 
	 * @return if player is occupied by one of the players or null (not occupied)
	 */
	public Player getOccupiedPlayer() {
		if (occupiedPlayer == null) {
			return null;
		}

		else {
			return occupiedPlayer;
		}
	}

	/**
	 * Set a value for the new object called playerToOccupy under player class
	 * 
	 * @param playerToOccupy the player that will occupy the point
	 */
	public void setOccupiedPlayer(Player playerToOccupy) {
		if (playerToOccupy == null) {
			occupiedPlayer = null;
		}

		else {
			occupiedPlayer = playerToOccupy;
		}
	}

	/**
	 * returns a string containing the x and y coordinates for every new object
	 * initiated under Point class
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
