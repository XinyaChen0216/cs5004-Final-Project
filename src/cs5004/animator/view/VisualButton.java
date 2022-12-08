package view;

import model.shape.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;

/**
 * This represents a VisualButton class, which extends from JFraeme and implements
 * ViewInterface.
 * 
 * @author xinyachen
 *
 */
public class VisualButton extends JFrame implements ViewInterface, ActionListener {

  private ViewPanel viewPanel;
  private JMenuBar menuBar;
  private JMenu file;
  private JMenuItem exit;
  private JPanel buttonPane;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;

  public VisualButton(int tempo) {
    super("Easy Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewPanel = new ViewPanel(tempo);
    this.add(viewPanel);
    this.pack();

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
  }

  @Override
  public String displayView(List<Shape> shapeList) {
    this.viewPanel.displayView(shapeList);
    return "";
  }

@Override
public void actionPerformed(ActionEvent e) {
   Component c = (Component) e.getSource();
   if(c.getName().equals("Quit")){
    System.exit(0);
   }

    
}

  // @Override
  // public String getViewType() {
  //   return "visual";
  // }

}
