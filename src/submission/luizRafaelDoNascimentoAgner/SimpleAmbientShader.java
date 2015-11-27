package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * This shader simply returns the ambient material color of the {@code surface} point to be shaded.
 *
 */
public class SimpleAmbientShader implements Shader {
  
  private Triangle triangle;
  private float a0, a1, a2;
  
  private void setValues(Vertex surface){
        a0 = (new SimpleTriangle(triangle.getV1().getPoints(), triangle.getV2().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
        a1 = (new SimpleTriangle(triangle.getV2().getPoints(), triangle.getV0().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
        a2 = (new SimpleTriangle(triangle.getV0().getPoints(), triangle.getV1().getPoints(), surface.getPoints())).getArea()/triangle.getArea();
  }  
  
  @Override
  public Color shade(Vertex surface) {
    this.setValues(surface);
    float r, g, b;
    r = a0*triangle.getV0().getMaterial().getAmbientColor().r + a1*triangle.getV1().getMaterial().getAmbientColor().r + a2*triangle.getV2().getMaterial().getAmbientColor().r;
    g = a0*triangle.getV0().getMaterial().getAmbientColor().g + a1*triangle.getV1().getMaterial().getAmbientColor().g + a2*triangle.getV2().getMaterial().getAmbientColor().g;
    b = a0*triangle.getV0().getMaterial().getAmbientColor().b + a1*triangle.getV1().getMaterial().getAmbientColor().b + a2*triangle.getV2().getMaterial().getAmbientColor().b;
    return new Color(r, g, b);
  }

  @Override
  public void setTriangle(Triangle triangle){
      this.triangle = triangle;
  }
  
}