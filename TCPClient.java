package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
                System.out.println("Enter 'quit' to exit:"); // Inform the user how to quit the program..
		if ("quit".equalsIgnoreCase(name)) break; // Exiting the loop if user enters 'quit'.
		    
		System.out.println("Enter name:"); // Prompt for entering memebr's name.
                String name = scanner.nextLine(); // Reading the member's name.
                
                System.out.println("Enter address:"); // Prompt for entering member's address.
                String address = scanner.nextLine(); // Reading the member's address.

                System.out.println("Enter phone number:"); // Prompt for entering member's phone number.
                String phone = scanner.nextLine(); // Reading the member's phone number.

                // Creating a new Member object with user-provided information.
                Member member = new Member(name, address, phone);
                // Sending the Member object to the server.
                out.writeObject(member);
                // Waiting for and receiving the response from the server.
                Member response = (Member) in.readObject();
                // Displaying the server's response.
                System.out.println("Server Response: " + response);
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
// TO DO: Need to check for user inputs, and dont allow blank input. Not sure how. 
// TO DO: Need to allow the user to re-enter details if required. 
// TO DO: Should the loop stay open to constantly accept client name, number, address, etc? 
