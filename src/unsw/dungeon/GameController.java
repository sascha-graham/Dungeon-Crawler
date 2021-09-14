package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameController {
	
	private List<String> dungeons;
	private Stage primaryStage;
	private GridPane inventoryPane;
	private Inventory inventory;
	private DungeonApplication dungeonApplication;
	
	/**
	 * a controller for the user interface
	 * @param dungeons
	 */
	public GameController(DungeonApplication dungeonApplication, List<String> dungeons) {
		this.dungeons = dungeons;
		this.inventory = null;
		this.dungeonApplication = dungeonApplication;
	}
	
	/**
     * show the player a main menu from which to choose a dungeon
     * @param primaryStage
     */
    public void displayMainMenu(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	primaryStage.setTitle("Dungeon Explorer");
    	
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text scenetitle = new Text("DUNGEON EXPLORER");
    	scenetitle.setId("title-text");
    	grid.add(scenetitle, 0, 0, 2, 1);

        MenuBar menuBar = new MenuBar();
        
        Menu dungeonMenu = new Menu("Select dungeon file");
        dungeonMenu.setId("menu-title");
        menuBar.getMenus().add(dungeonMenu);
        
        for (String dungeon : dungeons) {
        	MenuItem dungeonOption = new MenuItem(dungeon);
        	dungeonOption.setOnAction(e -> {
        		try {
					loadDungeon(primaryStage, dungeonOption.getText());
				} catch (IOException e1) {
					System.out.println("Error: couldn't load dungeon");
					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
        	});
            dungeonMenu.getItems().add(dungeonOption);
        }
        
        grid.add(menuBar, 0, 5);

        Scene scene = new Scene(grid, 960, 600);
        
        primaryStage.setScene(scene);
        scene.getStylesheets().add(DungeonApplication.class.getResource("main_menu.css").toExternalForm());
        
        primaryStage.show();
    }
    
    /**
     * load a particular dungeon to be played
     * @param dungeonFile
     * @throws IOException 
     * @throws InterruptedException 
     * @throws JSONException 
     */
    public void loadDungeon(Stage primaryStage, String dungeonFile) throws IOException, JSONException, InterruptedException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(this, dungeonFile);

    	DungeonController controller = dungeonLoader.loadController();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
    	loader.setController(controller);
    	GridPane root = new GridPane();
    	GridPane map = loader.load();
    	root.add(map, 0, 0);
    	
    	this.inventoryPane = new GridPane();
    	
    	inventoryPane.setId("inv");
    	Label inventoryTitle = new Label("Inventory");
    	inventoryPane.add(inventoryTitle, 0, 0);
    	
    	ImageView view = new ImageView(new Image("greatsword_1_new.png"));
    	inventoryPane.add(view, 0, 1);
    	inventoryPane.add(new Label("3 uses"), 1, 1);
    	root.add(inventoryPane, 1, 0);
    	
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(DungeonApplication.class.getResource("inventory.css").toExternalForm());
    	
    	map.requestFocus(); // this must occur after the scene has been built
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
    }
    
    /**
     * shows a congratulation screen to player for completing a level
     */
    public void loadWinScreen() {
    	dungeonApplication.loadWinScreen();
    }
    
    /**
     * shows a premade screen to the player when killed by an enemy
     */
    public void loadLoseScreen() {
    	dungeonApplication.loadLoseScreen();
    }
    
    /**
     * maintain current inventory in application
     * @param inventory
     */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * getter for the players inventory
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public void updateTreasure() {
		dungeonApplication.updateTreasure();
	}
	
	public void updateKey() {
		dungeonApplication.updateKey();
	}
	
	public void updateSword() {
		dungeonApplication.updateSword();
	}
	
	public void updateInvinc() {
		dungeonApplication.updateInvinc();
	}
	
	public void updateBombs() {
		dungeonApplication.updateBombs();
	}

}
