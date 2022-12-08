package model.shape;

/**
 * This represents a Oval class, which extends from ShapeAbstract.
 * @author xinyachen
 *
 */
public class Oval extends ShapeAbstract {
  
  /**
   * A constructor to create Oval objects.
   * @param name the name of this rectangle object
   * @param x the x axis of center of oval
   * @param y the y axis of center of oval
   * @param xRadius the X radius of oval
   * @param yRadius the Y radius of oval
   * @param red the red percentage of color
   * @param green the green percentage of color
   * @param blue the blue percentage of color
   * @param startTime the time of appearing the shape
   * @param endTime the time of disappearing the shape
   */
  public Oval(String name, double x, double y, double xRadius, double yRadius, 
      double red, double green, double blue, int startTime, int endTime) {
    super(name, x, y, xRadius, yRadius, red, green, blue, startTime, endTime);
  }
  
  public String getType() {
    return "ellipse";
  }
  
  /**
   * Generate a animated shape at the current time.
   */
  public Oval generateAnimatedShape(double currTime) {
    this.animatedUpdate(currTime);
    return this;
  }
  
  @Override
  public String toString() {
    String s = "";
    s += "Name: " + this.getName() + "\n";
    s += "Type: " + this.getType() + "\n";
    s += "Center: " + this.center.toString() + ", ";
    s += "X radius: " + this.xRadius + ", ";
    s += "Y radius: " + this.yRadius + ", ";
    s += this.color.toString() + "\n";
    s += this.time.toString();
    return s;
  }
}
