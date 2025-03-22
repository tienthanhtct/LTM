package b9_DB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class StInfoServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket s = new ServerSocket(54321);
		while (true) {
			Socket socket = s.accept();
			ServerProc proc = new ServerProc(socket);
			proc.start();
		}
	}
}
