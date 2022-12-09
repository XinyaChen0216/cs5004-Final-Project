package model.shape;


/**
 * This represents a Rectangle class, which extends from ShapeAbstract.
 * 
 * @author xinyachen
 *
 */
public class Rectangle extends ShapeAbstract {

  /**
   * A constructor to create Rectangle objects.
   * 
   * @param name      the name of this rectangle object
   * @param x         the x axis of lower left corner of rectangle
   * @param y         the y axis of lower left corner of rectangle
   * @param width     the width of rectangle
   * @param height    the height of rectangle
   * @param red       the red percentage of color
   * @param green     the green percentage of color
   * @param blue      the blue percentage of color
   * @param startTime the time of appearing the shape
   * @param endTime   the time of disappearing the shape
   */
  public Rectangle(String name, double x, double y, double width, double height, double red,
      double green, double blue, int startTime, int endTime) {
    super(name, x, y, width, height, red, green, blue, startTime, endTime);
  }

  public String getType() {
    return "rectangle";
  }

  /**
   * Generate a animated rectangle at the current time.
   */
  public Rectangle generateAnimatedShape(double currTime) {
    this.animatedUpdate(currTime);
    return this;
  }

  /**
   * A string description of this rectangle object.
   */
  @Override
  public String toString() {
    String s = "";
    s += "Name: " + this.getName() + "\n";
    s += "Type: " + this.getType() + "\n";
    s += "Min corner: " + this.center.toString() + ", ";
    s += "Width: " + this.xRadius + ", ";
    s += "Height: " + this.yRadius + ", ";
    s += this.color.toString() + "\n";
    s += this.time.toString();
    return s;
  }
}
