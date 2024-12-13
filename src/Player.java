import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public boolean sliding = false;
	public boolean previous = true;
	public long startTime; // Slide start time
	Rectangle collider;
	public static float vy = 0;	// Y velocity
	public static float vx = 0;	// X velocity
	float gravity = 0.5f;
	final float max_xvel = 15.0f;
	float xvel_increment = 5.0f;
	final float min_f_cor = 0.3f; // Minimum friction value before it sets it to 0
	public float fric = 0.5f;

	public Player(int xf, int yf) {
		x = xf;
		y = yf;
		movingLeft = false;
		movingRight = false;
		collider = new Rectangle(xf, yf, size, size);
		
	}
	public void friction() {
		if(vx < min_f_cor && vx > -min_f_cor) {
			vx = 0;
		}
		else if(vx > 0) {
			vx -= fric;
		}
		else {
			vx += fric;
		}
	}
	public void draw(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.drawRect(Main.width / 2 - 23, Main.height / 2 - 23, size-4, size-4);
	}
	public void update() {
		friction();
		if(sliding == false)
		{
		if (movingLeft == true) {
			vx -= xvel_increment;
			if(vx < -xvel_increment) {
				vx = -xvel_increment;
			}
			previous = false; // this is false if the player last moved left and true if the player last moved right
		}
		if (movingRight == true) {
			vx += xvel_increment;
			if(vx > xvel_increment) {
				vx = xvel_increment;
			}
			previous = true;
		}
		}else {
			if(System.currentTimeMillis() - startTime >= 750) {
				sliding = false;
			}
			if((movingLeft == true && previous == true) || (movingRight == true && previous == false)){
				sliding = false;
			}
			if(previous) {
				vx = xvel_increment * 2;
			}
			else {
				vx = -xvel_increment * 2;
			}
			
		}
		lastcamx = camx;
		lastcamy = camy;
		camx+=vx;
		camy+=vy;
//		x += vx;
//		y += vy;
		if(!GamePanel.Grounded) {
			vy += gravity;
			if (vy > 40) {
				vy = 40;
			}

		}
//		if(vy>15) {
//			vy = 15;
//		}
		collider.setBounds(x,y,size,size);
	}

	
	
	
}