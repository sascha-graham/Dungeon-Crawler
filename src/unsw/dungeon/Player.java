package unsw.dungeon;

import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private State state;
    private Sword sword;
    private InvPotion invPotion;
    private Collision collision;
    private Inventory inventory;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     * @throws InterruptedException 
     */
    public Player(GameController gameController, Dungeon dungeon, int x, int y, String type) throws InterruptedException {
        super(x, y, type, null);
        this.dungeon = dungeon;
    	this.collision = new Collision(dungeon); 
    	this.inventory = new Inventory(gameController);
        this.state = new DefaultState(this);
        
    }

    /**
     * Attempt to move the player up one cell in the dungeon by checking
     * what entities exist at that coordinate and determining if they
     * are able to be walked through or not.  
     */
    public void moveUp() {
        if (getY() > 0)
        	if(collision.checkCanMoveThrough(getX(), getY()-1, "UP")) 
                y().set(getY() - 1);
    			state.onMove();
    }

    /**
     * Attempt to move the player down one cell in the dungeon by checking
     * what entities exist at that coordinate and determining if they
     * are able to be walked through or not.  
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
        	if(collision.checkCanMoveThrough(getX(), getY()+1, "DOWN"))
        		y().set(getY() + 1);
        		state.onMove();
    }

    /**
     * Attempt to move the player left one cell in the dungeon by checking
     * what entities exist at that coordinate and determining if they
     * are able to be walked through or not.  
     */
    public void moveLeft() {
        if (getX() > 0)
        	if(collision.checkCanMoveThrough(getX()-1, getY(), "LEFT"))
        		x().set(getX() - 1);
        		state.onMove();
    }

    /**
     * Attempt to move the player right one cell in the dungeon by checking
     * what entities exist at that coordinate and determining if they
     * are able to be walked through or not.  
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
        	if(collision.checkCanMoveThrough(getX()+1, getY(), "RIGHT"))
        		x().set(getX() + 1);
        		state.onMove();
    }
    
    /**
     * @return dungeon - object of type dungeon, pointing to the dungeon.
     */
    public Dungeon getDungeon() {
    	return dungeon;
    }
    
    /**
     * @return state - State object pertaining to the state of the player, dead, alive, or invincible
     */
    public State getState() {
    	return state;
    }
    
    /**
     * @param state - State object used to change and reflect the state of the player, dead, alive, or invinc
     */
    public void changeState(State state) {
    	this.state = state;
    }
    
    /**
     * @return sword - true if we have a sword, false if we dont
     */
    private boolean hasSword() {
    	return sword != null;
    }
    
    /**
     * @return inventory - Inventory object
     */
    public Inventory getInventory() {
    	return inventory;
    }
    
    /**
     * @return collision - collision object
     */
    public Collision getCollision() {
    	return collision;
    }
    
    /**
     * changes the enemy strategy to evade once we collect an invi potion
     */
    public void Invincible() {
    	dungeon.setEvadeStrategy();
    }
    
    /**
     * changes the enemy strategy to pursue once potion timer reaches 0
     */
    public void regularHuman() {
    	dungeon.setPursueStrategy();
    }
    
    /**
     * Triggers the enemy to move toward the player every second, called by timeline method in dungeon controller
     */
    public void triggerEnemiesMoveTowards() {
    	dungeon.triggerEnemiesMoveTowards();
    }
    
    /**
     * Triggers the enemy to move away from the player every second, called by timeline method in dungeon controller
     */
    public void triggerEnemiesMoveAway() {
    	dungeon.triggerEnemiesMoveAway();
    }
    
    /**
     * @param x - integer representing the x coordinate of enemy 
     * @param y - integer representing the y coordinate of enemy 
     */
    public void slayEnemy(int x, int y) {
    	List<Entity> entityCollection = dungeon.collectEntitiesAtGivenCoordinate(x, y);
    	
    	for(Entity e : entityCollection) {
        	if(e.getType().equals("enemy")) {
        		inventory.reduceSwordDurability();        		
        		dungeon.removeEnemy(e);
        	}
    	}

    }
    
    /**
     * Changes the exploded enemy image from enemy to explosion
     */
    public void bombNearestEnemy() {
    	Enemy nearestEnemy = dungeon.locateNearestEnemy();
    	if(nearestEnemy == null) return;
    		
    	nearestEnemy.changeImage(1);        		
    	nearestEnemy.explode();
    	inventory.decreaseBombCount();
    	
    }
    
    /**
     * Observers what state the player is in, called from dungeon controller
     */
    public void observePlayerState() {
    	if(inventory.getInvincibility() == 0) {
    		regularHuman();
    	} else {
    		inventory.decrementInvincibility();
    	}
    }
    
    /**
     * Checks whether all the goals in the dungeon are complete
     */
    public void checkGoals() {
    	dungeon.checkGoals();
    }

    /**
     * @param direction - String representing the direction of the entity interacting with this player
     * @return boolean - True if entity can interact, false otherwise
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
