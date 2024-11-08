import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Platform {
	int x;
	int y;
	int w;
	int h;
	Rectangle2D collider;
	public Platform(int x_int, int y_int, int width, int height) {
		x = x_int;
		y = y_int;
		w = width;
		h = height;
		collider = new Rectangle2D.Float(x,y,w,h);
	}
	public void update() {
		collider.setRect((x - Player.camx), (y-Player.camy), w, h);
	}
	public void draw(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.draw(new Rectangle2D.Float((x - Player.camx), (y-Player.camy), (float)w, (float)h));
//		g.drawRect(x, (int)(Main.width / 2 - 25+(y-Player.camy)), w, h);
//		g.drawRect(x, y, w, h);
	}
}


//g.fillRect(x, Main.width / 2 - 25+(y-Player.camy), width, height);
//Main.width / 2 - 25