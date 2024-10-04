import java.awt.Graphics;
import java.awt.Rectangle;

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
	Rectangle collider;
	public static int vy = 0;	// Y velocity
	public static int vx = 0;	// X velocity
	int gravity = 1;

	public Player(int xf, int yf) {
		x = xf;
		y = yf;
		movingLeft = false;
		movingRight = false;
		collider = new Rectangle(xf, yf, size, size);
		
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
		camx += vx;
		camy += vy;
		vy += 1;
	}
	
	
	
}
