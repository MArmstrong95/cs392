/* **********************************

Morgan Armstrong
10/04/17
CSCI 392: Assignment 7, invert image

********************************** */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class image1
{

	public static void main(String[] args)
	{
		// State that image conversion is beginning
		System.out.println("Starting Image Conversion...");

		// Read argument from command line, process if there is a file name
		try
		{
			invertImage(args[0]);
			System.out.println("Done.");
		}
		catch (Exception e)
		{
			System.out.println("Cannot open File. Aborting.");
		}

		
	}


	// Function to process and invert image
	public static void invertImage(String imageName)
	{
		// Create image file
		BufferedImage my_img = null;
		
		// Try to read from file and invert RGB colors
		try
		{
			my_img = ImageIO.read(new File(imageName));

			for (int x = 0; x < my_img.getWidth(); x++)
			{
				for (int y = 0; y < my_img.getHeight(); y++)
				{
					int rgba = my_img.getRGB(x, y);
					Color col = new Color(rgba, true);
					col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
					my_img.setRGB(x, y, col.getRGB());
				}
			}
		} 
		catch (IOException e) 
		{
			System.out.println("Can't open file!");
		}

		// Try to write a new invert image file
		try 
		{
			File new_img = new File("inv_"+imageName);
			ImageIO.write(my_img, "jpg", new_img);
		}
		catch (IOException e)
		{
			System.out.println("File not found. Cannot invert image. Exiting.");
		}
	}

}