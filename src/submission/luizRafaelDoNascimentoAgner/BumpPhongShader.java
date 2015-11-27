package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * A special shader based on the Phong illumination model that creates bumps in the x-y-plane
 * according to the function:
 * 
 * f(x, y) = sin^2(pi * x) * sin^2(pi * y)
 *
 */
public class BumpPhongShader extends PhongShader {

  /**
   * @param phong a phong shader to use for the actual shading
   * @param cellSize size of the bumps in the x-y-plane measured in pixels
   * @param bumpHeight amplitude of the bumps in positive z-direction (out of the image plane)
   */
  public BumpPhongShader(PhongShader phong, float cellSize, float bumpHeight) {
    super(phong);
    // TODO: implement this method and the rest of this class necessary for the assignment
  }

}