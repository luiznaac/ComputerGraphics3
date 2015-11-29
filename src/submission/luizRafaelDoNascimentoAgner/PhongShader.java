package submission.luizRafaelDoNascimentoAgner;

import assignment.*;
import static java.lang.Math.pow;

/**
 * A shader that implements the Phong illumination model.
 *
 */
public class PhongShader implements Shader {
   public Triangle triangle;
   public float ka, kd, ks;
   public float a0, a1, a2, s;
   public Point viewer, light;
   public Vertex surface;
  /**
   * @param viewer position
   * @param light position
   * @param ambient light color
   * @param diffuse light color
   * @param specular light color
   * @param exponent specular exponent
   */
  public PhongShader(Point viewer, Point light, java.awt.Color ambient, java.awt.Color diffuse, java.awt.Color specular, float exponent) {
      this.viewer = viewer;
      this.light = light;
      ka = ambient.getRed()/255.0f;
      kd = diffuse.getRed()/255.0f;
      ks = specular.getRed()/255.0f;
      this.s = exponent;
  }

  /**
   * Copy constructor.
   * 
   * @param phong another Phong shader instance
   */
  public PhongShader(PhongShader phong) {
  }
  
  @Override
  public void setTriangle(Triangle triangle){
      this.triangle = triangle;
  }
  
  @Override
  public Color shade(Vertex surface) {
    this.surface = surface;
    this.setValues(surface);
    float r, g, b, ra, ga, ba, dprod, sprod, h;
    Vector reflect, view;
    surface.setNormal(this.calcNormal());
    r = (a0*triangle.getV0().getMaterial().getAmbientColor().r + a1*triangle.getV1().getMaterial().getAmbientColor().r + a2*triangle.getV2().getMaterial().getAmbientColor().r)*ka;
    g = (a0*triangle.getV0().getMaterial().getAmbientColor().g + a1*triangle.getV1().getMaterial().getAmbientColor().g + a2*triangle.getV2().getMaterial().getAmbientColor().g)*ka;
    b = (a0*triangle.getV0().getMaterial().getAmbientColor().b + a1*triangle.getV1().getMaterial().getAmbientColor().b + a2*triangle.getV2().getMaterial().getAmbientColor().b)*ka;
    Vector l = (new Vector(light.x-surface.getPoints().x, light.y-surface.getPoints().y, light.z-surface.getPoints().z)).unitVector();
    dprod = l.dotProduct(surface.getNormal());
    if(dprod > 0){
        ra = a0*triangle.getV0().getMaterial().getDiffuseColor().r + a1*triangle.getV1().getMaterial().getDiffuseColor().r + a2*triangle.getV2().getMaterial().getDiffuseColor().r;
        ga = a0*triangle.getV0().getMaterial().getDiffuseColor().g + a1*triangle.getV1().getMaterial().getDiffuseColor().g + a2*triangle.getV2().getMaterial().getDiffuseColor().g;
        ba = a0*triangle.getV0().getMaterial().getDiffuseColor().b + a1*triangle.getV1().getMaterial().getDiffuseColor().b + a2*triangle.getV2().getMaterial().getDiffuseColor().b;
        r = r + kd*dprod*ra;
        g = g + kd*dprod*ga;
        b = b + kd*dprod*ba;
    } 
    ra = a0*triangle.getV0().getMaterial().getSpecularColor().r + a1*triangle.getV1().getMaterial().getSpecularColor().r + a2*triangle.getV2().getMaterial().getSpecularColor().r;
    ga = a0*triangle.getV0().getMaterial().getSpecularColor().g + a1*triangle.getV1().getMaterial().getSpecularColor().g + a2*triangle.getV2().getMaterial().getSpecularColor().g;
    ba = a0*triangle.getV0().getMaterial().getSpecularColor().b + a1*triangle.getV1().getMaterial().getSpecularColor().b + a2*triangle.getV2().getMaterial().getSpecularColor().b;
    h = l.dotProduct(surface.getNormal());
    reflect = (new Vector(2*h*surface.getNormal().x-l.x, 2*h*surface.getNormal().y-l.y, 2*h*surface.getNormal().z-l.z)).unitVector();
    view = (new Vector(surface.getPoints().x - viewer.x, surface.getPoints().y - viewer.y, surface.getPoints().z - viewer.z)).unitVector();
    sprod = view.dotProduct(reflect);
    r = r + ks*(float)pow(sprod,s)*ra;
    g = g + ks*(float)pow(sprod,s)*ga;
    b = b + ks*(float)pow(sprod,s)*ba;
// System.out.println("L: " + l + " N: " + surface.getNormal() + " R: " + reflect);
    
    return new Color(r, g, b);
  }
  
  public void setValues(Vertex surface){
        a0 = (new SimpleTriangle(triangle.getV1().getPoints(), triangle.getV2().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
        a1 = (new SimpleTriangle(triangle.getV2().getPoints(), triangle.getV0().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
        a2 = (new SimpleTriangle(triangle.getV0().getPoints(), triangle.getV1().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
  }  
  
  private Vector calcNormal(){
        return (new Vector(a0*triangle.getV0().getNormal().x + a1*triangle.getV1().getNormal().x + a2*triangle.getV2().getNormal().x, 
                          a0*triangle.getV0().getNormal().y + a1*triangle.getV1().getNormal().y + a2*triangle.getV2().getNormal().y, 
                          a0*triangle.getV0().getNormal().z + a1*triangle.getV1().getNormal().z + a2*triangle.getV2().getNormal().z)).unitVector();
  }
}