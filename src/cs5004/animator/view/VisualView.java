package view;

import model.ModelInterface;
import model.Shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This represents a VisualView class, which extends from JFraeme and implements
 * ViewInterface.
 * 
 * @author xinyachen
 *
 */
public class VisualView extends JFrame implements ViewInterface {

  private ViewPanel viewPanel;

  private String viewType;
  private int tempo;

  public VisualView(String viewType, int tempo) {
    super("Easy Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewPanel = new ViewPanel(tempo);
    this.add(viewPanel);

    this.pack();
    this.setLocationRelativeTo(null);

    this.setVisible(true);

    this.viewType = viewType;
    this.tempo = tempo;
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    this.viewPanel.displayView(shapeList);
    return "";

  }

  // public void paintComponent(Graphics g) {
  //   Graphics2D g2D = (Graphics2D) g;

  // }

  // @Override
  // public void actionPerformed(ActionEvent e) {
  //   repaint();

  // }

  @Override
  public String getViewType() {
    return "visual";
  }

  // for (Shape shape: shapeList) {
  // for (AnimationInterface animation: shape.getAnimationList()) {
  // this.all_shape_animation_list.add(animation);
  // }
  // }
  // Collections.sort(all_shape_animation_list, new
  // Comparator<AnimationInterface>() {
  // public int compare(AnimationInterface a, AnimationInterface b) {
  // if (a.getAnimationTime().getStartTime() ==
  // b.getAnimationTime().getStartTime()) {
  // return a.getAnimationTime().getEndTime() - b.getAnimationTime().getEndTime();
  // } else {
  // return a.getAnimationTime().getStartTime() -
  // b.getAnimationTime().getStartTime();
  // }
  // }
  // });

}
