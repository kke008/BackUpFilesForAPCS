/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
   /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
	Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayScale */
  public static void testGrayscale()
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
	Picture beach = new Picture("images/swan.jpg");
	beach.explore();
    beach.pixelate(21);
    beach.explore();
  }
  
  /** Method to test blur */
  public static void testBlur()
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
    Picture newBeach = beach.blur(3);
    newBeach.explore();
  }
  
  /** Method to test enhance */
  public static void testEnhance() {
	  Picture beach = new Picture("images/water.jpg");
	  beach.explore();
	  Picture newBeach = beach.enhance(3);
	  newBeach.explore();
  }
  
  /** Mehtod to test shiftRight */
  public static void testShiftRight() {
	  Picture bike = new Picture("images/redMotorcycle.jpg");
	  bike.explore();
	  Picture newBike = bike.shiftRight(50);
	  newBike.explore(); 
  }
  
  /** Mehtod to test stairStep */
  public static void testStairStep() {
	  Picture bike = new Picture("images/redMotorcycle.jpg");
	  bike.explore();
	  Picture newBike = bike.stairStep(1, 400);
	  newBike.explore(); 
  }
  
  /** Mehtod to test turn90 */
  public static void testTurn90() {
	  Picture bike = new Picture("images/redMotorcycle.jpg");
	  bike.explore();
	  Picture newBike = bike.turn90();
	  newBike.explore(); 
  }
  
  /** Mehtod to test zoomUpperLeft */
  public static void testZoomUpperLeft() {
	  Picture bike = new Picture("images/redMotorcycle.jpg");
	  bike.explore();
	  Picture newBike = bike.zoomUpperLeft();
	  newBike.explore(); 
  }
  
  /** Mehtod to test tileMirror */
  public static void testTileMirror() {
	  Picture bike = new Picture("images/redMotorcycle.jpg");
	  bike.explore();
	  Picture newBike = bike.tileMirror();
	  newBike.explore(); 
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test edgeDetectionBelow */
  public static void testEdgeDetectionBelow() {
	  Picture swan = new Picture ("images/swan.jpg");
	  swan.explore();
	  Picture newSwan = swan.edgeDetectionBelow(10);
	  newSwan.explore();
  }
  
  /** Method to test greenScreen() */
  public static void testGreenScreen() {
	  Picture compositePic = greenScreen();
	  compositePic.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testShiftRight();
    //testStairStep();
    //testTurn90();
    //testZoomUpperLeft();
    //testTileMirror();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetectionBelow();
    //testEdgeDetection2();
    testGreenScreen();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
