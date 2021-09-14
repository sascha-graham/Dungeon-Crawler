package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.Entity;

class TreasureGoalTest {

	@Test
	void treasureGoalTest() {
		CreateGoal goal = new CreateGoal("AND", "treasure");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);
		
		Treasure t = new Treasure(1, 0, dungeonTester, "treasure"); // Spawn treasure
		dungeonTester.addEntity(t);
		
		t = new Treasure(2, 0, dungeonTester, "treasure"); // Spawn treasure
		dungeonTester.addEntity(t);
		
		t = new Treasure(3, 0, dungeonTester, "treasure"); // Spawn treasure
		dungeonTester.addEntity(t);
		
		t = new Treasure(3, 1, dungeonTester, "treasure"); // Spawn treasure
		dungeonTester.addEntity(t);
		
		dungeonTester.countStartingEnemiesTreasureSwitches();
				
		player.moveRight(); // Move into treasure	
		assertEquals(1, player.getX()); 
		assertEquals(0, player.getY()); 
		
		player.moveRight(); // Move into more treasure	
		assertEquals(2, player.getX()); 
		assertEquals(0, player.getY()); 

		player.moveRight(); // Move into more treasure	
		assertEquals(3, player.getX()); 
		assertEquals(0, player.getY()); 

		player.moveDown(); //  Move into more treasure	
		assertEquals(3, player.getX()); 
		assertEquals(1, player.getY());
		
		
	
	}

}