package unsw.dungeon;

public class Archer extends Entity {
	
	private Dungeon dungeon;
	
	public Archer(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);

	}
	
    /**
	 * @return dungeon.getPlayer - Returns a Player object
	 */ 
	public Player getPlayer() {
		return dungeon.getPlayer();
	}
	
	/**
	 * Sets the archers X coordinate
	 * @param dirX - the direction the enemy is moving
	 */
    public void setX(int dirX) {
    	if (getX()-dirX < dungeon.getWidth() &&  getX()-dirX > 0)
    		x().set( (getX()-dirX));
    }
    
    /**
	 * Sets the archers Y coordinate
	 * @param dirY - the direction the enemy is moving
	 */
    public void setY(int dirY) {
    	if (getY()-dirY < dungeon.getHeight() &&  getY()-dirY > 0)
    		y().set((getY()-dirY));
    }
    
    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns false as a player is unable to move into an enemy
	 */ 
	@Override
	public boolean interactWithEntity(String direction) {

		return false;
	}

    /**
	 * 
	 * @param entity - object of type Entity whose coordinates we wish to compare against this specific entity
	 * @return int - Returns 0 if the coordinates are not equal, 1 otherwise. 
	 */ 
	@Override
	public int compareCoordinates(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EnemyMovementStrategy getStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStrategy(EnemyMovementStrategy strategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkExplosion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		return false;
	}


}
