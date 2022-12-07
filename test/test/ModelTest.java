package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cs5004.animator.model.AnimationInterface;
import cs5004.animator.model.AnimationType;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Color;
import cs5004.animator.model.ModelImplementation;
import cs5004.animator.model.ModelInterface;
import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JUnit test to test methods in model.
 * @author xinyachen
 *
 */
public class ModelTest {
  
  /**
   * Test if Time class creates objects properly.
   */
  @Test
  public void testTime() {
    try {
      new Time(-1, 2);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Time should be non-negative.", e.getMessage());
    }
    
    try {
      new Time(3, -4);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Time should be non-negative.", e.getMessage());
    }
    
    try {
      Time t1 = new Time(0, 0);
      String expected = "Appears at t=0" + "\nDisappears at t=0";
      String actual = t1.toString();
      assertEquals(expected, actual);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
    
    try {
      Time t1 = new Time(10, 100);
      String expected = "Appears at t=10" + "\nDisappears at t=100";
      String actual = t1.toString();
      assertEquals(expected, actual);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
    
    try {
      new Time(100, 99);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("End time cannot be less than start time.", e.getMessage());
    }
    
    try {
      Time t1 = new Time(100, 100);
      String expected = "Appears at t=100" + "\nDisappears at t=100";
      String actual = t1.toString();
      assertEquals(expected, actual);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }    
  }
  
  /**
   * Test if Point2D class creates objects properly.
   */
  @Test
  public void testPoint2D() {
    Point2D p1 = new Point2D(0,0.1);
    String expected1 = "(0.0,0.1)"; 
    String actual1 = p1.toString();
    assertEquals(expected1, actual1);
    assertEquals(0.0, p1.getX(), 0.001);
    assertEquals(0.1, p1.getY(), 0.001);
    
    Point2D p2 = new Point2D(-2.82,0);
    String expected2 = "(-2.82,0.0)";
    String actual2 = p2.toString();
    assertEquals(expected2, actual2);
    assertEquals(-2.82, p2.getX(), 0.001);
    assertEquals(0.0, p2.getY(), 0.001);
  }
  
  /**
   * Test if Color class creates objects properly.
   */
  @Test
  public void testColor() {
    try {
      new Color(-1,0.5,0.8);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid value(s). Each value should be in the range of [0,1].", e.getMessage());
    }
    
    try {
      new Color(1,-0.5,-0.8);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid value(s). Each value should be in the range of [0,1].", e.getMessage());
    }
     
    try {
      new Color(1.1,1.1,1.1);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid value(s). Each value should be in the range of [0,1].", e.getMessage());
    }
      
    try {
      Color c1 = new Color(0.8,0.0,1.0);
      assertEquals("Color: (0.8,0.0,1.0)", c1.toString());
      assertEquals(0.8, c1.getRed(), 0.001);
      assertEquals(0.0, c1.getGreen(), 0.001);
      assertEquals(1.0, c1.getBlue(), 0.001);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
  }
  
  /**
   * Test if Rectangle class creates objects properly.
   */
  @Test
  public void testRectangle() {
    try {
      new Rectangle("r1",10,10,10,-2,1.0,0.9,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The xRadius/width and yRadius/height should be positive.", e.getMessage());
    }
    
    try {
      new Rectangle("r1",10,10,-10,10,1.0,0.9,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The xRadius/width and yRadius/height should be positive.", e.getMessage());
    } 
    
    try {
      new Rectangle("r1",10,10,10,10,1.1,0.9,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid value(s). Each value should be in the range of [0,1].", e.getMessage());
    }
    
    try {
      new Rectangle("r1",10,10,10,10,1.0,0.9,0.0,100,99);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("End time cannot be less than start time.", e.getMessage());
    }
    
    try {
      new Rectangle("r1",10,10,10,10,1.0,0.9,0.0,-1,100);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Time should be non-negative.", e.getMessage());
    }
    
    try {
      Rectangle r1 = new Rectangle("r1",10,20,30,40,1.0,0.9,0.0,100,200);
      assertEquals(10, r1.getPosition().getX(), 0.001);
      assertEquals(20, r1.getPosition().getY(), 0.001);
      assertEquals(30, r1.getXRadius(), 0.001);
      assertEquals(40, r1.getYRadius(), 0.001);
      assertEquals("Color: (1.0,0.9,0.0)", r1.getColor().toString());
      assertEquals("Appears at t=100" + "\nDisappears at t=200", r1.getTime().toString());
      assertEquals("r1", r1.getName());
      assertEquals("rectangle", r1.getType());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
  }
  
  /**
   * Test if Oval class creates objects properly.
   */
  @Test
  public void testOval() {
    try {
      new Oval("o1",10,10,-9,20,1.0,0.9,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The xRadius/width and yRadius/height should be positive.", e.getMessage());
    }
    
    try {
      new Oval("o1",10,10,10,-6,1.0,0.9,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The xRadius/width and yRadius/height should be positive.", e.getMessage());
    } 
    
    try {
      new Oval("o1",10,10,10,10,1.0,-0.1,0.0,100,200);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid value(s). Each value should be in the range of [0,1].", e.getMessage());
    }
    
    try {
      new Oval("o1",10,10,10,10,1.0,0.9,0.0,100,10);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("End time cannot be less than start time.", e.getMessage());
    }
    
    try {
      new Oval("o1",10,10,10,10,1.0,0.9,0.0,100,-100);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Time should be non-negative.", e.getMessage());
    }
    
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      assertEquals(10, o1.getPosition().getX(), 0.001);
      assertEquals(20, o1.getPosition().getY(), 0.001);
      assertEquals(30, o1.getXRadius(), 0.001);
      assertEquals(40, o1.getYRadius(), 0.001);
      assertEquals("Color: (1.0,0.9,0.0)", o1.getColor().toString());
      assertEquals("Appears at t=100" + "\nDisappears at t=200", o1.getTime().toString());
      assertEquals("o1", o1.getName());
      assertEquals("ellipse", o1.getType());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
  }

  /**
   * Test if ChangeColor class works properly for Rectangle.
   */
  @Test
  public void testChangeColorRectangle() {
    try {
      Rectangle r1 = new Rectangle("r1",10,20,30,40,1.0,0.9,0.0,100,200);
      new ChangeColor(r1, 10, 90, AnimationType.ChangeColor, 
          new Color(1.0,0.9,0.0), new Color(1.0,1.0,1.0));
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape cannot be animated before it appears or after "
          + "it disappears.", e.getMessage());
    }
    
    try {
      Rectangle r1 = new Rectangle("r1",10,20,30,40,1.0,0.9,0.0,100,200);
      new ChangeColor(r1, 201, 250, AnimationType.ChangeColor, 
          new Color(1.0,0.9,0.0), new Color(1.0,1.0,1.0));
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape cannot be animated before it appears or after "
          + "it disappears.", e.getMessage());
    } 
    
    try {
      Rectangle r1 = new Rectangle("r1",10,20,30,40,1.0,0.9,0.0,100,200);
      new ChangeColor(r1, 150, 125, AnimationType.ChangeColor, 
          new Color(1.0,0.9,0.0), new Color(1.0,1.0,1.0));
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("End time cannot be less than start time.", e.getMessage());
    }
    
    try {
      Rectangle r1 = new Rectangle("r1",10,20,30,40,1.0,0.9,0.0,100,200);
      ChangeColor change1 = new ChangeColor(r1, 150, 180, AnimationType.ChangeColor, 
          new Color(1.0,0.9,0.0), new Color(1.0,1.0,1.0));
      new ChangeColor(r1, 170, 190, AnimationType.ChangeColor, 
          new Color(1.0,1.0,1.0), new Color(0.5,0.5,0.5));
      List<AnimationInterface> expected = new ArrayList<>();
      expected.add(change1);
      List<AnimationInterface> actual = r1.getAnimationList();
      //It tests and shows that the 2nd change has not been added into the animationList.
      //It is because change1 and the 2nd change are the same AnimationType 
      //and they have overlapped time. 
      assertEquals(expected.toString(), actual.toString());
      assertEquals(AnimationType.ChangeColor, change1.getType());
      assertEquals("r1", change1.getShapeName());
      assertEquals(new Time(150,180).toString(), change1.getAnimationTime().toString());
      assertEquals(new Color(1.0,1.0,1.0).toString(), change1.getNewColor().toString());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
    try {
      Shape r = new Rectangle("r",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
      Color newColor = new Color(0.5, 0.5, 0.0);
      AnimationInterface a = new ChangeColor(r, 100, 200, AnimationType.ChangeColor, 
          new Color(1.0,0.0,0.0), newColor);
      String expected = "Shape r changes color from Color: (1.0,0.0,0.0) "
          + "to Color: (0.5,0.5,0.0) from t=100 to t=200";
      String actual = a.toString();
      assertEquals(expected, actual);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    } 
  } 
  
  /**
   * Test if moving of rectangle works properly.
   */
  @Test
  public void testMoveRectangle() {
    Shape r = new Rectangle("r",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    Point2D newPosition = new Point2D(300.0,300.0);
    AnimationInterface a = new Move(r, 100, 200, AnimationType.Move, 
        new Point2D(200.0,200.0), newPosition);
    String expected = "Shape r moves from (200.0,200.0) to (300.0,300.0) from t=100 to t=200";
    String actual = a.toString();
    assertEquals(expected, actual);
  }
  
  /**
   * Test if scaling of rectangle works properly.
   */
  @Test
  public void testScaleRectangle() {
    Shape r = new Rectangle("r",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    double newWidth = 100.0;
    double newHeight = 200.0;
    AnimationInterface a = new Scale(r, 100, 200, AnimationType.Scale, 
        50.0, 100.0, newWidth, newHeight);
    String expected = "Shape r scales from Width: 50.0, Height: 100.0 "
        + "to Width: 100.0, Height: 200.0 from t=100 to t=200";
    String actual = a.toString();
    assertEquals(expected, actual);
  } 
  
  /**
   * Test if change of color works properly for Oval.
   */
  @Test
  public void testOvelChangeColor() {
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      ChangeColor change1 = new ChangeColor(o1, 180, 200, AnimationType.ChangeColor, 
          new Color(1.0,0.9,0.0), new Color(0.0,0.0,0.0));
      List<AnimationInterface> expected = new ArrayList<>();
      expected.add(change1);
      List<AnimationInterface> actual = o1.getAnimationList();
      assertEquals(expected.toString(), actual.toString());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
  }
   
  /**
   * Test if Move class works properly for Oval.
   */
  @Test
  public void testOvalMove() {
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      Move m1 = new Move(o1, 110, 180, AnimationType.Move, new Point2D(10,20), new Point2D(10,-10));
      assertEquals(new Point2D(10,-10).toString(), m1.getNewPosition().toString());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
    
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      Move change1 = new Move(o1, 110, 180, AnimationType.Move, 
          new Point2D(10,20), new Point2D(10,-10));
      assertEquals(new Point2D(10,-10).toString(), change1.getNewPosition().toString());
      
      new Move(o1, 100, 130, AnimationType.Move, new Point2D(10,-10), new Point2D(10,20));
      List<AnimationInterface> expected = new ArrayList<>();
      expected.add(change1);
      List<AnimationInterface> actual = o1.getAnimationList();
      //It tests and shows that the 2nd change has not been added into the animationList.
      //It is because change1 and the 2nd change are the same AnimationType 
      //and they have overlapped time. 
      assertEquals(expected.toString(), actual.toString());
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
  }

  /**
   * Test if Scale works properly for Oval.
   */
  @Test
  public void testOvalScale() {
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      Scale change1 = new Scale(o1, 120, 140, AnimationType.Scale, 30, 40, 50, 60);
      List<AnimationInterface> expected = new ArrayList<>();
      expected.add(change1);
      assertEquals(expected.toString(), o1.getAnimationList().toString());
      assertEquals(50, change1.getNewWidth(), 0.001);
      assertEquals(60, change1.getNewHeight(), 0.001);
    } catch (IllegalArgumentException e) {
      fail("An exception should not have been thrown.");
    }
    
    try {
      Oval o1 = new Oval("o1",10,20,30,40,1.0,0.9,0.0,100,200);
      new Scale(o1, 300, 400, AnimationType.Scale, 30, 40, 50, 60);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape cannot be animated before it appears or after "
          + "it disappears.", e.getMessage());
    }
  }
   
  /**
   * Test if toString in Rectangle class works properly.
   */
  @Test
  public void testRectangleToString() {
    Shape r = new Rectangle("r",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    String expected = "Name: r\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100";
    String actual = r.toString();
    assertEquals(expected, actual);
  }
  
  /**
   * Test if toString in Oval class works properly.
   */
  @Test
  public void testOvalToString() {
    Shape o =  new Oval("o",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    String expected = "Name: o\n"
        + "Type: ellipse\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100";
    String actual = o.toString();
    assertEquals(expected, actual);
  }

//  /**
//   * Test if a list of animation works properly.
//   */
//  @Test 
//  public void testListOfAnimation() {
//    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
//    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
//    new Move(r1, 10, 50, AnimationType.Move, new Point2D(200.0,200.0), new Point2D(300.0, 300.0));
//    new Move(o1, 20, 70, AnimationType.Move, new Point2D(500.0,100.0), new Point2D(500.0, 400.0));
//    new ChangeColor(o1, 50, 80, AnimationType.ChangeColor, 
//        new Color(0.0,0.0,1.0), new Color(0.0,1.0,0.0));
//    new Scale(r1, 51, 70, AnimationType.Scale, 50.0, 100.0, 25.0, 100.0);
//    new Move(r1, 70, 100, AnimationType.Move, new Point2D(300.0,300.0), new Point2D(200.0,200.0));
//    Shape r2 = new Rectangle("r2",300.0, 400.0, 10.0, 300.0, 1.0, 0.8, 0.1, 1, 100);
//    new ChangeColor(r2, 20, 40, AnimationType.ChangeColor, 
//        new Color(1.0,0.8,0.1), new Color(0.2,0.4,0.8));
//    ModelInterface model = new ModelImplementation();
//    model.addShape(r1);
//    model.addShape(o1);
//    model.addShape(r2);
//    String expectedToString = "Shape:\n"
//        + "Name: r1\n"
//        + "Type: rectangle\n"
//        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
//        + "Appears at t=1\n"
//        + "Disappears at t=100\n\n"
//        + "Name: o1\n"
//        + "Type: ellipse\n"
//        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
//        + "Appears at t=6\n"
//        + "Disappears at t=100\n\n"
//        + "Name: r2\n"
//        + "Type: rectangle\n"
//        + "Min corner: (300.0,400.0), Width: 10.0, Height: 300.0, Color: (1.0,0.8,0.1)\n"
//        + "Appears at t=1\n"
//        + "Disappears at t=100\n\n"
//        + "Shape r1 moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
//        + "Shape r2 changes color from Color: (1.0,0.8,0.1) to Color: (0.2,0.4,0.8) "
//        + "from t=20 to t=40\n"
//        + "Shape o1 moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
//        + "Shape o1 changes color from Color: (0.0,0.0,1.0) to Color: (0.0,1.0,0.0) "
//        + "from t=50 to t=80\n"
//        + "Shape r1 scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
//        + "from t=51 to t=70\n"
//        + "Shape r1 moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100";
//    String actualToString = model.toString();
//    assertEquals(expectedToString, actualToString);
//    
//    List<Shape> expectedShapeList = new ArrayList<>(Arrays.asList(r1,o1,r2)); 
//    List<Shape> actualShapeList = model.getShapeList();
//    assertEquals(expectedShapeList,actualShapeList);
//  }
}
