package engine;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Tile
{
    private boolean collidable;
    private static final int COLLIDABLE_MIN = 1;
    private static final int COLLIDABLE_MAX = 100;
    
    private boolean phaseable;
    private static final int PHASEABLE_MIN = 51;
    private static final int PHASEABLE_MAX = 150;
    
    private static final int DUAL_IMAGE_MIN = 51;
    private static final int DUAL_IMAGE_MAX = 150;
    
    private int xpos;
    private int ypos;
    
    private int imageInt;
    
    private ImageIcon tileIcon;
    private Image tileImage;
    private Image phaseImage;
    
    public Tile(int i, int y, int x)
    {
        imageInt = i;
        xpos = x;
        ypos = y;
        collidable = (imageInt>=COLLIDABLE_MIN && imageInt<=COLLIDABLE_MAX);
        phaseable = (imageInt>=PHASEABLE_MIN && imageInt<=PHASEABLE_MAX);
        
        if (imageInt == 0) {
        	tileIcon = null;
        	tileImage = null;
        } else if (imageInt > 0 && imageInt < DUAL_IMAGE_MIN) {
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + imageInt + ".png"));
        	tileImage = tileIcon.getImage();
        } else if (imageInt >= DUAL_IMAGE_MIN && imageInt <= DUAL_IMAGE_MAX){
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + imageInt + ".png"));
        	tileImage = tileIcon.getImage();
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + (imageInt + 1) + ".png"));
        	phaseImage = tileIcon.getImage();
        }
    }

    public void setXPos(int x)
    {
        xpos = x;
    }

    public void setYPos(int y)
    {
        ypos = y;
    }

    public int getXPos()
    {
        return xpos;
    }

    public int getYPos()
    {
        return ypos;
    }

    public boolean getCollidable()
    {
        return collidable; 
    }
    
    public boolean getPhaseable()
    {
    	return phaseable;
    }

    public Image getImage()
    {
        return tileImage;
    }
    
    public Image getImage(boolean phased)
    {
    	if (phased) {
    		return phaseImage;
    	} else {
    		return tileImage;
    	}
    }
    
    public String toString()
    {
       return "xPos:"+xpos+" yPos:"+ypos+" IsCollidable:"+collidable+" ImagePath:"+getImage();
    }
}

