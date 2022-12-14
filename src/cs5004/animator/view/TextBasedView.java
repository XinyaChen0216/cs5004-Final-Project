package cs5004.animator.view;

import cs5004.animator.model.AnimationInterface;
import cs5004.animator.model.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This represents a TextBasedView, which implements ViewInterface.
 * @author xinyachen
 *
 */
public class TextBasedView implements ViewInterface {
  private String viewType;
  private int tempo;
  
  /**
   * A constructor that takes in viewType and tempo as parameters to create a 
   * text based view object. 
   * @param viewType the type of this view
   * @param tempo the speed of diaplaying the view
   */
  public TextBasedView(String viewType, int tempo) {
    this.viewType = viewType;
    this.tempo = tempo;
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    String s = "";

    s += "Shape:\n";

    for (int i = 0; i < shapeList.size(); i++) {
      s += shapeList.get(i).toString();
      if (i != shapeList.size()) {
        s += "\n";
        s += "\n"; 
      }
    }
    
    List<AnimationInterface> all_shape_animation_list = new ArrayList<>();

    for (Shape shape: shapeList) {
      for (AnimationInterface animation: shape.getAnimationList()) {
        all_shape_animation_list.add(animation);
      }
    }
    Collections.sort(all_shape_animation_list, new Comparator<AnimationInterface>() {
      public int compare(AnimationInterface a, AnimationInterface b) {
        if (a.getAnimationTime().getStartTime() == b.getAnimationTime().getStartTime()) {
          return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
        } else {
          return a.getAnimationTime().getStartTime() - b.getAnimationTime().getStartTime();
        }
      }
    });
    
    int all_shape_animation_list_size = all_shape_animation_list.size();
    
    Collections.sort(all_shape_animation_list, new Comparator<AnimationInterface>() {
      public int compare(AnimationInterface a, AnimationInterface b) {
        
        if (a.getAnimationTime().getStartTime() == b.getAnimationTime().getStartTime()) {
          return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
        } else {
          return a.getAnimationTime().getStartTime() - b.getAnimationTime().getStartTime();
        }
      }
    });
    
    for (int i = 0; i < all_shape_animation_list_size ; i++) {
      s += all_shape_animation_list.get(i).toString();
      if (i != all_shape_animation_list_size - 1) {
        s += "\n";
      }
    } 
    return s;
  }

  @Override
  public String getViewType() {
    return "text";
  }
}
