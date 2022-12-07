package cs5004.animator.view;

import cs5004.animator.model.Shape;

import java.util.List;

/**
 * This represents a view interface.
 * @author xinyachen
 *
 */
public interface ViewInterface {

  /**
   * To display view to users.
   * @param shapeList the list of shape
   * @return the view to users
   */
  String displayView(List<Shape> shapeList);

  /**
   * Get the view type.
   * @return the view type
   */
  String getViewType();
}
