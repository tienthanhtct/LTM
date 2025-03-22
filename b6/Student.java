package b6;

//sv.txt : text, UTF-8  \t   1SV / 1line
//MSSV \t Họ và tên  \t  năm sinh
//Diem.txt
//MSSV \t đ1 \t d2  \t ... dn
//1. loadData()
//2. export
public class Student {
	private int id;
	private String name;
	private int bYear;
	private double grade;

	public Student(int id, String name, int bYear) {
		this.id = id;
		this.name = name;
		this.bYear = bYear;
//		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getbYear() {
		return bYear;
	}

	public void setbYear(int bYear) {
		this.bYear = bYear;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", bYear=" + bYear + ", grade=" + grade + "]";
	}

}
