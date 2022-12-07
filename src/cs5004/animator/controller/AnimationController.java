package controller;

import model.ModelInterface;
import view.ViewInterface;

/**
 * A controller that is to mediate the interactions between model and views.
 * @author xinyachen
 *
 */
public class AnimationController implements AnimationControllerInterface {

  private ModelInterface model;
  private ViewInterface view;
  
  public AnimationController(ModelInterface model, ViewInterface view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {
   
   this.view.displayView(this.model.getShapeList());
  }
}
