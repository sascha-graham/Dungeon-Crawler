package unsw.dungeon;

public class Enemy extends Entity {
	
	private Dungeon dungeon;
	private EnemyMovementStrategy enemyStrategy;
	private boolean enemyState;
	private boolean explode = false;
	private int blastTime;
	
	public Enemy(int x, int y, Dungeon dungeon, String type, boolean alive) {
		super(x, y, type, null);
		this.dungeon = dungeon;
		this.enemyStrategy = new EnemyPursueStrategy(this);
		this.enemyState = alive;
		this.explode = false;
		this.blastTime = 2;
	}
	
	public Player getPlayer() {
		return dungeon.getPlayer();
	}
	
	/**
	 * Sets the enemy's x coordinate
	 * @param dirX - the direction the enemy is moving
	 */
    public void setX(int dirX) {
    	if (getX()-dirX < dungeon.getWidth() &&  getX()-dirX > 0)
    		x().set( (getX()-dirX));
    	if (dungeon.samePosition(this, dungeon.getPlayer())) {
    		dungeon.lose();
    	}
    }
    
    /**
	 * Sets the enemy's y coordinate
	 * @param dirY - the direction the enemy is moving
	 */
    public void setY(int dirY) {
    	if (getY()-dirY < dungeon.getHeight() &&  getY()-dirY > 0)
    		y().set((getY()-dirY));
    	if (dungeon.samePosition(this, dungeon.getPlayer())) {
    		dungeon.lose();
    	}
    }

	/**
	 * @param direction - String direction of the entity that is moving toward this enemy.
	 * @return boolean - True if player can interact with enemy, false otherwise. 
	 * this will be true when the player is invincible
	 */
	@Override
	public boolean interactWithEntity(String direction) {

		if(direction.equals("enemy")) return false;

		Player player = dungeon.getPlayer();
		Inventory inventory = player.getInventory();
		
		if(inventory.getInvincibility() > 0) {
			dungeon.removeEntityFromDungeon(this);
			dungeon.removeEnemy(this);
			return true;
		}
		
		return false;
	}

	/**
	 * @return enemyStrategy - EnemyMovementStrategy object that is either pursue or evade, depending on the players state. This
	 */
	@Override
	public EnemyMovementStrategy getStrategy() {
		return enemyStrategy;
	}

	/**
	 * @param strategy - EnemyMovementStrategy object that sets this enemy to either pursue or evade, depending on the players state.
	 */
	@Override
	public void setStrategy(EnemyMovementStrategy strategy) {
		this.enemyStrategy = strategy;
		
	}

	/**
	 * This is called after sufficient time of an Enemy being exploded, to change the image to that of an explosion 
	 */
	@Override
	public void checkExplosion() {
		if(explode == true && blastTime > 0) {
			blastTime--;
			return;
		} else if(explode == true && blastTime == 0){	
			dungeon.removeEntityFromDungeon(this);
			dungeon.removeEnemy(this);
		}
	}
	
	/**
	 * @return boolean - True if the enemy has been exploded, false otherwise
	 */
	@Override
	public boolean explode() {
		explode = true;	
		return true;
	}
	
	@Override
	public int compareCoordinates(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
