import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Platform {
    public Vector2D position;
    public int width, height;
    public Rectangle2D collider;

    public Platform(float x_int, float y_int, int width, int height) {
        position = new Vector2D(x_int, y_int);
        this.width = width;
        this.height = height;
        collider = new Rectangle2D.Float(position.x, position.y, width, height);
    }

    public void update() {
        // Update the platform's collider based on the position
        collider.setRect(position.x - Player.camx, position.y - Player.camy, width, height);
    }

    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.GRAY);
        g.fill(new Rectangle2D.Float(position.x - Player.camx, position.y - Player.camy, width, height));

        // Optional: Draw the outline of the platform for debugging
        g.setColor(Color.BLACK);
        g.draw(new Rectangle2D.Float(position.x - Player.camx, position.y - Player.camy, width, height));
    }
}
