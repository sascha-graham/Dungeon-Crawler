package unsw.dungeon;

public abstract class Subgoal implements GoalInterface {
	
	private Dungeon dungeon;
	
	public Subgoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public abstract boolean isComplete();
	
}
