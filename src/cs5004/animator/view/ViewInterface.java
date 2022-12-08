package view;

import java.util.List;

import model.ModelInterface;
import model.animation.AnimationInterface;
import model.shape.Shape;

/**
 * This represents a view interface.
 * @author xinyachen
 *
 */
public interface ViewInterface {

  String displayView(List<Shape> shapeList);

  // /**
  //  * Get the view type.
  //  * @return the view type
  //  */
  // String getViewType();
}
