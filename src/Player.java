import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	 int x;
	 int y;
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
	final int gravity = 1;

	public Player(int xf, int yf) {
		x = xf;
		y = yf;
		movingLeft = false;
		movingRight = false;
		collider = new Rectangle(xf, yf, size, size);
		
	}
	public void draw(Graphics g) {
		g.drawRect(Main.width / 2 - 25, Main.height / 2 - 25, size, size);
	}
	public void update() {
		if (movingLeft == true) {
			camx-=10;
		}
		if (movingRight == true) {
			camx+=10;
		}
		camx+=vx;
		camy+=vy;
		x += vx;
		y += vy;
		vy += gravity;
		collider.setBounds(x,y,size,size);
	}
	
	
	
}
