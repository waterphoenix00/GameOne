package engine;


import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static final String GAME_NAME = "Game Name";
	
	public static void main(String[] args) {
		new GameFrame();
	}
	
	
	public GameFrame() {
		super(GAME_NAME);
		add(new GamePanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(true);
		setVisible(true);
	}
}
