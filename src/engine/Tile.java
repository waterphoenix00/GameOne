package engine;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Tile
{
    private boolean collidable;
    private int xpos;
    private int ypos;
    private int imageInt;
    private static final int COLLIDABLE_MIN = 1;
    private static final int COLLIDABLE_MAX = 50;
    private ImageIcon tileIcon;
    private Image tileImage;
    
    public Tile(int i, int y, int x)
    {
        imageInt = i;
        xpos = x;
        ypos = y;
        collidable = (imageInt>=COLLIDABLE_MIN && imageInt<=COLLIDABLE_MAX);
        if (imageInt > 0) {
        	System.out.println(imageInt+".png");
        	tileIcon = new ImageIcon(this.getClass().getResource(imageInt+".png"));
        	tileImage = tileIcon.getImage();
        } else {
        	tileIcon = null;
        	tileImage = null;
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

    public Image getImage()
    {
        return tileImage;
    }
    
    public String toString()
    {
       return "xPos:"+xpos+" yPos:"+ypos+" IsCollidable:"+collidable+" ImagePath:"+getImage();
    }
}

