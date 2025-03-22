package b2;

import java.io.File;

public class FindFileOrFolder {
	public static boolean findFirst(String path, String pattern) {
		File folder = new File(path);

		// Kiểm tra xem đường dẫn là một thư mục và tồn tại
		if (!folder.exists() || !folder.isDirectory()) {
			System.err.println("Không tìm thấy thư mục: " + path);
			return false;
		}

		// Lấy danh sách các tệp và thư mục con bên trong thư mục
		File[] contents = folder.listFiles();

		// Kiểm tra từng tệp và thư mục
		if (contents != null) {
			for (File item : contents) {
				// Kiểm tra xem tên tệp hoặc thư mục chứa pattern
				if (item.getName().contains(pattern)) {
					System.out.println("Đường dẫn đầy đủ: " + item.getAbsolutePath());
					return true; // Tìm thấy
				}

				// Nếu là thư mục, gọi đệ quy để kiểm tra bên trong thư mục này
				if (item.isDirectory() && findFirst(item.getAbsolutePath(), pattern)) {
					return true; // Tìm thấy trong thư mục con
				}
			}
		}

		// Không tìm thấy trong thư mục và các thư mục con
		return false;
	}

	public static void main(String[] args) {
		// Thay thế bằng đường dẫn của thư mục bạn muốn tìm kiếm
		String folderPath = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest - Copy";
		String searchPattern = "pattern"; // Thay thế bằng chuỗi bạn muốn tìm kiếm

		boolean found = findFirst(folderPath, searchPattern);
		if (found) {
			System.out.println("Tìm thấy tệp/thư mục chứa chuỗi pattern.");
		} else {
			System.out.println("Không tìm thấy tệp/thư mục chứa chuỗi pattern.");
		}
	}
}
