package FitnessClub; 

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
public static void main(String[] args) throws IOException {
//create a server socket on port 2245
ServerSocket welcomeSocket = new ServerSocket(2245);
//thread pool for managing client connections 
ExecutorService executor = Executors.newCachedThreadPool();


while (true) {
// accept incoming client connections
Socket connectionSocket = welcomeSocket.accept();
//handle each client connection in a separate thread
executor.submit(new ClientHandler(connectionSocket));

}
}
}

// Confirmation message below needs to be appropriately modified. 

     public void run(){
        try { // an echo server
           String data = in.readUTF();
         // System.out.println(data);
          out.writeUTF("Server received:"+data);
