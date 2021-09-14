package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CreateGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

class PlayerMovementTest {

	// We test if we can move from bottom of grid to top, then if we are at top and we try
	// move up, our coordinates should remain unchanged as we can't move higher..
	@Test
	void testMoveUp() {
		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		player.moveUp();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(1, player.getY()); // Should be able to move up

		player.moveUp();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveUp();

		assertEquals(2, player.getX()); // Should not be able to move up
		assertEquals(0, player.getY()); // Should not be able to move up

	}

	// We test if we can move from top of grid to bottom, then if we are at bottom and we try
	// move down, our coordinates should remain unchanged as we cant move lower.
	@Test
	void testMoveDown() {

		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		player.moveDown();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(1, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(2, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(3, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(2, player.getX()); // Should not be able to move up
		assertEquals(3, player.getY()); // Should not be able to move up

	}

	// Same concept as above two
	@Test
	void testMoveRight() {

		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		player.moveRight();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveRight();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveRight();

		assertEquals(3, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveRight();

		assertEquals(3, player.getX()); // Should not be able to move up
		assertEquals(0, player.getY()); // Should not be able to move up

	}

	// Same concept as above three
	@Test
	void testMoveLeft() {

		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		player.moveLeft();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveLeft();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveLeft();

		assertEquals(0, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveLeft();

		assertEquals(0, player.getX()); // Should not be able to move up
		assertEquals(0, player.getY()); // Should not be able to move up

	}

	// Here we step downward from 0,0 (top left of grid) to 3,3 (bottom right), then spiral to the center to test
	// the combination of all movements.
	@Test
	void testMoveAllOver() {

		CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
		JSONObject conditions = goal.json;
		
		Dungeon dungeonTester = new Dungeon(4,4, conditions);
		Player player = new Player(dungeonTester, 0, 0, "player");
		dungeonTester.setPlayer(player);

		player.moveRight();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(0, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(1, player.getY()); // Should be able to move up

		player.moveRight();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(1, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(2, player.getX()); // Should not be able to move up
		assertEquals(2, player.getY()); // Should not be able to move up

		player.moveRight();

		assertEquals(3, player.getX()); // Should be able to move up
		assertEquals(2, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(3, player.getX()); // Should be able to move up
		assertEquals(3, player.getY()); // Should be able to move up

		player.moveDown();

		assertEquals(3, player.getX()); // Should be able to move up
		assertEquals(3, player.getY()); // Should be able to move up

		player.moveLeft();

		assertEquals(2, player.getX()); // Should be able to move up
		assertEquals(3, player.getY()); // Should be able to move up


		player.moveLeft();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(3, player.getY()); // Should be able to move up

		player.moveUp();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(2, player.getY()); // Should be able to move up

		player.moveUp();

		assertEquals(1, player.getX()); // Should be able to move up
		assertEquals(1, player.getY()); // Should be able to move up

	}
}
