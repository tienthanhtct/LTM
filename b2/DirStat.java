package b2;

import java.io.File;

public class DirStat {
	public static void dirTree(String path) {
		File dir = new File(path);
		if (!dir.exists())
			return;
		int level = 0;
		if (dir.isFile())
			printFile(dir, level);// de quy
		if (dir.isDirectory())
			dirTreeHelper(dir, level);
	}

	private static long dirTreeHelper(File dir, int level) {
		long total = 0;
		File[] list = dir.listFiles();
		for (File f : list) {
			if (f.isDirectory())
				total += dirTreeHelper(f, level + 1);
		}
		for (File f : list) {
			if (f.isFile())
				total += printFile(f, level + 1);
		}
		for (File f : list) {
			if (f.isFile())
				total += printFile(f, level + 1);
		}
		printDir(dir, level, total);
		return total;

	}

	private static void printDir(File dir, int level, long cap) {
		StringBuilder sb = getIndent(level);
		sb.append(dir.getName().toUpperCase());
		sb.append(":");
		sb.append(cap);
	}

	private static void printFile(File dir, int level) {
		StringBuilder sb = new StringBuilder();
		if (level == 0) {
			sb.append("+-");
		} else {
			sb.append("   ");
			for (int i = 1; i < level; i++) {
				sb.append("|  ");
			}
			sb.append("+-");
		}
		sb.append(dir.getName().toLowerCase());
		System.out.println(sb.toString());
	}

	private static StringBuilder getIndent(int level) {
		StringBuilder sb = new StringBuilder();
		if (level == 0) {
			sb.append("+-");
		} else {
			sb.append("   ");
			for (int i = 1; i < level; i++) {
				sb.append("|  ");
			}
			sb.append("+-");
		}
		return sb;
	}

	public static void main(String[] args) {
		String path = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\FileTest";
		dirTree(path);
	}
}
