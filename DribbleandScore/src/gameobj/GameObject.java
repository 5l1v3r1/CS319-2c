package gameobj;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/* ========
 * author: S. Umut Balkan
 * file: GameObject.java
 * ========
 * Definition:
 * GameObject class is "abstract" because; 
 * When we talk about abstract classes we are defining "characteristics" of an object type
 * Simply specifying what "GameObject is.
 * I choose not to use an interface because interface simply means that what the object "can do".
 */

public abstract class GameObject {
	// I'm using "protected" access modifier for these variables. (can only be seen by this class & subclasses)
	protected int xPosition, yPosition; // x & y coordinates for drawing purpose
	protected int width, height; 		// the width and height of all frames
	protected int count;   				// the number of frames
	protected int columns; 				// the number of columns (how many frames are in one row in the image)
	protected int offsetX; 				// the offset of the first frame
	protected int offsetY; 				// the offset of the first frame
	protected ImageView image; 				// Image of the object in sprite-sheet form
	protected Duration duration;		// the duration of a single cycle (that is how long it should take to go through all frames)
	
	
	
	
	
	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}
	/**
	 * @param xPosition the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}
	/**
	 * @param yPosition the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}
	/**
	 * @return the offsetX
	 */
	public int getOffsetX() {
		return offsetX;
	}
	/**
	 * @param offsetX the offsetX to set
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	/**
	 * @return the offsetY
	 */
	public int getOffsetY() {
		return offsetY;
	}
	/**
	 * @param offsetY the offsetY to set
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	/**
	 * @return the image
	 */
	public ImageView getImageView() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(ImageView image) {
		this.image = image;
	}
	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}