package b6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentManagerment {

	public static List<Student> loadData(String stFile, String gradeFile, String charset)
			throws IOException, FileNotFoundException {
		List<Student> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(stFile), charset));
		String line, token;
		StringTokenizer st;
		int id;
		String name;
		int year;
		double grade;
		while ((line = reader.readLine()) != null) {
			st = new StringTokenizer(line, "\t");
			id = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			year = Integer.parseInt(st.nextToken());
			list.add(new Student(id, name, year));
		}
		reader.close();

		reader = new BufferedReader(new InputStreamReader(new FileInputStream(gradeFile), charset));
		return list;
	}

	public static void export(List<Student> list, String textFile, String charset) throws IOException {
		PrintWriter pw = new PrintWriter(textFile, charset);
		for (Student st : list) {
			String line = st.getId() + "/t" + st.getName() + "/t" + st.getbYear() + "/t" + st.getGrade();
			pw.println(line);
		}
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		String stFile = "";
		String gradeFile = "";
		String charset = "UTF-8";

		List<Student> list = loadData(stFile, gradeFile, charset);
		System.out.println(list);

		String expCharset = "UTF-16";
		String textFile = "";
		export(list, textFile, expCharset);
	}
}
