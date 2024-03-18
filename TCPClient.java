//Re-wrote some parts to align with the Week 2 examples. Added indentation. 
package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Scanner scanner = null; // Declare a Scanner object to read user input.
        Socket socket = null; // Declare a Socket object to establish a TCP connection.
        try {
            String hostName = "localhost"; // The server's hostname.
            int serverPort = 2245; // The port number on which the server is listening.
            socket = new Socket(hostName, serverPort); // Establishing connection to the server.
            scanner = new Scanner(System.in); // Initialising the scanner to read from system's input.

		while (true) {
                System.out.println("Enter member name or 'quit' to exit:"); // Prompt user to enter text.
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase("quit")) { // If the user types "quit", exit the program. 
                    break; 
                }
			
		// Handle exceptions related to the host of the server being unknown.
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage()); 
        // Handle I/O exceptions that may occur during use.
		} catch (IOException e) {
            System.out.println("IO:" + e.getMessage()); 
		// Close the scanner and socket in the finally block to ensure they are closed.
        } finally {
        }
    }
}
// TO DO: Data transmission logic and user interaction.
