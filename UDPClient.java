package FitnessClub;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
    private static String fileName;
    private static final String hostName = "localhost";  // Assuming the server is running locally
    
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner input = new Scanner(System.in);
        try {
           aSocket = new DatagramSocket();  // Create a UDP socket.

            // Input the name of the file to request.
            System.out.print("Enter the name of the file to request from the server: ");
            fileName = input.nextLine();

            // Prepare the message to send to the server.
            byte[] m = fileName.getBytes();
            InetAddress aHost = InetAddress.getByName(hostName);

            // Port number.
            int serverPort = 2245;
            
            // Create a UDP datagram with the file name
            DatagramPacket request = new DatagramPacket(m, fileName.length(), aHost, serverPort);
            
            // Send the request to the server
            aSocket.send(request);

            // Prepare a buffer to receive the response.
            byte[] buffer = new byte[1000];  
            
            // Waiting for response.
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            
            // Display the response.
            String response = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Server Response: " + response);

            } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}

//TO DO: Add server response. 
