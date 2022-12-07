package cs5004.animator.controller;

import cs5004.animator.model.ModelInterface;
import cs5004.animator.view.ViewInterface;

/**
 * A controller that is to mediate the interactions between model and views.
 * @author xinyachen
 *
 */
public class AnimationController implements AnimationControllerInterface {

  private ModelInterface model;
  private ViewInterface view;

  /**
   * A constructor that takes in model and view as parameters to create a controller object.
   * @param model the model that contains all information
   * @param view the view to displaying information to users
   */
  public AnimationController(ModelInterface model, ViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {
    this.view.displayView(this.model.getShapeList());
  }
}
