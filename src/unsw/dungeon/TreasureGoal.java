package unsw.dungeon;

public class TreasureGoal extends Subgoal {

	public TreasureGoal(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public boolean isComplete() {
		return super.getDungeon().allTreasureCollected();
	}

}
