package pong;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Sprite
{
	private Rectangle boundary;
	private boolean isVisible;
	
	public Sprite(int x,int y, int width, int height)
	{
		boundary = new Rectangle(x, y, width, height); 
	}
	public boolean isVisible()
	{
		return isVisible;
	}
	public int getX()
	{
		return boundary.x;
	}
	public int getY()
	{
		return boundary.y;
	}
	public int getWidth()
	{
		return boundary.width;
	}
	public int getHeight()
	{
		return boundary.height;
	}
	public Rectangle getBounds()
	{
		return boundary;
	}
	public void setX(int x)
	{
		boundary.x = x;
	}
	public void setY(int y)
	{
		boundary.y = y;
	}
	public void setLocation(int x, int y)
	{
		boundary.x = x;
		boundary.y = y;
	}
	public void setVisible(boolean visible)
	{
		isVisible = visible;
	}
	abstract void drawSprite(Graphics g);
}