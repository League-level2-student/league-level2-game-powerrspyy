public class Vector2D {
    public float x, y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Add two vectors
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    // Subtract two vectors
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    // Scale the vector by a scalar
    public Vector2D scale(float scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Get the length (magnitude) of the vector
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    // Normalize the vector (make its length 1)
    public Vector2D normalize() {
        float len = length();
        if (len != 0) {
            return scale(1.0f / len);
        }
        return this;
    }

    // Copy the vector
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }
}
