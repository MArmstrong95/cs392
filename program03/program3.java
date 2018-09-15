/* ******************************************

Morgan Armstrong
9/12/17
Java HW 3

****************************************** */

import java.io.*;
import java.util.*;

// sum all the integers in a file 

public class hw3Armstrong
{
	public static void main(String[] args) throws IOException
	{
		String fname;              // name of the input file
		String dataline = "";      // data value from file
		int sum = 0;               // sum of the values
		BufferedReader inputFile;  // file buffer
		ArrayList<Integer> arrayListNums = new ArrayList<Integer>(); // arraylist created for list of integers
     

     		// prompt and read the name of the file
     		InputStreamReader stdin = new InputStreamReader(System.in);
     		BufferedReader keyboard = new BufferedReader(stdin);
     		System.out.print("Enter the name of a file to process: ");
     		fname = keyboard.readLine(); 

     		// open the data file
     		try
     		{
     			FileReader freader = new FileReader(fname);
        		inputFile = new BufferedReader(freader);
     		}
     		catch (IOException e)
     		{
     			System.out.println("Error: Unable to open file: " + fname);
     			System.out.println("Can not recover from error.  Exiting.");
        		return;
     		} 

     		// read until eof and sum the values
     		dataline = inputFile.readLine();
     
     		while (dataline != null)
     		{
     			// If the dataline is an integer, adds to the sum, then adds to the arrayList
     			try
        		{
        			sum += Integer.parseInt(dataline);
        			arrayListNums.add(Integer.parseInt(dataline));
        			dataline = inputFile.readLine();
        		}
        		catch (Exception e1) // If dataline is not an integer, skips to next dataline
        		{
        			dataline = inputFile.readLine();
     			}
     		}
     		
     		inputFile.close();
     
     		// Checks if the array list is empty (i.e. if the file contains any integers at all)
     		if(arrayListNums.isEmpty())
     		{
     			System.out.println ("The file contains no integers.");
     		}
     		else
     		{
     			// print the final sum
     			System.out.println ("The sum of the integers:");
     	
     			// Sorts and prints out the list of ints in the array
     			Collections.sort(arrayListNums);
     			for(int counter: arrayListNums)
     			{
     				System.out.println(counter);
     			}
     	
     			// Prints out the sum.
     			System.out.println ("is " + sum);
     		}
   	}
}
