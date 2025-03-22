package b1;

import java.io.File;

public class DeleteFolderContents {
	public static boolean delete(String path) {
		try {
			File folder = new File(path);

			// kiểm tra xem file có tồn tại hay không
			if (!folder.exists() || !folder.isDirectory()) {
				System.err.println("Không tìm thấy thư mục: " + path);
				return false;
			}

			// Lấy danh sách các tệp và các thư mục con bên trong thư mục ra
			File[] contents = folder.listFiles();

			// Kiểm tra nếu thư mục rỗng
			if (contents == null || contents.length == 0) {
				System.out.println("Thư mục rỗng: " + path);
				return true; // thư mục rỗng thì không có gì để xóa
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
					// Gọi đệ quy để xóa nội dung của thư mục con
					if (delete(content.getPath())) {
						return false;
					}
				}
			}

			// Thư mục đã được xóa hoàn toàn
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

//Hàm test
	public static void main(String[] args) {
		// đưa đường dẫn
		// tuyệt đối vào
		String folderPath = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest - Copy";

		boolean success = delete(folderPath);
		if (success) {
			System.out.println("Xóa thành công nội dung của thư mục.");
		} else {
			System.out.println("Xóa không thành công nội dung của thư mục.");

		}
	}
}
