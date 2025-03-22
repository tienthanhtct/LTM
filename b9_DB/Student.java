package b9_DB;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private int id;
	private String name;
	private int dOBirth;
	private double grade;

	public Student(int id, String name, int dOBirth, double grade) {
		this.id = id;
		this.name = name;
		this.dOBirth = dOBirth;
		this.grade = grade;
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

	public int getdOBirth() {
		return dOBirth;
	}

	public void setdOBirth(int dOBirth) {
		this.dOBirth = dOBirth;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
