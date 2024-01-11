import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	
 *	@since	
 */
public class SortMethods {
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}
	
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param first	first index of arr to sort
	 *	@param last		last index of arr to sort
	 */
	public void mergeSortRecurse(List<String> arr, int first, int last) {
		// insert your code here
		List<String> temp = arr;
			
		if (last - first == 1 && temp.get(last).compareTo(temp.get(first)) < 0) {
			String x = temp.get(last);
			temp.set(last, temp.get(first));
			temp.set(first, temp.get(last));
		}
			
		else if (last - first > 1) {
			int halfInd = (first + last) / 2;
			List<String> half1 = new ArrayList<String>(halfInd + 1);
			for (int i = first; i <= halfInd; i++)
				half1.add(temp.get(i));
				
			List<String> half2 = new ArrayList<String>(temp.size() - halfInd - 1);
			for (int j = halfInd + 1; j <= last; j++)
				half2.add(temp.get(j));
			
			mergeSortRecurse(half1, 0, half1.size());
			mergeSortRecurse(half2, 0, half2.size());
					 
			for (int k = first; k <= halfInd; k++)
				arr.set(k, half1.get(k - first));
				
			for (int l = halfInd + 1; l <= halfInd; l++)
				arr.set(l, half1.get(l - halfInd - 1));
				
			merge(temp, first, halfInd, last);
		 }
		 for (int m = first; m <= last; m++)
			arr.set(m, temp.get(m));
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	public void merge(List<String> arr, int first, int mid, int last) {
		// Insert your code here
		List<String> merged = arr;
		int ind1 = first;
		int ind2 = mid + 1;
		int index = first;
		
		while (index <= last && ind1 <= mid && ind2 <= last) {
			if (arr.get(ind1).compareTo(arr.get(ind2)) < 0) {
				merged.set(index, arr.get(ind1));
				ind1++;
			}
			else if (arr.get(ind1).compareTo(arr.get(ind2)) > 0) {
				merged.set(index, arr.get(ind2));
				ind2++;
			}
			else {
				merged.set(index, arr.get(ind1));;
				ind1++;
				index++;
				merged.set(index, arr.get(ind2));
				ind2++;
			}
			index++;
		}
		
		if (index <= last) {
			if (ind1 > mid) {		// fill in rest with second half
				for (int y = ind2; y <= last; y++) {
					merged.set(index, arr.get(y));
					index++;
				}
			}
			
			else {		// fill in rest with first half
				for (int z = ind1; z <= mid; z++) {
					merged.set(index, arr.get(z));
					index++;
				}
			}
		}
	}

	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
