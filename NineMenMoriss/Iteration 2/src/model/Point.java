/*
 * Point represents the a point on the board
 */

package model;

/** 
 * public class Point creates the points on the board
 * 	10.00 6 August 2020
 * 	@author Armeen Rashidian, Daniel Kim
 * 	Team D
 */

public class Point {
	private double x;
	private double y;
	private Stone occupiedStone;
	private int occupiedPlayer;	// 1 if player1, 2 if player2, 0 if no player
	
	/**
	 * Constructor creates a point on the board based on their coordinates 
	 * 	@param xCoord
	 * 	@param yCoord
	 */
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}
	
	/**
	 * getX returns the x coordinate of the point on the board
	 * 	@return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * getY returns the y coordinate of the point on the board
	 * 	@return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * getOccupiedStone gets the occupied stone on the point
	 * 	@return occupiedStone
	 */
	public Stone getOccupiedStone() {
		return occupiedStone;
	}
	
	/**
	 * setOccupiedStone sets a stone on the point
	 * 	@param stone, the stone to be placed on the point
	 */
	public void setOccupiedStone(Stone stone) {
		occupiedStone = stone;
	}
	
	/**
	 * getOccupiedPlayer returns the number of the player occupying the stone (if any)
	 * 	@return occupiedPlayer, the integer number of the player
	 */
	public int getOccupiedPlayer() {
		return occupiedPlayer;
	}	
	
	/**
	 * setOccupiedPlayer sets the player occupying the point when they place their stone onto it
	 * 	@param occupiedPlayer, the integer number of the player who's stone is to be placed
	 */
	public void setOccupiedPlayer(int occupiedPlayer) {
		this.occupiedPlayer = occupiedPlayer;
	}
	
	/**
	 * to String returns a string representation of the point based on its x and y coordinates
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
