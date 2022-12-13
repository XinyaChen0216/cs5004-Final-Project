package view;

import model.shape.Shape;

import java.util.List;

/**
 * This represents a view interface.
 * @author xinyachen
 *
 */
public interface ViewInterface {

  /**
   * A display method of view, showing output to the users.
   * @param shapeList a list of shapes that is going to animating
   * @return a String description of the motions
   */
  String displayView();
}
