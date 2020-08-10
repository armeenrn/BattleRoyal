package model;

/**
 * The class can be accessed to view details about any and all stones of any
 * player.
 * 
 * @author srish
 *
 */
public class Stone {
	private Point location;
	private Player owner;
	private boolean isDead;

	/**
	 * Constructor of a Stone object
	 * 
	 * @param location location of the Stone object
	 * @param Player   player the stone belongs to
	 */
	public Stone(Player owner) {
		this.owner = owner;
		isDead = false;
	}

	/**
	 * Constructor of Stone object
	 * 
	 * @param owner    owner of the stone
	 * @param location location of the stone.
	 */
	public Stone(Player owner, Point location) {
		this.owner = owner;
		this.location = location;
		isDead = false;
	}

	/**
	 * Setter method to set location of where the stone will be placed.
	 * 
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * get the location of the stone as type point
	 * 
	 * @return location of type point
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Getter method to get player who the stone belongs to
	 * 
	 * @return owner of the stone as player type
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Getter method for all stones that are dead (removed from the game by
	 * opponent)
	 * 
	 * @return boolean statement of true or false for a stone is dead
	 */
	public boolean getDead() {
		return isDead;
	}

	/**
	 * Setter method for to set a stone as dead if removed from the game
	 */
	public void setDead() {
		isDead = true;
	}

}
