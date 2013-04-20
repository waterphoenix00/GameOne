package engine;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Camera
{
    private double x;
    private double y;
    
    private int column;
    private int row;
    
    private GamePanel gp;
    
    private static final int XBORDER = 128;
    private static final int YBORDER = 128;
    
    private static final int SCREEN_ROW_NUM = 9;
    private static final int SCREEN_COLUMN_NUM = 16;
    
    private static final int TILE_SIZE = 64;
    
    private Tile[][] tileGrid;
    
    private static final String bg = "imgs/Background.jpg";
    private Image background;
    
    public Camera(GamePanel gamePanel, int x, int y)
    {
        gp = gamePanel;
        this.x = x;
        this.y = y;
        
        column = x/TILE_SIZE;
        row = y/TILE_SIZE;
        
        background = new ImageIcon(this.getClass().getResource(bg)).getImage();
        tileGrid = gp.getGrid().getTileGrid();
    }

    public void draw(Graphics g)
    {
    	PlayerObject p = gp.getPlayer();
        g.drawImage(background, 0, 0, null);
        for(int i=row; i <= row + SCREEN_ROW_NUM + 1; i++)
        {
            for(int j=column; j <= column + SCREEN_COLUMN_NUM + 1; j++)
            {
            	if(i < tileGrid.length && j < tileGrid[0].length) {
            		if(tileGrid[i][j].getImage() != null) {
            			g.drawImage(tileGrid[i][j].getImage(p.getPhased()), 
            					(int)((j*TILE_SIZE)-x), (int)((i*TILE_SIZE)-y), null);
            		}
            	}
            	
            }
        }
        for (GameObject obj: gp.getGameObjects()) {
        	obj.draw(g, x, y);
        }
        gp.getPlayer().draw(g, x, y);
    }
    
    public void getObjects()
    {

    }

    public void update()
    {
    	PlayerObject player = gp.getPlayer();
    	if (player.getX() - x <= XBORDER) {
    		x = Math.max(0, player.getX() - XBORDER);
    	} else if ((x + SCREEN_COLUMN_NUM * TILE_SIZE) - (player.getX() + player.getWidth()) <= XBORDER) {
    		x = Math.min(tileGrid[0].length * TILE_SIZE - (SCREEN_COLUMN_NUM * TILE_SIZE),
    				(player.getX() + player.getWidth() + XBORDER) - (SCREEN_COLUMN_NUM * TILE_SIZE));
    	}
    	if (player.getY() - y <= YBORDER) {
    		y = Math.max(0, player.getY() - YBORDER);
    	} else if ((y + SCREEN_ROW_NUM * TILE_SIZE) - (player.getY() + player.getHeight()) <= YBORDER) {
    		y = Math.min(tileGrid.length * TILE_SIZE - (SCREEN_ROW_NUM * TILE_SIZE),
    				(player.getY() + player.getHeight() + YBORDER) - (SCREEN_ROW_NUM * TILE_SIZE));
    	}
    	row = (int)(y/TILE_SIZE);
    	column = (int)(x/TILE_SIZE);
    }
}
