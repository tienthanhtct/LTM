package b3;

import java.io.*;

public class FileSplitter {
	public static void main(String[] args) {
		String sourceFilePath = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest\\test.txt";
		int partSize = 1024 * 1024; // 1 MB
		split(sourceFilePath, partSize);
	}

	public static void split(String filePath, int partSize) {
		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			int bytesRead;
			byte[] buffer = new byte[partSize];
			int partNumber = 1;

			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				String partFilePath = filePath + "." + String.format("%03d", partNumber);
				try (FileOutputStream fileOutputStream = new FileOutputStream(partFilePath)) {
					fileOutputStream.write(buffer, 0, bytesRead);
				}
				partNumber++;
			}

			System.out.println("File split into " + (partNumber - 1) + " parts.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
