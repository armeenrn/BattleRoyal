
public class Point {
	
	private double x;
	private double y;
	private int occupyingPlayerNumber;
	private Stone occupiedStone;
	private Player occupiedPlayer;

	
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
		occupyingPlayerNumber = 0;
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
		occupiedStone = stoneToOccupy; 
	}
	
	public int getOccupyingPlayerNumber() {
		return occupyingPlayerNumber;
	}
	
	
	public void setOccupyingPlayerNumber(int playerNumber) {
		occupyingPlayerNumber = playerNumber;
	}
	
	public Player getOccupiedPlayer() {
		return occupiedPlayer;
	}
	
	public void setOccupiedPlayer(Player playerToOccupy) {
		occupiedPlayer = playerToOccupy;
	}
	
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
