package model;

import javafx.scene.shape.Circle;

public class Point {
	
	private double x;
	private double y;
	private Stone occupiedStone;
	private Player occupiedPlayer;
	private Circle circle;
	
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Stone getOccupiedStone( ){
		return occupiedStone;
	}
	
	public void setOccupiedStone(Stone stoneToOccupy) {
		if (stoneToOccupy == null) {
			occupiedStone = null;
		}
		
		else {
			occupiedStone = stoneToOccupy; 	
		}
	}
	
	
	public Player getOccupiedPlayer() {
		if (occupiedPlayer == null) {
			return null;
		}
		
		else {
			return occupiedPlayer;	
		}
	}
	
	
	public void setOccupiedPlayer(Player playerToOccupy) {
		if (playerToOccupy == null) {
			occupiedPlayer = null;
		}
		
		else {
			occupiedPlayer = playerToOccupy; 	
		}
	}
	
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
