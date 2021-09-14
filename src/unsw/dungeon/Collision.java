package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Collision {

    private Dungeon dungeon;

    public Collision(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
   
    /**
	 * @param x - int representing the x coordinate of the player
	 * @param y - int representing the y coordinate of the player
	 * @param Direction - String representing the direction the player is moving (up, down, left, or right)
	 * @return boolean - Returns false if a player is unable to move through/into the entity in the direction the player is attempting to move
	 */     
    public boolean checkCanMoveThrough(int x, int y, String direction) {
    	List<Entity> entityCollection = dungeon.collectEntitiesAtGivenCoordinate(x, y); // Store in a list all entities in the direction the player is attempting to move
    	
    	// This essentially covers the case where a player has a key in inventory and pushes a boulder into another key, which makes it so the player can no longer push the boulder
    	// as the key is the first item to be detected. So we handle this case by checking if we ever have a key and a boulder on the same coordinate and preference boulder entity.
    	for(Entity e1 : entityCollection) {
    		for(Entity e2 : entityCollection) {
    			if(e1 instanceof Key && e2 instanceof Boulder) { 
    				Player player = dungeon.getPlayer();
    				Inventory inventory = player.getInventory();
    				if(inventory.getKey() != null) {
    					return (e2.interactWithEntity(direction));
    				}
    			}
    		}
    	}
    	
    	if(entityCollection.size() == 0)return true; // Return true if the list is empty, as there are no entities at such coordinates, ergo no restrictions
    	
    	for(Entity e : entityCollection) { // While there are entities in the list, determine what they are, and if we can move through them
    		
    		String type = e.getType();
    		
    		switch (type) {
    		
    		case "wall":
    			return (e.interactWithEntity(direction));
    		
    		case "boulder":
    			return (e.interactWithEntity(direction));
    		
    		case "door":
    			return (e.interactWithEntity(direction));
    		
    		case "key":	
    			return (e.interactWithEntity(direction));
    		
    		case "treasure":
    			return (e.interactWithEntity(direction));
    		
    		case "invPotion":
    			return (e.interactWithEntity(direction));
    		
    		case "sword":
    			return (e.interactWithEntity(direction));
    		
    		case "switch":
    			return (e.interactWithEntity(direction));
    		
    		case "exit":
    			return (e.interactWithEntity(direction));
    		
    		case "enemy":
    			return (e.interactWithEntity(direction));
    		
    		case "portal":
    			return (e.interactWithEntity(direction));
    		
    		case "bomb":    			
    			return (e.interactWithEntity(direction));
    		
    		}
    	
    	}
    	
    	return true;
    }
	

}
