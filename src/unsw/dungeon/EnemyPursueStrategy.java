package unsw.dungeon;

public class EnemyPursueStrategy implements EnemyMovementStrategy {

	private Enemy enemy;
	
	public EnemyPursueStrategy(Enemy enemy) {
		this.enemy = enemy;
	}

	/**
	 * Sets the enemy movement strategy to pursue the player.
	 * It does this by taking the difference of the enemy and the players coordinates
	 * and moving one cell closer each time
	 */
	@Override
	public void movementStrategy() {
		Player player = enemy.getPlayer();
		Collision collision = player.getCollision();
		
		int foundMoveFlag=0;
		int dirX,dirY;
		
		int pX = player.getX();
		int pY = player.getY();
		
		int eX = enemy.getX();
		int eY = enemy.getY();
		
		int diffY = eY-pY;	
		int diffX= eX-pX;
		
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
		if (collision.checkCanMoveThrough((eX-dirX),eY, "enemy") && dirX!=0) {
			enemy.setX(dirX);
			foundMoveFlag=1;
		} else if (collision.checkCanMoveThrough(eX,(eY-dirY), "enemy") && foundMoveFlag==0) {
			enemy.setY(dirY);
			foundMoveFlag=1;
		}
		
	}		
	

}
