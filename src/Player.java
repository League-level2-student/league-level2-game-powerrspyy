import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Player {
    public static float camx;
	public static float camy;
	public Vector2D position;
    public Vector2D velocity;
    public int size = 50;
    public Rectangle collider;
    public boolean movingLeft;
    public boolean movingRight;

    private float gravity = 0.5f;
    private final float max_xvel = 5.0f;
    private float xvel_increment = 1.0f;
    private final float min_friction = 0.3f;
    private float friction = 0.6f;

    public Player(float xf, float yf) {
        this.position = new Vector2D(xf, yf);
        this.velocity = new Vector2D();
        this.collider = new Rectangle((int) position.x, (int) position.y, size, size);
    }

    // Apply friction to the velocity
    public void applyFriction() {
        if (Math.abs(velocity.x) < min_friction) {
            velocity.x = 0;
        } else if (velocity.x > 0) {
            velocity.x -= friction;
        } else {
            velocity.x += friction;
        }
    }

    // Update the player movement
    public void update() {
        applyFriction();

        // Update position based on velocity
        position = position.add(velocity);

        if (!GamePanel.Grounded) {
            velocity.y += gravity;  // Apply gravity if not grounded
        }

        // Update collider position (adjust to camera view)
        collider = updateCollider();
        if(movingLeft && !movingRight) {
        	moveLeft();
        }
        if(movingRight && !movingLeft) {
        	moveRight();
        }
        
    }

    // Update the collider bounds based on the player's position
    public Rectangle updateCollider() {
        Rectangle c = new Rectangle((int) position.x, (int) position.y, size, size);
        return c;
        // Optionally, you can also update the collider as needed
    }

    // Handle player movement input
    public void moveLeft() {
        velocity.x -= xvel_increment;
        if (velocity.x < -max_xvel) {
            velocity.x = -max_xvel;
        }
    }

    public void moveRight() {
        velocity.x += xvel_increment;
        if (velocity.x > max_xvel) {
            velocity.x = max_xvel;
        }
    }

    public void jump(boolean grounded) {
        if (grounded) {
            velocity.y = -15;  // Jump with an initial upward velocity
        }
    }

    // Draw the player to the screen
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.BLUE);
        g.fillRect((int) (position.x - Player.camx), (int) (position.y - Player.camy), size, size);
    }
}
