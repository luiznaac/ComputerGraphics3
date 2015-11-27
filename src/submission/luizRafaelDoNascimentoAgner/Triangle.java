package submission.luizRafaelDoNascimentoAgner;

import assignment.*;
import static java.awt.Color.WHITE;
import java.awt.Dimension;
import java.awt.Graphics2D;
import static java.lang.Math.abs;

/**
 * A class to represent a triangle that encapsulates its drawing using an arbitrary shader. While
 * drawing this class performs barycentric interpolation for all vertex attributes and feeds the
 * interpolated values into a shader which is solely responsible for the actual shading.
 *
 */
public class Triangle {
    
    private Vertex v0, v1, v2;
    private Shader shader;
    private float area;
  /**
   * Constructor that defines a triangle by three vertices and a shader.
   * 
   * @param v0 first vertex
   * @param v1 second vertex
   * @param v2 third vertex
   * @param shader which does the actual coloring
   */
  public Triangle(Vertex v0, Vertex v1, Vertex v2, Shader shader) {
      this.v0 = v0;
      this.v1 = v1;
      this.v2 = v2;
      this.shader = shader;
      setArea();
  }

  private Triangle(Vertex v0, Vertex v1, Vertex v2){
      this.v0 = v0;
      this.v1 = v1;
      this.v2 = v2;
      setArea();
  }
  
  /**
   * Draw the triangle.
   * 
   * @param context used for drawing
   * @param dimension of the canvas in pixels
   */
  public void draw(Graphics2D context, Dimension dimension) {
      shader.setTriangle(this);
      for(int i = 0 ; i < dimension.getHeight() ; i++){
          for(int j = 0 ; j < dimension.getWidth() ; j++){
              if(isInside(i, j)){
                context.setColor(shader.shade(new Vertex(new Point(i, j), null)).getColor());
                context.drawLine(i, j, i, j);
              }
          }
      }
  }

  /**
   * Draw the triangle with its optional wireframe on top.
   * 
   * @param context used for drawing
   * @param dimension of the canvas in pixels
   * @param wireframe on/off
   */
  public void draw(Graphics2D context, Dimension dimension, boolean wireframe) {
      this.draw(context, dimension);
      if(wireframe)
          drawWireframe(context);
  }

  /**
   * Draw the triangle's wireframe. Lines are drawn in white and the vertices are drawn with its
   * unshaded diffuse color value as circles with a 10 pixel diameter.
   * 
   * Note: The DrawHelper class is quite handy for drawing lines and circles...
   * 
   * @param context used for drawing
   */
  public void drawWireframe(Graphics2D context) {
      int x0, y0, x1, y1, x2, y2;
      x0 = (int)v0.getPoints().x;
      x1 = (int)v1.getPoints().x;
      x2 = (int)v2.getPoints().x;
      y0 = (int)v0.getPoints().y;
      y1 = (int)v1.getPoints().y;
      y2 = (int)v2.getPoints().y;
      context.setColor(WHITE);
      context.drawLine(x0, y0, x1, y1);
      context.drawLine(x1, y1, x2, y2);
      context.drawLine(x2, y2, x0, y0);
      context.setColor(v0.getMaterial().getAmbientColor().getColor());
      context.fillOval(x0 - 5, y0 - 5, 10, 10);
      context.setColor(v1.getMaterial().getAmbientColor().getColor());
      context.fillOval(x1 - 5, y1 - 5, 10, 10);
      context.setColor(v2.getMaterial().getAmbientColor().getColor());
      context.fillOval(x2 - 5, y2 - 5, 10, 10);
  }
  
  /**
   * Calculate the area of the triangle
   */
  private void setArea(){
      float x0, y0, x1, y1, x2, y2;
      x0 = v0.getPoints().x;
      x1 = v1.getPoints().x;
      x2 = v2.getPoints().x;
      y0 = v0.getPoints().y;
      y1 = v1.getPoints().y;
      y2 = v2.getPoints().y;
      area = abs(x1*y2-x1*y0-x0*y2-y1*x2+y1*x0+y0*x2)/2f;
  }
  
  public float getArea(){
      return area;
  }
  
  /*
  * Verify if the point is inside the triangle or not
  */
  private boolean isInside(int i, int j){
      float x0, y0, x1, y1, x2, y2, s, t;
      x0 = v0.getPoints().x;
      x1 = v1.getPoints().x;
      x2 = v2.getPoints().x;
      y0 = v0.getPoints().y;
      y1 = v1.getPoints().y;
      y2 = v2.getPoints().y;
      s = (1/(2*area))*(y0*x2-x0*y2+(y2-y0)*i+(x0-x2)*j);
      t = (1/(2*area))*(x0*y1-y0*x1+(y0-y1)*i+(x1-x0)*j);
        return (s > 0 && t > 0 && (1 - s - t) > 0);
  }
  
  public Vertex getV0(){
      return v0;
  }
  
  public Vertex getV1(){
      return v1;
  }
  
  public Vertex getV2(){
      return v2;
  }
}