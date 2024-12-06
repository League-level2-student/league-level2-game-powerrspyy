
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
//	public boolean x_right_check;
//	public boolean x_left_check;
	public boolean y_bottom_check;
	Timer frameDraw;
	Font textFont;
	Player player;
	public volatile static boolean Grounded = false;
//	public volatile static boolean x_right = false;
//	public volatile static boolean x_left = false;
	public volatile static boolean y_bottom = false;
	public boolean top;
	public boolean bottom;
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public boolean xleft;
	public boolean xright;
	public boolean x_coll = false;
	public volatile static boolean touchingGround = false;
	public int frames = 1;
	ArrayList<Platform> platforms = new ArrayList<>();
	Thread updateLoop = new Thread(() -> {
		while (true) {
			Player.lastcamx = Player.camx;
			Player.lastcamy = Player.camy;
			touchingGround = RaycastRectangleIntersection.doesRayIntersectAny(platforms, (int) player.x, player.y, (int) player.x - 1, (int) player.y + player.size + 1) || RaycastRectangleIntersection.doesRayIntersectAny(platforms, (int) player.x + player.size, player.y, (int) player.x + player.size + 1, (int) player.y + player.size + 1) || RaycastRectangleIntersection.doesRayIntersectAny(platforms, (int) player.x + player.size/2, player.y, (int) player.x + player.size/2, (int) player.y + player.size + 1);
			check_collisions();
			if (Player.camy > 10000) {
				restart();
			}

		}
	});

	public GamePanel() {
		platforms.add(new Platform(120, 120, 100, 50));
		platforms.add(new Platform(700, 700, 500, 300));
		platforms.add(new Platform(1400, 600, 200, 20));
		platforms.add(new Platform(1395, 400, 210, 20));
		platforms.add(new Platform(1100, 300, 100, 20));
		platforms.add(new Platform(655, 200, 125, 20));
		platforms.add(new Platform(0, -30, 100, 20));
		platforms.add(new Platform(-5, -303, 110, 20));
		platforms.add(new Platform(-7, -576, 124, 20));
		
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
		Player.vy = 0;
		Player.vx = 0;

	}

	public void draw(Graphics g) {
		player.draw(g);
		for (Platform p : platforms) {
			p.draw(g);
		}

	}

	public void check_collisions() {
		grounded_check = false;
		y_bottom_check = false;
		

		for (Platform p : platforms) {
			if (player.collider.intersects(p.collider)) {
				int m = 50;
				
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
				// Relative to platform
				bottom = (player.y + m > dy + p.h);
				top = (player.y + player.size - m < dy);
				right = (player.x + m > dx + p.w);
				left = (player.x + player.size - m < dx);
				// Player directions
				up = Player.vy < 0;
				down = Player.vy > 0;
				xleft = Player.vx > 0;
				xright = Player.vx <0;
				// Y_COLLISIONS
				if (bottom && up) {
					// Bottom collision

					if(y_bottom) {
						y_bottom_check = true;
					}else {
					Player.vy = 0;
					Player.camy = p.y - (player.y + player.size) + p.h + player.size - 1;
					y_bottom = true;
					y_bottom_check = true;
					}

				} else if (top && down) {
					// Top collision

					if(Grounded) {
						grounded_check = true;
					}else{
					Player.vy = 0;
					Player.camy = p.y - (player.y + player.size)+1;
					Grounded = true;
					grounded_check = true;}

				}

				// X_COLLISIONS
				else if (right && xright && (up || down)) {
					x_coll = true;
					// Right collision
//					if(x_right) {
//						x_right_check = true;
//					}else {
					Player.vx = 0;
					Player.camx = p.x - (player.x + player.size) + p.w + player.size;
//					x_right = true;
//					x_right_check = true;
//					}
				} else if (left && xleft && (up || down)) {
					x_coll = true;
					// Left collision
//					if(x_left) {
//						x_left_check = true;
//					}else {
					Player.vx = 0;
					Player.camx = p.x - (player.x + player.size);
//					x_left = true;
//					x_left_check = true;
//					}
				}

				

			}
		}
		if(!grounded_check) {
			Grounded = false;}
		if(!y_bottom_check) {
			y_bottom = false;}


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

				Grounded = false;
				if(touchingGround) {
					Player.vy = -15;
				}
				

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			if(touchingGround) {
				player.sliding = true;
				player.startTime = System.currentTimeMillis();
			}
					}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			restart();
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
		frames += 1;
		if(frames == 2) {
			repaint();
			frames = 1;
		}
			}

	public void update() {
		for (Platform p : platforms) {
			p.update();
		}
		player.update();

	}
}
