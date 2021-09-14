package unsw.dungeon;

public class ExitGoal extends Subgoal {
	
	public ExitGoal(Dungeon dungeon) {
		super(dungeon);
	}
	
	@Override
	public boolean isComplete() {
		return super.getDungeon().playerAtExit();
	}

}
