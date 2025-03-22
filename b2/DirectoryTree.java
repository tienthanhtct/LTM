package b2;

import java.io.File;

public class DirectoryTree {
	public static void displayDirectoryTree(File dir, int depth) {
		if (dir.isDirectory()) {
			// In ra khoảng trắng để tạo hiệu ứng cấu trúc cây
			for (int i = 0; i < depth; i++) {
				System.out.print(" -- ");
			}
			// In ra tên thư mục
			System.out.println(dir.getName());
			// Lấy danh sách các thư mục con và gọi đệ quy để in ra cấu trúc cây của chúng
			File[] subdirectories = dir.listFiles(File::isDirectory);
			if (subdirectories != null) {
				for (File subdirectory : subdirectories) {
					displayDirectoryTree(subdirectory, depth + 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		String folderPath = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest"; // Thay thế bằng đường dẫn
																							// của thư mục bạn muốn
		// hiển thị cấu trúc cây
		File rootFolder = new File(folderPath);

		if (rootFolder.exists() && rootFolder.isDirectory()) {
			System.out.println("Cấu trúc cây thư mục:");
			displayDirectoryTree(rootFolder, 0);
		} else {
			System.err.println("Thư mục không tồn tại hoặc không hợp lệ.");
		}
	}
}
