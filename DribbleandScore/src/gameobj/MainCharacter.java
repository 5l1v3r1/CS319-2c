package gameobj;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class MainCharacter extends GameObject {

	/*
	 * I use enums instead of integers (or String codes) to increase compile-time checking
	 * and avoid errors from passing in invalid constants
	 */
	public enum CHAR_STATE {JUMP, CROUCH, MOVE_LEFT, MOVE_RIGHT};
	
	// Properties
	private int xSpeed;		// x-axis speed
	private int ySpeed;		// y-axis speed
	private int lifePoints; // a value between 1-3
	
	
	// Constructor for MainCharacter
	public MainCharacter(
			Image image,
			Duration duration,
			int count,   		int columns,
			int offsetX, 		int offsetY,
			int width,   		int height,
			int xPosition, 		int yPosition,
			int xSpeed,			int ySpeed,
			int	lifePoints
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
		this.lifePoints = lifePoints;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	
	// Changes the MainCharacter animation according to the current "state"
	public void move(CHAR_STATE state){
		switch (state) {
		case JUMP:
			System.out.println("STATE = JUMP");   	// THESE CALLS ARE FOR TESTING PURPOSES
			break;
		case MOVE_LEFT:
			System.out.println("STATE = MOVE_LEFT");
			break;
		case MOVE_RIGHT:
			System.out.println("STATE = MOVE_RIGHT");
			break;
		case CROUCH:
			System.out.println("STATE = CROUCH");
			break;
		}
	}

	// ACCESSORS & MUTATORS
	/**
	 * @return the xSpeed
	 */
	public int getxSpeed() {
		return xSpeed;
	}


	/**
	 * @param xSpeed the xSpeed to set
	 */
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}


	/**
	 * @return the ySpeed
	 */
	public int getySpeed() {
		return ySpeed;
	}


	/**
	 * @param ySpeed the ySpeed to set
	 */
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}


	/**
	 * @return the lifePoints
	 */
	public int getLifePoints() {
		return lifePoints;
	}


	/**
	 * @param lifePoints the lifePoints to set
	 */
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
}
