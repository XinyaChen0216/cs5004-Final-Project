package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import model.shape.Shape;

public class ViewPanel extends JPanel implements ActionListener {

  final int PANEL_WIDTH = 800;
  final int PANEL_HEIGHT = 800;
  private boolean hasButton;
  private double currTime;
  private int tick;
  private List<Shape> shapeList;
  private Timer timer;

  public ViewPanel(int tick, List<Shape> shapeList, boolean hasButton) {
    super(true);
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.shapeList = shapeList;
    this.hasButton = hasButton;
    this.currTime = 0;
    this.tick = tick;
    this.timer = new Timer(1000 / tick, this);
  }

  public void displayView() {
    if (!hasButton)
      this.timer.start();
  }

  public List<Shape> getShapeList() {
    return this.shapeList;
  }

  public Timer getTimer() {
    return this.timer;
  }

  public double getCurrTime() {
    return this.currTime;
  }

  public void setCurrTime(int time) {
    this.currTime = time;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    for (int i = 0; i < this.shapeList.size(); i++) {
      Shape shape = this.shapeList.get(i);

      shape = shape.generateAnimatedShape(currTime);
      float red = (float) shape.getColor().getRed() / 255;
      float green = (float) shape.getColor().getGreen() / 255;
      float blue = (float) shape.getColor().getBlue() / 255;
      if (shape.getType().equals("rectangle")) {
        java.awt.Color shapeColor = new java.awt.Color(red, green, blue);
        g2D.setPaint(shapeColor);
        g2D.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getXRadius(), (int) shape.getYRadius());

      } else if (shape.getType().equals("ellipse")) {
        java.awt.Color shapeColor = new java.awt.Color(red, green, blue);
        g2D.setPaint(shapeColor);
        g2D.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getXRadius(), (int) shape.getYRadius());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.currTime += this.tick;
    repaint();
  }
}
