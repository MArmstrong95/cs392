/* ******************************************

Morgan Armstrong
9/19/17
Java HW 4

****************************************** */

import java.io.*;
import java.util.*;

public class hw4
{
	public static void main(String[] args) throws IOException
	{
		String fileName;			// Name of the input file
		String dataline = "";		// Data value from the file
		int max = 0;				// Used to find the max int in the file
		BufferedReader inputFile;	// File buffer
		ArrayList<Integer> arrayListNums = new ArrayList<Integer>(); // Arraylist for numbers to be stored
		int[] numArray = new int[10]; // Array for counting the number of numbers in each range

		// Reads the file name from the keyboard
		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(stdin);
		System.out.print("Please enter the name of a file to process: ");
		fileName = keyboard.readLine();

		// Open the data file
		try
		{
			FileReader freader = new FileReader(fileName);
			inputFile = new BufferedReader(freader);
		}
		catch(IOException e)
		{
			System.out.println("Error: Unable to open file: " + fileName);
			System.out.println("Cannot recover from error. Exiting.");
			return;
		}

		// Read until end of file
		dataline = inputFile.readLine();

		while (dataline != null)
		{
			// If the dataline is an integer, adds to the arrayList
			try
			{
				arrayListNums.add(Integer.parseInt(dataline));
				
				if (Integer.parseInt(dataline) > max)
				{
					max = Integer.parseInt(dataline);
				}

				dataline = inputFile.readLine();
			}
			catch (Exception e1)
			{
				dataline = inputFile.readLine();
			}
		}

		// Close the file
		inputFile.close();

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
				numArray[arrayListNums.get(i)/10] += 1;
			}

			// For loop for printing the numbers (range), as well as the histogram
			for (int j = 0; j < 10; j++)
			{
				if ((j*10) < max) // Makes sure that it doesn't print ranges past the max
				{
					System.out.print((j*10) + " - " + (j*10+9) + " ");
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