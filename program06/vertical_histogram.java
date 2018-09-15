/* **********************************

Morgan Armstrong
10/03/17
CSCI 392: Assignment 6, vertical_histogram

********************************** */

import java.io.*;
import java.util.*;

class vertical_histogram extends histogram
{
	public vertical_histogram(int linecount)
	{
		super(linecount);
		arrayListNums = new ArrayList<Integer>(); 	// ArrayList for numbers to be stored
		numArray = new int[100]; 					// Array for counting the number of numbers in each range
		max = 0;									// Max integer initialized
		grouping = 10;								// Grouping size default to 10
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

			// Find the maximum number of items in range
			int maximum = 0;
			for (int value : numArray)
			{
				if (value > maximum)
				{
					maximum = value;
				}
			}


			// Displays stars for vertical histogram. The spaces wrap around if not in full screen terminal though, and give extra spacing between the stars, just making it much taller
			for (int j = maximum; j >= 1; j--)
			{
				for (int k = 0; k <= 100/grouping; k++)
				{
					if (j <= numArray[k])
					{
						System.out.print("    *     ");
					}
					else
					{
						System.out.print("          ");
					}
				}
				System.out.println();
			}

			// For loop with nested ifs to make sure ranges are all of the same size, i.e. if 0, display 00; if 8, display 08
			for (int l = 0; l < max; l++)
			{
				if ((l*grouping) < max)
				{
					if ((l*grouping) == 0)
					{
						if (((l*grouping)+(grouping-1)) < 10)
						{
							System.out.print("|0" + (l*grouping) + " - 0" + (l*grouping+(grouping-1)) + "| ");
						}
						else
						{
							System.out.print("|0" + (l*grouping) + " - " + (l*grouping+(grouping-1)) + "| ");
						}
					}
					else
					{
						if ((l*grouping) < 10)
						{
							if (((l*grouping)+(grouping-1)) < 10)
							{
								System.out.print("|0" + (l*grouping) + " - 0" + (l*grouping+(grouping-1)) + "| ");
							}
							else
							{
								System.out.print("|0" + (l*grouping) + " - " + (l*grouping+(grouping-1)) + "| ");
							}
						}
						else
						{
							System.out.print("|" + (l*grouping) + " - " + (l*grouping+(grouping-1)) + "| ");
						}
					}
				}

			}

			System.out.println();
		}
	}
}