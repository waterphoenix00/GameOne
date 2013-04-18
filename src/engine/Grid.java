package engine;

import java.io.*;
import java.util.Scanner;

public class Grid
{
    int xTileDim;
    int yTileDim;
    int level;
    int[][] intGrid = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    Tile[][] tileGrid;
       
    public Grid(int x, int y, int l)
    {
        xTileDim = x;
        yTileDim = y;
        level = l;
        /*Scanner sc = new Scanner("Dang");
		try {
			sc = new Scanner(new File("GA"+level+".txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
        for(int i=0; i <yTileDim; i++)
        {
            String s = sc.nextLine();
            String[] sa = s.split(" ");  
            for(int j=0; j <xTileDim; j++)
            {
                intGrid[i][j]= Integer.parseInt(sa[j]);
            }
        }*/
        xTileDim = intGrid[0].length;
        yTileDim = intGrid.length;
        
        tileGrid = new Tile[yTileDim][xTileDim];
        for(int i=0; i <yTileDim; i++)
        {
            for(int j=0; j <xTileDim; j++)
            {
                tileGrid[i][j]=  new Tile(intGrid[i][j],i,j);
            }
        }
        
        //sc.close();
    }

    public Tile[][] getTileGrid()
    {
        return tileGrid;
    }

    public int[][] getIntGrid()
    {
        return intGrid;
    }
}
