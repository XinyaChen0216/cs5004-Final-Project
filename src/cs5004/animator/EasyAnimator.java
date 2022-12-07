package cs5004.animator;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.ModelImplementation;
import cs5004.animator.model.ModelInterface;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.ViewInterface;
import cs5004.animator.view.VisualView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This represents a easy animator class.
 * @author xinyachen
 *
 */
public final class EasyAnimator {

  /**
   * This is the main function of the program, and it takes an array of String as arguments. 
   * @param args an array of String
   */
  public static void main(String[] args) {

    ModelInterface model = null;

    JFrame frame = new JFrame();
    frame.setSize(100, 100);

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
            builder.build();
            model = AnimationReader.parseFile(new InputStreamReader(inputStream), builder);
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Input file is not found!", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } else if (args[i].equals("-out")) {
          try {
            outputFile = args[i + 1];
            // appendable = new FileWriter(outputFile);
            // appendable = System.out;
          } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Output source is not found!", "Error",
                JOptionPane.WARNING_MESSAGE);
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
      view = new TextBasedView(viewType, tempo);
    } else if (viewType.equals("visual")) {
      view = new VisualView(viewType, tempo);
    } else if (viewType.equals("svg")) {
      view = new SVGView(viewType, tempo);
    }

    new AnimationController(model, view).run();
  }
}
