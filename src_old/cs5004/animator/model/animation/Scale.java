package model.animation;

import model.shape.Shape;

/**
 * This represents Scale class, which extends from AnimationAbstration class. 
 * @author xinyachen
 *
 */
public class Scale extends AnimationAbstraction {

  private double newWidth;
  private double newHeight;
  
  /**
   * A constructor to create Scale objects.
   * @param shape the shape of the scaling animation
   * @param startTime the time of appearing the shape
   * @param endTime the time of disappearing the shape
   * @param newWidth the new width of the shape
   * @param newHeight the new height of the shape
   */
  public Scale(Shape shape, int startTime, int endTime, AnimationType type, double originalWidth, 
      double originalHeight, double newWidth, double newHeight) {
    super(shape, startTime, endTime, type);
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }
  
  /**
   * Get the old width of the shape.
   * @return the old width of the shape
   */
  public double getNewWidth() {
    return this.newWidth;
  }
  
  /**
   * Get the old height of the shape.
   * @return the old height of the shape
   */
  public double getNewHeight() {
    return this.newHeight;
  }
  
  /**
   * Get the old width of the shape.
   * @return the old width of the shape
   */
  public double getOldWidth() {
    return this.originalWidth;
  }
  
  /**
   * Get the old height of the shape.
   * @return the old height of the shape
   */
  public double getOldHeight() {
    return this.originalHeight;
  }
  
  /**
   * Get a String description of scaling of the shape.
   */
  @Override
  public String toString() {
    String s = "";
    s += "Shape " + this.shape.getName() + " scales from Width: " + this.originalWidth
        + ", Height: " + this.originalHeight + " to Width: " + this.newWidth 
        + ", Height: " + this.newHeight + " from t=" + this.time.getStartTime() 
        + " to t=" + this.time.getEndTime();
    return s;
  }
}
