package src;

import java.io.*;
import java.util.Scanner;
public class Grid
{
    int xTileDim;
    int yTileDim;
    int level;
    int[][] intGrid;
    Tile[][] tileGrid;
       
    public Grid(int x, int y, int l)throws FileNotFoundException
    {
        xTileDim = x;
        yTileDim = y;
        level = l;
        intGrid = new int [y][x];
        Scanner sc = new Scanner(new File("GA"+level+".txt"));
        for(int i=0; i <yTileDim; i++)
        {
            String s = sc.nextLine();
            String[] sa = s.split(" ");  
            for(int j=0; j <xTileDim; j++)
            {
                intGrid[i][j]= Integer.parseInt(sa[j]);
            }
        }
        
        tileGrid = new Tile[yTileDim][xTileDim];
        for(int i=0; i <yTileDim; i++)
        {
            for(int j=0; j <xTileDim; j++)
            {
                tileGrid[i][j]=  new Tile(intGrid[i][j],i,j);
            }
        }
        
        sc.close();
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
