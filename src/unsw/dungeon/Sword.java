package unsw.dungeon;

public class Sword extends Entity {
	
	private static final int maxDurability = 5;
	private int uses;
	private Dungeon dungeon;
	
	public Sword(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);
		uses = maxDurability;
		this.dungeon = dungeon;
	}

    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns true if player only has no sword, otherwise false
	 */ 
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return true;
		
		Player player = dungeon.getPlayer();
		Inventory inv = player.getInventory();
		
		if(inv.getSword() == null) {
			uses = maxDurability;
			inv.setSword(this); // Set this sword to the one in the inventory if we dont already possess a sword
		}
		dungeon.removeEntityFromDungeon(this);
	return true;
	}
	
    /**
	 * @return uses - Integer representing number of attacks left in the sword, starts at 5, ends at 0
	 */ 
	public int getDurability() {
		return uses;
	}
	
	
    /**
	 * reduce the number of attacks left in the sword by 1 each time
	 */
	public void reduceDurability() {
		this.uses--;
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
