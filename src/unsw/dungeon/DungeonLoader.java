package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private GameController gameController;
    private JSONObject json;

    public DungeonLoader(GameController gameController, String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     * @throws InterruptedException 
     * @throws JSONException 
     */
    public Dungeon load() throws JSONException, InterruptedException {
        int width = json.getInt("width");
        int height = json.getInt("height");

        JSONObject jsonConditions = json.getJSONObject("goal-condition");
        Dungeon dungeon = new Dungeon(gameController, width, height, jsonConditions);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        dungeon.countStartingEnemiesTreasureSwitches();
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) throws InterruptedException {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        Entity entity = null;
        
        switch (type) {
        case "player":
            Player player = new Player(gameController, dungeon, x, y, type);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, dungeon, type);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y, dungeon, type);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y, dungeon, type);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "door":
            int doorID = json.getInt("id");
        	int locked = 1;
        	Door door = new Door(x, y, dungeon, type, doorID, locked);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
            int keyID = json.getInt("id");
        	Key key = new Key(x, y, dungeon, type, keyID);
        	onLoad(key);
        	entity = key;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y, dungeon, type);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch floorSwitch = new Switch(x, y, dungeon, type);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
        case "portal":
        	int id = json.getInt("id");
        	Portal portal = new Portal(x, y, id, dungeon, type);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "enemy":
        	boolean alive = true;
        	Enemy enemy = new Enemy(x, y, dungeon, type, alive);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, dungeon, type);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invPotion":
        	InvPotion invPotion = new InvPotion(x, y, dungeon, type);
        	onLoad(invPotion);
        	entity = invPotion;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y, dungeon, type);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "archer":
        	Archer archer = new Archer(x, y, dungeon, type);
        	onLoad(archer);
        	entity = archer;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Switch floorSwitch);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(InvPotion invPotion);
    public abstract void onLoad(Bomb bomb);
    public abstract void onLoad(Archer archer);

    public abstract void unLoad(Entity entity);
}
