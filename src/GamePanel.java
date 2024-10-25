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
	public boolean grounded_check;
	Timer frameDraw;
	Font textFont;
	Player player;
	public static boolean Grounded = false;
	ArrayList<Platform> platforms = new ArrayList<>();
	Thread updateLoop = new Thread(() -> {
		while (true) {
			Player.lastcamx = Player.camx;
			Player.lastcamy = Player.camy;

			check_collisions();
//			System.out.println(Player.camx + " " + Player.camy);
			if (Player.camy > 10000) {
				restart();
			}

		}
	});

	public GamePanel() {
		platforms.add(new Platform(10, 10, 50, 50));
		platforms.add(new Platform(700, 700, 500, 300));
		platforms.add(new Platform(1400, 600, 200, 20));
		platforms.add(new Platform(1395, 400, 210, 20));
		
		titleFont = new Font("Arial", Font.PLAIN, 48);
		textFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 60, this);
		player = new Player(Main.width / 2 - 25, Main.height / 2 - 25);
		frameDraw.start();
		updateLoop.start();

	}

	public void restart() {
		Player.camy = 0;
		Player.camx = 0;

	}

	public void draw(Graphics g) {
		player.draw(g);
		for (Platform p : platforms) {
			p.draw(g);
		}

	}

	public void check_collisions() {
		grounded_check = false;

		for (Platform p : platforms) {
			if (player.collider.intersects(p.collider)) {
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
				if (player.x + m > dx + p.w) {
					// Right collision
					Player.vx = 0;
					Player.camx = p.x - (player.x + player.size) + p.w + player.size;
				} else if (player.x + player.size - m < dx) {
					// Left collision
					Player.vx = 0;
					Player.camx = p.x - (player.x + player.size);
				}

				// Y_COLLISIONS
				if (player.y + m > dy + p.h) {
					// Bottom collision
					Player.vy = 0;
					Player.camy = p.y - (player.y + player.size) + p.h + player.size;

				} else if (player.y + player.size - m < dy) {
					// Top collision
					if(Grounded) {
						grounded_check = true;
					}else{
					Player.vy = 0;
					Player.camy = p.y - (player.y + player.size)+1;
					Grounded = true;
					grounded_check = true;}

				}
				

			}
		}
		if(!grounded_check) {
			Grounded = false;}


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
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			Player.vy = -15;
			
			Grounded = false;
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
//		Player.lastcamx = Player.camx;
//		Player.lastcamy = Player.camy;
//
//		update();
//
//		check_collisions();
////		System.out.println(Player.camx + " " + Player.camy);
//		if(Player.camy > 10000) {
//			restart();
//		}
		update();
		repaint();
	}

	public void update() {
		for (Platform p : platforms) {
			p.update();
		}
		player.update();

	}
}
