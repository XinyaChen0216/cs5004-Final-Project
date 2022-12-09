package model.animation;

import model.shape.Shape;
import util.Color;
import util.Point2D;
import util.Time;

import java.util.List;

/**
 * This represents an abstract Animation class. 
 * @author xinyachen
 *
 */
public abstract class AnimationAbstraction implements AnimationInterface {

  protected Shape shape;
  protected Time time;
  protected Color oldColor;
  protected double originalWidth;
  protected double originalHeight;
  protected Point2D oldPosition;
  protected AnimationType type;
  
  /**
   * A constructor that takes in shape, start time, and end time as parameters
   * to create a animation objects.
   * @param shape the shape of the animation
   * @param startTime the time of appearing the shape
   * @param endTime the time of disappearing the shape
   * @throws IllegalArgumentException when the start time and/or end time is negative, or 
   *         when end time is less than start time
   */
  public AnimationAbstraction(Shape shape, int startTime, int endTime, AnimationType type) 
      throws IllegalArgumentException {
    this.time = new Time(startTime, endTime);
    this.shape = shape;
    this.type = type;
    // if (this.time.getEndTime() < this.shape.getTime().getStartTime()
    //     || this.time.getStartTime() > this.shape.getTime().getEndTime()) {
    //   throw new IllegalArgumentException("Shape cannot be animated before "
    //       + "it appears or after it disappears.");
    // }
     
    int list_size = this.shape.getAnimationList().size();
    if (list_size == 0) {
      this.shape.getAnimationList().add(this);
    } else {
      List<AnimationInterface> l = this.shape.getAnimationList();
      boolean canBeAdded = true;
      for (int i = 0; i < list_size; i++) {
        if (this.type.equals(l.get(i).getType())) {
          //the animations of same type cannot be performed in overlapped time
          if (!(this.time.getEndTime() <= l.get(i).getAnimationTime().getStartTime() 
              || this.time.getStartTime() >= l.get(i).getAnimationTime().getEndTime())) {
            canBeAdded = false;
            break;
          }
        }
      }
      if (canBeAdded) {
        l.add(this);
      }
    }
  }
  
  public AnimationType getType() {
    return this.type;
  }
  
  public String getShapeName() {
    return this.shape.getName();
  }
  
  public Time getAnimationTime() {
    return this.time;
  }
}
