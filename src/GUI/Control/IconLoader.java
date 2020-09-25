package GUI.Control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconLoader {

	public static Icon loadIco(String linkImage, int k, int m) {
	    try {
	        BufferedImage image = ImageIO.read(new File(linkImage));
	 
	        int x = k;
	        int y = m;
	        int ix = image.getWidth();
	        int iy = image.getHeight();
	        int dx = 0, dy = 0;
	 
	        if (x / y > ix / iy) {
	            dy = y;
	            dx = dy * ix / iy;
	        } else {
	            dx = x;
	            dy = dx * iy / ix;
	        }
	 
	        return new ImageIcon(image.getScaledInstance(dx, dy,
	                image.SCALE_SMOOTH));
	 
	    } catch (IOException e) {
	 
	        e.printStackTrace();
	    }
	 
	    return null;
	}

}
