package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * Abstract interface for a shader.
 *
 */
public interface Shader {

  /**
   * This method should compute the shade/color for the given point on a {@code surface}.
   * 
   * @param surface point on the object to be shaded
   * @return computed color value for the given {@code surface} point
   */
  public Color shade(Vertex surface);
  public void setTriangle(Triangle aThis);

}