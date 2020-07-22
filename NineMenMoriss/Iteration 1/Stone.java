
public class Stone {
	private Point location;
	private int owner;

	/**
	 * Constructor of a Stone object
	 * 
	 * @param location location of the Stone object
	 * @param Player player the stone belongs to
	 */
	public Stone(Point location, int Player) {
		this.location = location;
		this.owner = Player;
	}
	
	public Point getLocation() {
		return new Point(location);
	}
	
	public int getOwner() {
		return owner;
	}
}
