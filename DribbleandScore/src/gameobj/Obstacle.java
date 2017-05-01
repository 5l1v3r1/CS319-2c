package gameobj;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class Obstacle extends GameObject {
	
	// Properties
	private int type; // 0 = foo, 1 = Mud, 2 = Referee, 3 = DefensePlayer
	
	public Obstacle(
			Image image,
			Duration duration,
			int count,   		int columns,
			int offsetX, 		int offsetY,
			int width,   		int height,
			int xPosition, 		int yPosition,
			int type
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
		this.type = type;
	}

	
	// ACCESSORS AND MUTATORS
	
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
