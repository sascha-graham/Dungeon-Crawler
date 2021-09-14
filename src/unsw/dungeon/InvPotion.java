package unsw.dungeon;

public class InvPotion extends Entity {
	
	private Dungeon dungeon;
	
	public InvPotion(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);	
		this.dungeon = dungeon;
	}



    /**
     * @param direction - String indicating the direction of the entity interacting with this is moving 
	 * @return boolean - always true as we can always collect inviPotion
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return true;
		
		Player player = dungeon.getPlayer();
		Inventory inventory = player.getInventory();
		
		inventory.addInvincibility(); // Create an invinsible counter that is decreased by 1 value each second 
		dungeon.setEvadeStrategy(); // Change to invinisble as a state pattern he and then we can call strategy pattern based on state of player
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
