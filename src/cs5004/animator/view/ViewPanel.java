package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Shape;

public class ViewPanel extends JPanel implements ActionListener {

  final int PANEL_WIDTH = 1200;
  final int PANEL_HEIGHT = 1200;
  private double currTime;
  private int tick;
  private List<Shape> shapeList;
  Timer timer;
  
  public ViewPanel(int tick) {
    super(true);
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.currTime = 0;
    this.tick = tick;
    System.out.println(1000/tick);
    timer = new Timer(1000 / tick, this);
  }
  
  public void displayView(List<Shape> shapeList){
    this.shapeList = shapeList;
    timer.start();
  }
  
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    for (int i = 0; i < this.shapeList.size(); i++) {
      Shape shape = this.shapeList.get(i);
      // if (shape.getTime().getEndTime() < currTime || shape.getTime().getStartTime() > currTime) {
      //   continue;
      // } else {
        shape = shape.generateAnimatedShape(currTime);
        float red = (float)shape.getColor().getRed()/255;
        float green = (float)shape.getColor().getGreen()/255;
        float blue = (float)shape.getColor().getBlue()/255;
        if (shape.getType().equals("rectangle")) {
          java.awt.Color shapeColor = new java.awt.Color(red, green, blue);
          g2D.setPaint(shapeColor);
          g2D.fillRect((int)shape.getPosition().getX(), (int)shape.getPosition().getY(), 
              (int)shape.getXRadius(), (int)shape.getYRadius());

        } else if (shape.getType().equals("ellipse")) {
          java.awt.Color shapeColor = new java.awt.Color(red, green, blue);
          g2D.setPaint(shapeColor);
          g2D.fillOval((int)shape.getPosition().getX(), (int)shape.getPosition().getY(), 
              (int)shape.getXRadius(), (int)shape.getYRadius());
        }
      }
    // }
    this.currTime += this.tick;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();

  }
}
