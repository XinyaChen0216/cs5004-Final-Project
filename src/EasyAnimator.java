// package cs5004.animator;

import controller.AnimationController;
import model.ModelImplementation;
import model.ModelInterface;
import util.AnimationBuilder;
import util.AnimationReader;
import view.Svgview;
import view.TextBasedView;
import view.ViewInterface;
import view.VisualButton;
import view.VisualView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This represents a EasyAnimator class. 
 * @author xinyachen
 *
 */
public final class EasyAnimator {

  /**
   * This is the main method of the program.
   * @param args input arguments that users write
   */
  public static void main(String[] args) {

    ModelInterface model = null;
    Appendable appendable = System.out;
    JFrame frame = new JFrame();

    String viewType = "";
    String outputFile = "";
    int tempo = 1;

    ViewInterface view = null;

    int size = args.length;
    if (size == 0) {
      throw new IllegalArgumentException("Illegal argument. Nothing inside this argument.");
    } else {
      for (int i = 0; i < size; i++) {
        if (args[i].equals("-in")) {
          try {
            InputStream inputStream = new FileInputStream(args[i + 1]);
            AnimationBuilder<ModelInterface> builder = new ModelImplementation.Builder();
            model = AnimationReader.parseFile(new InputStreamReader(inputStream), builder);
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Input file is not found!", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } else if (args[i].equals("-out")) {
          if (args[i + 1].endsWith("txt") || args[i + 1].endsWith("svg")) {
            try {
              outputFile = args[i + 1];
              appendable = new FileWriter(outputFile);
            } catch (Exception e) {
              JOptionPane.showMessageDialog(frame, "Output source is not found!", "Error",
                  JOptionPane.WARNING_MESSAGE);
            }
          } 
        } else if (args[i].equals("-view")) {
          try {
            viewType = args[i + 1];
          } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Please enter a view type.", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } else if (args[i].equals("-speed")) {
          try {
            tempo = Integer.valueOf(args[i + 1]);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Please enter a speed of display.", "Error",
                JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    }

    if (viewType.equals("text")) {
      view = new TextBasedView(model.getShapeList());
    } else if (viewType.equals("visual")) {
      view = new VisualView(model.getShapeList(), tempo);
    } else if (viewType.equals("svg")) {
      view = new Svgview(model.getShapeList());
    } else if (viewType.equals("visualButton")) {
      view = new VisualButton(model.getShapeList(), tempo);
    }
    new AnimationController(model, view, appendable).run();
  }
}