package game.entities;

/**
 * Represents a monster for the player character to fight against.
 *
 * @author DorianBoel
 */
public class Monster extends GameCharacter {

	/**
	 * The monster's type.
	 */
	private final String type;
	
	/**
	 * The amount of score points the monster yields once defeated.
	 */
	private int points;
	
	/**
	 * Constructor for {@link Monster}.
	 * The monster's health, strength, and the amount of points it gives 
	 * when defeated vary depending on the given type
	 * (<em>loup</em>, <em>gobelin</em> or <em>troll</em>).
	 * 
	 * @param type The given monster type
	 */
	public Monster(String type) {
		this.type = type;
		switch (type) {
			case ("loup") :
				this.strength = (int) Math.floor(Math.random() * (8 - 3) + 3);
				this.health = (int) Math.floor(Math.random() * (10 - 5) + 5);
				this.points = 1;
				break;
			case ("gobelin") : 
				this.strength = (int) Math.floor(Math.random() * (10 - 5) + 5);
				this.health = (int) Math.floor(Math.random() * (15 - 10) + 10);
				this.points = 2;
				break;
			case ("troll") :
				this.strength = (int) Math.floor(Math.random() * (15 - 10) + 10);
				this.health = (int) Math.floor(Math.random() * (30 - 20) + 20);
				this.points = 5;
				break;
			default :
				return;
		}
	}

	/**
	 * Getter for {@link #type}.
	 * 
	 * @return This monster's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter for {@link #points}.
	 * 
	 * @return The amount of points this monster yields once defeated
	 */
	public int getPoints() {
		return points;
	}
	
}
