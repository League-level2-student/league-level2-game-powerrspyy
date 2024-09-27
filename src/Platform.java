import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {
	int x;
	int y;
	int w;
	int h;
	Rectangle collisionBox;
	public Platform(int x_int, int y_int, int width, int height) {
		x = x_int;
		y = y_int;
		w = width;
		h = height;
		collisionBox = new Rectangle(x,y,w,h);
	}
	public void update() {
		collisionBox.setBounds(Math.round(x - Player.camx), Math.round(y-Player.camy), w, h);
	}
	public void draw(Graphics g) {
		g.drawRect(Math.round(x - Player.camx), Math.round(y-Player.camy), w, h);
	}
}