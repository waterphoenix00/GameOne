package engine;

import java.io.*;
import java.util.ArrayList;

public class Grid
{
    private int xTileDim;
    private int yTileDim;
    private int level;
    int[][] intGrid = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    		{1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
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
        
        //*
        try {
        	ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
			BufferedReader br = new BufferedReader(
					new FileReader(getClass().getResource("GA" + level + ".txt").getPath()));
			String line = null;
			while ((line = br.readLine()) != null) {
				ArrayList<Integer> tempIn = new ArrayList<Integer>();
				for (String a : line.split(" ")) {
					tempIn.add(Integer.parseInt(a));
				}
				temp.add(tempIn);
			}
			
			intGrid = new int[temp.size()][temp.get(0).size()];
			
			for (int a = 0; a < intGrid.length; a++) {
				for (int b = 0; b < intGrid[a].length; b++) {
					intGrid[a][b] = temp.get(a).get(b);
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//*/
        
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
