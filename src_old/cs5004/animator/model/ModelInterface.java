package model;

import java.util.List;

import model.animation.AnimationInterface;
import model.shape.Shape;

/**
 * This represents a model interface.
 * @author xinyachen
 *
 */
public interface ModelInterface {

  int getX();

  int getY();

  int getWidth();

  int getHeight();

  /**
   * Get a list of shapes.
   * @return a list of shapes
   */
  List<Shape> getShapeList();
  
  /**
   * Add shapes into the shapeList, and then update the shapeList.
   */
  void addShape(Shape shape);
  
  /**
   * Get a list of all shapes' animations. 
   * @return a list of all shapes' animation
   */
  List<AnimationInterface> get_all_shape_animation_list();
  
  /**
   * Add and update the animation list.
   * @param animation the animation that is going to be added into the list
   */
  void addAnimation(AnimationInterface animation);
  
}
