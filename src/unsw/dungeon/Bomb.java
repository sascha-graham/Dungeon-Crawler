package unsw.dungeon;

public class Bomb extends Entity {
	
	private Dungeon dungeon;
	
	public Bomb(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);	
		this.dungeon = dungeon;

	}

    
    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns false if the player cannot move into this entity, true otherwise
	 */ 
	@Override
	public boolean interactWithEntity(String direction) {
				
		if(direction.equals("enemy")) return true;
		
		Player player = dungeon.getPlayer();
		player.getInventory().increaseBombCount();

		dungeon.removeEntityFromDungeon(this);
		return true;
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
