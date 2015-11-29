package submission.luizRafaelDoNascimentoAgner;

import assignment.Color;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

/**
 * A special shader based on the Phong illumination model that creates bumps in the x-y-plane
 * according to the function:
 * 
 * f(x, y) = sin^2(pi * x) * sin^2(pi * y)
 *
 */
public class BumpPhongShader extends PhongShader {
    private float cellSize, bumpHeight;
    private float ka, kd, ks;
    PhongShader phong;
    private Triangle triangle;
  /**
   * @param phong a phong shader to use for the actual shading
   * @param cellSize size of the bumps in the x-y-plane measured in pixels
   * @param bumpHeight amplitude of the bumps in positive z-direction (out of the image plane)
   */
  public BumpPhongShader(PhongShader phong, float cellSize, float bumpHeight) {
      super(phong);
      this.phong = phong;
      this.cellSize = cellSize;
      this.bumpHeight = bumpHeight;
      s = phong.s;
      ka = phong.ka;
      ks = phong.ks;
      kd = phong.kd;
  }

  @Override
  public Color shade(Vertex surface) {
    phong.setValues(surface);
    a0 = phong.a0;
    a1 = phong.a1;
    a2 = phong.a2;
    float r, g, b, ra, ga, ba, dprod, sprod, h;
    Vector reflect, view;
    surface.setNormal(this.calcBNormal(surface));
    r = (a0*triangle.getV0().getMaterial().getAmbientColor().r + a1*triangle.getV1().getMaterial().getAmbientColor().r + a2*triangle.getV2().getMaterial().getAmbientColor().r)*ka;
    g = (a0*triangle.getV0().getMaterial().getAmbientColor().g + a1*triangle.getV1().getMaterial().getAmbientColor().g + a2*triangle.getV2().getMaterial().getAmbientColor().g)*ka;
    b = (a0*triangle.getV0().getMaterial().getAmbientColor().b + a1*triangle.getV1().getMaterial().getAmbientColor().b + a2*triangle.getV2().getMaterial().getAmbientColor().b)*ka;
    Vector l = (new Vector(phong.light.x-surface.getPoints().x, phong.light.y-surface.getPoints().y, phong.light.z-surface.getPoints().z)).unitVector();
    dprod = l.dotProduct(surface.getNormal());
    if(dprod > 0){
        ra = a0*triangle.getV0().getMaterial().getDiffuseColor().r + a1*triangle.getV1().getMaterial().getDiffuseColor().r + a2*triangle.getV2().getMaterial().getDiffuseColor().r;
        ga = a0*triangle.getV0().getMaterial().getDiffuseColor().g + a1*triangle.getV1().getMaterial().getDiffuseColor().g + a2*triangle.getV2().getMaterial().getDiffuseColor().g;
        ba = a0*triangle.getV0().getMaterial().getDiffuseColor().b + a1*triangle.getV1().getMaterial().getDiffuseColor().b + a2*triangle.getV2().getMaterial().getDiffuseColor().b;
        r = r + kd*dprod*ra;
        g = g + kd*dprod*ga;
        b = b + kd*dprod*ba;
    }
    //System.out.println(a0 + ", " + a1 + ", " + a2);
    ra = a0*triangle.getV0().getMaterial().getSpecularColor().r + a1*triangle.getV1().getMaterial().getSpecularColor().r + a2*triangle.getV2().getMaterial().getSpecularColor().r;
    ga = a0*triangle.getV0().getMaterial().getSpecularColor().g + a1*triangle.getV1().getMaterial().getSpecularColor().g + a2*triangle.getV2().getMaterial().getSpecularColor().g;
    ba = a0*triangle.getV0().getMaterial().getSpecularColor().b + a1*triangle.getV1().getMaterial().getSpecularColor().b + a2*triangle.getV2().getMaterial().getSpecularColor().b;
    h = l.dotProduct(surface.getNormal());
    reflect = (new Vector(2*h*surface.getNormal().x-l.x, 2*h*surface.getNormal().y-l.y, 2*h*surface.getNormal().z-l.z)).unitVector();
    view = (new Vector(surface.getPoints().x - phong.viewer.x, surface.getPoints().y - phong.viewer.y, surface.getPoints().z - phong.viewer.z)).unitVector();
    sprod = view.dotProduct(reflect);
    r = r + ks*(float)pow(sprod,s)*ra;
    g = g + ks*(float)pow(sprod,s)*ga;
    b = b + ks*(float)pow(sprod,s)*ba; 
// System.out.println("L: " + l + " N: " + surface.getNormal() + " R: " + reflect);
   // System.out.println(r + ", " + g + ", " + b);
    return new Color(r, g, b);
  }
  
  private Vector calcBNormal(Vertex surface){
      float x0, y0, z0, x1, y1, z1, x2, y2, z2, fx, fy;
      int val = 5;
      Vector u, v;
      x0 = surface.getPoints().x;
      y0 = surface.getPoints().y;
      z0 = bumpHeight*(float)pow(sin(PI*x0/cellSize)*sin(PI*y0/cellSize), 2);
      surface.getPoints().z = z0;
      y1 = y0;
      x2 = x0;
      x1 = x0 + val;
      y2 = y0 + val;
      fx = (float)(2*bumpHeight*sin(x0*PI/cellSize)*cos(x0*PI/cellSize)*pow(sin(y0*PI/cellSize), 2)*PI);
      fy = (float)(2*bumpHeight*sin(y0*PI/cellSize)*cos(y0*PI/cellSize)*pow(sin(x0*PI/cellSize), 2)*PI);
      z1 = fx*(x1-x0)+fy*(y1-y0)+z0;
      z2 = fx*(x2-x0)+fy*(y2-y0)+z0;
      u = new Vector(x1-x0, y1-y0, z1-z0);
      v = new Vector(x2-x0, y2-y0, z2-z0);
      return (u.crossProduct(v)).unitVector();
  }
  
  @Override
  public void setTriangle(Triangle triangle){
      phong.triangle = triangle;
      this.triangle = triangle;
  }
}