package controller;

import java.io.IOException;
import java.io.FileWriter;

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
  private Appendable appendable;
  
  public AnimationController(ModelInterface model, ViewInterface view, Appendable appendable) {
    this.model = model;
    this.view = view;
    this.appendable = appendable;
  }

  @Override
  public void run() {
    
   try {
    this.appendable.append(this.view.displayView(this.model.getShapeList()));
    if (this.appendable != System.out) {
      ((FileWriter)this.appendable).flush();
      ((FileWriter)this.appendable).close();
    }
  } catch (IOException e) {
    e.printStackTrace();
  }
  }
}
