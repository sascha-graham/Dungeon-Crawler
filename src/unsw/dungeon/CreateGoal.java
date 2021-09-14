package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateGoal {

//	example
//	CreateGoal goal = new CreateGoal("AND", "treasure", "exit");
//	JSONObject conditions = goal.json;
	
	public JSONObject json;
	
	/**
	 * create a JSON Object to parse goals for testing
	 * @param type
	 * @param subgoals
	 */
	public CreateGoal(String type, String... subgoals) {
		json = new JSONObject();
		json.put("goal", type);
		
		JSONArray subs = new JSONArray();
		for (String str : subgoals) {
			JSONObject sub = new JSONObject();
			sub.put("goal", str);
			subs.put(sub);
		}
		
		json.put("subgoals", subs);
	}
}
