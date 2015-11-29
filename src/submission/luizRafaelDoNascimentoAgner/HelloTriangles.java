package submission.luizRafaelDoNascimentoAgner;

import assignment.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HelloTriangles {

  public static void main(String[] args) {
    helloTriangles();
  }

  public static void helloTriangles() {
    // create a new 500x500 pixel sized image
    Dimension dim = new Dimension(500, 500);
    BufferedImage img = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

    // create simple 2D drawing tools and fill canvas with background color
    Graphics2D g = img.createGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, dim.width, dim.height);

    // define some materials for our triangles
    Material red = new Material(Color.RED);
    Material green = new Material(Color.GREEN);
    Material blue = new Material(Color.BLUE);

    // define the vertices for the first triangle
    Vertex a = new Vertex(new Point(10, 200), red);
    Vertex b = new Vertex(new Point(200, 10), green);
    Vertex c = new Vertex(new Point(370, 460), blue);
    
    // create a simple shader for the first triangle
    Shader simple = new SimpleAmbientShader();

    // create and draw the first triangle
    Triangle t1 = new Triangle(a, b, c, simple);
    t1.draw(g, dim, true);
    BufferedImage img1 = assignment.ImageHelper.copy(img); // save image 1
    
    // define a new vertex for the second triangle
    Vertex d = new Vertex(new Point(30, 450), blue);

    // create a phong shader for the second and third triangle
    Point viewer = new Point(dim.width / 2, dim.height / 2, 500);
    Point light = new Point(0, 500, 500);
    Color la = Color.DARK_GRAY;
    Color ld = Color.GRAY;
    Color ls = Color.WHITE;
    PhongShader phong = new PhongShader(viewer, light, la, ld, ls, 20);

    // create and draw the second triangle
    Triangle t2 = new Triangle(a, c, d, phong);
    t2.draw(g, dim);

    // define new vertices with custom normals for the third triangle
    Vertex v0 = new Vertex(new Point(220, 10), red, new Vector(-1, 1, 1));
    Vertex v1 = new Vertex(new Point(490, 10), red, new Vector(0.5f, -0.5f, 1));
    Vertex v2 = new Vertex(new Point(490, 190), red, new Vector(-1, 1, 1));

    // create and draw the third triangle
    Triangle t3 = new Triangle(v0, v1, v2, phong);
    t3.draw(g, dim, true);
    t1.drawWireframe(g); // draw wireframe of the first triangle on top again
    BufferedImage img2 = assignment.ImageHelper.copy(img); // save image 2

    // define a new vertex for the fourth triangle
    Vertex e = new Vertex(new Point(490, 200), green);

    // create a bump shader for the fourth triangle
    Shader bump = new BumpPhongShader(phong, 50, 0.3f);

    // create and draw the fourth triangle
    Triangle t4 = new Triangle(b, e, c, bump);
    t4.draw(g, dim);
    t1.drawWireframe(g); // draw wireframe of the first triangle on top again
    BufferedImage img3 = assignment.ImageHelper.copy(img); // save image 3

    // ATTENTION: Do not change the following code lines for your submission!
    // You are required to use ImageHelper.saveAndDisplay(...) from the package assignment
    // to display and save your solution images.
    assignment.ImageHelper.saveAndDisplay(img1, "SimpleTriangle.png");
    assignment.ImageHelper.saveAndDisplay(img2, "PhongTriangles.png");
    assignment.ImageHelper.saveAndDisplay(img3, "BumpyTriangles.png");
  }

}