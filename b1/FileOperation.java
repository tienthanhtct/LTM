package b1;

import java.io.File;

public class FileOperation {
	static public boolean remove(String path) {
		File file = new File(path);
		if (!file.exists())
			return true;
//		if (file.isFile()) {
//			return file.delete();
//		}
		if (file.isDirectory()) {
			File[] list = file.listFiles();
			if (list != null)
				for (File f : list)
					remove(f.getAbsolutePath());
		}
		return file.delete();
	}

	public static void main(String[] args) {
		String path = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest - Copy";
		System.out.println(remove(path));
	}
}
