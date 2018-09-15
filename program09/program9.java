/* *************************************

Morgan Armstrong
CSCI 392: HW09 Web Crawler
11/8/17

************************************* */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class hw09 {
      public static void main(String[] args) {
            System.out.println("Starting Web Tester...\n");

            if (args.length == 0) {
                  System.out.println("Please specify a file name. Exiting...\n");
                  System.exit(1);
            }

            try {
                  // Connect to the server
                  Socket socket = new Socket ("faculty.winthrop.edu", 80);

                  // Get the reading and writing streams
                  InputStream sockIn = socket.getInputStream();
                  BufferedReader fromServer = new BufferedReader(new InputStreamReader(sockIn));
                  OutputStream sockOut = socket.getOutputStream();
                  PrintWriter toServer = new PrintWriter (new OutputStreamWriter(sockOut));

                  // Build the request message
                  String outmsg = "GET ";
                  outmsg += args[0];
                  outmsg += " HTTP/1.0\r\n\r\n";

                  // Send the request to the server
                  toServer.print(outmsg);
                  toServer.flush();

                  // Create string for HTML page
                  String htmlPage = "";

                  // Read the response and create string of webpage
                  String inputLine = fromServer.readLine();
                  while (inputLine != null) {
                        htmlPage += inputLine;
                        inputLine = fromServer.readLine();
                  }

                  // Replace all whitespace in string of html page to account
                  // for any difference in something like a="" vs. a = ""
                  htmlPage.replaceAll("\\s", "");

                  // Create pattern for finding links
                  Pattern linkPattern = Pattern.compile("<a[^>]+?href=(\"([^\"]*\")|\'([^\']*\'))", Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
                  Matcher pageMatcher = linkPattern.matcher(htmlPage);

                  // Create arraylist to store the found links
                  ArrayList<String> links = new ArrayList<String>();

                  // Make a counter for number of links found
                  int counter = 0;

                  // Loop through webpage string to find links.
                  // It also removes any " or ' that was left in the group
                  while(pageMatcher.find()) {
                        links.add(pageMatcher.group(1).replaceAll("[\"\']", ""));
                        counter++;
                  }

                  // Print arraylist full of links
                  for(int i = 0; i < links.size(); i++) {
                        System.out.println(links.get(i).toString());
                  }

                  System.out.print("\n----------------------------------------");
                  System.out.print("\n\nNumber of links found: ");
                  System.out.print(counter);


            } catch (Exception e) {
                  System.out.println(e.getMessage());
            }

            System.out.println("\nWeb Tester Finished...");
      }
}
