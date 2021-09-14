package unsw.dungeon;

public class Exit extends Entity {
	
	private Dungeon dungeon;
	
	public Exit(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);
		this.dungeon = dungeon;
	}

	/**
	 * @param direction - the direction the entity is moving into this exit
	 * @return boolean - True if you can walk through exit, false otherwise. True when the goal is completed, false when goal is incomplete
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		// May move into the exit at any time but the level is not completed unless all other conditions are met
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
