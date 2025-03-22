package b5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RafList {
	static final int NAME_LEN = 25;
	RandomAccessFile raf;
	int count = 0;
	int recSize = 0;
	int len;
	private int hsize;

	public RafList(String file) throws IOException {
		this.raf = new RandomAccessFile(file, "rw");
		if (raf.length() > 0) { // exist => read header
			count = raf.readInt();
			recSize = raf.readInt();
			len = (recSize - 16) / 2;
			System.out.println("Count " + count);
			System.out.println("Len " + len);
		} else { // create new
			len = NAME_LEN;
			recSize = 16 + len * 2;
			raf.writeInt(count);
			raf.writeInt(recSize);

		}
	}

	public void add(Student st) throws IOException {
		count++;
		raf.seek(raf.length());
//		st.writeFixedLengthName(raf, len);
		st.writeStudent(raf, len);
		raf.seek(0);
		raf.writeInt(count);
	}

	public Student get(int index) throws IOException {
		Student st = null;
		if (index >= count)
			return null;
		long pos = hsize + index * recSize;
		raf.seek(pos);
		st = new Student(index, null, index, pos);
		st.readStudent(raf, len);
		return st;
	}

	public void update(int index, Student st) throws IOException {
		if (index >= count)
			return;
		long pos = hsize + index * recSize;
		raf.seek(pos);
//		st.writeFixedLengthName(raf, len);
		st.writeStudent(raf, len);
	}

	public Student findById(int id) {

		return null;
	}

	public void close() throws IOException {
		raf.close();
	}

	public static void main(String[] args) throws IOException {
		String file = "";
		RafList list = new RafList(file);
		list.add(new Student(111, "Nguyễn Thị Hoa Hồng", 2003, 9.2));
		list.add(new Student(222, "Trần Văn Nguyên", 2005, 0.2));
		list.add(new Student(222, "Lê Thị Riêng", 2005, 7.2));
//		Student st = list.get(2);
//		System.out.println(st);
//		System.out.println(list.get(2));
//		list.update(2, new Student(222, "Lê Phi Hùng", 2005, 10));
//		System.out.println(list.get(2));
		list.close();
	}
}
