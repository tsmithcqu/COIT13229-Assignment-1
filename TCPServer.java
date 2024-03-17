//Re-wrote some parts to align with the Week 2 examples. Added indentation. 
package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    public static void main(String args[]) {
        ExecutorService executor = null; // Declare an ExecutorService to manage threads.
        try {
            int serverPort = 2245; // Server will listen on this port number.
            ServerSocket listenSocket = new ServerSocket(serverPort); // Create a server socket bound to the specified port above.

            // Listening loop not yet handling client data, just connections.
		// Handle general I/O exceptions.
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage()); // Basic error handling, needs more detail.
        } finally {
        }
    }
}
