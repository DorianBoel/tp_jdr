package game.entities;

public class PlayerCharacter extends GameCharacter {

	// Instance attributes
	private int score;
	
	//TODO:Add character name
	// Constructor
	public PlayerCharacter() {
		this.strength = (int) Math.floor(Math.random() * (18 - 12) + 12);
		this.health = (int) Math.floor(Math.random() * (50 - 20) + 20);
		this.score = 0;
	}
	
	// Instance methods
	public void addScore(int points) {
		this.score += points;
	}
	
	// Getters
	public int getScore() {
		return score;
	}
	
}
