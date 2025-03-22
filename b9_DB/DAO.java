package b9_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	private Connection conn = null;

	public void closeConnection() throws SQLException {
		if (conn != null)
			conn.close();
	}

	public DAO() throws ClassNotFoundException, SQLException {
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		String dbname = "F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\WorkSpace\\LTrinhMang\\src\\acessDB";
		String url = "jdbc:ucanaccess://" + dbname;
		Class.forName(driver);
		conn = DriverManager.getConnection(url);
	}

	public boolean checkUserName(String name) throws SQLException {
		String sql = "select * from user where username= ?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, name);
		ResultSet res = stat.executeQuery();
		return res.next();
	}

	public boolean login(String uname, String password) throws SQLException {
		String sql = "select * from user where uname = ? and password =?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, uname);
		stat.setString(2, password);
		ResultSet res = stat.executeQuery();
		return res.next();
	}

	public String findByID(String id) throws SQLException {
		int stId = Integer.parseInt(id);// Doi String id duoc nhap vao thanh int
		String sql = "select * from student where ID = ?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setInt(1, stId);
		ResultSet rs = stat.executeQuery();
		String res = null;
		if (rs.next()) {
			res = rs.getString("ID") + "\t" + rs.getString("stname");
		}
		return res;
	}

	public String findByName(String partName) throws SQLException {
		String sql = "select * from student where stname like ?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, "%" + partName);
		ResultSet rs = stat.executeQuery();
		String res = "";
		while (rs.next()) {
			res += rs.getString("ID") + "\t" + rs.getString("stname") + "\n";
		}
		if (res.isEmpty())
			res = null;
		return res;
	}
}
