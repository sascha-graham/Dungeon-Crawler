package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class TestKeyWithDoor {

	@Test
	void keyWithDoor() {

			CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
			JSONObject conditions = goal.json;
			
			Dungeon dungeonTester = new Dungeon(4,4, conditions);
			Player player = new Player(dungeonTester, 0, 0, "player");
			dungeonTester.setPlayer(player);
			
			Key key = new Key(1, 0, dungeonTester, "key", 0);
			dungeonTester.addEntity(key);


			int locked = 1;
			Door door = new Door(0, 1, dungeonTester, "door", locked, 0);

			dungeonTester.addEntity(door);

			assertNull(player.getInventory().getKey());


			player.moveDown(); // Attempt to open/enter door WITHOUT key

			assertEquals(0, player.getX());
			assertEquals(0, player.getY());

			player.moveRight(); // Move to cell with key, and collect

			assertEquals(1, player.getX());
			assertEquals(0, player.getY());

			player.moveLeft(); // Move back to where we started, then down, toward door

			assertEquals(0, player.getX());
			assertEquals(0, player.getY());

			player.moveDown(); // Attempt to open/enter door WITH key

			assertEquals(0, player.getX());
			assertEquals(1, player.getY());

	}

}
