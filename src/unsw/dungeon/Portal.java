package unsw.dungeon;

public class Portal extends Entity {
	
	private Portal link;
	private int id;
	private Dungeon dungeon;
	
	public Portal(int x, int y, int id, Dungeon dungeon, String type) {
		super(x, y, type, null);
		this.id = id;
		this.dungeon = dungeon;
	}
	

    /**
	 * @return link - Portal object this portal is associated with
	 */
	public Portal getLink() {
		return link;
	}
	
    /**
	 * @param portal - Portal object to link this portal with for bidirectional movement
	 */
	public void setLink(Portal portal) {
		link = portal;
	}
	
    /**
	 * @return id - int representing the id of this portal
	 */
	public int getId() {
		return id;
	}

    /**
	 * @param direction - String representing the direction the entity is moving toward this from
	 * @return boolean - moves the player to the coordinate of second portal and returns false to move through so we dont execute other methods in collision
	 */
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return false;
		
		Player player = dungeon.getPlayer();
		
		player.x().set(link.getX());
		player.y().set(link.getY());

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
