package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;

import cs5004.animator.util.AnimationBuilder;

/**
 * This represents a ModelImplementation class, which implements ModelInterface
 * interface.
 * 
 * @author xinyachen
 *
 */
public class ModelImplementation implements ModelInterface {

  private List<Shape> shapeList;
  private List<AnimationInterface> all_shape_animation_list;
  private HashMap<String, String> shapeMap;
  private HashSet<String> set;

  /**
   * A constructor that takes a list of shapes as arguments to create
   * ModelImplementation object.
   */
  public ModelImplementation() {
    this.shapeList = new ArrayList<>();
    all_shape_animation_list = new ArrayList<>();
    shapeMap = new HashMap<>();
    set = new HashSet<>();
    //    for (Shape shape: shapeList) {
    //      for (AnimationInterface animation: shape.getAnimationList()) {
    //        this.all_shape_animation_list.add(animation);
    //      }
    //    }
    //    Collections.sort(all_shape_animation_list, new Comparator<AnimationInterface>() {
    //      public int compare(AnimationInterface a, AnimationInterface b) {
    //        if (a.getAnimationTime().getStartTime() == b.getAnimationTime().getStartTime()) {
    //          return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
    //        } else {
    //          return a.getAnimationTime().getStartTime() - b.getAnimationTime().getStartTime();
    //        }
    //      }
    //    });
  }

  public List<Shape> getShapeList() {
    return new ArrayList<Shape>(this.shapeList);
  }

  @Override
  public List<AnimationInterface> get_all_shape_animation_list() {
    return new ArrayList<AnimationInterface>(this.all_shape_animation_list);
  }

  /**
   * Add shapes into the shapeList, and then update the shapeList.
   */
  public void addShape(Shape shape) {
    if (!this.set.contains(shape.getName())) {
      this.shapeList.add(shape);
    }
  }

  /**
   * 
   * @return
   */
  public void addAnimation(AnimationInterface animation) {
    this.all_shape_animation_list.add(animation);
  }

  public HashMap<String, String> getMap() {
    return this.shapeMap;
  }

  public void addMap(String name, String type) {
    this.shapeMap.put(name, type);
  }

  public HashSet<String> getSet() {
    return this.set;
  }

  public void addSet(String name) {
    this.set.add(name);
  }

  public static final class Builder implements AnimationBuilder<ModelInterface> {

    ModelImplementation model;
    int x;
    int y;
    int width;
    int height;

    @Override
    public ModelInterface build() {
      model = new ModelImplementation();
      return model;
    }

    @Override
    public AnimationBuilder<ModelInterface> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<ModelInterface> declareShape(String name, String type) {
      if (type.equals("rectangle") || type.equals("ellipse")) {
        this.model.addMap(name, type);
      }
      return this;
    }

    @Override
    public AnimationBuilder<ModelInterface> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      if (!this.model.set.contains(name)) {
        if (this.model.shapeMap.get(name).equals("rectangle")) {
          Shape currRec = new Rectangle(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
          this.model.addShape(currRec);
        } else if (this.model.shapeMap.get(name).equals("ellipse")) {
          Shape currOval = new Oval(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
          this.model.addShape(currOval);
        }
        this.model.addSet(name);
      }
      Shape currShape = null;
      for (int i = 0; i < this.model.getShapeList().size(); i++) {
        if (this.model.shapeList.get(i).getName().equals(name)) {
          currShape = this.model.getShapeList().get(i);
        }
      }

      if (x1 != x2 || y1 != y2) {
        new Move(currShape, t1, t2, AnimationType.Move, new Point2D(x1, y1), new Point2D(x2, y2));
      }
      if (w1 != w2 || h1 != h2) {
        new Scale(currShape, t1, t2, AnimationType.Scale, w1, h1, w2, h2);
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        new ChangeColor(currShape, t1, t2, AnimationType.ChangeColor, 
            new Color(r1, g1, b1), new Color(r2, g2, b2));
      }
      return this;
    }
  }

}
