package pong;

import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics;
import java.applet.AudioClip;

public class Paddle extends Sprite
{
	public static final int UP = 1;
	public static final int DOWN = 0;
	public static final int STOP = 0;
	
	private boolean keyPressed;
	private Point startingLocation;
	private int myDirection;
	private BufferedImage image;
	private int score;
	
	public Paddle(int x, int y)
	{
		super(x, y, 44, 210);
		image = Utility.loadImage("paddle.png");
		keyPressed = false;
		startingLocation = new Point(x, y);
		score = 0;
	}
	public void drawSprite(Graphics g)
	{
		g.drawImage(image, getX(), getY(), null);
	}
	public void reset()
	{
		setLocation(startingLocation.x, startingLocation.y);
		keyPressed = false;
	}
	public void initialize()
	{
		reset();
	}
	public void keyPressed(int dir)
	{
		myDirection = dir;
		keyPressed = true;
	}
	public int getScore()
	{
		return score;
	}
	public void increaseScore()
	{
		score++;
	}
	public void keyReleased(int dir)
	{
		if(myDirection == dir)
			keyPressed = false;
	}
	public void act()
	{
		if(keyPressed)
		{
			int y = getY();
			switch(myDirection)
			{
				case DOWN:
					y = Math.min(Screen.HEIGHT - getHeight(), y + 2);
					break;
				case UP:
					y = Math.max(0, y - 2);
			}
			setY(y);	
		}		
	}
}