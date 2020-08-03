package model;

public class Stone {
	private Point location;
	private Player owner;

	/**
	 * Constructor of a Stone object
	 * 
	 * @param location location of the Stone object
	 * @param Player   player the stone belongs to
	 */
	public Stone(Player owner) {
		this.owner = owner;
	}

	/**
	 * Set the location
	 * 
	 * @param location location of where the stone will be placed
	 */
	public void setLocation(Point location) {
		if (location == null) {
			this.location = null;
		}

		else {
			this.location = location;
		}
	}

	/**
	 * get the location of the stone as point type
	 * 
	 * @return location of type point
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * get the player type owner who owns the stone at a specific location
	 * 
	 * @return owner of the stone as player type
	 */
	public Player getOwner() {
		return owner;
	}

}
