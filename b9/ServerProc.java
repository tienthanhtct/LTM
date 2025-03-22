package b9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerProc extends Thread {
	Socket socket;
	PrintWriter netOut;
	BufferedReader netIn;
	DAO dao;

	public ServerProc(Socket socket) throws IOException {
		this.socket = socket;
		netOut = new PrintWriter(socket.getOutputStream(), true);
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.dao = new DAO();
	}

	public void run() {
		boolean isLogin = false;
		String com, param;
		String response = "";
		String lastUserName = null;

		try {
			netOut.println("Ready...");
			String line;
			// login
			while (!isLogin) {
				line = netIn.readLine();
				if (line == null || "QUIT".equalsIgnoreCase(line))
					break;
				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch (com) {
				case "USER":
					if (dao.checkUserName(param)) {// ok
						response = "OK";
						lastUserName = param;
					} else {
						response = "ERROR, invalid user name";
					}
					break;
				case "PASS":
					if (lastUserName == null) {
						response = "ERROR, user name first!";
						break;
					} else {
						if (dao.login(lastUserName, param)) {
							response = "OK, Login success.";
							isLogin = true;
						} else {
							response = "ERROR, Login failed.";
						}
					}
					break;
				default:
					response = "Lenh khong hop le";
					break;
				}
			}
			// find
			List<Student> res = new ArrayList<Student>();
			while (isLogin) {
				res.clear();
				line = netIn.readLine();
				if (line == null || "QUIT".equalsIgnoreCase(line))
					break;
				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch (com) {
				case "FINDBYID":
					res = dao.findByID(param);
					response = makeResponse(res);
					break;
				case "FINDBYNAME":
					res = dao.findByName(line.substring(param.length()).trim());
					response = makeResponse(res);
					break;
				default:
					response = "Lenh khong hop le.";
					break;
				}
				netOut.println(response);
			}
			netOut.println("Service is closed");
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String makeResponse(List<Student> res) {
		if (res.isEmpty()) {
			return "Khong tim thay";
		}
		String s = "";
		for (Student st : res)
			s += st;
		return s;
	}
}
