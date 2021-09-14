package unsw.dungeon;

public class EnemyEvadeStrategy implements EnemyMovementStrategy {
	
	private Enemy enemy;
	
	public EnemyEvadeStrategy (Enemy enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * Sets the enemy movement strategy to evade the player.
	 * It does this by taking the difference of the enemy and the players coordinates
	 * and moves one cell away in the direction where no obstructing entity is
	 */
	@Override
	public void movementStrategy() {
		Player player  = enemy.getPlayer();
		Collision collision = player.getCollision();

		int enemyX, enemyY = 0;
		int playerX, playerY = 0;
		int diffX, diffY, foundMoveFlag=0; 
		int dirX,dirY;
		
		playerX = player.getX();
		playerY = player.getY();
		
		enemyX = enemy.getX();
		enemyY = enemy.getY();
		
		diffX = enemyX - playerX;
		diffY = enemyY - playerY;	
		
		if (diffX != 0) {
			dirX=diffX/Math.abs(diffX);
		} else {
			dirX=0;
		}
		if (diffY != 0) {
			dirY=diffY/Math.abs(diffY);
		} else {
			dirY=0;
		}
		
		dirY=dirY*(-1);
		dirX=dirX*(-1);
		
		if (collision.checkCanMoveThrough((enemyX-dirX),enemyY, "enemy") && dirX!=0) {
			enemy.setX(dirX);
			foundMoveFlag=1;
		} else if (collision.checkCanMoveThrough(enemyX,(enemyY-dirY), "enemy") && foundMoveFlag==0) {
			enemy.setY(dirY);
			foundMoveFlag=1;
		}		
	}

}
