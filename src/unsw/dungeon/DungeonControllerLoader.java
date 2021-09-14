package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private GameController gameController;
    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image treasureImage;
    private Image openDoorImage;
    private Image closedDoorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image swordImage;
    private Image invPotionImage;
    private Image gameOverImage;
    private Image bombImage;
    private Image explosion;
    private Image archerImage;

    public DungeonControllerLoader(GameController gameController, String filename)
            throws FileNotFoundException {
        super(gameController, filename);

        entities = new ArrayList<>();
        
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        treasureImage = new Image("/gold_pile.png");
        openDoorImage = new Image("/open_door.png");
        closedDoorImage = new Image("/closed_door.png");
        keyImage = new Image("/key.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        portalImage = new Image("/portal.png");
        enemyImage = new Image("hound.png");
        swordImage = new Image("greatsword_1_new.png");
        invPotionImage = new Image("brilliant_blue_new.png");
        gameOverImage = new Image("game_over.png");
        bombImage = new Image("bomb.png");
        explosion = new Image("explosion.png");
        archerImage = new Image("archer.png");

    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        startingImage(player, view);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        startingImage(wall, view);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        startingImage(exit, view);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	startingImage(treasure, view);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(closedDoorImage);
    	startingImage(door, view);
    	addEntity(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	startingImage(key, view);
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	startingImage(boulder, view);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch floorSwitch) {
    	ImageView view = new ImageView(switchImage);
    	startingImage(floorSwitch, view);
    	addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
    	startingImage(portal, view);
    	addEntity(portal, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	startingImage(enemy, view);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	startingImage(sword, view);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(InvPotion invPotion) {
    	ImageView view = new ImageView(invPotionImage);
    	startingImage(invPotion, view);
    	addEntity(invPotion, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	startingImage(bomb, view);
    	addEntity(bomb, view);
    }
    
    @Override
    public void onLoad(Archer archer) {
    	ImageView view = new ImageView(archerImage);
    	startingImage(archer, view);
    	addEntity(archer, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    private void startingImage(Entity entity, ImageView view) {
        entity.setImage(view);
    }
    
    public void unLoad(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {    			 
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        
        entity.image().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(entity instanceof Door) {
        			ImageView view = entity.getImage();              	
                    view.setImage(openDoorImage);
                } else if(entity instanceof Enemy && entity.explode()) {
                	ImageView view = entity.getImage();              	
                	view.setImage(explosion);
                }
            }
        });
    }
    

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     * @throws InterruptedException 
     * @throws JSONException 
     */
    public DungeonController loadController() throws FileNotFoundException, JSONException, InterruptedException {
        return new DungeonController(gameController, load(), entities);
    }


}
