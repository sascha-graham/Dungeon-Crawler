package unsw.dungeon;

public class SwitchesGoal extends Subgoal {

	public SwitchesGoal(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public boolean isComplete() {
		return super.getDungeon().allSwitchesTriggered();
	}

}
