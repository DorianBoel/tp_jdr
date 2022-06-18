package game.entities;

public class Monster extends GameCharacter{

	private final String type;
	private int points;
	
	// Constructor
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

	// Getters
	public String getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}
	
}
