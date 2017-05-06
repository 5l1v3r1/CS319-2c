package gameobj;

import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Obstacle extends GameObject {
	
	// Properties
	private int type; // 0 = foo, 1 = Mud, 2 = Referee, 3 = DefensePlayer
	private boolean hit;
        public Obstacle(){}
	public Obstacle(
			ImageView image,
			Duration duration,
			int count,   		int columns,
			int offsetX, 		int offsetY,
			int width,   		int height,
			int xPosition, 		int yPosition,
			int type,               boolean hit
                        
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
                this.hit=hit;
	}

	
	// ACCESSORS AND MUTATORS
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
        public void setHit(boolean value){
            this.hit=value;
        }
        public boolean getHit()
        {
            return hit;
        }
                /**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
