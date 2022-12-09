package model.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.animation.AnimationInterface;
import model.animation.AnimationType;
import model.animation.ChangeColor;
import model.animation.Move;
import model.animation.Scale;
import util.Color;
import util.Point2D;
import util.Time;

/**
 * This represents a shape abstract class, which implements Shape interface.
 * 
 * @author xinyachen
 *
 */
public abstract class ShapeAbstract implements Shape {

  protected String name;
  protected Point2D center;
  protected double xRadius;
  protected double yRadius;
  protected Color color;
  protected Time time;
  protected List<AnimationInterface> animationList;

  /**
   * A constructor to create a shape object, either rectangle or oval.
   * 
   * @param name      the name of this shape object
   * @param x         the x axis of center of oval, or the x axis of lower left
   *                  corner of rectangle
   * @param y         the y axis of center of oval, or the y axis of lower left
   *                  corner of rectangle
   * @param xRadius   the X radius of oval, or the width of rectangle
   * @param yRadius   the Y radius of oval, or the height of rectangle
   * @param red       the red percentage of color
   * @param green     the green percentage of color
   * @param blue      the blue percentage of color
   * @param startTime the time of appearing the shape
   * @param endTime   the time of disappearing the shape
   * @throws IllegalArgumentException when the xRadius and/or yRadius is
   *                                  non-positive
   */
  public ShapeAbstract(String name, double x, double y, double xRadius, double yRadius, double red,
      double green, double blue, int startTime, int endTime) throws IllegalArgumentException {
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException(
          "The xRadius/width and " + "yRadius/height should be positive.");
    }
    this.name = name;
    this.center = new Point2D(x, y);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.color = new Color(red, green, blue);
    this.time = new Time(startTime, endTime);
    this.animationList = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public Color getColor() {
    return this.color;
  }

  public Point2D getPosition() {
    return this.center;
  }

  public double getXRadius() {
    return this.xRadius;
  }

  public double getYRadius() {
    return this.yRadius;
  }

  public Time getTime() {
    return this.time;
  }

  public List<AnimationInterface> getAnimationList() {
    return this.animationList;
  }

  /**
   * Common code for the generateAnimatedShape methods.
   * 
   * @param currTime the current time
   */
  public void animatedUpdate(double currTime) {
    double updatedX = this.center.getX();
    double updatedY = this.center.getY();
    double updatedWidth = this.xRadius;
    double updatedHeight = this.yRadius;
    double updatedRed = this.color.getRed();
    double updatedGreen = this.color.getGreen();
    double updatedBlue = this.color.getBlue();
    List<AnimationInterface> animationList = this.getAnimationList();
    Collections.sort(animationList, new Comparator<AnimationInterface>() {
      public int compare(AnimationInterface a, AnimationInterface b) {
        if (a.getAnimationTime().getStartTime() == b.getAnimationTime().getStartTime()) {
          return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
        } else {
          return a.getAnimationTime().getStartTime() -
              b.getAnimationTime().getStartTime();
        }
      }
    });
    for (int i = 0; i < animationList.size(); i++) {
      AnimationInterface animation = animationList.get(i);
      if (animation.getAnimationTime().getEndTime() < currTime) {
        if (animation.getType() == AnimationType.Move) {
          updatedX = ((Move) animation).getNewPosition().getX();
          updatedY = ((Move) animation).getNewPosition().getY();
        } else if (animation.getType() == AnimationType.ChangeColor) {
          updatedRed = ((ChangeColor) animation).getNewColor().getRed();
          updatedGreen = ((ChangeColor) animation).getNewColor().getGreen();
          updatedBlue = ((ChangeColor) animation).getNewColor().getBlue();
        } else if (animation.getType() == AnimationType.Scale) {
          updatedWidth = ((Scale) animation).getNewWidth();
          updatedHeight = ((Scale) animation).getNewHeight();
        }
      } else if (animation.getAnimationTime().getStartTime() <= currTime
          && animation.getAnimationTime().getEndTime() >= currTime) {
        if (animation.getType() == AnimationType.Move) {
          updatedX = (((Move) animation).getOldPosition().getX()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((Move) animation).getNewPosition().getX()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());

          updatedY = (((Move) animation).getOldPosition().getY()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((Move) animation).getNewPosition().getY()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());
        } else if (animation.getType() == AnimationType.ChangeColor) {
          updatedRed = (((ChangeColor) animation).getOldColor().getRed()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((ChangeColor) animation).getNewColor().getRed()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());
          updatedGreen = (((ChangeColor) animation).getOldColor().getGreen()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((ChangeColor) animation).getNewColor().getGreen()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());
          updatedBlue = (((ChangeColor) animation).getOldColor().getBlue()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((ChangeColor) animation).getNewColor().getBlue()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());
        } else if (animation.getType() == AnimationType.Scale) {
          updatedWidth = (((Scale) animation).getOldWidth()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((Scale) animation).getNewWidth()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());

          updatedHeight = (((Scale) animation).getOldHeight()
              * (animation.getAnimationTime().getEndTime() - currTime)
              + ((Scale) animation).getNewHeight()
                  * (currTime - animation.getAnimationTime().getStartTime()))
              / (animation.getAnimationTime().getEndTime()
                  - animation.getAnimationTime().getStartTime());
        }
      }
    }
    System.out.println(currTime);
    System.out.println(this.name);
    System.out.println(updatedX);
    System.out.println(updatedY);
    System.out.println();
    this.center = new Point2D(updatedX, updatedY);
    this.xRadius = updatedWidth;
    this.yRadius = updatedHeight;
    this.color = new Color(updatedRed, updatedGreen, updatedBlue);
  }
}