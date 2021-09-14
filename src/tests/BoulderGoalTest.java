package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.CreateGoal;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Switch;
import unsw.dungeon.Player;

class BoulderGoalTest {

	@Test
	void testBoulderGoal() {
		CreateGoal goal = new CreateGoal("AND", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		Switch sw = new Switch(1, 2, dungeonTester, "switch"); // Spawn floorswitch
		dungeonTester.addEntity(sw);
		
		sw = new Switch(2, 2, dungeonTester, "switch"); // Spawn floorswitch
		dungeonTester.addEntity(sw);
		
		Boulder boulder = new Boulder(1, 1, dungeonTester,  "boulders"); // Spawn boulder
		dungeonTester.addEntity(boulder);
		
		boulder = new Boulder(2, 1, dungeonTester,  "boulders"); // Spawn boulder
		dungeonTester.addEntity(boulder);
		
		dungeonTester.countStartingEnemiesTreasureSwitches();
		
		player.moveRight(); // Move atop a boulder	
		assertEquals(1, player.getX()); 
		assertEquals(0, player.getY()); 

		
		player.moveDown(); // Push boulder down onto switch		
		assertEquals(1, player.getX()); 
		assertEquals(1, player.getY()); 

		player.moveUp(); // Move back to where we came form and go behind another boulder		
		assertEquals(1, player.getX()); 
		assertEquals(0, player.getY()); 

		player.moveRight(); // Move atop a boulder		
		assertEquals(2, player.getX()); 
		assertEquals(0, player.getY()); 
		
		player.moveDown(); // Push boulder down onto switch		
		assertEquals(2, player.getX()); 
		assertEquals(1, player.getY());
		
		// Should say complete
	
	}

}