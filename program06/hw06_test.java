/* ******************************************

Morgan Armstrong
10/03/17
CSCI 392: HW 6 main

****************************************** */

import java.io.*;
import java.util.*;

class hw06_test
{
	public static void main(String[] args) throws IOException
	{
		String fileName;			// Name of the input file
		String dataline = "";		// Data value from the file
		BufferedReader inputFile;	// File buffer
		int barSizeRange = 10;		// Input bar range size
		int nextvalue = 0;
		int linecount = 0;
		histogram myHistogram = new histogram(linecount);
		vertical_histogram vertHistogram = new vertical_histogram(linecount);

		// Reads the file name from the keyboard
		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(stdin);
		System.out.print("Please enter the name of a file to process: ");
		fileName = keyboard.readLine();

		// Reads grouping range from keyboard
		System.out.print("Bar range size: ");
		barSizeRange = Integer.parseInt(keyboard.readLine());
		myHistogram.grouping = barSizeRange;
		vertHistogram.grouping = barSizeRange;

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
				nextvalue = Integer.parseInt(dataline);
				
				myHistogram.add_value(nextvalue);
				vertHistogram.add_value(nextvalue);

				dataline = inputFile.readLine();
			}
			catch (Exception e1)
			{
				dataline = inputFile.readLine();
			}
		}

		// Close the file
		inputFile.close();

		System.out.println("Regular Histogram:");
		myHistogram.write();
		System.out.println("Vertical Histogram:");
		vertHistogram.write();
		
	}
}