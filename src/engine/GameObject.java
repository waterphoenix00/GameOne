package engine;

import java.awt.Graphics;

public interface GameObject {
	public void update();
	public void draw(Graphics g, int cameraX, int cameraY);
	public int getX();
	public int getY();
	public int getHeight();
	public int getWidth();
}
