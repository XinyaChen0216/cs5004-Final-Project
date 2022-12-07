package cs5004.animator.model;

/**
 * This represents ChangeColor class, which extends from AnimationAbstration class. 
 * @author xinyachen
 *
 */
public class ChangeColor extends AnimationAbstraction {

  private Color newColor;
  
  /**
   * A constructor that creates a ChangeColor object. 
   * @param shape the shape of the changing color animation
   * @param startTime the time of appearing the shape
   * @param endTime the time of disappearing the shape
   * @param newColor the new color of the shape
   */
  public ChangeColor(Shape shape, int startTime, int endTime, AnimationType type, 
      Color oldColor, Color newColor) {
    super(shape, startTime, endTime, type);
    this.oldColor = oldColor;
    this.newColor = newColor;
  }
  
  /**
   * Get the new color of the shape.
   * @return the new color of the shape
   */
  public Color getNewColor() {
    return this.newColor;
  }
  
  /**
   * Get the old color of the shape.
   * @return the old color of the shape
   */
  public Color getOldColor() {
    return this.oldColor;
  }
  
  /**
   * Get a String description of changing color of the shape. 
   */
  @Override
  public String toString() {
    String s = "";
    s += "Shape " + this.shape.getName() + " changes color from " + this.oldColor.toString()
      + " to " + this.newColor.toString() + " from t=" + this.time.getStartTime()
      + " to t=" + this.time.getEndTime();
    return s;
  }
}
