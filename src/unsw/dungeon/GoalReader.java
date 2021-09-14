package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalReader {
	
	private Dungeon dungeon;
	private CompoundGoal compoundGoal;
	
	/**
	 * parses the game conditions from JSON file, uses composite pattern
	 * @param jsonConditions
	 */
	public GoalReader(JSONObject jsonConditions, Dungeon dungeon) {
		if (jsonConditions == null) return;
		this.dungeon = dungeon;
		
		this.compoundGoal = new CompoundGoal(jsonConditions, dungeon);
		
	}
	
	/**
	 * @return compoundGoal- CompoundGoal type object used for string manipulation
	 */
	public CompoundGoal getCompoundGoal() {
		return compoundGoal;
	}
	
	
	
}
