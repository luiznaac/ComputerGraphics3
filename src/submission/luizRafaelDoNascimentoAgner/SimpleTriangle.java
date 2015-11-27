package submission.luizRafaelDoNascimentoAgner;

import assignment.*;
import static java.lang.Math.abs;

/**
 *
 * @author Luiz Agner
 */
public class SimpleTriangle {
    
    private Point a, b, c;
    private float area;
    
    public SimpleTriangle(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
        setArea();
    }
    
    private void setArea(){
      float x0, y0, x1, y1, x2, y2;
      x0 = a.x;
      x1 = b.x;
      x2 = c.x;
      y0 = a.y;
      y1 = b.y;
      y2 = c.y;
      area = abs(x1*y2-x1*y0-x0*y2-y1*x2+y1*x0+y0*x2)/2f;
  }
    
    public float getArea(){
        return area;
    }
    
}
