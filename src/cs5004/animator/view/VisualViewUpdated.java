package view;

import model.ModelInterface;
import model.shape.Shape;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * This represents a VisualView class, which extends from JFraeme and implements ViewInterface.
 * @author xinyachen
 *
 */
public class VisualViewUpdated extends JFrame implements ViewInterface, ActionListener {
  
  private ViewPanel viewPanel;
  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;
  private JPanel buttonPane;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  
  private String viewType;
  private int tempo;
  
  public VisualViewUpdated(String viewType, int tempo) {
    super("Easy Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewPanel = new ViewPanel(tempo);
    this.add(viewPanel);
    
    this.pack();
    this.setLocationRelativeTo(null);
    
    this.menuBar = new JMenuBar();
    this.setJMenuBar(this.menuBar);
    this.file = new JMenu("File");
    this.menuBar.add(this.file);
    
    this.exit = new JMenuItem("Exit");
    this.exit.setName("Quit");
    this.file.add(this.exit);
    this.exit.addActionListener(this);
    
    this.buttonPane = new JPanel(true);
    this.buttonPane.setBackground(Color.WHITE);
    this.buttonPane.setSize(200,500);
    this.buttonPane.setLocation(0,0);
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
    
    
    this.add(buttonPane); 
    
    this.viewPanel.repaint();
    this.setVisible(true);
    
   
    
    this.viewType = viewType;
    this.tempo = tempo;
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    //this.viewPanel.setModel(model);
    
    Timer timer = new Timer(1000 / tempo,this);
    timer.start();
    
    return "";

  }
  
  public void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component)e.getSource();
    if (c.getName().equals("Quit")) {
      System.exit(0);
    } else if(c.getName().equals("start")) {
      
    }
    
  }

  @Override
  public String getViewType() {
    return "visual";
  }

      //for (Shape shape: shapeList) {
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
