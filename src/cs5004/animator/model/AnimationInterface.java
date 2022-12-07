package model;

/**
 * This represents a Animation interface, which has three methods. 
 * The shape can be moving, scaling, and changing colors. 
 * @author xinyachen
 *
 */
public interface AnimationInterface {

  /**
   * Get animation time of the shape.
   * @return animation time of the shape
   */
  Time getAnimationTime();
  
  /**
   * Get the name of animation shape.
   * @return the name of animation shape
   */
  String getShapeName();
  
  /**
   * Get the type of animation.
   * @return the type of animation
   */
  public AnimationType getType();
}
