package pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PongGame extends JFrame
{
	private static final int INTROSCREEN = 0;
	private static final int GAMESCREEN = 1;
	
	private Screen[] screens;
	private int currentScreen; 
	private boolean gameRunning;
	private Back back;
	
	private class Back extends JPanel
	{
		public Back()
		{
			setFocusable(false);
			setLayout(null);
			setBackground(Color.BLACK);
		}
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			screens[currentScreen].drawScreen(g);
		}
	}
	
	private class GameLoop implements Runnable
	{
		public void run()
		{
			GameTimer gameTimer = new GameTimer();
			gameRunning = true;
			while(gameRunning)
			{
				if(gameTimer.getElapsedTime() > 5) //20 fps
				{
					gameTimer.restart();
					screens[currentScreen].update();
					repaint();
				}
			}
			System.exit(0);
		}
	}
	public PongGame()
	{
		super("Pong");
		setContentPane(new Back());
		gameRunning = true;
		currentScreen = INTROSCREEN;
		
		screens = new Screen[2];
		screens[INTROSCREEN] = new IntroScreen();
		screens[GAMESCREEN] = new GameScreen();
		
		addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){} //not used
			
			public void keyPressed(KeyEvent e){
				screens[currentScreen].keyPressed(e.getKeyCode());
			}
			public void keyReleased(KeyEvent e)
			{
				Option opt = screens[currentScreen].keyReleased(e.getKeyCode());
				switch(opt)
				{
					case EXIT:
						gameRunning = false;
						break;
					case GAMEOVER:
						screens[GAMESCREEN].exit();
						screens[INTROSCREEN].reset();
						currentScreen = INTROSCREEN;
						break;
					case PLAY:
						screens[GAMESCREEN].reset();
						currentScreen = GAMESCREEN;
						setBackground(Color.BLACK);
						break;
				}
			}
		});
		if(!setFullScreenMode())
		{
			setUndecorated(true);
			setSize(Screen.WIDTH, Screen.HEIGHT);
		}
		setVisible(true);
		new Thread(new GameLoop()).run();
	}
	public boolean setFullScreenMode()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if(gd.isFullScreenSupported())
		{
			setUndecorated(true);
			setResizable(false);
			gd.setFullScreenWindow(this);
			return true;
		}
		return false;
	}
	public static void main(String[] args)
	{
    	new PongGame();
    }
}