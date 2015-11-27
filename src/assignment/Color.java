package assignment;

/**
 * Simple color class that stores an RGB-tuple in floating point precision. A valid color component
 * is defined in the range [0, 1].
 *
 */
public class Color {

  public float r, g, b;

  /**
   * Constrain a given value to lie in a defined range between a min and a max value.
   * 
   * @param value to be clamped
   * @param min value
   * @param max value
   * @return clamped value
   */
  public static float clamp(float value, float min, float max) {
    if (value > max)
      value = max;
    if (value < min)
      value = min;
    return value;
  }

  public Color(float r, float g, float b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Initialize from a java.awt.Color instance.
   * 
   * @param color java.awt.Color instance
   */
  public Color(java.awt.Color color) {
    this.r = color.getRed() / 255.0f;
    this.g = color.getGreen() / 255.0f;
    this.b = color.getBlue() / 255.0f;
  }

  /**
   * Convert into a java.awt.Color instance.
   * 
   * @return java.awt.Color instance
   */
  public java.awt.Color getColor() {
    return new java.awt.Color(clamp(r, 0, 1), clamp(g, 0, 1), clamp(b, 0, 1));
  }

  @Override
  public String toString() {
    return "Color(" + r + ", " + g + ", " + b + ")";
  }

}