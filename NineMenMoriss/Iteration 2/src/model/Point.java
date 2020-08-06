package model;

public class Point {
	
	private double x;
	private double y;
	private Stone occupiedStone;
	private int occupiedPlayer;
	
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Stone getOccupiedStone() {
		return occupiedStone;
	}
	
	public void setOccupiedStone(Stone stone) {
		occupiedStone = stone;
	}
	
	public int getOccupiedPlayer() {
		return occupiedPlayer;
	}	
	
	public void setOccupiedPlayer(int occupiedPlayer) {
		this.occupiedPlayer = occupiedPlayer;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
