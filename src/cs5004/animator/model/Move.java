package model;

/**
 * This represents a Move class, which extends from AnimationAbstraction.
 * @author xinyachen
 *
 */
public class Move extends AnimationAbstraction {

  private Point2D newPosition;
  
  /**
   * A constructor to create Move objects.
   * @param shape the shape of the moving animation
   * @param startTime the time of appearing the shape
   * @param endTime the time of disappearing the shape
   * @param newPosition the new position of the shape
   */
  public Move(Shape shape, int startTime, int endTime, AnimationType type, 
      Point2D oldPosition, Point2D newPosition) {
    super(shape, startTime, endTime, type);
    this.oldPosition = oldPosition;
    this.newPosition = newPosition;
  }
  
  /**
   * Get the new position of the shape.
   * @return the new position of the shape
   */
  public Point2D getNewPosition() {
    return this.newPosition;
  }
  
  /**
   * Get the old position of the shape.
   * @return the old position of the shape.
   */
  public Point2D getOldPosition() {
    return this.oldPosition;
  }

  /**
   * Get a String description of the moving of the shape.
   */
  @Override
  public String toString() {
    String s = "";
    s += "Shape " + this.shape.getName() + " moves from " + this.oldPosition.toString() 
      + " to " + this.newPosition.toString() + " from t=" + this.time.getStartTime() 
      + " to t=" + this.time.getEndTime();
    return s;
  }
}
