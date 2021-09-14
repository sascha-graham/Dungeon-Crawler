package unsw.dungeon;

public class EnemiesGoal extends Subgoal {

	public EnemiesGoal(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public boolean isComplete() {
		return super.getDungeon().allEnemiesKilled();
	}

}
