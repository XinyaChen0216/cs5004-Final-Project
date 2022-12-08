package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.animation.AnimationInterface;
import model.animation.AnimationType;
import model.animation.ChangeColor;
import model.animation.Move;
import model.animation.Scale;
import model.shape.Oval;
import model.shape.Rectangle;
import model.shape.Shape;

import java.util.HashSet;

import util.AnimationBuilder;
import util.Color;
import util.Point2D;

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
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * A constructor that takes a list of shapes as arguments to create
   * ModelImplementation object.
   */
  public ModelImplementation() {
    shapeList = new ArrayList<>();
    all_shape_animation_list = new ArrayList<>();
    shapeMap = new HashMap<>();
    set = new HashSet<>();
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
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
    // if (!this.set.contains(shape.getName())) {
    this.shapeList.add(shape);
    // }
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

  // /**
  // * Get a String description of ModelImplementation.
  // */
  // @Override
  // public String toString() {
  // String s = "";
  //
  // s += "Shape:\n";
  //
  // for (int i = 0; i < this.shapeList.size(); i++) {
  // s += this.shapeList.get(i).toString();
  // if (i != this.shapeList.size()) {
  // s += "\n";
  // s += "\n";
  // }
  // }
  //
  // int all_shape_animation_list_size = this.all_shape_animation_list.size();
  // for (int i = 0; i < all_shape_animation_list_size; i++) {
  // s += this.all_shape_animation_list.get(i).toString();
  // if (i != all_shape_animation_list_size - 1) {
  // s += "\n";
  // }
  // }
  // return s;
  // }

  public static final class Builder implements AnimationBuilder<ModelInterface> {

    ModelImplementation model = new ModelImplementation();

    @Override
    public ModelInterface build() {
      return model;
    }

    @Override
    public AnimationBuilder<ModelInterface> setBounds(int x, int y, int width, int height) {
      this.model.x = x;
      this.model.y = y;
      this.model.width = width;
      this.model.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<ModelInterface> declareShape(String name, String type) {
      if (type.equals("rectangle") || type.equals("ellipse")) {
        this.model.shapeMap.put(name, type);
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
          this.model.shapeList.add(currRec);
        } else if (this.model.shapeMap.get(name).equals("ellipse")) {
          Shape currOval = new Oval(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
          this.model.shapeList.add(currOval);
        }
        this.model.set.add(name);
      } else {
        Shape currShape = null;
        for (int i = 0; i < this.model.shapeList.size(); i++) {
          if (this.model.shapeList.get(i).getName().equals(name)) {
            currShape = this.model.shapeList.get(i);
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
      }
      return this;
    }
  }

}
