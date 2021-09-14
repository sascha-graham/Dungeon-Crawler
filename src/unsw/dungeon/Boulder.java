package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity {
	
	private Dungeon dungeon;
	private boolean switchActive = false;
	
	public Boulder(int x, int y, Dungeon dungeon, String type) {
		super(x, y, type, null);
		this.dungeon = dungeon;
	}
	
	/**
	 * Moves the boulder up 1 cell
	 */
	public void moveBoulderUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }
	
	/**
	 * Moves the boulder down 1 cell
	 */
    public void moveBoulderDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    /**
	 * Moves the boulder left 1 cell
	 */
    public void moveBoulderLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    /**
	 * Moves the boulder right 1 cell
	 */
    public void moveBoulderRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
	 * @param activate - boolean variable changing the switchActive variable to true as a boulder is now on the switch
	 */     
    public void activateSwitch (boolean activate) {
    	this.switchActive = activate;
    }
    
    
    /**
	 * @return switchActive - a boolean variable initially set to false, returns true if a boulder is on the switch
	 */ 
    public boolean switchStatus () {
    	return switchActive;
    }

    
    /**
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns false if the player cannot move into this entity, true otherwise
	 */ 
	@Override
	public boolean interactWithEntity(String direction) {
		
		if(direction.equals("enemy")) return false;
		
		Player player = dungeon.getPlayer();
		Collision collision = player.getCollision();
		List<Entity> entities = null;		
		int boulderOnSwitchFlag=0;
    	
		switch (direction) {
    	case "UP": 
    		entities = dungeon.collectEntitiesAtGivenCoordinate(getX(),getY()-1); // store in a list all entities at the coordinates the player is attempting to move the boulder and determine if we can move through
    		break;
    	case "DOWN": 
    		entities = dungeon.collectEntitiesAtGivenCoordinate(getX(),getY()+1); // store in a list all entities at the coordinates the player is attempting to move the boulder and determine if we can move through
    		break;
    	case "LEFT": 
    		entities = dungeon.collectEntitiesAtGivenCoordinate(getX()-1,getY()); // store in a list all entities at the coordinates the player is attempting to move the boulder and determine if we can move through
    		break;
    	case "RIGHT": 
    		entities = dungeon.collectEntitiesAtGivenCoordinate(getX()+1,getY()); // store in a list all entities at the coordinates the player is attempting to move the boulder and determine if we can move through
    		break;	
    	}
		
		if(entities == null) return false; // If entities == null its because the direction we pass into this function as an empty string, which indicates that anything other than boulder is moving.

    	for (Entity e: entities) {
    		if(e.getType().equals("boulder")) {
    			return false;
    		} else if (!collision.checkCanMoveThrough(e.getX(), e.getY(), direction)) { // If we are trying to move a boulder into an entity that is solid, return false
    			return false;
    		} else if (e instanceof Switch) { // If the boulder has been moved onto a switch, set activateSwitch to true
    			this.activateSwitch (true);
    			dungeon.setBoulderSwitchCount(dungeon.getNumActiveSwitches()+1);
    			boulderOnSwitchFlag=1;
    		}
    	}
    	
    	if (boulderOnSwitchFlag==0 && dungeon.getNumActiveSwitches()!=0 && this.switchStatus()) { // Return activateSwitch to false if we move the boulder off the switch
    		dungeon.setBoulderSwitchCount(dungeon.getNumActiveSwitches()-1);
    		this.activateSwitch(false);
    	}
    		switch (direction) { // If we make it here, we can permissibly move the boulder in the direction passed into the function 
        	case "UP": 
        		this.moveBoulderUp(); // Moves boulder up
        		break;
        	case "DOWN": 
        		this.moveBoulderDown(); // Moves boulder down
        		break;
        	case "LEFT": 
        		this.moveBoulderLeft(); // Moves boulder left
        		break;
        	case "RIGHT": 
        		this.moveBoulderRight(); // Moves boulder right
        		break;	
        	}
    	
    		//dungeon.getPlayer().checkGoal();
    		
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
