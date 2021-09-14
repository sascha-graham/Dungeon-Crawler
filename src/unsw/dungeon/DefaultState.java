package unsw.dungeon;

public class DefaultState extends State {
	
	public DefaultState(Player player) {
		super(player);
	}

	@Override
	public void onMove() {
		getPlayer().triggerEnemiesMoveTowards();
	}
}
