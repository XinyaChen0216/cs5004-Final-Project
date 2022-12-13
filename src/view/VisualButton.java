package view;

import model.shape.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
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
 * This represents a VisualButton class, which extends from JFraeme and
 * implements
 * ViewInterface.
 * 
 * @author xinyachen
 *
 */
public class VisualButton extends JFrame implements ViewInterface, ActionListener {

  private ViewPanel viewPanel;
  private JButton restartButton;
  private JPanel buttonPane;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;

  public VisualButton(List<Shape> shapeList, int tempo) {
    super("Easy Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewPanel = new ViewPanel(tempo, shapeList, true);

    this.buttonPane = new JPanel(true);
    this.buttonPane.setBackground(Color.WHITE);
    this.buttonPane.setLocation(0, 0);

    this.buttonPane.setLayout(new FlowLayout());

    this.startButton = new JButton("Start");
    this.startButton.setName("start");
    this.startButton.addActionListener((e) -> {
      if (viewPanel.getCurrTime() == 0)
        viewPanel.getTimer().start();
    });

    this.pauseButton = new JButton("Pause");
    this.pauseButton.setName("pause");
    this.pauseButton.addActionListener((e) -> viewPanel.getTimer().stop());

    this.resumeButton = new JButton("Resume");
    this.resumeButton.setName("resume");
    this.resumeButton.addActionListener((e) -> {
      if (viewPanel.getCurrTime() > 0 && !viewPanel.getTimer().isRunning())
        viewPanel.getTimer().start();
    });

    this.restartButton = new JButton("Restart");
    this.restartButton.setName("restart");
    this.restartButton.addActionListener((e) -> {
      List<Shape> shapList = viewPanel.getShapeList();
      for (int i = 0; i < shapList.size(); i++) {
        Shape currShape = shapList.get(i);
        currShape.restartShape();
      }
      viewPanel.setCurrTime(0);
      viewPanel.getTimer().start();
    });

    this.buttonPane.add(this.startButton);
    this.buttonPane.add(this.pauseButton);
    this.buttonPane.add(this.resumeButton);
    this.buttonPane.add(this.restartButton);

    this.add(this.buttonPane, BorderLayout.PAGE_START);
    this.add(viewPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);

  }

  @Override
  public String displayView() {
    this.viewPanel.displayView();
    return "";
  }

  @Override
  public void actionPerformed(ActionEvent e) {}
}
