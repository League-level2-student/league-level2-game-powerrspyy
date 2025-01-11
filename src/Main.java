import java.awt.*;

import javax.swing.JFrame;

public class Main {
	GamePanel gamePanel = new GamePanel();

	static Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 

// width will store the width of the screen 
	public static final int width = (int)size.getWidth(); 

// height will store the height of the screen 
	public static final int height = (int)size.getHeight();
	
	
	JFrame frame;// = new JFrame();

	public Main() {
		frame = new JFrame("GLItCHeD");
//		System.out.println(width + " " + height); 

		// frame.setTitle("")

	}

	public void setup() {
		frame.addKeyListener(gamePanel);
		frame.setSize(width, height);
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setup();
	}
	

}