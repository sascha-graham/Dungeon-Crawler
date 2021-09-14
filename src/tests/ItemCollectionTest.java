package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.json.JSONObject;
//import org.junit.*;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.InvPotion;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;

class ItemCollectionTest {
	Dungeon dungeonTester = new Dungeon(20, 20, null);

	/* Check that our inventory does not contain a key,
	 * Walk into a key and assert that a key is now in our inventory
	 * 
	 */
	
	@Test
	public void collectKey() {
		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		Dungeon dungeonTester = new Dungeon(4,4, conditions);	
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);
		
		Key key = new Key(1, 0, dungeonTester, "key", 0);
		dungeonTester.addEntity(key);

		assertNull(player.getInventory().getKey());
		
		player.moveRight();
		
		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up
		
		assertNotNull(key);
	
	}
	
	@Test
	public void collectTreasure() {
		Dungeon dungeonTester = new Dungeon(4,4, null);	
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		
		Treasure treasure = new Treasure(1, 0, dungeonTester, "treasure");
		dungeonTester.addEntity(treasure);
		
		assertEquals(0, player.getInventory().getTreasure());

		player.moveRight();
		
		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		assertEquals(1, player.getInventory().getTreasure());
			
	}
	
	@Test
	public void collectSword() {
		Dungeon dungeonTester = new Dungeon(4,4, null);	
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		Sword sword = new Sword(1, 0, dungeonTester, "sword");
		dungeonTester.addEntity(sword);

		assertNull(player.getInventory().getSword());

		player.moveRight();
		
		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up
		
		assertNotNull(player.getInventory().getSword());
			
	}
	
	@Test
	public void collectPotion() {
		Dungeon dungeonTester = new Dungeon(4,4, null);	
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		InvPotion potion = new InvPotion(1, 0, dungeonTester, "invPotion");
		dungeonTester.addEntity(potion);

		assertEquals(-1, player.getInventory().getInvincibility());

		player.moveRight();
		
		assertEquals(1, player.getX()); 
		assertEquals(0, player.getY()); 
		

		assertEquals(5, player.getInventory().getInvincibility());
			
	}
	
	
}