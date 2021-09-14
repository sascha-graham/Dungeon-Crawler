package unsw.dungeon;

public class Wall extends Entity {
	
	private Dungeon dungeon;
	
    public Wall(int x, int y, Dungeon dungeon, String type) {
        super(x, y, type, null);
        this.dungeon = dungeon;
    }

    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - False always as can never walk through a wall
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		return false;
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
