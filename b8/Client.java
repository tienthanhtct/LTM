package b8;

import java.io.*;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12345);

			// Setup input and output streams
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Read and print the welcome message from the server
			String welcomeMessage = reader.readLine();
			System.out.println(welcomeMessage);

			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				// Prompt the user for input
				System.out.print("Enter operation (e.g., 2 + 2) or 'quit' to exit: ");
				String userInput = consoleReader.readLine();

				// Send the user input to the server
				writer.write(userInput + "\n");
				writer.flush();

				// Receive and print the server's response
				String response = reader.readLine();
				System.out.println(response);

				// Check if the user wants to quit
				if (userInput.equalsIgnoreCase("quit")) {
					break;
				}
			}

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
