import java.awt.Graphics;

public class Player {
	final int x;
	final int y;
	static float camx;
	static float camy;
	static float lastcamx;
	static float lastcamy;
	int size = 50;
	public boolean movingLeft;
	public boolean movingRight;

	public Player(int xf, int yf) {
		x = xf;
		y = yf;
		movingLeft = false;
		movingRight = false;
	}
	public void draw(Graphics g) {
		g.drawRect(x, y, size, size);
	}
	public void update() {
		if (movingLeft == true) {
			camx-=10;
		}
		if (movingRight == true) {
			camx+=10;
		}
	}
	
	
	
}
