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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;

public class ViewPanel extends JPanel implements ActionListener {

  final int PANEL_WIDTH = 600;
  final int PANEL_HEIGHT = 400;
  private double currTime;
  private int tick;
  private List<Shape> shapeList;
  Timer timer;

  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;
  private JPanel buttonPane;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;


  public ViewPanel(int tick) {
    super(true);
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.currTime = 0;
    this.tick = tick;
    timer = new Timer(1000 / tick, this);


    this.menuBar = new JMenuBar();
    //this.setJMenuBar(this.menuBar);
    this.file = new JMenu("File");
    this.menuBar.add(this.file);
    
    this.exit = new JMenuItem("Exit");
    this.exit.setName("Quit");
    this.file.add(this.exit);
    this.exit.addActionListener(this);
    
    this.buttonPane = new JPanel(true);
    this.buttonPane.setBackground(Color.WHITE);
    this.buttonPane.setSize(200,200);
    this.buttonPane.setLocation(100,100);
    
    this.buttonPane.setLayout(new FlowLayout());
    
    
    
    this.startButton = new JButton("Start");
    this.startButton.setName("start");
    this.startButton.addActionListener(this);

    this.pauseButton = new JButton("Pause");
    this.pauseButton.setName("pause");
    this.pauseButton.addActionListener(this);

    this.resumeButton = new JButton("Resume");
    this.resumeButton.setName("resume");
    this.resumeButton.addActionListener(this);

    this.restartButton = new JButton("Restart");
    this.restartButton.setName("restart");
    this.restartButton.addActionListener(this);
    
    
    this.buttonPane.add(this.startButton);
    this.buttonPane.add(this.pauseButton);
    this.buttonPane.add(this.resumeButton);
    this.buttonPane.add(this.restartButton);
    
    this.add(this.buttonPane);
    this.add(buttonPane); 
    
    this.repaint();


    
   this.setVisible(true);
 
  }

  public void displayView(List<Shape> shapeList) {
    this.shapeList = shapeList;
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    
    for (int i = 0; i < this.shapeList.size(); i++) {
      Shape shape = this.shapeList.get(i);
      
      // if (shape.getTime().getEndTime() < currTime || shape.getTime().getStartTime()
      // > currTime) {
      // continue;
      // } else {
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
    this.currTime += this.tick;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();

  }

  public void setCurrentTime(int currTime) {
    this.currTime = currTime;
  }
}
