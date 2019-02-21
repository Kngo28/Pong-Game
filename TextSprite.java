package pong;

import java.awt.*;

class TextSprite
{
	private String text;
	private Point loc;
	private Font font;
	private Color color;
	
	public TextSprite(String text, Point loc, Font font, Color color)
	{
		this.text = text;
		this.loc = loc;
		this.font = font;
		this.color = color;
	}
	public void drawSprite(Graphics g)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, loc.x, loc.y);
	}
}