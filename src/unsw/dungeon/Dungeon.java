/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

	private GameController gameController;
    private int width, height;
    private List<Entity> entities;
    private List<Portal> portals;
    private int killCount;
    private List<Treasure> treasure;
    private Exit exit;
    private Player player;
    private int numSwitchesActive = 0;
    private int numSwitches = 0;
    private int numTreasure = 0;
    private int numEnemies = 0;
    private CompoundGoal goals;
    
    public Dungeon(GameController gameController, int width, int height, JSONObject jsonConditions) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.portals = new ArrayList<>();
        this.killCount = 0;
        this.treasure = new ArrayList<>();
        this.player = null;
        this.countStartingEnemiesTreasureSwitches();     
        GoalReader goalReader = new GoalReader(jsonConditions, this);
        this.goals = goalReader.getCompoundGoal();
    }
    
    public void setGameController(GameController gameController) {
    	this.gameController = gameController;
    }

    /**
	 * @return width - int representing the width of the dungeon
	 */
    public int getWidth() {
        return width;
    }
    /**
	 * @return height - int representing the height of the dungeon
	 */
    public int getHeight() {
        return height;
    }
   
    /**
	 * @return player - object instance of a player entity
	 */
    public Player getPlayer() {
        return player;
    }

    /**
	 * @param player - associated a player object with this dungeon
	 */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
	 * @param entity - Entity object added to a list of entities 
	 */
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Portal) {
        	addPortal((Portal) entity);
        }
        if (entity instanceof Treasure) {
        	addTreasure((Treasure) entity);
        }
        if (entity instanceof Exit) {
        	exit = (Exit) entity;
        }
    }
    
    /**
  	 * Void method that is observed to move the enemy whenever this is called from Player
  	 */  
    public void triggerEnemiesMoveTowards() {

    }
    
    /**
   	 * Void method that is observed to move the enemy whenever this is called from Player
   	 */
    public void triggerEnemiesMoveAway() {

    }
 
    /**
	 * @param entity - Entity object to be removed from an entities list
	 */
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
	 * @param portal - Portal object added to a portal list and then linked with another portal
	 */
    private void addPortal(Portal portal) {
    	portals.add(portal);
    	linkPortal(portal);
    }
    
    /**
	 * @param tr - Treasure object added to a list of treasure objects
	 */
    public void addTreasure(Treasure tr) {
    	treasure.add(tr);
    }
    
    /**
	 * @param tr - Treasure object removed from a list of treasure objects
	 */   
    public void removeTreasure(Treasure tr) {
    	treasure.remove(tr);
    }
    
    /**
	 * @param portal - Portal object used to find and link with another portal 
	 */     
    private void linkPortal(Portal portal) {
    	for (Portal p : portals) {
    		if (p.getId() == portal.getId() && (p != portal)) {
    			p.setLink(portal);
    			portal.setLink(p);
    		}
    	}
    }
    
    /**
	 * Change the enemy strategy to evade the player, this is called from player class when player obtains invPotion
	 */
    public void setEvadeStrategy() {
       	for(Entity e : entities) {
    		if(e instanceof Enemy) {
    			EnemyMovementStrategy evade = new EnemyEvadeStrategy((Enemy) e);
    			e.setStrategy(evade);
    		}
    	}   	
    }
    
    /**
	 * Change the enemy strategy to pursue the player, this is called from player class when players invPotion runs out 
	 */
    public void setPursueStrategy() {
    	for(Entity e : entities) {
    		if(e instanceof Enemy) {
    			EnemyMovementStrategy pursue = new EnemyPursueStrategy((Enemy) e);
    			e.setStrategy(pursue);
    		}
    	}
    }
    
    /**
	 * @return numSwitchesActive - integer indicating how many boulders are on switches, used to determine end of a goal
	 */
    public int getNumActiveSwitches() {
		return numSwitchesActive;
	}
    
    /**
	 * @param switchCount - integer used to adjust the number of boulders on a switch
	 */
	public void setBoulderSwitchCount(int switchCount) {
		this.numSwitchesActive = switchCount;
	}
	
    /**
	 * Counts the number of enemies, treasure, and switches in the dungeon for later use in checking goal completion
	 */
	public void countStartingEnemiesTreasureSwitches() {
		for(Entity e : entities) {
			if(e instanceof Enemy) {
				numEnemies++;
			} else if(e instanceof Treasure) {
				numTreasure++;
			} else if(e instanceof Switch) {
				numSwitches++;
			}
		}
	}
	
    /**
	 * @param e - Entity object to be removed from an entity list
	 */
	public void removeEntityFromDungeon(Entity e) {
		e.x().set(getHeight()+1);
		e.y().set(getWidth()+1);
		//entities.remove(e);
	}

    /**
	 * This is constantly called from the dungeon controller on a java library timeline method to move the enemy toward the player every second 
	 */
	public void moveEnemies() {
		for(Entity e : entities) {
			if( e != null) {
				if(e.getType().equals("enemy")) {
					e.getStrategy().movementStrategy();
				}
			}

		}
	}
	
    /**
	 * @param enemy - Entity object of type enemy to be removed from an entity list once the player destroys them
	 */
	public void removeEnemy(Entity enemy) {
		enemy.x().set(getHeight()+1);
		enemy.y().set(getWidth()+1);
		entities.remove(enemy);
		killCount++;
	}
    
    /**
     * 
     * @param x and y coordinates of the direction the player intends to access
     * @return returns a list of all entities at the coordinate the player intends to access 
     */
    public List<Entity> collectEntitiesAtGivenCoordinate(int x, int y) {
    	List<Entity> entityCollection = new ArrayList<Entity>();
    	
    	for(Entity e : entities) {
    		if(e == null) break;
    		
    		if(e.getX() == x && e.getY() == y) {
    			entityCollection.add(e);
    		}
    	}
    	
    	return entityCollection;
    
    }
    
    /**
 	 * An observer is attached to this method which regularly checks if an enemy has been exploded using a bomb and if so removes them from dungeon
 	 */
    public void explosion() {
    	for (Entity entity : entities) {
    		if(entity != null) {
    			entity.checkExplosion();
    		}
    	}
    }
    /**
	 * @return nearestEnemy - finds the enemy nearest to the player and returns it.
	 */
    public Enemy locateNearestEnemy() {
    	List<Entity> enemyList = new ArrayList<Entity>();
    	
    	for(Entity e : entities) { // Add all enemies in the dungeon to an entity list
    		if(e == null) break;
    		
    		if(e instanceof Enemy) {
    			enemyList.add(e);
    		}
    	}
    	
    	Player player = getPlayer();    	
    	Enemy nearestEnemy=null;
    	
    	int diffX, diffY, minDiff=0, totalDiff; 
    	for (Entity e: enemyList) {	// Loop through all enemies and calculate which enemy in the dungeon is nearest to the player
    			diffX = e.getX()-player.getX();
    			diffX = Math.abs(diffX);
    			diffY = e.getY()-player.getY();
    			diffY = Math.abs(diffY);
    			totalDiff = diffX+diffY;
    			if (totalDiff < minDiff || minDiff == 0) {
    				nearestEnemy = (Enemy) e;
    				minDiff = totalDiff;
    			}
    	}    	
    	return nearestEnemy;
    
    }
    
    /**
	 *  checks if the particular goal has been completed 
	 */
    public void checkGoals() {
    	if (goals.isComplete()) win();
    }
    
    /**
	 *  checks if the player is at an exit
	 */
    public boolean playerAtExit() {
    	return samePosition(player, exit);
    }
    
    /**
	 * @param e1 - entity object whose coordinates will be compared with another
	 * @param e2 - entity object whose coordinates will be compared with another
	 * @return true or false depending on whether e1 and  e2 share coordinates.
	 */
    public boolean samePosition(Entity e1, Entity e2) {
    	return (e1.getX() == e2.getX() && e1.getY() == e2.getY());
    }
    
    /**
	 * @return numSwitchesActive - the number of switches that contain a boulder
	 */
    public boolean allSwitchesTriggered() {
    	return numSwitchesActive == numSwitches; // this doesn't seem to work
    }
    
    /**
	 * @return treasure - boolean indicating whether all treasure has been collected, true if yes, false otherwise
	 */
    public boolean allTreasureCollected() {
    	return treasure.isEmpty();
    }
    
    /**
  	 * @return numEnemies - boolean indicating whether all enemies have been killed, true if yes, false otherwise
  	 */
    public boolean allEnemiesKilled() {
    	return numEnemies == killCount;
    }
    
    /**
   	 * trigger showing the menu screen
   	 */
    public void win() {
    	gameController.loadWinScreen();
    }
    
    /**
     * show losing screen
     */
    public void lose() {
    	gameController.loadLoseScreen();
    }
}
