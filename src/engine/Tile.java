package engine;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Tile
{
	public static final int TILE_SIZE = 64;
	
    private boolean collidable;
    private static final int COLLIDABLE_MIN = 1;
    private static final int COLLIDABLE_MAX = 100;
    
    private boolean phaseable;
    private static final int PHASEABLE_MIN = 51;
    private static final int PHASEABLE_MAX = 150;
    
    private static final int DUAL_IMAGE_MIN = 51;
    private static final int DUAL_IMAGE_MAX = 150;
    
    private int column;
    private int row;
    
    private int xpos;
    private int ypos;
    
    private int imageInt;
    
    private ImageIcon tileIcon;
    private Image tileImage;
    private Image phaseImage;
    
    public Tile(int i, int y, int x)
    {
        imageInt = i;
        column = x;
        row = y;
        xpos = column * TILE_SIZE;
        ypos = row * TILE_SIZE;
        collidable = (imageInt>=COLLIDABLE_MIN && imageInt<=COLLIDABLE_MAX);
        phaseable = (imageInt>=PHASEABLE_MIN && imageInt<=PHASEABLE_MAX);
        
        if (imageInt == 0) {
        	tileIcon = null;
        	tileImage = null;
        } else if (imageInt > 0 && imageInt < DUAL_IMAGE_MIN) {
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + imageInt + ".png"));
        	tileImage = tileIcon.getImage();
        	phaseImage = tileImage;
        } else if (imageInt >= DUAL_IMAGE_MIN && imageInt <= DUAL_IMAGE_MAX){
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + imageInt + ".png"));
        	tileImage = tileIcon.getImage();
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + (imageInt + 1) + ".png"));
        	phaseImage = tileIcon.getImage();
        } else {
        	tileIcon = new ImageIcon(this.getClass().getResource("imgs/" + imageInt + ".png"));
        	tileImage = tileIcon.getImage();
        	phaseImage = tileImage;
        }
    }

    public void setColumn(int x)
    {
        column = x;
        xpos = column * TILE_SIZE;
    }

    public void setRow(int y)
    {
        row = y;
        ypos = row * TILE_SIZE;
    }

    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
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
       return "xPos:"+column+" yPos:"+row+" IsCollidable:"+collidable+" ImagePath:"+getImage();
    }
    
    public void objectUpdate(GameObject gobj) {
    	if (imageInt == 151) {
    		/*if ((gobj.getY() + gobj.getHeight() - ypos) 
    				>= gobj.getX() + gobj.getWidth()/2 - xpos + 2) {
    			System.out.println("in if update");
    			gobj.setY(ypos + TILE_SIZE - 
    					((gobj.getX() + gobj.getWidth()/2) - xpos) - gobj.getHeight());
    		}*/
    		
    		gobj.setY(ypos + TILE_SIZE - 
					((gobj.getX() + gobj.getWidth()/2) - xpos) - gobj.getHeight());
    		
    		/*
    		if (((gobj.getY()+gobj.getHeight()-1)%TILE_SIZE) 
    				>= ((gobj.getX()+gobj.getWidth()/2)%TILE_SIZE)+10) {
        		System.out.println("in if update");
    			gobj.setY(((int)((gobj.getY()+gobj.getHeight()-1)/TILE_SIZE)) * TILE_SIZE +
    					TILE_SIZE - ((gobj.getX()+gobj.getWidth()/2)%TILE_SIZE) - gobj.getHeight() + 1);
    			
    			
    			//gobj.setY((((int)((gobj.getY()+gobj.getHeight()-1)/TILE_SIZE))*TILE_SIZE +
    					//TILE_SIZE - ((gobj.getX()+gobj.getWidth()/2)%TILE_SIZE))-gobj.getHeight());
    		}*/
    	}
    }
}

