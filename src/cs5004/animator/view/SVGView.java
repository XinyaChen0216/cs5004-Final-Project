package view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.animation.AnimationInterface;
import model.animation.AnimationType;
import model.animation.ChangeColor;
import model.animation.Move;
import model.animation.Scale;
import model.shape.Shape;

/**
 * This represents a SVG view, which implements ViewInterface.
 * @author xinyachen
 *
 */
public class SVGView implements ViewInterface {
  
  private String viewType;
  private int tempo;
  
  public SVGView(String viewType, int tempo) {
    this.viewType = viewType;
    this.tempo = tempo;
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    String svg = "";
    svg += "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n";
    //List<Shape> shapeList = model.getShapeList();
    int size =  shapeList.size();

    for (int i = 0; i < size; i++) {
      Shape shape = shapeList.get(i);
      List<AnimationInterface> animationList = shape.getAnimationList();
      Collections.sort(animationList, new Comparator<AnimationInterface>() {
        public int compare(AnimationInterface a, AnimationInterface b) {
          if (a.getAnimationTime().getStartTime() == b.getAnimationTime().getStartTime()) {
            return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
          } else {
            return a.getAnimationTime().getStartTime() - b.getAnimationTime().getStartTime();
          }
        }
      });

      if (shape.getType().equals("rectangle")) {
        
        svg += "<rect id=\"" + shape.getName() + "\" x=\"" + shape.getPosition().getX() + 
            "\" y=\"" + shape.getPosition().getY() + "\" width=\"" + shape.getXRadius() + 
            "\" height=\"" + shape.getYRadius() + "\" fill=\"rgb(" + shape.getColor().getRed() +
            "," + shape.getColor().getGreen() + "," + shape.getColor().getBlue() + ")\" " + 
            "visibility=\"visible\" >\n";

        for (int j = 0; j < animationList.size(); j++) {
          AnimationInterface animation = animationList.get(j); 
          String reusedCode1 = "<animate attributeType=\"xml\" begin=\"" + animation.getAnimationTime().getStartTime()*1000 +
              "ms\" dur=\"" + (animation.getAnimationTime().getEndTime() - animation.getAnimationTime().getStartTime())*1000 +
              "ms\" attributeName=\"";
          if (animation.getType() == AnimationType.Move) {
            if (shape.getPosition().getX() != ((Move) animation).getNewPosition().getX()) {
              svg += reusedCode1 + "x\" from=\"" + shape.getPosition().getX() + "\" to=\"" + ((Move) animation).getNewPosition().getX() +
                  "\" fill=\"freeze\" />\n";
            }
            if (shape.getPosition().getY() != ((Move) animation).getNewPosition().getY()) {
              svg += reusedCode1 + "y\" from=\"" + shape.getPosition().getY() + "\" to=\"" + ((Move) animation).getNewPosition().getY() +
                  "\" fill=\"freeze\" />\n";
            }
          } else if(animation.getType() == AnimationType.Scale) {
            if (shape.getXRadius() != ((Scale) animation).getNewWidth()) {
              svg += reusedCode1 + "width\" from=\"" + shape.getXRadius() + "\" to=\"" + ((Scale) animation).getNewWidth() +
                  "\" fill=\"freeze\" />\n";
            }
            if (shape.getYRadius() != ((Scale) animation).getNewHeight()) {
              svg += reusedCode1 + "height\" from=\"" + shape.getYRadius() + "\" to=\"" + ((Scale) animation).getNewHeight() +
                  "\" fill=\"freeze\" />\n";
            }
          } else if(animation.getType() == AnimationType.ChangeColor) {
            if ((shape.getColor().getRed() != ((ChangeColor) animation).getNewColor().getRed()) 
                || (shape.getColor().getGreen() != ((ChangeColor) animation).getNewColor().getGreen()) 
                || (shape.getColor().getBlue() != ((ChangeColor) animation).getNewColor().getBlue())) {
              svg += reusedCode1 + "fill\" from=\"rgb(" + shape.getColor().getRed()*255 +
                  "," + shape.getColor().getGreen()*255 + "," + shape.getColor().getBlue()*255 + ")\" to=\"rgb(" + ((ChangeColor) animation).getNewColor().getRed()*255 +
                  "," + ((ChangeColor) animation).getNewColor().getGreen()*255 + "," + ((ChangeColor) animation).getNewColor().getBlue()*255 + ")" +
                  "\" fill=\"freeze\" />\n";
            }
          }
        }
        svg += "</rect>\n";
      }
      if (shape.getType().equals("ellipse")) {
        
        svg += "<ellipse id=\"" + shape.getName() + "\" cx=\"" + shape.getPosition().getX() + 
            "\" cy=\"" + shape.getPosition().getY() + "\" rx=\"" + shape.getXRadius() + 
            "\" ry=\"" + shape.getYRadius() + "\" fill=\"rgb(" + shape.getColor().getRed() +
            "," + shape.getColor().getGreen() + "," + shape.getColor().getBlue() + ")\" " + 
            "visibility=\"visible\" >\n"; 
        for (int j = 0; j < animationList.size(); j++) {
          AnimationInterface animation = animationList.get(j);
          String reusedCode2 = "<animate attributeType=\"xml\" begin=\"" + animation.getAnimationTime().getStartTime()*1000 +
              "ms\" dur=\"" + (animation.getAnimationTime().getEndTime() - animation.getAnimationTime().getStartTime())*1000 +
              "ms\" attributeName=\"";
          if (animation.getType() == AnimationType.Move) {
            if (shape.getPosition().getX() != ((Move) animation).getNewPosition().getX()) {
              svg += reusedCode2 + "cx\" from=\"" + shape.getPosition().getX() + "\" to=\"" + ((Move) animation).getNewPosition().getX() +
                  "\" fill=\"freeze\" />\n";
            }
            if (shape.getPosition().getY() != ((Move) animation).getNewPosition().getY()) {
              svg += reusedCode2 + "cy\" from=\"" + shape.getPosition().getY() + "\" to=\"" + ((Move) animation).getNewPosition().getY() +
                  "\" fill=\"freeze\" />\n";
            }
          } else if(animation.getType() == AnimationType.Scale) {
            if (shape.getXRadius() != ((Scale) animation).getNewWidth()) {
              svg += reusedCode2 + "rx\" from=\"" + shape.getXRadius() + "\" to=\"" + ((Scale) animation).getNewWidth() +
                  "\" fill=\"freeze\" />\n";
            }
            if (shape.getYRadius() != ((Scale) animation).getNewHeight()) {
              svg += reusedCode2 + "ry\" from=\"" + shape.getYRadius() + "\" to=\"" + ((Scale) animation).getNewHeight() +
                  "\" fill=\"freeze\" />\n";
            }
          } else if(animation.getType() == AnimationType.ChangeColor) {
            if ((shape.getColor().getRed() != ((ChangeColor) animation).getNewColor().getRed()) 
                || (shape.getColor().getGreen() != ((ChangeColor) animation).getNewColor().getGreen()) 
                || (shape.getColor().getBlue() != ((ChangeColor) animation).getNewColor().getBlue())) {
              svg += reusedCode2 + "fill\" from=\"rgb(" + shape.getColor().getRed()*255 +
                  "," + shape.getColor().getGreen()*255 + "," + shape.getColor().getBlue()*255 + ")\" to=\"rgb(" + ((ChangeColor) animation).getNewColor().getRed()*255 +
                  "," + ((ChangeColor) animation).getNewColor().getGreen()*255 + "," + ((ChangeColor) animation).getNewColor().getBlue()*255 + ")" +
                  "\" fill=\"freeze\" />\n";
            }
          }
        }
        svg += "</ellipse>\n";
      }
    }
    svg += "</svg>";
    return svg;
  }

  @Override
  public String getViewType() {
    return "svg";
  }
}
