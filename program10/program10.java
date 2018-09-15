/* *************************************

Morgan Armstrong
CSCI 392: HW10 Web Crawler 2.0
11/29/17

************************************* */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.net.*;

public class hw10 {

      public static void main(String[] args) throws IOException {
            Document doc = null, subDoc = null;
            Elements links = null, subLinks = null;
            Element link = null, subLink = null;
            String url = "";
            String path = "";

            // Get url path from user
            try {
                  url = args[0];
            } catch (Exception e) {
                  System.out.println("Please enter a path name on server: faculty.winthrop.edu. Exiting.");
                  return;
            }

            // Server name
            String server = "https://faculty.winthrop.edu";

            // Set url to server + path name
            url = server + url;
            System.out.println("URL to process: " + url);

            // Validate url given
            if (isValidURL(url)) {
                  System.out.println("URL is valid: " + url);
            } else {
                  System.out.println("URL is NOT valid: " + url);
            }

            // Get the HTML file
            try {
                  doc = Jsoup.connect(url).get();
                  // Change url path to actual location. Used to change /csci392 to /csci392/ to determine between a folder and file
                  path = doc.location().substring(0, doc.location().lastIndexOf("/"));
            } catch (Exception e) {
                  System.out.println("Error: Unable to retrieve URL.");
                  return;
            }

            // Print the page title, if there is one
            try {
                  String title = doc.title();
                  System.out.println("Title of page: " + title);
            } catch (Exception e) { }

            // Print all links
            links = doc.select("a[href]");
            System.out.println("There are " + links.size() + " links:");
            for (int i = 0; i < links.size(); i++) {

                  link = links.get(i);

                  // Make sure link is not external (i.e. start with http:// or https://)
                  if (!link.attr("href").toLowerCase().matches("^\\w+://.*")) {
                        // Create string for testing new url
                        String newURL = path + "/" + link.attr("href");
                        
                        // Validate new url and do process over again for each sublink
                        if(isValidURL(newURL)) {
                              System.out.println("Valid    -->" + link.attr("href"));

                              try {
                                    subDoc = Jsoup.connect(newURL).get();
                              } catch (Exception e) { }

                              try {
                                    subLinks = subDoc.select("a[href]");
                                    System.out.println("\t There are " + subLinks.size() + " links:");

                                    for (int j = 0; j < subLinks.size(); j++) {
                                          
                                          subLink = subLinks.get(j);

                                          if(!subLink.attr("href").toLowerCase().matches("^\\w+://.*")) {
                                                String subHref = subLink.attr("href");
                                                subHref = subHref.replaceFirst("^../", "");
                                                String newerURL = path + "/" + subHref;
                                                if (isValidURL(newerURL)) {
                                                      System.out.println("\tValid    --> " + subLink.attr("href"));
                                                } else {
                                                      System.out.println("\tInvalid  --> " + subLink.attr("href"));
                                                }
                                          } else {
                                                System.out.println("\tExternal --> " + subLink.attr("href"));
                                          }
                                    }
                              } catch (Exception e) {
                                    // Doesn't print anything since it's not an html page
                              }

                              System.out.println();

                        } else {
                              System.out.println("Invalid  -->" + link.attr("href"));
                        }
                  } else {
                        System.out.println("External --> " + link.attr("href"));
                  }
                  
            }
      }

      public static boolean isValidURL(String urlCheck) {
            try {
                  URL url = new URL(urlCheck);
                  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                  connection.setRequestMethod("GET");
                  connection.connect();
                  int code = connection.getResponseCode();

                  // Returns true if response code is 200 OK
                  if (code == 200) {
                        return true;
                  } else {
                        return false;
                  }
            } catch(Exception e) {
                  // Returns false if connection can't be made
                  return false;
            }
      }

}
