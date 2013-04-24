package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerObject implements GameObject {
	
	private double x;
	private double y;
	
	private double width;
	private double height;
	
	private double[] vel = {0,0};
	private double[] acc = {0,.5};
	
	private static final String player= "imgs/charizard.png";
	private Image playerImg;
	
	private GamePanel gp;
	private Tile[][] tiles;
	private static final int TILE_SIZE = Tile.TILE_SIZE;
	
	private boolean phased;
	
	private boolean alive;
	
	/**
	 * This constructs a player object.
	 * 
	 * @param gp The GamePanel associated with this PlayerObject.
	 * @param x Starting x position.
	 * @param y Starting y position.
	 * @param w Width of this PlayerObject.
	 * @param h Height of this PlayerObject.
	 */
	
	public PlayerObject(GamePanel gp, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		this.gp = gp;
		tiles = gp.getGrid().getTileGrid();
		playerImg = new ImageIcon(this.getClass().getResource(player)).getImage();
		phased = false;
		setAlive(true);
	}

	@Override
	public void update() {
		accelerate();
		move(0, 1);
		if(collidingObjects()) {
			move(0, -1);
		}
		if (collidingTiles()) {
			moveBackTile(0);
			vel[0] = 0;
			while (collidingTiles()) {
				moveBackTile(0);
			}
		}
		
		move(1, 1);
		if(collidingObjects()) {
			move(1, -1);
		}
		if (collidingTiles()) {
			moveBackTile(1);
			vel[1] = 0;
			while (collidingTiles()) {
				moveBackTile(1);
			}
		}
		System.out.println("player update");
		tiles[(int)((y+height-1)/TILE_SIZE)][(int)((x+width/2)/TILE_SIZE)].objectUpdate(this);
	}

	@Override
	public void draw(Graphics g, double cameraX, double cameraY) {
		g.drawImage(playerImg, (int)(x -cameraX - (playerImg.getWidth(null)-width)/2)
				, (int)(y-cameraY - (playerImg.getHeight(null)-height)/2), null);
	}
	
	private void accelerate() {
		vel[0] += acc[0];
		vel[1] += acc[1];
	}
	
	private boolean collidingObjects() {
		ArrayList<GameObject> objects = gp.getGameObjects();
		for (GameObject obj: objects) {
			if (new Rectangle((int)x, (int)y, (int)width, (int)height).intersects(
					new Rectangle((int)obj.getX(), (int)obj.getY(), 
							(int)obj.getWidth(), (int)obj.getHeight()))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean collidingTiles() {
		for (int i = (int)((x+1)/TILE_SIZE); i <= (x+width-1)/TILE_SIZE; i++) {
			for (int j = (int)((y+1)/TILE_SIZE); j <= (y+height-1)/TILE_SIZE; j++) {
				if (phased) {
					if (tiles[j][i].getPhaseable())
						return true;
					if (tiles[j][i].getCollidable())
						return true;
				} else {
					if (tiles[j][i].getCollidable())
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Moves the player back based on tile collision.
	 * 
	 * @param axis What axis the move was on.
	 */
	
	private void moveBackTile(int axis) {
		if (axis == 0 && vel[0] > 0) {
			if ((x+width)%TILE_SIZE == 0) {
				x -= TILE_SIZE;
			} else {
				x -= ((x+width)%TILE_SIZE);
			}
		} else if (axis == 1 && vel[1] > 0) {
			if ((y+height)%TILE_SIZE == 0) {
				x -= TILE_SIZE;
			} else {
				y -= ((y+height)%TILE_SIZE);
			}
		} else if (axis == 0 && vel[0] < 0) {
			if (x%TILE_SIZE == 0) {
				x += TILE_SIZE;
			} else {
				x += TILE_SIZE - (x%TILE_SIZE);
			}
		} else if (axis == 1 && vel[1] < 0) {
			if (y%TILE_SIZE == 0) {
				y += TILE_SIZE;
			} else {
				y += TILE_SIZE - (y%TILE_SIZE);
			}
		}
	}
	
	/**
	 * Moves the player based on a directed axes, direction, and percent
	 * 
	 * @param axis The axis to move on. "0" is x, "1" is y, "2" is both
	 * @param dirDelta The direction and percent change based on this double.
	 * 
	 */
	
	private void move(int axis, double dirDelta) {
		if (axis == 0) {
			x += dirDelta * vel[0];
		} else if (axis == 1) {
			y += dirDelta * vel[1];
		} else {
			x += dirDelta * vel[0];
			y += dirDelta * vel[1];
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vel[0] = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			vel[0] = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			vel[1] = -10;
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			phased = !phased;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vel[0] = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			vel[0] = 0;
		}
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}
	
	public boolean getPhased() {
		return phased;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
