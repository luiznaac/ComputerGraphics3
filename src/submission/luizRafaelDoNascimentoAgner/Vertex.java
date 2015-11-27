package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * This class encapsulates a vertex of a two/three-dimensional object. It stores all its attributes
 * like the spatial position, the material and the normal.
 *
 */
public class Vertex {
    private Point position;
    private Material material;
    private Vector normal;
  /**
   * @param position in three-dimensional space
   * @param material of the surface point
   * @param normal of the surface point
   */
  public Vertex(Point position, Material material, Vector normal) {
      this.position = position;
      this.material = material;
      this.normal = normal;
  }

  /**
   * Constructor that initializes the normal to point along the positive z-axis (out of the image
   * plane).
   * 
   * @param position in three-dimensional space
   * @param material of the surface point
   */
  public Vertex(Point position, Material material) {
    this(position, material, new Vector(0, 0, 1));
  }
  
  public void setNormal(Vector normal){
      this.normal = normal;
  }
  
  public Point getPoints(){
      return position;
  }
  
  public Material getMaterial(){
      return material;
  }
  
  public Vector getNormal(){
      return normal;
  }
}