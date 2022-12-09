package controller;

import model.ModelInterface;
import view.ViewInterface;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A controller that is to mediate the interactions between model and views.
 * @author xinyachen
 *
 */
public class AnimationController implements AnimationControllerInterface {

  private ModelInterface model;
  private ViewInterface view;
  private Appendable appendable;
  
  /**
   * A constructor that takes in model, view, and appendable as parameters to create
   * controller object. 
   * @param model the model of this program
   * @param view the view of this program
   * @param appendable append the output to the appendable and show it to users
   */
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
