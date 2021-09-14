package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.InvPotion;
import unsw.dungeon.Player;

class InvPotionTest {
	@Test
	void SlayEnemyWithInvincibilityAndTestTheyFlee() {

			CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
			JSONObject conditions = goal.json;
			
			Dungeon dungeonTester = new Dungeon(4,4, conditions);
			Player player = new Player(dungeonTester, 0, 0, "player");
			dungeonTester.setPlayer(player);

			InvPotion potion = new InvPotion(1, 0, dungeonTester, "invPotion");
			dungeonTester.addEntity(potion);

			boolean alive = true;
			Enemy enemy = new Enemy(2, 2, dungeonTester, "enemy", alive);
			dungeonTester.addEntity(enemy);

			assertEquals(0, dungeonTester.allEnemiesKilled());

			player.moveRight(); // Move into invincibility Potion

			assertEquals(1, player.getX());
			assertEquals(0, player.getY());

			assertEquals(5, player.getInventory().getInvincibility());

			player.moveDown(); // Move toward enemy

			assertEquals(1, player.getX());
			assertEquals(1, player.getY());
			System.out.println(enemy.getY());

			player.moveDown(); // Move toward and kill enemy

			assertEquals(1, player.getX());
			assertEquals(2, player.getY());

			assertEquals(2, enemy.getX());  // confirm enemy is in the coordinates we are about to walk into with potion
			assertEquals(2, enemy.getY());

			player.moveRight(); // Move toward and kill enemy

			assertEquals(2, player.getX());
			assertEquals(2, player.getY());

			assertNotEquals(player.getX(), enemy.getX()); // Confirm the enemy is killed when we walk into them
			assertNotEquals(player.getY(), enemy.getY());

	}

		@Test
		void TestInvinvibilityPotionDuration() {

				CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
				JSONObject conditions = goal.json;
				
				Dungeon dungeonTester = new Dungeon(4,4, conditions);
				Player player = new Player(dungeonTester, 0, 0, "player");
				dungeonTester.setPlayer(player);
				
				InvPotion potion = new InvPotion(1, 0, dungeonTester, "InvPotion");
				dungeonTester.addEntity(potion);

				player.moveRight(); // Move into invincibility Potion


				player.moveRight();  // Continue moving to test duration, wears off after 5 movements
				player.moveDown();
				player.moveDown();
				player.moveLeft();
				player.moveLeft();
				player.moveUp();


		}

}
