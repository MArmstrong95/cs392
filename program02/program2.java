/* ***************************************

Name: Morgan Armstrong
Assignment: Hello World 1.1
Date: 9/5/17

*************************************** */

class hello2 {
	public static void main (String[] args) {

		// Initialize number of hellos to print
		int numLoops;

		// Test the user input
		// If the user enters 0 arguments, then print out Hello World
		if (args.length == 0) {
			System.out.println("Hello World.");
			return;
		} else if (args.length == 1) {
			try {
				numLoops = Integer.parseInt (args[0]); // Check if the input is an int

				 // If it is, then print hello world, that many times
				for (int i = 0; i < numLoops; i++) {
					System.out.println("Hello World!");
				}
			// If it's not an int, print Hello "name" once
			} catch (Exception e1) {
				System.out.println("Hello " + args[0]);
				return;
			}


		} else if (args.length == 2) {
			try {
				numLoops = Integer.parseInt (args[0]); // Check the first input for an int

				try {
					numLoops = Integer.parseInt (args[1]); // Check if the second input is an int

					System.out.println("Usage Error: java hello2 name count"); // Error message if 2 ints
					return;
				} catch (Exception en1) {
					// If it isn't, print Hello name
					for (int i = 0; i < numLoops; i++) {
						System.out.println("Hello " + args[1]);
					}
				}
			// If first input isn't an int then process second input
			} catch (Exception e2) {
				try {
					numLoops = Integer.parseInt (args[1]); // Check the second input for an int

					 // If it is, print Hello "name"
					for (int i = 0; i < numLoops; i++) {
						System.out.println("Hello " + args[0]);
					}
				// If both int then print usage error
				} catch (Exception en2) {
					System.out.println("Usage Error: java hello2 name count");
					return;
				}
			}
		// If there are too many inputs, print error
		} else if (args.length > 2) {
			System.out.println("Usage Error: Too many arguments.");
		}
	}
}
