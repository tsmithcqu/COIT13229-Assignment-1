package FitnessClub;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
public static void main(String[] args){
Scanner scanner = new Scanner(System.in);
Socket socket = new Socket("localhost", 2245);

while(true) {
System.out.println("Enter member name or 'quit' to exit:");
String name = scanner.nextLine();
			
//Need to make this neater. Need to ask for first name and last name and address sweparately, and add so the user can change if needed. 
        }
    }
}
