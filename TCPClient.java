package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) {
        Scanner scanner = null; // Declare a Scanner object to read user input.
        Socket socket = null; // Declare a Socket object to establish a TCP connection.
        try {
            int serverPort = 1145; // The port number on which the server is listening.
            socket = new Socket("localhost", serverPort); // Establishing connection to the server running on 'localhost' and the server port above.
            scanner = new Scanner(System.in); // Initialising the scanner for user input.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); // Preparing the ObjectOutputStream for sending data to the server.
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // Preparing the ObjectInputStream for receiving data from the server.

            // Entering a loop to send multiple member data until 'quit' command is provided.
            while (true) {
                System.out.println("Enter 'quit' to exit or press Enter to continue:"); // Inform the user how to quit the program..
		String quit = scanner.nextLine(); // Reading quit.
                if ("quit".equalsIgnoreCase(quit)) break; // Exiting the loop if user enters 'quit'.
		    
		System.out.println("Enter name:"); // Prompt for entering memebr's name.
                String name = scanner.nextLine(); // Reading the member's name.
                
                System.out.println("Enter address:"); // Prompt for entering member's address.
                String address = scanner.nextLine(); // Reading the member's address.

                System.out.println("Enter phone number:"); // Prompt for entering member's phone number.
                String phone = scanner.nextLine(); // Reading the member's phone number.

                Member member = new Member(name, address, phone); // Creating a new Member object with user-provided information.
                out.writeObject(member); // Sending the Member object to the server.
                Member response = (Member) in.readObject(); // Waiting for and receiving the response from the server.
                System.out.println("Server Response: " + response); // Displaying the server's response.
            }
			
	// Handle exceptions related to the host of the server being unknown.
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host:" + e.getMessage()); 
        // Handle I/O exceptions that may occur during use.
	} catch (IOException e) {
            System.out.println("IO:" + e.getMessage()); 
        } finally { // Close the scanner and socket in the finally block to ensure they are closed.
        }
    }
}

