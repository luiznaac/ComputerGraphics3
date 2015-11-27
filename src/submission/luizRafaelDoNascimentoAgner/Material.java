package submission.luizRafaelDoNascimentoAgner;

import assignment.*;

/**
 * Class to encapsulate the ambient, diffuse and specular material colors of a surface.
 *
 */
public class Material {

  public Color ca, cd, cs;

  public Material(Color ambient, Color diffuse, Color specular) {
    this.ca = ambient;
    this.cd = diffuse;
    this.cs = specular;
  }

  /**
   * Initialze from a java.awt.Color instance and treat this instance as the ambient and diffuse
   * color coefficient of the created material.
   * 
   * @param diffuse color coefficient
   */
  public Material(java.awt.Color diffuse) {
    this.ca = new Color(diffuse);
    this.cd = new Color(diffuse);
    this.cs = new Color(java.awt.Color.WHITE);
  }
  
  public Color getAmbientColor(){
      return ca;
  }
  
  public Color getDiffuseColor(){
      return cd;
  }

  public Color getSpecularColor(){
      return cs;
  }
  
  @Override
  public String toString() {
    return "Material(" + ca + ", " + cd + ", " + cs + ")";
  }

}