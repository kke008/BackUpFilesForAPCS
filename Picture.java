import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the red and green to 0 */
  public void keepOnlyBlue()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		for (Pixel pixelObj : rowArray)
		{
			pixelObj.setRed(0);
			pixelObj.setGreen(0);
		}  
	  }
  }
  
  /** Method to negate all the pixels in a picture */
  public void negate()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		for (Pixel pixelObj : rowArray)
		{
			int r = pixelObj.getRed();
			int g = pixelObj.getGreen();
			int b = pixelObj.getBlue();
			pixelObj.setRed(255 - r);
			pixelObj.setGreen(225 - g);
			pixelObj.setBlue(225 - b);
		}  
	  }	  
  }
  
  /** Method to turn the picture into shades of gray */
  public void grayscale()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		for (Pixel pixelObj : rowArray)
		{
			int r = pixelObj.getRed();
			int g = pixelObj.getGreen();
			int b = pixelObj.getBlue();
			
			int gray = (r + g + b) / 3;
			pixelObj.setRed(gray);
			pixelObj.setGreen(gray);
			pixelObj.setBlue(gray);
		}  
	  }	  
  }
  
  /** To pixelate by dividing area into size x size.
    * @param size Side length of square area to pixelate.
    */
  public void pixelate(int size) {	
	  Pixel[][] pixels = this.getPixels2D();
	  int r = 0;
	  int c = 0;
	  while (r < pixels.length && c < pixels[0].length) {
		  int red = 0;
		  int green = 0;
		  int blue = 0;
		  int grids = 0;
		  for (int i = 0; i < size; i++) {
			  for (int j = 0; j < size; j++) {
				  if (r + i < pixels.length && c + j < pixels[0].length) {
					  red += pixels[r + i][c + j].getRed();
					  green += pixels[r + i][c + j].getGreen();
					  blue += pixels[r + i][c + j].getBlue();
					  grids++;
				  }
			  }
		  }
		  
		  for (int a = 0; a < size; a++) {
			  for (int b = 0; b < size; b++) {
				  if (r + a < pixels.length && c + b < pixels[0].length) {
					  pixels[r + a][c + b].setRed(red / grids);
					  pixels[r + a][c + b].setGreen(green / grids);
					  pixels[r + a][c + b].setBlue(blue / grids);
				  }
			  }
		  }
		  
		  if (c + size >= pixels[0].length) {
			  r += size;
			  c = 0;
		  }
		  else
			c += size;
	  }
  }
  
  /** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
  */
  public Picture blur(int size)	//////////////////////////////////////// NO IDEA IF THIS WORKS
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	
	int r = 0;
	int c = 0;
	int mid = size / 2;
	while (r < pixels.length && c < pixels[0].length) {
		int red = 0;
		int green = 0;
		int blue = 0;
		int grids = 0;
		for (int i = r - mid; i <= r + mid; i++) {
			for (int j = c - mid; c <= r + mid; j++) {
				if ( i >= 0 && i < pixels.length && j >=0 && c < pixels[0].length) {
					if (r + i < pixels.length && c + j < pixels[0].length) {
					  red += pixels[r + i][c + j].getRed();
					  green += pixels[r + i][c + j].getGreen();
					  blue += pixels[r + i][c + j].getBlue();
					  grids++;
				    }
				}
			}
		}
		resultPixels[r][c].setRed(red / grids);
		resultPixels[r][c].setGreen(green / grids);
		resultPixels[r][c].setBlue(blue / grids);
	}
	return result;
  }
  
  /** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
  */
  public Picture enhance(int size)	//////////////////////////////////// LOOK UP
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  int r = 0;
	  int c = 0;
	  int mid = size / 2;
	  while (r < pixels.length && c < pixels[0].length) {
		  int red = 0;
		  int green = 0;
		  int blue = 0;
		  int grids = 0;
		  for (int i = r - mid; i <= r + mid; i++) {
			  for (int j = c - mid; c <= r + mid; j++) {
				  if ( i >= 0 && i < pixels.length && j >=0 && c < pixels[0].length) {
					  if (r + i < pixels.length && c + j < pixels[0].length) {
						  red += pixels[r + i][c + j].getRed();
						  green += pixels[r + i][c + j].getGreen();
						  blue += pixels[r + i][c + j].getBlue();
						  grids++;
					  }
				  }
			  }
		  }
		  int avgR = red / grids;
		  int avgG = green / grids;
		  int avgB = blue / grids;
		  resultPixels[r][c].setRed(2 * pixels[r][c].getRed() + avgR);
		  resultPixels[r][c].setGreen(2 * pixels[r][c].getGreen() + avgG);
		  resultPixels[r][c].setBlue(2 * pixels[r][c].getBlue() + avgB);
	  }
	  return result;
  }
  
  /** Method that shifts a percent of the picture to the right, and wraps
   *  it around to the left.
   *  @param percent	the percent of the picture that will be shifted
   * 					and wrapped around
   *  @return			the shifted picture
   */
  public Picture shiftRight(int percent) {	//////////////////////////////////////  DONE BEING WRITTEN
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int shiftAmt = pixels[0].length * percent / 100;
	  for (int c = 0; c < pixels[0].length; c++) {
		  int newC = c + shiftAmt;
		  if (newC >= pixels[0].length)
			newC = pixels[0].length - 1 - newC;
		  for (int r = 0; r < pixels.length; r++) {
			 resultPixelsr[r][newC] = pixels[r][c];
		  }
	  }
	  return result;
  }
  
  /** Method that shifts each row of pixels in a picture a given amount to the
   *  right, and wraps it around to the right. This amount will increase a given
   *  number of times so the result will be jagged.
   *  @param shiftCount		the number of pixels to shift right at each stair step
   *  @param steps			the number of stair steps
   *  @return result		the altered picture
   */
  public Picture stairStep(int shiftCount, int steps) {		//////////////////// DONE BEING WRITTEN
	  Pixels[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int stepSize = (pixels.length - 1) / steps;
	  int shiftAmt = 0;
	  for(int r = 0; r < pixels.length; r++) {
		  if (r % stepSize == 0)
			  shiftAmt += shiftCount;
			  
		  for (int c = 0; c < pixels[0].length; c++) {
			  int newC = c + shiftAmt;
			  if (newC >= pixels[0].length)
				newC = pixels[0].length - 1 - newC;
				resultPixels[r][newC] = pixels[r][c];
		  }
	  }
	  return result;
  }
  
  /** Method that rotates a picture 90 degrees clockwise
   *  @return result	the rotated picture
   */
  public Picture turn90() {		///////////////////////////////////////////// doNE BEING WRITTED
	  Pixels[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels[0].length, pixels.length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  for (int r = 0; r < pixels.length; r++) {
		  for (int c = 0; c < pixels[0].length; c++) {
			  resultPixels[c][pixels.length - r] = pixels[r][c];
		  }
	  }
	  return result;
  }
  
  /** Method that zooms in on the upper left corner (25%) of the image by
   *  replicating each pixel to make it seem larger
   *  @return result	the zoomed in picture
   */
  public Picture zoomUpperLeft() {	////////////////////////////////////////// DONE BEING WRITTEN
	  Pixels[][] pixels = this.getPixels2D();
	  int rLength = pixels.length / 4;
	  int rWidth = pixels[0].length / 4;
	  Picture result = new Picture(rLength, rWidth);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int r = 0;
	  int c = 0;
	  while (r < rLength) {
		  for (int i = 0; i < 2; i++) {
			  for (int j = 0; j < 2; j++) {
				  resultPixels[r + i][c + j] = pixel[r][c];
			  }
		  }
		  c += 2;
		  if (c >= rWidth) {
			  c = 0;
			  r += 2;
		  }
	  }
	  return result;
  }
  
  /** Method to tile the image by reducing it to 25% of its original size, then 
   *  mirror it horizontally and / or vertically and move it to the other three
   *  quadrants
   *  @return result	the tiled picture
   */
  public Picture tileMirror() {		////////////////////// DONE WRITING TRY SEPARATELY
	  Pixels[][] pixels = this.getPixels2D();
	  int rLength = pixels.length / 4 * 4;
	  int rWidth = pixels[0].length / 4 * 4;
	  Picture result = new Picture(rWidth, rLength);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int midL = rLength / 2;
	  int midW = rWidth / 2;
	  
	  // reduce image by finding average of every 4 pixels and making it 1
	  int r = 0;
	  int c = 0;
	  while (r < midL) {
		  int red = 0;
		  int green = 0;
		  int blue = 0;
		  for (int i = 2*r; i < 2*r + 2; i++) {
			  for (int j = 2*c; j < 2*c + 2; j++) {
				  red += pixels[i][j].getRed();
				  green += pixels[i][j].getGreen();
				  blue += pixels[i][j].getBlue();
			  }
		  }
		  resultPixels[r][c].setRed(red / 4);
		  resultPixels[r][c].setGreen(green / 4);
		  resultPixels[r][c].setBlue(blue / 4);
		  
		  c++;
		  if (c >= midW) {
			  c = 0;
			  r++;
		  }
	  }
	  
	  // mirroring into quadrant I
	  for (int r1 = 0; r1 < midL; r1++) {
		  for (int c1 = midW; c1 < rWidth; c1++) {
			  resultPixels[r1][c1] = resultPixels[r1][2*midW - c1 - 1];
		  }
	  }
	  
	  // mirroring into quadrant III
	  for (int r3 = midL; r3 < rlength; r3++) {
		  for (int c3 = 0; c3 < midW; c3++) {
			  resultPixels[r3][c3] = resultPixels[2*midL - r3 - 1][c3];
		  }
	  }
	  
	  // mirroring into quadrant IV
	  for (int r4 = 0; r4 < midL; r4++) {
		  for (int c4 = 0; c4 < midW; c4++) {
			  resultPixels[r4][c4] = resultPixels[midL - r4 - 1][midW - c4 - 1]
		  }
	  }
  }
   
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
