package b1;

import java.io.*;

public class FileInputStreamDemo {
	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("Syntax - FileInputStreamDemo file");
			return;
		}
		try {
			InputStream fileInput = new FileInputStream(args[0]);
			int data = fileInput.read();
			while (data != -1) {
				System.out.write(data);
				data = fileInput.read();
			}
//			System.out.flush();
			fileInput.close();
		} catch (IOException ioe) {
			System.err.println("I/O error - " + ioe);
		}
	}
}
