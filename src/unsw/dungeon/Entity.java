package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String type;
    private ImageView image;
    private IntegerProperty change;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type, ImageView image) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
        this.image = image;
        this.change = new SimpleIntegerProperty(0);
    }

    /**
     * @return x - IntegerProperty class pertaining to the x coordinate of the player
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * @return y - IntegerProperty class pertaining to the y coordinate of the player
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * @return x - an integer pertaining to the x coordinate of the player
     */ 
    public int getX() {
        return x().get();
    }
   
    /**
     * @return y - an integer pertaining to the y coordinate of the player
     */
    public int getY() {
        return y().get();
    }
    
    /**
     * @return type - String pertaining to the type of entity, for example "wall", "treasure", "player" "door" etc
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param view - ImageView used to associate an image with a particular entity at run time
     */
    public void setImage(ImageView view) {
    	this.image = view;
    }
    
    /**
     * @return image - ImageView, an image of a particular entity
     */
    public ImageView getImage() {
    	return this.image;
    }
    
    /**
     * @param value - int used to alert an observer whenever an image needs to be changed, this value changes.
     */
    public void changeImage(int value) {
    	change.set(value);
    }
    
    /**
     * @return change - Integer Property is returned to observer to determine if a value has changed or not
     */
    public IntegerProperty image() {
        return change;
    }
       
    public abstract boolean interactWithEntity(String direction);

    public abstract EnemyMovementStrategy getStrategy();
	
	public abstract void setStrategy(EnemyMovementStrategy strategy);  
	
	public abstract int compareCoordinates(Entity entity);  

	public abstract void checkExplosion();  
	
	public abstract boolean explode();  


	
	
}
