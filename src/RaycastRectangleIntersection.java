import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class RaycastRectangleIntersection {
	
    // Method to check if the ray intersects with any rectangle in the list
    public static boolean doesRayIntersectAny(ArrayList<Platform> platforms, int startX, int startY, int endX, int endY) {
        // Iterate over each rectangle in the list and check for intersection
        for (Platform plat : platforms) {
            if (doesRayIntersect(plat.collider, startX, startY, endX, endY)) {
                return true;  // Return true as soon as we find an intersection
            }
        }
        return false;  // Return false if no intersection is found with any rectangle
    }
    
    // Method to check if the ray intersects with the rectangle
    public static boolean doesRayIntersect(Rectangle2D collider, int startX, int startY, int endX, int endY) {
        // Define the four edges of the rectangle as line segments
        Line2D.Double top = new Line2D.Double(collider.getMinX(), collider.getMinY(), collider.getMaxX(), collider.getMinY());
        Line2D.Double bottom = new Line2D.Double(collider.getMinX(), collider.getMaxY(), collider.getMaxX(), collider.getMaxY());
        Line2D.Double left = new Line2D.Double(collider.getMinX(), collider.getMinY(), collider.getMinX(), collider.getMaxY());
        Line2D.Double right = new Line2D.Double(collider.getMaxX(), collider.getMinY(), collider.getMaxX(), collider.getMaxY());

        // Create the ray (line segment from start to end)
        Line2D.Double ray = new Line2D.Double(startX, startY, endX, endY);

        // Check for intersection with all sides of the rectangle
        return ray.intersectsLine(top) || ray.intersectsLine(bottom) || ray.intersectsLine(left) || ray.intersectsLine(right);
    }
}