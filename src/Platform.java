import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {
	int x;
	int y;
	int w;
	int h;
	Rectangle collider;
	public Platform(int x_int, int y_int, int width, int height) {
		x = x_int;
		y = y_int;
		w = width;
		h = height;
		collider = new Rectangle(x,y,w,h);
	}
	public void update() {
		collider.setBounds(x, y, w, h);
	}
	public void draw(Graphics g) {
//		g.drawRect(Math.round(x - Player.camx), Math.round(y-Player.camy), w, h);
		g.drawRect(x, (int)(Main.width / 2 - 25+(y-Player.camy)), w, h);
	}
}


//g.fillRect(x, Main.width / 2 - 25+(y-Player.camy), width, height);
//Main.width / 2 - 25