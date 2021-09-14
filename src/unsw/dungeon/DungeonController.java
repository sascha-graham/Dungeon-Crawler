package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    private Dungeon dungeon;
    private GameController gameController;

    public DungeonController(GameController gameController, Dungeon dungeon, List<ImageView> initialEntities) {
    	this.gameController = gameController;
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }
    
    /**
     * getter for dungeon
     * @return
     */
    public Dungeon getDungeon() {
    	return dungeon;
    }

    /**
	 * Initialises the state of the dungeon, laying down the ground and establishing observer and TimeLine methods.
	 */    
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");
        
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        Timeline enemyTimeline;
    	enemyTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> { dungeon.moveEnemies(); } )); // Calls the moveEnemies method in dungeon every second to automatically pursue the player

    	enemyTimeline.setCycleCount(Timeline.INDEFINITE);
    	enemyTimeline.play();
    	
    	Timeline invincTimeline;
    	invincTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> { player.observePlayerState(); } )); // Determines the players state, whether, dead, alive, or invincible, to determine the enemy strategy
    	invincTimeline.setCycleCount(Timeline.INDEFINITE);
    	invincTimeline.play();
    	
    	Timeline explosion;
    	explosion = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> { dungeon.explosion(); } )); // Observes whether an enemy has been exploded and if so shows an explosion image
    	explosion.setCycleCount(Timeline.INDEFINITE);
    	explosion.play();
    }
    
    /**
	 * Associates keyboard keys with particular player actions and calls methods to fulfill their functions. For example UP moves player Y+1 in the map
	 * W Attack with a sword in the same direction as UP
	 * B Throws a Bomb
	 */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case W:
        	if (player.getInventory().getSword() != null) { // Determines if the player possesses a sword, if so, it attacks UP for W
        			player.slayEnemy(player.getX(), player.getY()-1);
        	}
        	break;
        case A:
        	if (player.getInventory().getSword() != null) { // Determines if the player possesses a sword, if so, it attacks LEFT for A
        			player.slayEnemy(player.getX()-1, player.getY());
        	}
        	break;
        case D:
        	if (player.getInventory().getSword() != null) { // Determines if the player possesses a sword, if so, it attacks RIGHT for D
        			player.slayEnemy(player.getX()+1, player.getY());
        	}
        	break;
        case S:
        	if (player.getInventory().getSword() != null) { // Determines if the player possesses a sword, if so, it attacks DOWN for S
        			player.slayEnemy(player.getX(), player.getY()+1);
        	}
        case B:
        	if (player.getInventory().getBombCount() != 0) {
        			player.bombNearestEnemy();
        	}
        default:
            break;
        }
        dungeon.checkGoals();
    }

}

