package b8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
	Socket socket;
	BufferedReader netIn;
	PrintWriter netOut;

	double operand1, operand2;
	char operator;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream(), true);
		netOut.println("Welcome to computing system ... ");
	}

	public void run() {
		String request, response;
		try {
			while (true) {
				// request rec.
				request = netIn.readLine();
				if (request.equalsIgnoreCase("EXIT"))
					break;
				try {
					// split request
					requestAnal(request);
					// do
					double res = doRequest();
					// response
					response = request + "=" + res;
					netOut.println(response);
				} catch (MyException e) {
					netOut.println(e.getMessage());
				}
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void requestAnal(String request) throws MyException {
		StringTokenizer st = new StringTokenizer(request, "+-*/");
		String str1, str2;
		str1 = st.nextToken();
		try {
			str2 = st.nextToken();
		} catch (NoSuchElementException e) {
//check operand1
			String line = request.trim();
			char c = line.charAt(0);
			if ((c == '+') || (c == '-'))
				throw new MyException("Thieu toan hang 1");
			else
				throw new MyException("Thieu toan hang 2");
		}
		this.operator = request.charAt(str1.length());
		try {
			this.operand1 = Double.parseDouble(str1.trim());
		} catch (java.lang.NumberFormatException e) {
			throw new MyException("Toan hang 1 khog phai so");
		}
		try {
			this.operand2 = Double.parseDouble(str2.trim());
		} catch (java.lang.NumberFormatException e) {
			throw new MyException("Toan hang 2 khog phai so");
		}
		this.operand2 = Double.parseDouble(str2.trim());
	}

	private double doRequest() {
		double res = 0;
		switch (this.operator) {
		case '+':
			res = operand1 + operand2;
			break;
		case '-':
			res = operand1 - operand2;
			break;
		case '*':
			res = operand1 * operand2;
			break;
		case '/':
			res = operand1 / operand2;
			break;
		}
		return res;
	}
}
