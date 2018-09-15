/* **********************************

Morgan Armstrong
10/25/17
CSCI 392: Assignment 8, green screen

********************************** */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class GreenScreen {

	public static void main(String[] args) throws IOException {

		String FG_FileName;			// File name for foreground image
		String BG_FileName;			// File name for background image

		// Reads the file names from the keyboard
		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(stdin);
		System.out.print("Please enter the name of the foreground image file: ");
		FG_FileName = keyboard.readLine();

		System.out.print("Please enter the name of the background image file: ");
		BG_FileName = keyboard.readLine();

		System.out.println("Processing files...");

		// Function call
		mergeImages(FG_FileName, BG_FileName);

		System.out.println("Done.");

	}

	public static void mergeImages(String foregroundImage, String backgroundImage)
	{
		BufferedImage f_img = null;
		BufferedImage b_img = null;

		try {
			f_img = ImageIO.read(new File(foregroundImage));
			b_img = ImageIO.read(new File(backgroundImage));

			int width1 = f_img.getWidth();
			int height1 = f_img.getHeight();
			int width2 = b_img.getWidth();
			int height2 = b_img.getHeight();

			// Only works with same size images, so check if same size to continue
			if ((width1 != width2) || (height1 != height2))
			{
				System.out.println("Images are not the same size. Please enter two images of the same height and width. Aborting.");
				return;
			}

			// Iterates through pixels
			for (int x = 0; x < width1; x++)
			{
				for (int y = 0; y < height1; y++)
				{
					int rgba1 = f_img.getRGB(x, y);
					int rgba2 = b_img.getRGB(x, y);
					Color fColor = new Color(rgba1, true);
					Color bColor = new Color(rgba2, true);

					// Checks the color of each pixel in the foreground image if it is a shade of green and replaces it with the background pixel if it is
					if ((fColor.getRed() >= 0 && fColor.getRed() <= 120) && (fColor.getGreen() >= 105) && (fColor.getBlue() >= 0 && fColor.getBlue() <= 105))
					{
						f_img.setRGB(x, y, bColor.getRGB());
					}
					else
					{
						f_img.setRGB(x, y, fColor.getRGB());
					}
				}
			}

			// Creates a new file
			try {
				File merged_img = new File("merged_"+foregroundImage);
				ImageIO.write(f_img, "jpg", merged_img);
				} catch (IOException e) {
					System.out.println("Cannot create new file.");
				}
		}
		catch (IOException e) {
			System.out.println("Can't open file! Process failed.");
		}

		
	}
}