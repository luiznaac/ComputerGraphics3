package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * This class encapsulates a three-dimensional vector.
 *
 */
public class Vector {
    public float x, y, z;
    
  public Vector(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector unitVector(){
      float norm;
      norm = (float)Math.sqrt(x*x+y*y+z*z);
      return new Vector(x/norm, y/norm, z/norm);
  }
  
  public float dotProduct(Vector v){
      return x*v.x+y*v.y+z*v.z;
  }
  
  public Vector crossProduct(Vector v){
      float x1, y1, z1;
      x1 = v.x;
      y1 = v.y;
      z1 = v.z;
      return new Vector(y*z1 - y1*z, x1*z - x*z1, x*y1 - x1*y);
  }
  
  @Override
  public String toString(){
      return "(" + x + ", " + y + ", " + z + ")";
  }
}