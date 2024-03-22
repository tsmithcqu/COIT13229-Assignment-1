package FitnessClub;

import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(2245); // Create a UDP socket bound to the specified port. 
            
            while (true) {
                byte[] buffer = new byte[1000];  // Buffer for receiving incoming requests.

                // Receive request from client.
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                
                // Extract file name from request.
                String fileName = new String(request.getData(), 0, request.getLength());
                System.out.println("File requested by Client: " + fileName);
            }
        }
    }
}

//To Do: send back to client. 
//To Do: Exception handling. 
