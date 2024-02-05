public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
  
  /** 
   * counts number of times a given integer appears in the matric.
   * @param val		given integer to look for
   * @return count	number of times val appears
   */
  public int getCount(int val) 
  {
	  int count = 0;
	  for (int i = 0; i < matrix.length; i++) 
	  {
		  for (int j = 0; j < matrix[0].length; j++)
		  {
			  if (matrix[i][j] == val)
				count++;
		  }
	  }
	  return count;
  }
  
  /**
   * returns largest value in matrix
   * @return max	largest value in matrix
   */
  public int getLargest()
  {
	  int max = matrix[0][0];
	  for (int i = 0; i < matrix.length; i++) 
	  {
		  for (int j = 0; j < matrix[0].length; j++)
		  {
			  if (matrix[i][j] > max)
				max = matrix[i][j];
		  }
	  }
	  return max;
  }
  
  /**
   * returns total of all values in given column
   * @param col		index of given column
   * @return total	total of all ints in col
   */
  public int getColTotal(int col) {
	  int total = 0;
	  for (int i = 0; i < matrix.length; i++)
	  {
		  total += matrix[i][col];
	  }
	  return total;
  }
  
  /**
   * reverses values in each row (first with last, second to first with
   * second to last, etc.). Prints both the original array and the
   * reversed array.
   */
  public void reverseRows()
  {
	  System.out.println("Original array:");
	  for (int a = 0; a < matrix.length; a++)
	  {
		  for (int b = 0; b < matrix[0].length; b++)
		  {
			  System.out.printf("| %d ", matrix[a][b]);
		  }
		  System.out.println("|");
	  }
	  
	  int first = 0;
	  int last - matrix[0].length - 1;
	  int row = 0;
	  while (row < matrix.length) 
	  {
		  if (first == last || first > last)
		  {
			first = 0;
			last = matrix[0].length - 1;
			row++;
		  }

		  else
		  {
			  int temp = matrix[row][first];
			  matrix[row][first] = matrix[row][last];
			  matrix[row][last] = temp;
			  first++;
			  last--;
		  }
	  }

	  System.out.println("Reversed array:");
	  for (int c = 0; c < matrix.length; c++)
	  {
		  for (int d = 0; d < matrix[0].length; d++)
		  {
			  System.out.printf("| %d ", matrix[c][d]);
		  }
		  System.out.println("|");
	  }
  }
 
}
