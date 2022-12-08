package model.shape;

import java.util.List;

import model.animation.AnimationInterface;
import util.Color;
import util.Point2D;
import util.Time;

/**
 * This represents a Shape interface.
 * @author xinyachen
 *
 */
public interface Shape {
  
  /**
   * Get the name of the shape.
   * @return the name of the shape
   */
  String getName();
  
  /**
   * Get the type of the shape.
   * @return the type of the shape
   */
  String getType();
  
  /**
   * Get the color of the shape.
   * @return the color of the shape.
   */
  Color getColor();
  
  /**
   * Get the position of the shape.
   * @return the position of the shape.
   */
  Point2D getPosition();
  
  /**
   * Get the XRadius of oval or the width of rectangle.
   * @return the XRadius of oval or the width of rectangle.
   */
  double getXRadius();
  
  /**
   *Get the YRadius of oval or the height of rectangle.
   *@return the YRadius of oval or the height of rectangle.
   */
  double getYRadius();
  
  /**
   * Get the time of appearing and disappear the shape.
   * @return the time of appearing and disappear the shape
   */
  Time getTime();
  
  /**
   * Get the list of this shape object's animation.
   * @return the list of this shape object's animation.
   */
  List<AnimationInterface> getAnimationList();
  
  /**
   * Generate a new shape object at the current time.
   * @param currTime current time
   * @return a new shape object at the current time
   */
  Shape generateAnimatedShape(double currTime);
}

