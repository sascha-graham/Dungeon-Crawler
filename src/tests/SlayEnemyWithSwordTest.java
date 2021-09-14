package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class SlayEnemyWithSwordTest {
	@Test
	void SlayEnemyWithSword() {
			
			CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
			JSONObject conditions = goal.json;
			
			Dungeon dungeonTester = new Dungeon(4,4, conditions);
			Player player = new Player(dungeonTester, 0, 0, "player");
			dungeonTester.setPlayer(player);
			
			Sword sword = new Sword(1, 0, dungeonTester, "sword");
			dungeonTester.addEntity(sword);
			
			boolean alive = true;
			Enemy enemy = new Enemy(2, 2, dungeonTester,"enemy", alive);
			dungeonTester.addEntity(enemy);
			
			assertEquals(0, dungeonTester.allEnemiesKilled()); // Confirm we have no kills
			
			player.moveRight(); // Move into sword
			
			assertEquals(1, player.getX()); 
			assertEquals(0, player.getY()); 
			
			assertEquals(5, player.getInventory().getSword().getDurability()); 
			
			player.moveUp(); // Lure the enemy toward us
			
			assertEquals(1, player.getX()); 
			assertEquals(0, player.getY()); 
			
			
			player.slayEnemy(player.getX(), player.getY()+1); // slay enemy			


			assertEquals(0, dungeonTester.allEnemiesKilled()); // Confirm we have killed the enemy

			
			
	}
}