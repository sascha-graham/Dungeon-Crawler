package unsw.dungeon;

public class Treasure extends Entity {
	
	private Dungeon dungeon;
	
	public Treasure(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);
		this.dungeon = dungeon;
	}

    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - always Returns TRUE as a player has no limit on the amount of treasure they can collect 
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return true;
		
		Player player = dungeon.getPlayer();
		Inventory inv = player.getInventory();
		
		inv.setTreasure(inv.getTreasure()+1); // increment the treasure count by one
		dungeon.removeTreasure(this);
		dungeon.removeEntityFromDungeon(this);
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
