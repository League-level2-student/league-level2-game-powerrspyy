import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	ArrayList<Platform> platforms = new ArrayList<>();
	Thread updateLoop = new Thread(() -> {
		while (true) {
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
	public boolean check_grounded() {
		float rayLength = player.size + 3;  // Ray length slightly longer than the player's height
	    float rayStartX = player.position.x + player.size;  // Start ray at the middle of the player
	    float rayStartY = player.position.y + player.size;  // Start ray just below the player
	    Rectangle ray = new Rectangle((int) rayStartX, (int) rayStartY, player.size, (int) rayLength);
	    for (Platform p : platforms) {
	        if (ray.intersects(p.collider)) {
	        	return true;

	        }
	    }
		return false;
	    
	}

	public void check_collisions() {
	    // Raycast checks for all sides
	    // Bottom collision check (grounded)
	    GamePanel.Grounded = check_grounded();

	    // Left collision check (raycast to the left of the player)
	    left = check_left_collision();
	    
	    // Right collision check (raycast to the right of the player)
	    right = check_right_collision();
	    
	    // Top collision check (raycast upwards)
	    top = check_top_collision();

	    // Handle player movement based on the results of raycasts
	    if (left) {
	        player.velocity.x = 0;  // Stop moving left if colliding with something on the left
	        player.position.x = player.position.x - 6;  // Push player to the right (adjust by rayLength)
	    }

	    if (right) {
	        player.velocity.x = 0;  // Stop moving right if colliding with something on the right
	        player.position.x = player.position.x + 6;  // Push player to the left (adjust by rayLength)
	    }

	    if (top) {
	        player.velocity.y = 0;  // Stop upward velocity if colliding with something above
	        player.position.y = player.position.y + 6;  // Push player down (adjust by rayLength)
	    }

	    // For grounded (bottom) collision, stop downward movement and reset position to be on top of the platform
	    if (GamePanel.Grounded) {
	        player.velocity.y = 0;  // Stop downward velocity
	        player.position.y = player.position.y - 6;  // Adjust player position to be above the platform
	    }
	}

	    

//	    if (!grounded_check) {
//	        GamePanel.Grounded = false;
//	    }

//	    if (!y_bottom_check) {
//	        y_bottom = false;
//	    }

	public boolean check_left_collision() {
	    // Cast a ray slightly to the left of the player
	    int rayLength = 3;  // A small distance to check for collisions
	    float rayStartX = player.position.x;  // Start at the left edge of the player
	    float rayStartY = player.position.y;  // Ray starts in the middle vertically

	    Rectangle ray = new Rectangle((int) rayStartX - rayLength, (int) rayStartY, rayLength, player.size);  // Cast a ray to the left
	    for (Platform p : platforms) {
	        if (ray.intersects(p.collider)) {
	            return true;  // Collision detected
	        }
	    }
	    return false;  // No collision
	}

	public boolean check_right_collision() {
	    // Cast a ray slightly to the right of the player
	    int rayLength = 3;  // A small distance to check for collisions
	    float rayStartX = player.position.x + player.size;  // Start at the right edge of the player
	    float rayStartY = player.position.y;  // Ray starts in the middle vertically

	    Rectangle ray = new Rectangle((int) rayStartX, (int) rayStartY, rayLength, player.size);  // Cast a ray to the right
	    for (Platform p : platforms) {
	        if (ray.intersects(p.collider)) {
	            return true;  // Collision detected
	        }
	    }
	    return false;  // No collision
	}

	public boolean check_top_collision() {
	    // Cast a ray upwards from the top of the player
	    int rayLength = 3;  // A small distance to check for collisions
	    float rayStartX = player.position.x;  // Start at the center horizontally
	    float rayStartY = player.position.y;  // Start at the top edge of the player

	    Rectangle ray = new Rectangle((int) rayStartX, (int) rayStartY - rayLength, player.size, rayLength);  // Cast a ray upwards
	    for (Platform p : platforms) {
	        if (ray.intersects(p.collider)) {
	            return true;  // Collision detected
	        }
	    }
	    return false;  // No collision
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
			
			player.jump(Grounded);
			
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
