package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    public static void main(String[] args) {
        ExecutorService threadPool = null;

        try {
            int serverPort = 1145; // Server will listen on this port number.
            ServerSocket listenSocket = new ServerSocket(serverPort); // Create a server socket bound to the specified port above.
            threadPool = Executors.newCachedThreadPool(); // Thread pool for managing client connections.
            System.out.println("TCP Server is running..."); // Notification that server is successfully running.

            while (true) { // Infinite loop to continuously accept new client connections.
                Socket clientSocket = listenSocket.accept(); // Accepts client connections.
		System.out.println("Client connected"); // Message to notify that client has connected.
                threadPool.execute(new Connection(clientSocket)); // Assign handling of the new client connection to a new thread.
            }

        } catch (IOException e) { // Catch and handle exceptions related to input/output operations.
            System.out.println("Listen socket:" + e.getMessage());
        } 
    }
}

class Connection extends Thread {
    ObjectInputStream in; // For reading objects from the client.
    ObjectOutputStream out; // For writing objects to the client.
    Socket clientSocket; // Socket representing the connection to the client.

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket; // Assigning the client socket.
            in = new ObjectInputStream(clientSocket.getInputStream()); // Initialising the input stream.
            out = new ObjectOutputStream(clientSocket.getOutputStream()); // Initialising the output stream.
            this.start(); // Starting the thread.
        } catch (IOException e) { // Catch and handle exceptions related to input/output operations.
            System.out.println("Connection:" + e.getMessage()); // Display exceptions. 
        }
    }

    public void run() {
        try {
            Member member = (Member) in.readObject(); // Read a Member object from the client.
            synchronized (this) { // Synchronising to handle one client at a time.
                System.out.println("Received member data: " + member); // Display the received member object here.
                out.writeObject(member); // Respond to the client with the processed information.
            	}
	}
}
	// Handle general I/O exceptions.
          catch (IOException e) {
            System.out.println("Listen :" + e.getMessage()); // Basic error handling, needs more detail.
        } finally {
        }
    }
}


//To do: Add Timer Function
//To Do: Add functionality to write to Member.txt. 
// To Do: Need to send confirmation message back to the client. Will need to modify TCPClient to display this message. 
