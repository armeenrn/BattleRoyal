package model;

public class Stone {
	private Point location;
	private Player owner;
	private boolean isDead;

	/**
	 * Constructor of a Stone object
	 * 
	 * @param location location of the Stone object
	 * @param Player player the stone belongs to
	 */
	public Stone(Player owner) {
		this.owner = owner;
		isDead = false;
	}

	public Stone(Player owner, Point location) {
		this.owner = owner;
		this.location = location;
		isDead = false;
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
	
	public boolean getDead() {
		return isDead;
	}
	
	public void setDead() {
		isDead = true;
	}

}
