package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform ;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.applet.Applet;
import java.applet.AudioClip;

class Utility
{
	private Utility(){}

    public static BufferedImage loadImage(String imageName)
    {
        try
        {
      		return ImageIO.read(Utility.class.getResource("resources/" + imageName));
		}
	    catch(Exception ioexception) {
	    	JOptionPane.showMessageDialog(null, "Error reading " + imageName + "!", "Utility", JOptionPane.ERROR_MESSAGE);
	    	return null;
	    }
    }

    public static AudioClip loadAudio(String clipName)
    {
    	AudioClip clip = Applet.newAudioClip(Utility.class.getResource("resources/" + clipName));
    	if (clip == null)
    		JOptionPane.showMessageDialog(null, "Error reading " + clipName + "!", "Utility", JOptionPane.ERROR_MESSAGE);
        return clip;
    }
    
    public static void rotateImage(Graphics g, BufferedImage image, int direction, int x, int y){
     	Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, Utility.rotate(direction, image.getWidth(null), image.getHeight(null)), x, y);
    }
    
    private static AffineTransformOp rotate(int rotation, int width, int height) {
        AffineTransform at = new AffineTransform() ;
        at.rotate(rotation * Math.PI / 2, width / 2, height / 2);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }

}