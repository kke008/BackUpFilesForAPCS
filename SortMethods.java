/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Mr.Greenstein and Karen Ke
 *	@since	November 30, 2023
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length - 1; outer > 0; outer--)
			for (int inner = 0; inner < outer; inner++)
				if (arr[inner].compareTo(arr[inner + 1]) > 0)
					swap(arr, inner, inner + 1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		int lengthOfUnsorted;
		for (lengthOfUnsorted = arr.length; lengthOfUnsorted > 1; lengthOfUnsorted--) {
			int indexOfMax = 0;
			for (int inner = 0; inner < lengthOfUnsorted; inner++) {
				if (arr[inner].compareTo(arr[indexOfMax]) > 0)
					indexOfMax = inner;
			}
			swap(arr, indexOfMax, lengthOfUnsorted - 1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for (int i = 1; i < arr.length; i++) {
			Integer temp = arr[i];
			int index = i;
			while (index > 0 && temp < arr[index - 1]){
				arr[index] = arr[index - 1];
				index--;
			}
			arr[index] = temp;
		} 
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		sortHalves(arr);
	}
	
	public Integer[] sortHalves(Integer[] temp) {
		if (temp.length == 2 && temp[1] < temp[0])
			swap(temp, 0, 1);
			
		else if (temp.length > 2) {
			Integer[] half1 = new Integer[temp.length / 2];
			for (int i = 0; i < half1.length; i++)
				half1[i] = temp[i];
			
			Integer[] half2 = new Integer[temp.length - temp.length / 2];
			for (int j = 0; j < half2.length; j++)
				half2[j] = temp[j + temp.length / 2];
			
			half1 = sortHalves(half1);
			half2 = sortHalves(half2);
			 
			temp = merge(half1, half2);
		 }
		 System.out.print("temp: ");				////////////////////////////////////////
		 for (int i = 0; i < temp.length; i++) {
			 System.out.print(temp[i] + ", ");
		 }
		 System.out.println();
		 
		 return temp;
	}
	
	public Integer[] merge(Integer[] half1, Integer[] half2) {
		Integer[] merged = new Integer[half1.length + half2.length];
		int ind1 = 0;
		int ind2 = 0;
		int index = 0;
		while (index < merged.length && ind1 < half1.length && ind2 < half2.length) {
			
			if (half1[ind1] < half2[ind2]) {
				merged[index] = half1[ind1];
				ind1++;
			}
			else if (half1[ind1] > half2[ind2]) {
				merged[index] = half2[ind2];
				ind2++;
			}
			else {
				merged[index] = half1[ind1];
				index++;
				merged[index] = half2[ind2];
				ind1++;
				ind2++;
			}
			index++;
		}
		
		if (ind1 == half1.length) {
			for (int a = ind2; a < half2.length; a++) {
				merged[index] = half2[a];
				index++;
			}
		}
		else if (ind2 == half2.length) {
			for (int b = ind1; b < half1.length; b++) {
				merged[index] = half1[b];
				index++;
			}
		}
		
		return merged;
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
/*		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		*/
		
		
	/*	for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}
