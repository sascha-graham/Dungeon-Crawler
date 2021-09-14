package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.CreateGoal;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Switch;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class ExitGoalTest {

	@Test
	void exitGoalTest() {
		CreateGoal goal = new CreateGoal("AND", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);
		
		Exit exit = new Exit(2,2, dungeonTester, "exit"); // Load an exit into the dungeon
		dungeonTester.addEntity(exit);		
						
		player.moveRight(); // Move toward exit
		assertEquals(1, player.getX()); 
		assertEquals(0, player.getY()); 	
		
		player.moveRight(); // Move toward exit
		assertEquals(2, player.getX()); 
		assertEquals(0, player.getY()); 
		
		player.moveDown(); // Move toward exit
		assertEquals(2, player.getX()); 
		assertEquals(1, player.getY());
		
		player.moveDown(); // Move into exit
		assertEquals(2, player.getX()); 
		assertEquals(2, player.getY());
		
		 // Confirm the game is complete once we move into exit

		
	}

}