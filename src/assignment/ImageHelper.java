package assignment;

import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImageHelper {

  /**
   * Saves and displays given image.
   * 
   * @param image image to be saved and displayed
   * @param filename filename for image to be saved
   */
  public static void saveAndDisplay(BufferedImage image, String filename) {
    try {
      if (!ImageIO.write(image, getExtension(filename), new File(filename)))
        System.err.println("Could not find an appropriate image format writer for the extension: " + getExtension(filename));
    } catch (Exception e) {
      System.err.println("Could not write image file: " + e.getMessage());
    }

    ImageIcon ii = new ImageIcon(image);
    JOptionPane.showMessageDialog(null, ii, filename, JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Retrieve extension of a filename.
   * 
   * @param filename the filename from which the extension should be extracted
   * @return file extension
   */
  public static String getExtension(String filename) {
    int i = filename.lastIndexOf('.');
    if (i > 0) {
      return filename.substring(i + 1);
    } else {
      return "";
    }
  }

  /**
   * Create a deep copy of an image.
   * 
   * @param source image to be copied
   * @return deep copy of the given image {@code source}
   */
  public static BufferedImage copy(BufferedImage source) {
    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
    Graphics g = b.getGraphics();
    g.drawImage(source, 0, 0, null);
    g.dispose();
    return b;
  }

}