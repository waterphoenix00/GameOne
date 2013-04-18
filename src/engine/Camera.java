package engine;

import java.awt.Color;
import java.awt.Graphics;

public class Camera
{
    private int x;
    private int y;
    
    private int column;
    private int row;
    
    private GamePanel gp;
    
    public Camera(GamePanel gamePanel, int x, int y)
    {
        gp = gamePanel;
        this.x = x;
        this.y = y;
        
        column = x/64;
        row = y/64;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        Tile[][] tileGrid = gp.getGrid().getTileGrid();
        for(int i=row; i < row+11; i++)
        {
            for(int j=column; j < column+18; j++)
            {
            	//System.out.println(i + " " + j + " " + tileGrid[i][j].getImage());
            	if(tileGrid[i][j].getImage() != null) {
            		System.out.println(i + " " + j);
            		g.drawImage(tileGrid[i][j].getImage(), (j*64)-x, (i*64)-y, null);
            		//g.drawImage(new ImageIcon(this.getClass().getResource("1.png")).getImage(), 0, 0, null);
            	}
            }
        }
        
    }
    
    public void getObjects()
    {

    }

    public void update()
    {

    }
}