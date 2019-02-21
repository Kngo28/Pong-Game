package pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class IntroScreen implements Screen
{
	private Rectangle[] selection;
	private TextSprite[] text;
	private int menuOption;
	private Color selectColor;
	
	public IntroScreen()
	{
		menuOption = 0;
		selectColor = new Color(140, 240, 170);
	}
	private void initText(Graphics g)
	{
		text = new TextSprite[5];
		String str = "Pong";
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 128);
		FontMetrics fm = g.getFontMetrics(font);
		Point titleLoc = new Point();
		int titleHeight = fm.getHeight();
		titleLoc.x = (Screen.WIDTH - fm.stringWidth(str)) / 2 - 100;
		titleLoc.y = (Screen.HEIGHT - titleHeight) / 2 - 100;
		text[0] = new TextSprite(str, titleLoc, font, Color.GRAY);
		titleLoc.x = titleLoc.x + 100;
		titleLoc.y = titleLoc.y + 100;
		text[1] = new TextSprite(str, titleLoc, font, Color.WHITE);
		str = "(c) 2018 Kevin Ngo";
		font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		fm = g.getFontMetrics(font);
		Point loc = new Point();
		loc.x = (Screen.WIDTH - fm.stringWidth(str)) / 2;
		loc.y = titleLoc.y + fm.getHeight();
		text[2] = new TextSprite(str, loc, font, Color.BLACK);
		selection = new Rectangle[2];
		str = "Start Game";
		Point topLoc = new Point();
		topLoc.x = (Screen.WIDTH - fm.stringWidth(str)) / 2;
		topLoc.y = Screen.HEIGHT - 160;
		text[3] = new TextSprite(str, topLoc, font, Color.WHITE);
		selection[0] = new Rectangle(topLoc.x - 10, topLoc.y - fm.getAscent(), fm.stringWidth(str) + 20, fm.getHeight() + 2);
		str = "Exit Game";
		loc = new Point();
		loc.x = (Screen.WIDTH - fm.stringWidth(str)) / 2;
		loc.y = topLoc.y + fm.getHeight();
		text[4] = new TextSprite(str, loc, font, Color.WHITE);
		selection[1] = new Rectangle(loc.x - 10, loc.y - fm.getAscent(), fm.stringWidth(str) + 20, fm.getHeight() + 2);
	}
	public void drawScreen(Graphics g)
	{
		//Graphics handle
		if(text == null)
			initText(g);
		g.setColor(selectColor);
		g.fillRect(selection[menuOption].x, selection[menuOption].y, selection[menuOption].width, selection[menuOption].height);
		for(TextSprite sprite: text)
			sprite.drawSprite(g);
	}
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_DOWN)
			menuOption = ++menuOption % 2;
		else if(key == KeyEvent.VK_UP)
			menuOption = --menuOption < 0 ? 1 : menuOption;
	}
	public Option keyReleased(int key)
	{
		if(key == KeyEvent.VK_ENTER)
			return menuOption == 0 ? Option.PLAY : Option.EXIT;
		return Option.CONTINUE;
	}
	public void update() {}
	public void reset() {}
	public void exit() {}
}