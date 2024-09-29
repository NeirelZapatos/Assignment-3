import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Sorting {
	final int MAX_SIZE = 10000000;

	// Set this to true if you wish the arrays to be printed.
	final static boolean OUTPUT_DATA = false;
	
	public static String sortAlg= null;
	public static  int size = 0;
	
	public static void main(String[] args) {
		readInput();
		int [] data = new int[size];
		GenerateSortedData(data, size);
		Sort(data, size,"Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size,"Nearly Sorted Data");
		
		GenerateReverselySortedData(data, size);
		Sort(data, size,"Reversely Sorted Data");
		
		GenerateRandomData(data, size);
		Sort(data, size,"Random Data");
			
		System.out.println("\nProgram Completed Successfully.");
		
	}
	
	@SuppressWarnings("resource")
	public static void readInput(){
		System.out.println("  I:\tInsertion Sort");
		System.out.println("  M:\tMergeSort");
		System.out.println("  Q:\tQuickSort");
		System.out.println("  S:\tSTLSort");
	    System.out.println("Enter sorting algorithm:");
	    Scanner reader = new Scanner(System.in);
	    sortAlg = reader.next();
	    System.out.println(sortAlg);
		String sortAlgName = "";
		
		if(sortAlg.equals("I"))
			sortAlgName = "Insertion Sort";
		else if(sortAlg.equals("M"))
			sortAlgName = "MergeSort";
		else if(sortAlg.equals("Q"))
			sortAlgName = "QuickSort";
		else if(sortAlg.equals("S"))
			sortAlgName = "STLSort";
		else {
			System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
			System.exit(1);
		}
		System.out.println("Enter input size: ");
	    size = reader.nextInt();
		System.out.println("\nSorting Algorithm: " + sortAlgName);
        System.out.println("\nInput Size = "  + size);
	}
	
	/******************************************************************************/

	public static void GenerateSortedData(int data[], int size)
	{
		int i;
		
		for(i=0; i<size; i++)
			data[i] = i * 3 + 5;
	}
	/*****************************************************************************/
	public static void GenerateNearlySortedData(int data[], int size)
	{
		int i;
		
		GenerateSortedData(data, size);
		
		for(i=0; i<size; i++)
			if(i % 10 == 0)
				if(i+1 < size)
					data[i] = data[i+1] + 7;
	}
	/*****************************************************************************/

	public static void GenerateReverselySortedData(int data[], int size)
	{
		int i;
		
		for(i = 0; i < size; i++)
			data[i] = (size-i) * 2 + 3;
	}
	/*****************************************************************************/

	public static void GenerateRandomData(int data[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			data[i] = new Random().nextInt(10000000);
	}
	/*****************************************************************************/

	
	public static void Sort(int[] data, int size,  String string)
	{

		System.out.print("\n"+string+":");
		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

		// Sorting is about to begin ... start the timer!
		long start_time = System.nanoTime();
			if (sortAlg.equals("I"))
			{
				InsertionSort(data, size);
			}
			else if (sortAlg.equals("M"))
			{
				MergeSort(data, 0, size-1);
			}
			else if (sortAlg.equals("Q"))
			{
				int depth = QuickSort(data, 0, size-1, 0);
				System.out.println("\nDepth: " + depth);
			}
			else if (sortAlg.equals("S"))
			{
				STLSort(data, size);
			}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		
		double elapsed = System.nanoTime()-start_time;
		elapsed=elapsed/1000000;


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
	
	/*****************************************************************************/

	public static boolean IsSorted(int data[], int size)
	{
		int i;

		for(i=0; i<(size-1); i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
	/*****************************************************************************/

	public static void InsertionSort(int data[], int size)
	{
		//Write your code here
		for (int i = 1; i < data.length; i++) {
			int key  = data[i];
			int j;
			for (j = i - 1; j >= 0 && data[j] > key; j--) {
				data[j + 1] = data[j];
			}

			data[j + 1] = key;
		}
//		System.out.println("InsertionSort");
	}
	/*****************************************************************************/

	public static void MergeSort(int data[], int lo, int hi)
	{
		//Write your code here
		//You may create other functions if needed
		if (lo >= hi) {
			return;
		}

		int mid = (lo + hi) / 2;

		MergeSort(data, lo, mid);
		MergeSort(data, mid + 1, hi);
		Combine(data, lo, mid, hi);
//		System.out.println("MergeSort");
	}

	public static void Combine(int data[], int lo, int mid, int hi) {
		int n1 = mid - lo + 1;
		int n2 = hi - mid;

		int[] left = new int[n1 + 1];
		int[] right = new int[n2 + 1];

		int leftMax = Integer.MIN_VALUE;
		int rightMax = Integer.MIN_VALUE;
		for (int i = 0; i < n1; i++) {
			left[i] = data[i + lo];
			leftMax = Math.max(left[i], leftMax);
		}
		for (int i = 0; i < n2; i++) {
			right[i] = data[i + 1 + mid];
			rightMax = Math.max(right[i], rightMax);
		}

		int max = Math.max(leftMax, rightMax);
		left[n1] = max + 1;
		right[n2] = max + 1;

		int i = 0;
		int j = 0;
		for (int k = lo; k <= hi; k++) {
			if (left[i] < right[j]) {
				data[k] = left[i];
				i++;
			} else {
				data[k] = right[j];
				j++;
			}
		}
	}
	/*****************************************************************************/

	public static int QuickSort(int data[], int lo, int hi, int depth)
	{
		//Write your code here
		//You may create other functions if needed
		if (lo >= hi) {
			return depth;
		}
		int m = Partition(data, lo, hi);

		// for using insertion sort for array of 40 or less
		int arrayLength = hi - lo + 1;
		if (arrayLength <= 40) {
			InsertionSortForQuickSort(data, lo, hi);
			return depth;
		}

		int leftDepth = QuickSort(data, lo, m - 1, depth + 1);
		int rightDepth = QuickSort(data, m + 1, hi, depth + 1);
		return Math.max(leftDepth, rightDepth);

		//System.out.println("QuickSort");
	}

	public static int Partition(int data[], int lo, int hi) {
		Random rand = new Random();

		// for the random index pivot
		int randIndex = rand.nextInt((hi - lo) + 1) + lo;
		swap(hi, randIndex, data);

		// for the median of three random index pivot
//		int randIndex1 = rand.nextInt((hi - lo) + 1) + lo;
//		int randIndex2 = rand.nextInt((hi - lo) + 1) + lo;
//		int randIndex3 = rand.nextInt((hi - lo) + 1) + lo;
//
//		int num1 = data[randIndex1];
//		int num2 = data[randIndex2];
//		int num3 = data[randIndex3];
//
//		int[] randNums = {num1, num2, num3};
//		Arrays.sort(randNums);
//		int median = randNums[1];
//
//		if (median == num1) {
//			swap(randIndex1, hi, data);
//		} else if (median == num2) {
//			swap(randIndex2, hi, data);
//		} else {
//			swap(randIndex3, hi, data);
//		}

		int v = data[hi];
		int x = lo - 1;

		for (int y = lo; y <= hi - 1; y++) {
			if (data[y] <= v) {
				swap(x + 1, y, data);
				x++;
			}
		}

		swap(hi, x + 1, data);
		return x + 1;
	}

	public static void InsertionSortForQuickSort(int[] data, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			int key = data[i];
			int j;
			for (j = i - 1; j >= 0 && data[j] > key; j--) {
				data[j + 1] = data[j];
			}

			data[j + 1] = key;
		}
	}
	/*****************************************************************************/

	public static void STLSort(int data[], int size)
	{
		//Write your code here
		//Your code should simply call the STL sorting function
		Arrays.sort(data);
		//System.out.println("STLSort");
		
	}
	/*****************************************************************************/
	
	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}
	
	/*****************************************************************************/
	
	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}

}
