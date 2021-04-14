import java.awt.Color;

public class Sprite {
	
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height; 
	private Color colour;
	
	// Getter methods
	public int getXPosition() {return xPosition;}
	public int getyPosition() {return yPosition;}
	public int getxVelocity() {return xVelocity;}
	public int getyVelocity() {return yVelocity;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public Color getColor() {return colour;}
	
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
	public void setColor(Color colour) {
		this.colour = colour;
	}
	
	public void setXPosition(int newX, int panelWidth) {
		xPosition = newX;
		
		if(xPosition < 0) {
			xPosition = 0;
		} else if(xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}	
	}
	
	public void setYPosiiton(int newY, int panelHeight) {
		yPosition = newY;
		
		if(yPosition < 0) {
			yPosition = 0;
		} else if(yPosition + height > panelHeight) {
			yPosition = panelHeight - height;
		}		
	}
	
	
	
}
