package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerObject implements GameObject {
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int[] vel = {0,0};
	private int[] acc = {0,1};
	
	private static final String player= "charizard.png";
	private Image playerImg;
	
	private GamePanel gp;
	private Tile[][] tiles;
	private static final int TILE_SIZE = 64;
	
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
	}

	@Override
	public void update() {
		System.out.println(vel[1]);
		accelerate();
		move(0, 1);
		if(collidingObjects()) {
			move(0, -1);
		}
		if (collidingTiles()) {
			System.out.println("here");
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
			System.out.println("here1");
			moveBackTile(1);
			vel[1] = 0;
			while (collidingTiles()) {
				moveBackTile(1);
			}
		}
	}

	@Override
	public void draw(Graphics g, int cameraX, int cameraY) {
		g.drawImage(playerImg, x -cameraX - (playerImg.getWidth(null)-width)/2
				, y-cameraY - (playerImg.getHeight(null)-height)/2, null);
	}
	
	private void accelerate() {
		vel[0] += acc[0];
		vel[1] += acc[1];
	}
	
	private boolean collidingObjects() {
		ArrayList<GameObject> objects = gp.getGameObjects();
		for (GameObject obj: objects) {
			if (new Rectangle(x, y, width, height).intersects(
					new Rectangle(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight()))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean collidingTiles() {
		for (int i = (x+1)/TILE_SIZE; i <= (x+width-1)/TILE_SIZE; i++) {
			for (int j = (y+1)/TILE_SIZE; j <= (y+height-1)/TILE_SIZE; j++) {
				if (tiles[j][i].getCollidable())
					return true;
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
			vel[1] = -20;
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
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

}
