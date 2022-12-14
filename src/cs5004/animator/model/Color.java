package cs5004.animator.model;

/**
 * This represents a Color class, which consists of three fields. They
 * are red, green and blue. 
 * @author xinyachen
 *
 */
public class Color {

  private double red;
  private double green;
  private double blue;
  
  /**
   * A constructor that takes in red, green and blue percentages as parameters to create 
   * Color objects. 
   * @param red the percentage of red in color
   * @param green the percentage of green in color
   * @param blue the percentage of blue in color
   * @throws IllegalArgumentException when the percentage is not within the range of [0.0,1.0], or 
   *         when the total percentage is not added up to 1.
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (red < 0 || red > 1.0 || green < 0 || green > 1.0 || blue < 0 || blue > 1.0) {
      throw new IllegalArgumentException("Invalid value(s). "
          + "Each value should be in the range of [0,1].");
    }
    
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  /**
   * Get the red value.
   * @return the red value
   */
  public double getRed() {
    return this.red;
  }
  
  /**
   * Get the green value.
   * @return the green value
   */
  public double getGreen() {
    return this.green;
  }
  
  /**
   * Get the blue value.
   * @return the blue value
   */
  public double getBlue() {
    return this.blue;
  }
  
  /**
   * Return a String description of color.
   */
  @Override
  public String toString() {
    String s = "";
    s += "Color: (" + this.red + "," + this.green + "," + this.blue + ")";
    return s;
  }
}
