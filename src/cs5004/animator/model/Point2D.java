package cs5004.animator.model;

/**
 * This class represents a 2D point. This point is denoted in 
 * Cartesian coordinates as (x,y).
 * @author xinyachen
 *
 */
public class Point2D {
  
  private double x;
  private double y;
  
  /**
   * A constructor that takes in x axis and y axis as arguments to create
   * a Point2D object.
   * @param x the x axis of the point
   * @param y the y axis of the point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Get the x coordinate value.
   * @return the x coordinate value
   */
  public double getX() {
    return this.x;
  }
  
  /**
   * Get the y coordinate value.
   * @return the y coordinate value
   */
  public double getY() {
    return this.y;
  }
  
  /**
   * Return a String description of position.
   */
  @Override
  public String toString() {
    String s = "";
    s += "(" + this.x + "," + this.y + ")";
    return s;
  }
}
