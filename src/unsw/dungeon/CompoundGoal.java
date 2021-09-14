package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CompoundGoal implements GoalInterface {
	
	private GoalType goalType;
	private Dungeon dungeon;
	private List<CompoundGoal> compoundGoals;
	private List<Subgoal> subgoals;
	
	/**
	 * a compound goal as part of a composite pattern
	 * contains compound goals and subgoals
	 * @param jsonConditions
	 * @param dungeon
	 */
	public CompoundGoal(JSONObject jsonConditions, Dungeon dungeon) {
		this.dungeon = dungeon;
		compoundGoals = new ArrayList<CompoundGoal>();
		subgoals = new ArrayList<Subgoal>();
		
		this.goalType = null;
		String goal = jsonConditions.getString("goal");
		switch (goal) {
		case "AND":
			goalType = GoalType.AND;
			break;
		case "OR":
			goalType = GoalType.OR;
			break;
		}
		
		JSONArray subGoals = jsonConditions.getJSONArray("subgoals");
		for (int i = 0; i < subGoals.length(); i++) {
			JSONObject object = subGoals.getJSONObject(i);
			if (object.has("subgoals")) {
				addCompoundGoal(object);
			} else {
				addSubgoal(object);
			}
		}
	}

	public void add(Subgoal subgoal) {
		subgoals.add(subgoal);
	}
	
	/**
	 * @param goal- JSONObject object of the goals required for the dungeon
	 */
	private void addSubgoal(JSONObject goal) {
		String objective = goal.getString("goal");
		switch (objective) {
		case "exit":
			subgoals.add(new ExitGoal(dungeon));
			System.out.println("making exit goal");
			break;
		case "enemies":
			subgoals.add(new EnemiesGoal(dungeon));
			System.out.println("making enemies goal");
			break;
		case "boulders":
			subgoals.add(new SwitchesGoal(dungeon));
			System.out.println("making switches goal");
			break;
		case "treasure":
			subgoals.add(new TreasureGoal(dungeon));
			System.out.println("making treasure goal");
		}
	}
	
	/**
	 * create another layer of compound goal(s)
	 * @param jsonConditions
	 */
	private void addCompoundGoal(JSONObject jsonConditions) {
		compoundGoals.add(new CompoundGoal(jsonConditions, dungeon));
	}

	@Override
	public boolean isComplete() {
		switch (goalType) {
		case AND:
			if (subgoalsComplete() && compoundGoalsComplete()) {
				return true;
			}
			break;
		case OR:
			if (anyGoalComplete()) {
				return true;
			}
			break;
		}
		
		return false;
	}
	
	/**
	 * check if child subgoals are complete
	 * @return
	 */
	private boolean subgoalsComplete() {
		for (Subgoal subgoal : subgoals) {
			if (!subgoal.isComplete()) return false;
		}
		return true;
	}
	
	/**
	 * check if child compound goals are complete
	 * @return
	 */
	private boolean compoundGoalsComplete() {
		for (CompoundGoal compoundGoal : compoundGoals) {
			if (!compoundGoal.isComplete()) return false;
		}
		
		return true;
	}
	
	/**
	 * check if any goals have been completed
	 * @return
	 */
	private boolean anyGoalComplete() {
		for (CompoundGoal compoundGoal : compoundGoals) {
			if (compoundGoal.isComplete()) return true;
		}
		for (Subgoal subgoal : subgoals) {
			if (subgoal.isComplete()) return true;
		}
		
		return false;
	}
}
