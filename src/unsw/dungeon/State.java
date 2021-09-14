package unsw.dungeon;

public abstract class State {
	
	private Player player;
	
	
	public State(Player player) { // sets state of player to be player
		this.player = player;
	}
	
	public Player getPlayer() { // returns the state of the player
		return player;
	}
	
	public abstract void onMove();
}
