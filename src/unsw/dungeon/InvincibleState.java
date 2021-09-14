package unsw.dungeon;

public class InvincibleState extends State {

	public InvincibleState(Player player) {
		super(player);
	}


    /**
	 * Change the strategy of the enemy when we walk into an invicPotion to evade
	 */
	@Override
	public void onMove() {
		getPlayer().triggerEnemiesMoveAway();
	}
}
