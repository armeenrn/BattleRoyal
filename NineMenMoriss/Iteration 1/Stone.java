
public class Stone {
	private Point location;
	private Player owner;

	/**
	 * Constructor of a Stone object
	 * 
	 * @param location location of the Stone object
	 * @param Player player the stone belongs to
	 */
	public Stone(Player owner) {
		this.owner = owner;
	}

	public void setLocation(Point location) {
		if (location == null) {
			this.location = null;
		}
		
		else {
			this.location = location;	
		}	
	}
	
	public Point getLocation() {
		return location;	
	}
	
	public Player getOwner() {
		return owner;
	}
	

}
