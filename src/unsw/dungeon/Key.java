package unsw.dungeon;

public class Key extends Entity {
	
	private Dungeon dungeon;
	private int id;
	
	public Key(int x, int y, Dungeon dungeon, String type, int id) {
		super(x, y, type, null);
		this.id = id;
		this.dungeon = dungeon;
	}
	
	public int getKeyID() {
		return id;
	}


    /**
     * @param direction - String indicating the direction of the entity interacting with this is moving 
	 * @return boolean - true if the player does not possess a key, false otherwise
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return true;
		
		Player player = dungeon.getPlayer();
		
		if(player.getInventory().getKey() == null) { // Check player does not already possess a key
			player.getInventory().setKey(this);
			dungeon.removeEntityFromDungeon(this); // remove key from dungeon

		}
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
