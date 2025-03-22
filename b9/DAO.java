package b9;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	List<Student> los;
	List<User> lou;

	public DAO() {
		los = new ArrayList<Student>();
		los.add(new Student(123, "Nguyễn Văn Sáng", 2001, 8.6));
		los.add(new Student(122, "Nguyễn Văn Ánh", 2002, 3.6));

		lou = new ArrayList<User>();
		lou.add(new User("nvSang", "1234"));
		lou.add(new User("nvLam", "12345"));
	}

	public boolean checkUserName(String uname) {
		for (User u : lou) {
			if (u.name.equals(uname))
				return true;
		}
		return false;
	}

	public boolean login(String uname, String password) {
		for (User user : lou) {
			if (user.name.equals(uname) && user.password.equals(password))
				return true;
		}
		return false;
	}

	public List<Student> findByID(String id) {
		List<Student> list = new ArrayList<Student>();
		int stId = Integer.parseInt(id);// Doi String id duoc nhap vao thanh int
		for (Student st : los) {
			if (st.getId() == stId) {
				list.add(st);
				break;
			}
		}
		return list;
	}

	public List<Student> findByName(String name) {
		List<Student> list = new ArrayList<Student>();
		for (Student st : los) {
			if (st.getName().endsWith(name)) {
				list.add(st);
			}
		}
		return list;
	}
}
