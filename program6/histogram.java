/* ******************************************

Morgan Armstrong
9/25/17
Java HW 5 Histogram Class

****************************************** */

import java.io.*;
import java.util.*;

class histogram
{

	protected ArrayList<Integer> arrayListNums;	// Arraylist created for adding values, regardless of number of lines in file
	protected int[] numArray;					// Array for storing the amount of numbers in each range
	protected int max;							// Max integer
	public int grouping;						// Grouping variable to determine ranges

	// Constructor created with linecount parameter to work with Dannelly's main(), but linecount never used
	public histogram(int linecount)
	{
		arrayListNums = new ArrayList<Integer>(); 	// ArrayList for numbers to be stored
		max = 0;									// Max integer initialized
		grouping = 10;								// Grouping size default to 10
		numArray = new int[100]; 		// Array for counting the number of numbers in each range
	}

	public void add_value(int value)
	{
		// Adds an already checked int value to the array
		arrayListNums.add(value);

		// Determines the max integer, used for printing the histogram (write() function)
		if (value > max)
		{
			max = value;
		}
		
	}

	public void write()
	{
		// Fills the array for counting asterisks with 0's in case write() is called multiple times
		Arrays.fill(numArray, 0);

		// Checks if the arraylist is empty (i.e. if the file contains any integers at all)
		if (arrayListNums.isEmpty())
		{
			System.out.println("The file contains no integers.");
		}
		else
		{
			// Sorts arraylist in ascending order
			Collections.sort(arrayListNums);

			// Traverses through the array list and adds 1 to an array depending on what range it's in
			for (int i = 0; i < arrayListNums.size(); i++)
			{
				numArray[arrayListNums.get(i)/grouping] += 1;
			}

			// For loop for printing the numbers (range), as well as the histogram
			// Only allowing ranges up to integers below 100
			for (int j = 0; j < max; j++)
			{
				if ((j*grouping) < max) // Makes sure that it doesn't print ranges past the max
				{
					System.out.print((j*grouping) + " - " + (j*grouping+(grouping-1)) + " ");
					for (int k = 0; k < numArray[j]; k++)
					{
						System.out.print("*");
					}
					System.out.println();
				}
				else
				{
					return;
				}
				
			}

		}
	}
}