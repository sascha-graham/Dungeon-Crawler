package unsw.dungeon;

public class Door extends Entity {
	
	private int id;
	private int doorAccess;
	private Dungeon dungeon;
	
	public Door(int x, int y, Dungeon dungeon, String type, int id, int locked) {
		super(x, y, type, null);
		this.doorAccess = locked; // locked = 1
		this.id = id;
		this.dungeon = dungeon;
	}
	
    /**
	 * Sets the doorAccess variable to unlocked (0) once the player has walked into this door with appropriate key, and permits continous use
	 */ 	
	public void unlockDoor() {
		int unlocked = 0;
		this.doorAccess = unlocked;
	}
	
    /**
	 * @return - integer representing whether the door is open or closed. locked = 1. unlocked = 0;
	 */
	public int getDoorAccess() {
		return doorAccess;
	}

    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns true as a player is permitted to collect a key
	 */ 
	@Override
	public boolean interactWithEntity(String direction) {
		int unlocked = 0;
		if(doorAccess == unlocked) return true; // If door has previously been accessed by the player, return true to indicate the player can walk through
		
		Player player = dungeon.getPlayer();
		Inventory inventory = player.getInventory();
		
		if(inventory.getKey() == null) { // If the player does not possess a key, return false to indicate the player can not walk through
			return false;
		}
		
		if(inventory.getKey().getKeyID() == this.id) { // If the player possess a key and it matches this doors ID, unlock door and return true
			unlockDoor();
			int unlock = 1;
			changeImage(unlock); // change image of door to open door
			inventory.setKey(null);
			return true;
		}
		
		return false; 
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
	public int compareCoordinates(Entity player) {
		if(player.getX() == this.getX() && player.getY() == this.getY()) {
			return 1;
		}
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
