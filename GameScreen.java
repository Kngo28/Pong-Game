package pong;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.applet.AudioClip;

public class GameScreen implements Screen
{
	private Paddle paddle1;
	private Paddle paddle2;
	private Ball ball;
	private Font font;
	private boolean gameOver;
	
	public GameScreen()
	{
	/*	subTimer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Submarine sub = getAvailSub();
				if(sub != null)
				{
					sub.initialize();
					sub.setVisible(true);
				}
			}
		});
		font = new Font(Font.SANS_SERIF, Font.BOLD, 32);*/
		paddle1 = new Paddle(50, Screen.HEIGHT / 2);
		paddle2 = new Paddle(Screen.WIDTH - 92, Screen.HEIGHT / 2);
		ball = new Ball();
		font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
	}
	public void drawScreen(Graphics g)
	{
		paddle1.drawSprite(g);
		paddle2.drawSprite(g);
		ball.drawSprite(g);
		g.setFont(font);
		g.setColor(Color.blue);
		g.drawString("Score: " + paddle1.getScore(), 40, 40);
		g.drawString("Score: " + paddle2.getScore(), Screen.WIDTH - 160, 40);
		if(gameOver)
		{
			String gameOver = "GAME OVER";
			g.setColor(Color.WHITE);
			g.setFont(font);
			int width = g.getFontMetrics().stringWidth(gameOver);
			g.drawString(gameOver, (Screen.WIDTH - width) >> 1, 300);
		}
	}
	public void keyPressed(int key)
	{
		switch(key)
		{
			case KeyEvent.VK_UP:
				paddle2.keyPressed(Paddle.UP);
				break;
			case KeyEvent.VK_DOWN:
				paddle2.keyPressed(Paddle.DOWN);
				break;
			case KeyEvent.VK_W:
				paddle1.keyPressed(Paddle.UP);
				break;
			case KeyEvent.VK_S:
				paddle1.keyPressed(Paddle.DOWN);
				break;
			case KeyEvent.VK_SPACE:
				ball.go();
				break;
		}
	}
	public Option keyReleased(int key)
	{
		switch(key)
		{
			case KeyEvent.VK_UP:
				paddle2.keyReleased(Paddle.UP);
				break;
			case KeyEvent.VK_DOWN:
				paddle2.keyReleased(Paddle.DOWN);
				break;
			case KeyEvent.VK_M:
				break;
			case KeyEvent.VK_W:
				paddle1.keyReleased(Paddle.UP);
				break;
			case KeyEvent.VK_S:
				paddle1.keyReleased(Paddle.DOWN);
				break;
			case KeyEvent.VK_1:
				break;
			case KeyEvent.VK_ESCAPE:
				return Option.GAMEOVER;
		}
		return Option.CONTINUE;
	}
	public void gameOver()
	{
		gameOver = true;
	}
	public void exit()
	{

	}
	public void reset()
	{
		paddle1.reset();
		paddle2.reset();
		ball.reset();
		gameOver = false;
	}
	public void update()
	{
		if(!gameOver)
		{
			paddle1.act();
			paddle2.act();
			ball.act(paddle1, paddle2);
			if(ball.getX() > Screen.WIDTH + 50)
			{
				paddle1.increaseScore();
				reset();
			}
			if(ball.getX() < -50)
			{
				paddle2.increaseScore();
				reset();
			}
		}
	}
}