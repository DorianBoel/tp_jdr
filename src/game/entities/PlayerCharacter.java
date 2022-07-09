package game.entities;

/**
 * Represents the in-game character controlled by the player.
 *
 * @author DorianBoel
 */
public class PlayerCharacter extends GameCharacter {

	// Instance attributes
	/**
	 * The player's current total score
	 */
	private int score;
	
	/**
	 * Constructor for {@link #PlayerCharacter}.
	 * The player character's strength and health are given random values,
	 * and the score is initialized at 0.
	 */
	public PlayerCharacter() {
		this.strength = (int) Math.floor(Math.random() * (18 - 12) + 12);
		this.health = (int) Math.floor(Math.random() * (50 - 20) + 20);
		this.score = 0;
	}
	
	/**
	 * Adds a given amount of points to this character's current score.
	 * 
	 * @param points The amount of points to add to the score
	 */
	public void addScore(int points) {
		this.score += points;
	}

	/**
	 * Getter for {@link #score}.
	 * 
	 * @return This character's current total score
	 */
	public int getScore() {
		return score;
	}
	
}
