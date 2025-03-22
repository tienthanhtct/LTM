package b1;

import java.io.File;

public class Bai1 {
//	public class DeleteFolderContents {
	public static boolean delete(String path) {
		try {
			File folder = new File(path);

			// Kiểm tra xem đường dẫn là một thư mục và tồn tại
			if (!folder.exists() || !folder.isDirectory()) {
				System.err.println("Không tìm thấy thư mục: " + path);
				return false;
			}

			// Lấy danh sách các tệp và thư mục con bên trong thư mục
			File[] contents = folder.listFiles();

			// Kiểm tra nếu thư mục là rỗng
			if (contents == null || contents.length == 0) {
				System.out.println("Thư mục rỗng: " + path);
				return true; // Thư mục rỗng, không có gì để xóa
			}

			// Lặp qua danh sách các tệp và thư mục con và xóa chúng
			for (File content : contents) {
				if (content.isFile()) {
					// Xóa tệp
					if (!content.delete()) {
						System.err.println("Không thể xóa tệp: " + content.getPath());
						return false;
					}
				} else if (content.isDirectory()) {
					// Gọi đệ quy để xóa nội dung thư mục con
					if (!delete(content.getPath())) {
						return false;
					}
				}
			}

			// Thư mục đã được xóa hoàn toàn
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		String folderPath = ""; // Thay thế bằng đường dẫn
								// của thư mục bạn muốn
								// xóa
		boolean success = delete(folderPath);
		if (success) {
			System.out.println("Xóa thành công nội dung của thư mục.");
		} else {
			System.out.println("Xóa không thành công nội dung của thư mục.");
		}
	}
}
