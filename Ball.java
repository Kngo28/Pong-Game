package pong;

import java.awt.image.BufferedImage;
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.*;

public class Ball extends Sprite
{
	private class Velocity
	{
		private String[] directions = {"NE", "SE", "SW", "NW"};
		public int xComponent;
		public int yComponent;
		private String myDirection;
	//	private String nextDirection;
		
		public Velocity(int a, int b)
		{
			xComponent = a;
			yComponent = b;
			myDirection = directions[2];
		//	nextDirection = directions[1];
		}
		private void setVelocity(int a, int b)
		{
			xComponent = a;
			yComponent = b;
		}
		private int randomNumber()
		{
		//	int c = (int)(Math.random() * 3) + 1;
		//	return c;
			int c = 1;
			return c;
		}
		private String getDirection()
		{
			return myDirection;
		}
		public void setNextDirections(String d)
		{
			myDirection = d;
	//		nextDirection = n;
		}
		private void reset()
		{
	/*		xComponent = -1 * velocity.randomNumber();
			yComponent =  velocity.randomNumber();
			myDirection = directions[2];
	//		nextDirection = directions[1];*/
		}	
	}
	private static final int TOP = 0;
	private static final int BOTTOM = 1;
	private static final int RIGHT = 2;
	private static final int LEFT = 3;
	
	private Point startingLocation;
	private Velocity velocity;
	private boolean start;
	private Color color;
	private int xSpeed;
	private int ySpeed;
	private boolean speedBoost;
	private boolean hasHit;
	
	public Ball()
	{
		super(Screen.WIDTH / 2, 0, 80, 80);
		xSpeed = 2;
		ySpeed = 2;
		velocity = new Velocity(-xSpeed, ySpeed);
		startingLocation = new Point(Screen.WIDTH / 2, 0);
		start = false;
		color = Color.WHITE;
		speedBoost = false;
		hasHit = false;
		setVisible(true);
	}
	public void drawSprite(Graphics g)
	{
		g.setColor(color);
		g.fillOval(getX(), getY(), 80, 80);
	}
	public void go()
	{
		start = true;
	}
	public boolean collidesWith(Paddle p)
	{
		if(getY() > Screen.HEIGHT - 50 || getY() < 0)
			return true;
		else if(getBounds().intersects(p.getBounds()))
			return true;
		return false;
	}
	private int collisionType(Paddle p1, Paddle p2)
	{
		if(getY() > Screen.HEIGHT - 50)
			return BOTTOM;
		else if(getY() < 0)
			return TOP;
		else if(getBounds().intersects(p1.getBounds()))
			return LEFT;
		else if(getBounds().intersects(p2.getBounds()))
			return RIGHT;
		return -1;
}
	public void act(Paddle p1, Paddle p2)
	{
		if(isVisible() && start)
		{
			setX(getX() + velocity.xComponent);
			setY(getY() + velocity.yComponent);
			if(collidesWith(p1) || collidesWith(p2))
			{
				ballDirection(p1, p2);
				color = Color.WHITE;
			}
		}
	}
	public void ballDirection(Paddle p1, Paddle p2)
	{
		int collision = collisionType(p1, p2);
	//	System.out.println(collision);
		switch(collision)
		{
			case TOP:
				if(velocity.getDirection() == "NE")
				{
					velocity.setVelocity(xSpeed, ySpeed);
					velocity.setNextDirections("SE");
				}
				if(velocity.getDirection() == "NW")
				{
					velocity.setVelocity(-xSpeed, ySpeed);
					velocity.setNextDirections("SW");
				}
				break;
			case BOTTOM:
				if(velocity.getDirection() == "SE")
				{
					velocity.setVelocity(xSpeed, -ySpeed);
					velocity.setNextDirections("NE");
				}
				if(velocity.getDirection() == "SW")
				{
					velocity.setVelocity(-xSpeed, -ySpeed);
					velocity.setNextDirections("NW");
				}
				break;
			case RIGHT:
				if(velocity.getDirection() == "NE")
				{
					velocity.setVelocity(-xSpeed, -ySpeed);
					velocity.setNextDirections("NW");
				}
				if(velocity.getDirection() == "SE")
				{
					velocity.setVelocity(-xSpeed, ySpeed);
					velocity.setNextDirections("SW");
				}
				break;
			case LEFT:
				if(velocity.getDirection() == "NW")
				{
					velocity.setVelocity(xSpeed, -ySpeed);
					velocity.setNextDirections("NE");
				}
				if(velocity.getDirection() == "SW")
				{
					velocity.setVelocity(xSpeed, ySpeed);
					velocity.setNextDirections("SE");
					System.out.println("boop");
				}
				break;
		}	
	}
	public void reset()
	{
		color = Color.WHITE;
		setLocation(Screen.WIDTH / 2, 0);
		velocity.reset();
		start = false;
		speedBoost = false;
		hasHit = false;
	}
}