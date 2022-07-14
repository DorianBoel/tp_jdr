package game.entities;

/**
 * Represents a character (player or non-player) within the game.
 *
 * @author DorianBoel
 */
public abstract class GameCharacter {

	/**
	 * A value equal to the character's combat strength.
	 */
	protected int strength;
	
	/**
	 * A value equal to the character's current health points.
	 */
	protected int health;
	
	/**
	 * Inflicts a given amount of damage to this character
	 * by substracting it from its current health points.
	 * If the health value becomes negative as a result,
	 * it is readjusted to 0.
	 * 
	 * @param damage The amount of damage the character receives
	 */
	public void receiveDamage(int damage) {
		this.health -= damage;
		if (this.health < 0) {
			this.health = 0;
		}
	}
	
	/**
	 * Getter for {@link #strength}.
	 * 
	 * @return This character's combat strength
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * Getter for {@link #health}.
	 * 
	 * @return This character's current health points
	 */
	public int getHealth() {
		return health;
	}
	
}
