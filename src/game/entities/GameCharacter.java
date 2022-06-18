package game.entities;

public abstract class GameCharacter {

	// Instance attributes
	protected int strength;
	protected int health;
	
	// Instance methods
	public void receiveDamage(int damage) {
		this.health -= damage;
		if (this.health < 0) {
			this.health = 0;
		}
	}
	
	// Getters
	public int getStrength() {
		return strength;
	}
	
	public int getHealth() {
		return health;
	}
	
}
