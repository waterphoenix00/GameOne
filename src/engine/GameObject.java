package engine;

import java.awt.Graphics;

public interface GameObject {
	public void update();
	public void draw(Graphics g, double cameraX, double cameraY);
	public double getX();
	public double getY();
	public double getHeight();
	public double getWidth();
}
