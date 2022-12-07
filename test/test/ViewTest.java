package test;

import static org.junit.Assert.assertEquals;

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
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextBasedView;
import cs5004.animator.view.ViewInterface;
import cs5004.animator.view.VisualView;

/**
 * JUnit tests to test the methods in view class.
 * @author xinyachen
 *
 */
public class ViewTest {

  /**
   * Test if rectangle moving works in SVG.
   */
  @Test
  public void testSvgMoveRectangle() {
    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    AnimationInterface change1 = new Move(r1, 10, 50, AnimationType.Move, 
        new Point2D(200.0,200.0), new Point2D(300.0, 400.0));
   
    ModelInterface m = new ModelImplementation();
    m.addShape(r1);
    m.addAnimation(change1);
    
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r1\" x=\"200.0\" y=\"200.0\" "
        + "width=\"50.0\" height=\"100.0\" fill=\"rgb(1.0,0.0,0.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"x\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"y\" "
        + "from=\"200.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if rectangle scaling works in SVG.
   */
  @Test
  public void testSvgScaleRectangle() {
    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 0.5, 0.5, 0.5, 1, 100);
    AnimationInterface change1 = new Scale(r1, 5, 15, AnimationType.Scale, 
        50.0, 100.0, 25.0, 200.0);
    
    ModelInterface m = new ModelImplementation();
    m.addShape(r1);
    m.addAnimation(change1);
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r1\" x=\"200.0\" y=\"200.0\" "
        + "width=\"50.0\" height=\"100.0\" fill=\"rgb(0.5,0.5,0.5)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"10000ms\" attributeName=\"width\" "
        + "from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"10000ms\" "
        + "attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if rectangle changing color works in SVG.
   */
  @Test
  public void testSvgChangeColorRectangle() {
    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 0.5, 0.5, 0.5, 1, 100);
    AnimationInterface change1 = new ChangeColor(r1, 5, 8, AnimationType.ChangeColor, 
        new Color(0.5,0.5,0.5), new Color(0.0,1.0,0.0));

    ModelInterface m = new ModelImplementation();
    m.addShape(r1);
    m.addAnimation(change1);
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r1\" x=\"200.0\" y=\"200.0\" "
        + "width=\"50.0\" height=\"100.0\" fill=\"rgb(0.5,0.5,0.5)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"3000ms\" attributeName=\"fill\" "
        + "from=\"rgb(127.5,127.5,127.5)\" to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if oval moving works in SVG.
   */
  @Test
  public void testSvgMoveOval() {
    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    AnimationInterface change1 = new Move(o1, 20, 70, AnimationType.Move, 
        new Point2D(500.0,100.0), new Point2D(500.0, 400.0));
    
    ModelInterface m = new ModelImplementation();
    m.addShape(o1);
    m.addAnimation(change1);
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" "
        + "rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0.0,0.0,1.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"50000ms\" attributeName=\"cy\" "
        + "from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if oval scaling works in SVG.
   */
  @Test
  public void testSvgScaleOval() {
    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    AnimationInterface change1 = new Scale(o1, 7, 20, AnimationType.Scale, 60.0, 30.0, 25.0, 100.0);
    ModelInterface m = new ModelImplementation();
    m.addShape(o1);
    m.addAnimation(change1);
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" "
        + "rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0.0,0.0,1.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"13000ms\" attributeName=\"rx\" "
        + "from=\"60.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"13000ms\" attributeName=\"ry\" "
        + "from=\"30.0\" to=\"100.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if oval changing color works in SVG.
   */
  @Test
  public void testSvgChangeColorOval() {
    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    AnimationInterface change1 = new ChangeColor(o1, 10, 20, AnimationType.ChangeColor, 
        new Color(0.0,0.0,1.0), new Color(0.0,1.0,0.0));
    
    ModelInterface m = new ModelImplementation();
    m.addShape(o1);
    m.addAnimation(change1);
    SVGView v = new SVGView(m);
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" "
        + "rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0.0,0.0,1.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" attributeName=\"fill\" "
        + "from=\"rgb(0.0,0.0,255.0)\" to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
 
  /**
   * Test if a list of shape works in SVG view.
   */
  @Test
  public void testSvgListOfAnimation() {
    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    AnimationInterface change1 = new Move(r1, 10, 50, AnimationType.Move, 
        new Point2D(200.0,200.0), new Point2D(300.0, 400.0));
    AnimationInterface change2 = new ChangeColor(r1, 10, 50, AnimationType.ChangeColor, 
        new Color(1.0,0.0,0.0), new Color(0.0,1.0,0.0));
    AnimationInterface change3 = new Scale(r1, 5, 15, AnimationType.Scale, 
        50.0, 100.0, 25.0, 200.0);
    
    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    AnimationInterface change4 = new Move(o1, 20, 70, AnimationType.Move, 
        new Point2D(500.0,100.0), new Point2D(500.0, 400.0));
    AnimationInterface change5 = new ChangeColor(o1, 10, 20, AnimationType.ChangeColor, 
        new Color(0.0,0.0,1.0), new Color(0.0,1.0,0.0));
    
    ModelInterface m = new ModelImplementation();
   
    m.addShape(r1);
    m.addShape(o1);
    m.addAnimation(change1);
    m.addAnimation(change2);
    m.addAnimation(change3);
    m.addAnimation(change4);
    m.addAnimation(change5);
    
    
    SVGView v = new SVGView(m);
    
    String expected = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r1\" x=\"200.0\" y=\"200.0\" "
        + "width=\"50.0\" height=\"100.0\" fill=\"rgb(1.0,0.0,0.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"10000ms\" attributeName=\"width\" "
        + "from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"10000ms\" "
        + "attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"x\" "
        + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"y\" "
        + "from=\"200.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"fill\" "
        + "from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" "
        + "rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0.0,0.0,1.0)\" visibility=\"visible\" >\n"
        + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" attributeName=\"fill\" "
        + "from=\"rgb(0.0,0.0,255.0)\" to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"50000ms\" attributeName=\"cy\" "
        + "from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>";
    String actual = v.displayView();
    assertEquals(expected, actual);
    //System.out.println(actual);
  }
  
  /**
   * Test if a list of animation works properly in text-based view.
   */
  @Test 
  public void testListOfAnimationTextBasedView() {
    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 100);
    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 6, 100);
    AnimationInterface change1 = new Move(r1, 10, 50, AnimationType.Move, 
        new Point2D(200.0,200.0), new Point2D(300.0, 300.0));
    AnimationInterface change2 = new Move(o1, 20, 70, AnimationType.Move, 
        new Point2D(500.0,100.0), new Point2D(500.0, 400.0));
    AnimationInterface change3 = new ChangeColor(o1, 50, 80, AnimationType.ChangeColor, 
        new Color(0.0,0.0,1.0), new Color(0.0,1.0,0.0));
    AnimationInterface change4 = new Scale(r1, 51, 70, AnimationType.Scale, 
        50.0, 100.0, 25.0, 100.0);
    AnimationInterface change5 = new Move(r1, 70, 100, AnimationType.Move, 
        new Point2D(300.0,300.0), new Point2D(200.0,200.0));
    Shape r2 = new Rectangle("r2",300.0, 400.0, 10.0, 300.0, 1.0, 0.8, 0.1, 1, 100);
    AnimationInterface change6 = new ChangeColor(r2, 20, 40, AnimationType.ChangeColor, 
        new Color(1.0,0.8,0.1), new Color(0.2,0.4,0.8));

    ModelInterface model = new ModelImplementation(); 
    model.addShape(r1);
    model.addShape(o1);
    model.addShape(r2);
    
    model.addAnimation(change1);
    model.addAnimation(change2);
    model.addAnimation(change3);
    model.addAnimation(change4);
    model.addAnimation(change5);
    model.addAnimation(change6);
  
    ViewInterface v = new TextBasedView(model);
    String expectedToString = "Shape:\n"
        + "Name: r1\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n\n"
        + "Name: o1\n"
        + "Type: ellipse\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n\n"
        + "Name: r2\n"
        + "Type: rectangle\n"
        + "Min corner: (300.0,400.0), Width: 10.0, Height: 300.0, Color: (1.0,0.8,0.1)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n\n"
        + "Shape r1 moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape r2 changes color from Color: (1.0,0.8,0.1) to Color: (0.2,0.4,0.8) "
        + "from t=20 to t=40\n"
        + "Shape o1 moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape o1 changes color from Color: (0.0,0.0,1.0) to Color: (0.0,1.0,0.0) "
        + "from t=50 to t=80\n"
        + "Shape r1 scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape r1 moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100";
    String actualToString = v.displayView();
    assertEquals(expectedToString, actualToString);
  }
  
//  @Test
//  public void testVisualViewRectangleDisplay() {
//    Shape r1 = new Rectangle("r1",200.0, 200.0, 50.0, 100.0, 1.0, 0.0, 0.0, 1, 10);
//    AnimationInterface change1 = new Move(r1, 1, 5, AnimationType.Move, 
//    new Point2D(200.0,200.0), new Point2D(300.0, 300.0));
//    AnimationInterface change2 =new Scale(r1, 2, 3, AnimationType.Scale, 
//    50.0, 100.0, 25.0, 100.0);
//    AnimationInterface change3 =new ChangeColor(r1, 4, 8, AnimationType.ChangeColor, 
//        new Color(1.0,0.0,0.0), new Color(0.2,0.4,0.8)); 
//    
//    ModelInterface model = new ModelImplementation();
//    model.addShape(r1);
//    model.addAnimation(change1);
//    model.addAnimation(change2);
//    model.addAnimation(change3);
//    ViewInterface v = new VisualView(model, 1);
//    v.displayView();
//    
//    try {
//      Thread.sleep(100000);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
  
//  @Test
//  public void testVisualViewOvalDisplay() {
//    Shape o1 =  new Oval("o1",500.0, 100.0, 60.0, 30.0, 0.0, 0.0, 1.0, 1, 100);
//    AnimationInterface change1 =new Move(o1, 2, 7, AnimationType.Move, 
//        new Point2D(500.0,100.0), new Point2D(500.0, 400.0));
//    AnimationInterface change2 =new ChangeColor(o1, 5, 10, AnimationType.ChangeColor, 
//        new Color(0.0,0.0,1.0), new Color(0.0,1.0,0.0));
//    AnimationInterface change3 =new Scale(o1, 5, 9, AnimationType.Scale, 60.0, 30.0, 10.0, 20.0);
//    
//    Shape o2 = new Oval("o2", 100.0, 200.0, 40.0, 40.0, 0.2, 0.3, 0.3, 1, 10);
//    AnimationInterface change4 =new ChangeColor(o2, 2, 5, AnimationType.ChangeColor, 
//        new Color(0.2,0.3,0.3), new Color(0.1, 0.4, 0.2));
//    AnimationInterface change5 =new Move(o2, 2, 5, AnimationType.Move, 
//        new Point2D(100.0,200.0), new Point2D(500.0, 400.0));
//    AnimationInterface change6 =new Scale(o2, 2, 5, AnimationType.Scale, 
//    40.0, 40.0, 100.0, 200.0);
//    
//
//    ModelInterface model = new ModelImplementation(); 
//    model.addShape(o1);
//    model.addShape(o2);
//    
//    model.addAnimation(change1);
//    model.addAnimation(change2);
//    model.addAnimation(change3);
//    model.addAnimation(change4);
//    model.addAnimation(change5);
//    model.addAnimation(change6);
//    
//    
//    
//    ViewInterface v = new VisualView(model, 1);
//    v.displayView();
//    
//    try {
//      Thread.sleep(100000);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
}
