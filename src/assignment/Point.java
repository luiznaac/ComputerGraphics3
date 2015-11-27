package assignment;

/**
 * Simple two/three-dimensional point class that encapsulates its x, y and z components.
 */
public class Point {

  public float x, y, z;

  public Point(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Constructs an instance of a point with the given {@code x} and {@code y} components
   * initializing the z component to zero.
   * 
   * @param x component of a point
   * @param y component of a point
   */
  public Point(float x, float y) {
    this(x, y, 0);
  }

  /**
   * Constructs a new instance with values copied from given point {@code p}.
   * 
   * @param p source point that should be duplicated
   */
  public Point(Point p) {
    this(p.x, p.y, p.z);
  }

  /**
   * Create the origin.
   */
  public Point() {
    this(0, 0, 0);
  }

  @Override
  public String toString() {
    return "Point(" + x + ", " + y + ", " + z + ")";
  }

}