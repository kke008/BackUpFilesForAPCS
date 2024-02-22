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
 * @author Barbara Ericson ericson@cc.gatech.edu and Karen Ke
 * @since February 5, 2024
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
  public Picture blur(int size)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	int mid = size / 2;
	for (int r = 0; r < pixels.length; r++) {
		for (int c = 0; c < pixels[0].length; c++) {
			int red = 0;
			int green = 0;
			int blue = 0;
			int grids = 0;
			for (int i = r - mid; i <= r + mid; i++) {
				for (int j = c - mid; j <= c + mid; j++) {
					if (i >= 0 && i < pixels.length && j >= 0 && 
											j < pixels[0].length) {
						red += pixels[i][j].getRed();
						green += pixels[i][j].getGreen();
						blue += pixels[i][j].getBlue();
						grids++;
					}
				}
			}
			resultPixels[r][c].setRed(red / grids);
			resultPixels[r][c].setGreen(green / grids);
			resultPixels[r][c].setBlue(blue / grids);
		}
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
  public Picture enhance(int size)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  int mid = size / 2;
	  for (int r = 0; r < pixels.length; r++) {
		  for (int c = 0; c < pixels[0].length; c++) {
			  int red = 0;
			  int green = 0;
			  int blue = 0;
			  int grids = 0;
			  for (int i = r - mid; i <= r + mid; i++) {
				  for (int j = c - mid; j <= c + mid; j++) {
					  if ( i >= 0 && i < pixels.length && j >=0 && j < pixels[0].length) {
						  red += pixels[i][j].getRed();
						  green += pixels[i][j].getGreen();
						  blue += pixels[i][j].getBlue();
						  grids++;
					  }
				  }
			  }
			  int avgR = red / grids;
			  int avgG = green / grids;
			  int avgB = blue / grids;
			  resultPixels[r][c].setRed(2 * pixels[r][c].getRed() - avgR);
			  resultPixels[r][c].setGreen(2 * pixels[r][c].getGreen() - avgG);
			  resultPixels[r][c].setBlue(2 * pixels[r][c].getBlue() - avgB);
		  }
	  }
	  return result;
  }
  
  /** Method that shifts a percent of the picture to the right, and wraps
   *  it around to the left.
   *  @param percent	the percent of the picture that will be shifted
   * 					and wrapped around
   *  @return			the shifted picture
   */
  public Picture shiftRight(int percent) {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int shiftAmt = pixels[0].length * percent / 100;
	  for (int r = 0; r < pixels.length; r++) {
		  for (int c = 0; c < pixels[0].length; c++) {
			  int newC = c + shiftAmt;
			  if (newC >= pixels[0].length)
				  newC = newC - pixels[0].length;
			  
			  resultPixels[r][newC].setRed(pixels[r][c].getRed());
			  resultPixels[r][newC].setGreen(pixels[r][c].getGreen());
			  resultPixels[r][newC].setBlue(pixels[r][c].getBlue());
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
  public Picture stairStep(int shiftCount, int steps) {
	  Pixel[][] pixels = this.getPixels2D();
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
				  newC = newC - pixels[0].length;
			  
			  resultPixels[r][newC].setRed(pixels[r][c].getRed());
			  resultPixels[r][newC].setGreen(pixels[r][c].getGreen());
			  resultPixels[r][newC].setBlue(pixels[r][c].getBlue());
		  }
	  }
	  return result;
  }
  
  /** Method that rotates a picture 90 degrees clockwise
   *  @return result	the rotated picture
   */
  public Picture turn90() {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels[0].length, pixels.length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  for (int r = 0; r < pixels.length; r++) {
		  for (int c = 0; c < pixels[0].length; c++) {
			  int newC = pixels.length - 1 - r;
			  resultPixels[c][newC].setRed(pixels[r][c].getRed());
			  resultPixels[c][newC].setGreen(pixels[r][c].getGreen());
			  resultPixels[c][newC].setBlue(pixels[r][c].getBlue());
		  }
	  }
	  return result;
  }
  
  /** Method that zooms in on the upper left corner (25%) of the image by
   *  replicating each pixel to make it seem larger
   *  @return result	the zoomed in picture
   */
  public Picture zoomUpperLeft() {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int r = 0;	// current row in og pic
	  int c = 0;	// current column in og pic
	  int rMax = pixels.length / 2 - 1;		// half the rows
	  int cMax = pixels[0].length / 2 - 1;	// half the columns
	  int rowInd = 0;	// used to go through rows of new pic
	  int colInd = 0;	// used to go through columns of new pic
	  
	  while (r <= rMax) {
		  int red = pixels[r][c].getRed();
		  int green = pixels[r][c].getGreen();
		  int blue = pixels[r][c].getBlue();
		  
		  for (int i = 0; i < 2; i++) {
			  for (int j = 0; j < 2; j++) {
				  if (rowInd + i < resultPixels.length && 
								colInd + j < resultPixels[0].length) {
					  resultPixels[rowInd + i][colInd + j].setRed(red);
					  resultPixels[rowInd + i][colInd + j].setGreen(green);
					  resultPixels[rowInd + i][colInd + j].setBlue(blue);
				  }
			  }
		  }
		  
		  colInd += 2;
		  if (colInd >= resultPixels[0].length) {
			  colInd = 0;
			  rowInd += 2;
		  }
		  c++;
		  if (c > cMax) {
			  c = 0;
			  r++;
		  }
	  }
	  return result;
  }
  
  /** Method to tile the image by reducing it to 25% of its original size, then 
   *  mirror it horizontally and / or vertically and move it to the other three
   *  quadrants
   *  @return result	the tiled picture
   */
  public Picture tileMirror() {	
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  
	  int midL = pixels.length / 2;
	  int midW = pixels[0].length / 2;
	  
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
		  for (int c1 = midW; c1 < pixels[0].length; c1++) {
			  int red = resultPixels[r1][2*midW - c1 - 1].getRed();
			  int green = resultPixels[r1][2*midW - c1 - 1].getGreen();
			  int blue = resultPixels[r1][2*midW - c1 - 1].getBlue();

			  resultPixels[r1][c1].setRed(red);
			  resultPixels[r1][c1].setGreen(green);
			  resultPixels[r1][c1].setBlue(blue);
		  }
	  }
	  
	  // mirroring into quadrant III
	  for (int r3 = midL; r3 < pixels.length; r3++) {
		  for (int c3 = 0; c3 < midW; c3++) {
			  int red = resultPixels[2*midL - r3 - 1][c3].getRed();
			  int green = resultPixels[2*midL - r3 - 1][c3].getGreen();
			  int blue = resultPixels[2*midL - r3 - 1][c3].getBlue();
			  
			  resultPixels[r3][c3].setRed(red);
			  resultPixels[r3][c3].setGreen(green);
			  resultPixels[r3][c3].setBlue(blue);
		  }
	  }
	  
	  // mirroring into quadrant IV
	  for (int r4 = 0; r4 < midL; r4++) {
		  for (int c4 = 0; c4 < midW; c4++) {
			  int row = resultPixels.length - 1 - r4;
			  int col = resultPixels[0].length - 1 - c4;
			  
			  resultPixels[row][col].setRed(resultPixels[r4][c4].getRed());
			  resultPixels[row][col].setGreen(resultPixels[r4][c4].getGreen());
			  resultPixels[row][col].setBlue(resultPixels[r4][c4].getBlue());
		  }
	  }
	  return result;
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

 ////////////////////////////////////////////////////////////////// ADD EDGE DETECTION BELOW HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  
  /** Method to superimpose two images of subjects with a green background on to 
   *  a picture of a background, and move and size them to look natural.
   */
  public Picture greenScreen() {
	  Picture cat = new Picture("kitten1GreenScreen.jpg");
	  Pixel[][] catPix = cat.getPixels2D();
	  Picture mouse = new Picture("mouse1GreenScreen.jpg");
	  Pixel[][] mousePix = mouse.getPixels2D();
	  Picture bkg = new Picture("IndoorHouseLibraryBackground.jpg");
	  Pixel[][] bkgPix = bkg.getPixels2D();
	  Picture result = new Picture(bkg);
	  Pixel[][] resultPix = result.getPixels2D();
	  
	  Color green = catPix[0][0].getColor();
	  int threshold = 100;
	  
	  // resizing cat (into 149 x 149)
	  Picture newCat = new Picture(catPix.length / 2, catPix[0].length / 2);
	  Pixel[][] newCatPix = newCat.getPixels2D();
	  int catR = 0;
	  int catC = 0;
	  while (catR < newCatPix.length) {
		  int redC = 0;
		  int greenC = 0;
		  int blueC = 0;
		  int pixels = 0;
		  for (int i = 2*catR; i < 2*catR + 2; i++) {
			  for (int j = 2*catC; j < 2*catC + 2; j++) {
				  if (i < catPix.length && j < catPix[0].length) {
					  redC += catPix[i][j].getRed();
					  greenC += catPix[i][j].getGreen();
					  blueC += catPix[i][j].getBlue();
					  pixels++;
				  }
			  }
		  }
		  newCatPix[catR][catC].setRed(redC / pixels);
		  newCatPix[catR][catC].setGreen(greenC / pixels);
		  newCatPix[catR][catC].setBlue(blueC / pixels);
		  
		  catC++;
		  if (catC >= newCatPix[0].length) {
			  catR++;
			  catC = 0;
		  }
	  }
	  
	  // adding cat
	  for (int a = 0; a < newCatPix.length; a++) {
		  for (int b = 0; b < newCatPix[0].length; b++) {
			  if (newCatPix[a][b].colorDistance(green) > threshold)
				resultPix[a + 375][b + 520].setColor(newCatPix[a][b].getColor());
		  }
	  }
	  
	  // resizing mouse (into 74 x 124)
	  Picture newMouse = new Picture(mousePix.length / 2, mousePix[0].length / 2);
	  Pixel[][] newMousePix = newMouse.getPixels2D();
	  int mouseR = 0;
	  int mouseC = 0;
	  while (mouseR < newMousePix.length) {
		  int redM = 0;
		  int greenM = 0;
		  int blueM = 0;
		  int pixels = 0;
		  for (int i = 2*mouseR; i < 2*mouseR + 2; i++) {
			  for (int j = 2*mouseC; j < 2*mouseC + 2; j++) {
				  if (i < mousePix.length && j < mousePix[0].length) {
					  redM += mousePix[i][j].getRed();
					  greenM += mousePix[i][j].getGreen();
					  blueM += mousePix[i][j].getBlue();
					  pixels++;
				  }
			  }
		  }
		  newMousePix[mouseR][mouseC].setRed(redM / pixels);
		  newMousePix[mouseR][mouseC].setGreen(greenM / pixels);
		  newMousePix[mouseR][mouseC].setBlue(blueM / pixels);
		  
		  mouseC++;
		  if (mouseC >= newMousePix[0].length) {
			  mouseR++;
			  mouseC = 0;
		  }
	  }
	  
	  // adding mouse
	  for (int c = 0; c < newMousePix.length; c++) {
		  for (int d = 0; d < newMousePix[0].length; d++) {
			  if (newMousePix[c][d].colorDistance(green) > threshold)
				resultPix[c + 350][d + 300].setColor(newMousePix[c][d].getColor());
		  }
	  }
	  return result;
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
