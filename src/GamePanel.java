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
		platforms.add(new Platform(700, 1000, 500, 100));
		titleFont = new Font("Arial", Font.PLAIN, 48);
		textFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 60, this);
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
				int dy = Math.round(p.y - Player.camy); // Drawn y
				int dx = Math.round(p.x - Player.camx); // Drawn x
				if (player.y + player.size < dy && Player.vy > 0 && player.x + player.size > dx
						&& player.x > dx + p.w) {
					Player.vy = 0;
					System.out.println("Collision Detected!");
					Player.camx = Player.lastcamx;
					Player.camy = Player.lastcamy;
				}
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {

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
