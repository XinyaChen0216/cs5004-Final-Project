package view;

import model.shape.Shape;
import java.util.List;
import javax.swing.JFrame;

/**
 * This represents a VisualView class, which extends from JFraeme and implements
 * ViewInterface.
 * 
 * @author xinyachen
 *
 */
public class VisualView extends JFrame implements ViewInterface {

  private ViewPanel viewPanel;

  public VisualView(int tempo) {
    super("Easy Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewPanel = new ViewPanel(tempo);
    this.add(viewPanel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    this.viewPanel.displayView(shapeList);
    return "";

  }

  @Override
  public String getViewType() {
    return "visual";
  }

}
