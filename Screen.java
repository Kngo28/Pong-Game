package pong;

import java.awt.Graphics;
import java.awt.Toolkit;

public interface Screen
{
	int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	void drawScreen(Graphics g);
	
	void keyPressed(int key);
	
	Option keyReleased(int key);
	
	void update();
	
	void reset();
	
	void exit();
}