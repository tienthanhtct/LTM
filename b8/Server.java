package b8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket s = new ServerSocket(12345);
		while (true) {
			Socket socket = s.accept();
			ServerThread thread = new b8.ServerThread(socket);
			thread.start();
		}
	}
}
