package FitnessClub; 

import java.io.*;
import java.net.*;

public class TCPServer {
public static void main(String[] args) throws IOException {
ServerSocket welcomeSocket = new ServerSocket(2245);

while (true) {
Socket connectionSocket = welcomeSocket.accept();
}
}
}

// Confirmation message? Not sure where it goes. Investigate tomorrow. 
