import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Font titleFont;
	Timer frameDraw;
	Font textFont;
	Player player;
	ArrayList<Platform> platforms = new ArrayList<>();

	public GamePanel() {
		platforms.add(new Platform(10, 10, 50, 50));
		platforms.add(new Platform(700, 700, 500, 300));
		titleFont = new Font("Arial", Font.PLAIN, 48);
		textFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 200, this);
		player = new Player(Main.width / 2 - 25, Main.height / 2 - 25);
		frameDraw.start();

	}

	public void draw(Graphics g) {
		player.draw(g);
		for (Platform p : platforms) {
			p.draw(g);
		}

	}

	public void check_collisions() {
		for (Platform p : platforms) {
			if (player.collider.intersects(p.collider)) {
				System.out.println("HELLO");
				int m = 20;
				int dy = Math.round(p.y - Player.camy); // Drawn y
				int dx = Math.round(p.x - Player.camx); // Drawn x
//				if (player.y + player.size > dy && Player.vy > 0 && player.x + player.size > dx
//						&& player.x <= dx + p.w) {
//					Player.vy = 0;
//					
//					System.out.println("Collision Detected!");
////					Player.camx = Player.lastcamx;
//					Player.camy = p.y-(player.y+player.size);
//					System.out.println("hi");
////					player.y = dy-player.size;
//				}
				
	// X_COLLISIONS
				if(player.x + m > dy + p.w ) {
					// Right collision
				}
				else if(player.x + player.size - m < dy) {
					// Left collision
				}
				
				
				
				
				
	// Y_COLLISIONS
				if(player.y+m > dy+p.h) {
					// Bottom collision
					Player.vy = 0;
					Player.camy = p.y-(player.y+player.size) + p.h + player.size;
					 
				}
				else if(player.y + player.size - m < dy) {
					// Top collision
					Player.vy = 0;
					Player.camy = p.y-(player.y+player.size);

				}
				
				
				
				
				
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawLine(0, player.y + player.size - 20, 1000, player.y + player.size - 20);
		draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.movingLeft = true;
			

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.movingRight = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			Player.vy = -5;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.movingLeft = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.movingRight = false;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player.lastcamx = Player.camx;
		Player.lastcamy = Player.camy;

		update();

		check_collisions();
//		System.out.println(Player.camx + " " + Player.camy);
		repaint();
	}

	public void update() {
		for (Platform p : platforms) {
			p.update();
		}
		player.update();

	}
}
