
public class Sprite {
	
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height; 
	
	// Getter methods
	public int getXPosition() {return xPosition;}
	public int getyPosition() {return yPosition;}
	public int getxVelocity() {return xVelocity;}
	public int getyVelocity() {return yVelocity;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	// Setter methods
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
