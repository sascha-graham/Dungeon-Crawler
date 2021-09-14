package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	
	private List<String> dungeons;
	private Stage primaryStage;
	private GridPane inventoryPane;
	private Inventory inventory;
	private GameController gameController;
	private Dungeon dungeon;

    @Override
    public void start(Stage primaryStage) throws IOException {
    	dungeons = new ArrayList<String>();
    	dungeons.add("advanced.json");
    	dungeons.add("boulders.json");
    	dungeons.add("maze.json");
    	dungeons.add("marking.json");
    	
    	this.primaryStage = primaryStage;
    	GameController gameController = new GameController(this, dungeons);
    	this.gameController = gameController;
        primaryStage.setTitle("DungeonMenu");
        
        displayMainMenu(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * show the player a main menu from which to choose a dungeon
     * @param primaryStage
     */
    public void displayMainMenu(Stage primaryStage) {
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
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(gameController, dungeonFile);

    	DungeonController controller = dungeonLoader.loadController();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
    	loader.setController(controller);
    	GridPane root = new GridPane();
    	GridPane map = loader.load();
    	root.add(map, 0, 0);
    	
    	GridPane inventoryPane = new GridPane();
    	this.inventoryPane = inventoryPane;
    	inventoryPane.setId("inv");
    	Label inventoryTitle = new Label("Inventory");
    	inventoryPane.add(inventoryTitle, 0, 0);
    	
    	root.add(inventoryPane, 1, 0);
    	
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(DungeonApplication.class.getResource("inventory.css").toExternalForm());
    	
    	map.requestFocus(); // this must occur after the scene has been built
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	this.dungeon = controller.getDungeon();
    	dungeon.getPlayer().getInventory().setGameController(gameController);
    	gameController.setInventory(dungeon.getPlayer().getInventory());
    	dungeon.setGameController(gameController);
    }
    
    /**
     * shows a congratulation screen to player for completing a level
     */
    public void loadWinScreen() {
    	primaryStage.setTitle("Dungeon Explorer");
    	
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    	
    	Text scenetitle = new Text("You Win! Play again?");
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
     * shows a screen to player when killed by an enemy
     * option to return to menu
     */
    public void loadLoseScreen() {
    	primaryStage.setTitle("Dungeon Explorer");
    	
    	Button button = new Button("Back to Main Menu");
    	button.setId("menu-btn");
    	button.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	        gameController.displayMainMenu(primaryStage);
    	    }
    	});

    	HBox hbox = new HBox();
    	hbox.getChildren().addAll(button); // button will be left of text

    	ImageView image = new ImageView("game_over.png");
    	image.setFitHeight(600);
    	image.setFitWidth(960);

    	StackPane stackPane = new StackPane();
    	stackPane.getChildren().addAll(image, hbox); // hbox with button and text on top of image view

    	HBox root = new HBox();
    	root.getChildren().add(stackPane);
    	
    	Scene scene = new Scene(root, 960, 600);
    	
    	primaryStage.setScene(scene);
        scene.getStylesheets().add(DungeonApplication.class.getResource("lose.css").toExternalForm());
        
        primaryStage.show();
    }
    
    /**
     * maintain current inventory in application
     * @param inventory
     */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * show the right amount of treasure in the inventory
	 */
	public void updateTreasure() {
		int numTreasure = gameController.getInventory().getTreasure();
		if (numTreasure == 1) {
			ImageView view = new ImageView(new Image("gold_pile.png"));
			view.setId("treasure-view");
	    	inventoryPane.add(view, 0, 2);
	    	Label label = new Label(Integer.toString(1));
	    	label.setId("treasure-label");
	    	inventoryPane.add(label, 1, 2);
		} else {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("treasure-label".equals(node.getId())) {
					Label label = (Label) node;
					label.setText(Integer.toString(numTreasure));
				}
			}
		}
	}
	
	/**
	 * show a key in players inventory
	 */
	public void updateKey() {
		Key key = gameController.getInventory().getKey();
		if (key == null) {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("key-view".equals(node.getId())) {
					ImageView view = (ImageView) node;
					view.setImage(null);
				}
			}
		} else {
			ImageView view = new ImageView(new Image("key.png"));
			view.setId("key-view");
			inventoryPane.add(view, 0, 3);
		}
	}
	
	/**
	 * show remaining sword uses
	 */
	public void updateSword() {
		Sword sword = gameController.getInventory().getSword();
		if (sword == null) {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("sword-view".equals(node.getId())) {
					ImageView view = (ImageView) node;
					view.setImage(null);
				}
				if ("sword-label".equals(node.getId())) {
					Label label = (Label) node;
					label.setText(null);
				}
			}
		} else if (sword.getDurability() == 5) {
			ImageView view = new ImageView(new Image("greatsword_1_new.png"));
			view.setId("sword-view");
	    	inventoryPane.add(view, 0, 1);
	    	Label label = new Label(Integer.toString(5));
	    	label.setId("sword-label");
	    	inventoryPane.add(label, 1, 1);
		} else {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("sword-label".equals(node.getId())) {
					Label label = (Label) node;
					label.setText(Integer.toString(sword.getDurability()));
				}
			}
		}
	}
	
	/**
	 * show remaining invincibility time
	 */
	public void updateInvinc() {
		int invTime = gameController.getInventory().getInvincibility();
		if (invTime == 0) {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("inv-view".equals(node.getId())) {
					ImageView view = (ImageView) node;
					view.setImage(null);
				}
				if ("inv-label".equals(node.getId())) {
					Label label = (Label) node;
					label.setText(null);
				}
			}
		} else {
			List<Node> list = inventoryPane.getChildren();
			boolean found = false;
			for (Node node : list) {
				if ("inv-label".equals(node.getId())) {
					found = true;
					Label label = (Label) node;
					label.setText(Integer.toString(invTime));
				}
			}
			
			// if no icon then make a new one
			if (found == false) {
				ImageView view = new ImageView(new Image("brilliant_blue_new.png"));
				view.setId("inv-view");
		    	inventoryPane.add(view, 0, 4);
		    	Label label = new Label(Integer.toString(invTime));
		    	label.setId("inv-label");
		    	inventoryPane.add(label, 1, 4);
			}
		}
	}
	
	/**
	 * show player how many bombs are in the inventory
	 */
	public void updateBombs() {
		int numBombs = gameController.getInventory().getBombCount();
		if (numBombs == 0) {
			List<Node> list = inventoryPane.getChildren();
			for (Node node : list) {
				if ("bomb-view".equals(node.getId())) {
					ImageView view = (ImageView) node;
					view.setImage(null);
				}
				if ("bomb-label".equals(node.getId())) {
					Label label = (Label) node;
					label.setText(null);
				}
			}
		} else {
			ImageView view = new ImageView(new Image("bomb.png"));
			view.setId("bomb-view");
	    	inventoryPane.add(view, 0, 5);
	    	Label label = new Label(Integer.toString(numBombs));
	    	label.setId("bomb-label");
	    	inventoryPane.add(label, 1, 5);
		}
	}
	
	/**
	 * get list of dungeon files
	 * @return
	 */
	public List<String> getDungeons() {
		return dungeons;
	}
}
