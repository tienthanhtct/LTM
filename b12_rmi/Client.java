package b12_rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry(1099);
		ISearch dao = (ISearch) reg.lookup("SEARCH");

		// netIn để nhận vào thư người dùng nhập
		BufferedReader netIn = new BufferedReader(new InputStreamReader(System.in));

		// netOut là server in ra
		PrintStream netOut = System.out;

		String com, param, response;
		String line;
		String rs;

		while (true) {
			line = netIn.readLine();
			if (line == null || "QUIT".equalsIgnoreCase(line))
				break;
			StringTokenizer st = new StringTokenizer(line);
			com = st.nextToken().toUpperCase();
			param = st.nextToken();
			switch (com) {
			case "FINDBYID":
				rs = dao.findByID(param);
				if (rs == null)
					response = "Khong tim thay";
				else
					response = rs;
				break;
			case "FINDBYNAME":
				param = line.substring(com.length()).trim();
				rs = dao.findByName(param);
				if (rs == null)
					response = "Khong tim thay";
				else
					response = rs;
				break;
			default:
				response = "Lenh khong hop le";
				break;
			}
			netOut.println(response);
		}
		netOut.println("Service is closed");

	}
}
