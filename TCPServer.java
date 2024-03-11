package FitnessClub; 

import java.io.*;
import java.net.*;

public class TCPServer {
public static void main(String[] args){
ServerSocket welcomeSocket = new ServerSocket(2245);

while (true) {
Socket connectionSocket = welcomeSocket.accept();
Connection c = new Connection(clientSocket);
System.out.printf("\nServer waiting on: %d for client from %d ",
listenSocket.getLocalPort(), clientSocket.getPort() );
}
catch(IOException e){
System.out.println("Listen :"+e.getMessage());
}
}
}

// Confirmation message? Not sure where it goes. Investigate tomorrow. 
