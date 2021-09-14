package unsw.dungeon;

public class Switch extends Entity {
	
	private Dungeon dungeon;
	private boolean triggered;
	
	public Switch(int x, int y, Dungeon dungeon, String type) {
        super(x, y, type, null);
        
        this.dungeon = dungeon;
        this.triggered = false;
    }

    /**
	 * Sets this trigger to true if there is a boulder on it
	 */
	public void trigger() {
		triggered = true;
	}
	

    /**
	 * sets this trigger to false if there is no boulder on it, either initially or when one is moved off
	 */
	public void untrigger() {
		triggered = false;
	}
	

    /**
	 * @param triggered - boolean true if there is a boulder on this trigger, false otherwise
	 */
	public boolean isTriggered() {
		return triggered;
	}

    /**
	 * @param direction - String representing direction of entity walking onto this
	 * @return boolean - Always true as we can always walk on a trigger
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		
		return true;
	}

	@Override
	public EnemyMovementStrategy getStrategy() {
		return null;
	}

	@Override
	public void setStrategy(EnemyMovementStrategy strategy) {
		
	}

	@Override
	public int compareCoordinates(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
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
