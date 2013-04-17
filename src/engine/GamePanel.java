package engine;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	private static final int FPS = 60;
	private static final long PERIOD = 1000000000/FPS; //Period in ns.
	private static final int MAX_SKIPS = 5;
	
	private static final int PWIDTH = 1024;
	private static final int PHEIGHT = 576;
	
	private boolean running = false;
	private Thread animator;
	
	//private Graphics dbg;
	private Image dbImage;
	
	private ArrayList<GameObject> gameObjects;
	private GameObject player;
	private Grid foreground;
	
	public GamePanel() {
		setBackground(Color.BLACK);
		setSize(PWIDTH, PHEIGHT);
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	
	
	
	private void startGame() {
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	}

	@Override
	public void run() {
		long startTime;
		long endTime;
		int timeDifference;
		
		running = true;
		
		while(running) {
			
			startTime = System.nanoTime();
			
			update();
			render();
			paint();
			
			endTime = System.nanoTime();
			timeDifference = (int)(endTime - startTime);
			
			/*
			 * System.out.println(startTime + " " + endTime + " " 
					+ timeDifference + " " + PERIOD);
			*/
			
			try {
				if (timeDifference >= PERIOD) {
					for (int i = 0; i < MAX_SKIPS &&
							timeDifference >= PERIOD; i++) {
						update();
						timeDifference -= PERIOD;
					}
				} else {
					Thread.sleep((PERIOD-timeDifference)/1000000);
				}
			} catch (Exception e) {
				System.out.println("Sleep Error");
			}
			
		}

	}
	
	private void update() {
		
	}
	
	private void render() {
		
	}
	
	private void paint() {
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (dbImage != null)) {
				g.drawImage(dbImage, 0, 0, null);
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	}
	
	public class PlayerListener extends KeyAdapter {
		/*public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}*/
	}

}
