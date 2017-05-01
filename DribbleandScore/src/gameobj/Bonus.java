package application;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class Bonus extends GameObject {
	
	// Properties
	private int bonusDuration;
	private int type; // 0 = foo, 1 = Doping, 2 = ExtraLife, 3 = FootballShoe	
	
	// Constructor for Bonus
	public Bonus(
			Image image,
			Duration duration,
			int count,   		int columns,
			int offsetX, 		int offsetY,
			int width,   		int height,
			int xPosition, 		int yPosition,
			int bonusDuration, 	int type
			)
	{
		this.image = image;
		this.duration = duration;
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.bonusDuration = bonusDuration;
		this.type = type;
	}

	
	// ACCESSORS AND MUTATORS
	
	/**
	 * @return the bonusDuration
	 */
	public int getBonusDuration() {
		return bonusDuration;
	}

	/**
	 * @param bonusDuration the bonusDuration to set
	 */
	public void setBonusDuration(int bonusDuration) {
		this.bonusDuration = bonusDuration;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
