package unsw.dungeon;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Inventory {
	
	private GameController gameController;
	private Key key;
	private Sword sword;
	private int treasure;
	private int bombCount;
	private int invincPotionTime;
	
	public Inventory(GameController gameController) throws InterruptedException {
		this.gameController = gameController;
		this.key = null;
		this.sword = null;
		int treasure = 0;
		int invincPotion = 0;
		int bombCount = 0;
	}
	
	/**
	 * let the application know this is the current inventory
	 * @return 
	 */
	public void setAppInventory() {
		gameController.setInventory(this);
	}
	
	/**
	 * @return treasure - an integer representing the amount of treasure the player has collected 
	 */
	public int getTreasure() {
		return treasure;
	}

	/**
	 * @param treasure - int used to adjust the amount of treasure in the inventory
	 */
	public void setTreasure(int treasure) {
		this.treasure = treasure;
		gameController.updateTreasure();
	}

	/**
	 * @return key - Object of type key, initially null
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key - Sets the player to have an object of type key collected from dungeon
	 */
	public void setKey(Key key) {
		this.key = key;
		gameController.updateKey();
	}
	
	/**
	 * Increment the Invincibility potion by 5 seconds each time we walk into one.
	 */
	public void addInvincibility() {
		this.invincPotionTime += 5;
		gameController.updateInvinc();
	}

	/**
	 * @return boolean - Int representing the amount of seconds left of invincibility
	 */
	public int getInvincibility() {
		return this.invincPotionTime;
	}
	
	/**
	 * Decrement the Invincibility potion affects by 1 seconds each second
	 */
	public void decrementInvincibility() {
		this.invincPotionTime--;
		gameController.updateInvinc();
	}
	
	/**
	 * @return sword - Sword object, initially null
	 */
	public Sword getSword() {
		return sword;
	}

	/**
	 * @return bombCount - integer representing the number of bombs in players inventory
	 */
	public int getBombCount() {
		return bombCount;
	}
	
	/**
	 * @return bombCount - increases the bomb count by 1 
	 */
	public int increaseBombCount() {
		bombCount++;
		gameController.updateBombs();
		return bombCount;
	}
	
	/**
	 * @return bombCount - decreases the bomb count by 1 
	 */
	public int decreaseBombCount() {
		bombCount--;
		gameController.updateBombs();
		return bombCount;
	}
	
	/**
	 * @param sword - Sword, setting the null sword to be of this type of Sword
	 */
	public void setSword(Sword sword) {
		this.sword = sword;
		gameController.updateSword();
	}
	
	/**
	 * redudes the number of attacks the sword has left by 1 each time it is used
	 */
	public void reduceSwordDurability() {
		sword.reduceDurability();
		if(sword.getDurability() == 0) {
			setSword(null);
		}
		gameController.updateSword();
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

}
