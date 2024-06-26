package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    private static java.util.Timer serializationTimer = new java.util.Timer(); // Initialises a Timer for scheduling tasks. 
	
    public static void main(String[] args) {
        ExecutorService threadPool = null; // Executor service for managing threads

        try {
            int serverPort = 1145; // Server will listen on this port number.
            ServerSocket listenSocket = new ServerSocket(serverPort); // Create a server socket bound to the specified port above.
            threadPool = Executors.newCachedThreadPool(); // Thread pool for managing client connections.
            System.out.println("TCP Server is running..."); // Notification that server is successfully running.

            serializationTimer.schedule(new MemberListSerialization(), 0, 2000); // Schedule the task for serialising member list to run every 2 seconds starting immediately.

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
		//Writing the member details to a text file.
		FileWriter fw = new FileWriter("memberlist.txt", true); // Open the file in append mode.
                BufferedWriter bw = new BufferedWriter(fw); // Wrap FileWriter in BufferedWriter for efficient writing.
		PrintWriter outToFile = new PrintWriter(bw); // Wrap BufferedWriter in PrintWriter for easy text output.
		outToFile.println(member.toString()); // Write the member details to the file.
                outToFile.close(); // Close the file writer

                System.out.println("Received and saved member data: " + member); // Notify that member data has been received and saved

                out.writeObject("Member details received and saved successfully."); // Sending confirmation back to client

		}
        	} catch (EOFException e) { // Catch and handle EOFException
            	    System.out.println("EOF:" + e.getMessage());
        	} catch (IOException e) { // Catch and handle IOException
            	    System.out.println("IO:" + e.getMessage());
        	} finally {
            	try {
                    if (clientSocket != null) clientSocket.close(); // Close client socket if it's not null.
            	    } catch (IOException e) { // Catch and handle IOException during socket close.
                        System.out.println("Close failed:" + e.getMessage());
            }
        }
    }
}

// The below code, not including the exception handling, has been generated by Gen AI. However, all the comments have been added by me. 
class MemberListSerialization extends TimerTask {
    public void run() {
        try {
            ArrayList<Member> memberList = new ArrayList<>(); // Create a new ArrayList for storing Member objects.
            BufferedReader reader = new BufferedReader(new FileReader("memberlist.txt")); // Reader for the member list file.
            String line;
            while ((line = reader.readLine()) != null) { // Read each line from the file.
                memberList.add(new Member(line)); // Add new Member object to the list.
            }
            reader.close(); // Close the reader.

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("memberlistObject")); // Serialising the member list.
            oos.writeObject(memberList); // Write the member list object to the file.
            oos.close(); // Close the ObjectOutputStream.
            
            System.out.println("Member list serialised successfully"); // Notification of successful serialisation.

	// The exception handling code is developed by me. 
        } catch (IOException e) { // Catch and handle exceptions related to input/output operations
            System.out.println("Serialisation IO Exception: " + e.getMessage());
        }
    }
}
