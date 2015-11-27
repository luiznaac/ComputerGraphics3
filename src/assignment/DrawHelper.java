package assignment;

import java.awt.Graphics2D;

public class DrawHelper {

  /**
   * Draw a line between the given points after rounding both points to its nearest integer
   * position.
   * 
   * @param context used for drawing
   * @param a first point
   * @param b second point
   */
  public static void drawLine(Graphics2D context, Point a, Point b) {
    try {
      context.drawLine(Math.round(a.x), Math.round(a.y), Math.round(b.x), Math.round(b.y));
    } catch (NullPointerException e) {
    }
  }

  /**
   * Draw a solid circle at the given position.
   * 
   * @param context used for drawing
   * @param p center point of the circle
   */
  public static void drawPoint(Graphics2D context, Point p) {
    try {
      context.fillOval(Math.round(p.x) - 5, Math.round(p.y) - 5, 10, 10);
    } catch (NullPointerException e) {
    }
  }

}