import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Platform {
	int x;
	int y;
	int w;
	int h;
	boolean slime;
	boolean ice;
	boolean invis;
	boolean victoryPlat;
	Rectangle2D collider;
	public Platform(int x_int, int y_int, int width, int height, boolean isSlime, boolean isIce, boolean isInvis, boolean victoryBlock) {
		x = x_int;
		y = y_int;
		w = width;
		h = height;
		slime = isSlime;
		ice = isIce;
		invis = isInvis;
		victoryPlat = victoryBlock;
		collider = new Rectangle2D.Float(x,y,w,h);
	}
	public Platform(int x_int, int y_int, int width, int height, boolean isSlime, boolean isIce, boolean isInvis) {
		this(x_int, y_int, width, height, isSlime, isIce, isInvis, false);
	}
	public void update() {
		collider.setRect((x - Player.camx), (y-Player.camy), w, h);
	}
	public void draw(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		
		if(victoryPlat) {
			g.setColor(new Color(255, 202, 79));
		}
		
		if(slime && ice) {
			g.setColor(new Color(110, 219, 187));
		}
		else if(slime) {
			g.setColor(new Color(110, 219, 125));
		}
		else if(ice) {
			g.setColor(new Color(110, 219, 250));
		}
		else if(invis) {
			g.setColor(new Color(235, 235, 235));
		}
		g.draw(new Rectangle2D.Float((x - Player.camx), (y-Player.camy), (float)w, (float)h));
		g.fill(new Rectangle2D.Float((x - Player.camx), (y-Player.camy), (float)w, (float)h));
		g.setColor(Color.BLACK);
//		g.drawRect(x, (int)(Main.width / 2 - 25+(y-Player.camy)), w, h);
//		g.drawRect(x, y, w, h);
	}
}


//g.fillRect(x, Main.width / 2 - 25+(y-Player.camy), width, height);
//Main.width / 2 - 25