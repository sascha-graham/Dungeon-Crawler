package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;

class TeleportTest {

	@Test
	void test() {
		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);
		
		Portal p1 = new Portal(1, 0, 0, dungeonTester, "portal");
		dungeonTester.addEntity(p1);
		
		Portal p2 = new Portal(1, 0, 0, dungeonTester, "portal");
		dungeonTester.addEntity(p2);
		
		
		player.moveRight(); // Move into teleporter 1
		
		assertEquals(p2.getX()+1, player.getX()); //Check if player has been teleported to teleporter 2
		assertEquals(p2.getY(), player.getY()); 
		
		player.moveLeft(); // Move into teleporter
		assertEquals(p1.getX()-1, player.getX()); //Check if player has been teleported back to teleporter 1
		assertEquals(p1.getY(), player.getY()); 
		
	}

}