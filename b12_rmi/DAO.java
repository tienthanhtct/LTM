package b12_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO extends UnicastRemoteObject implements ISearch {
	private Connection conn = null;

	public void closeConnection() throws SQLException {
		if (conn != null)
			conn.close();
	}

//Khi làm với Remote thì chỉ chấp nhận 1 loại EXcention là remoteException

	public DAO() throws RemoteException {
		try {
			String driver = "net.ucanaccess.jdbc.UcanaccessDriver";

//			String url = "jdbc:ucanaccess://D:\\students.mdb";// thay bang vi tri DB khac

			String url = "jdbc:ucanaccess://F:\\Download(F)\\TaiLieuDaiHoc\\Nam3_1\\LTrinhMang\\WorkSpace\\LTrinhMang\\src\\acessDB";
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public boolean checkUserName(String name) throws SQLException {
//		String sql = "select * from user where username= ?";
//		PreparedStatement stat = conn.prepareStatement(sql);
//		stat.setString(1, name);
//		ResultSet res = stat.executeQuery();
//		return res.next();
//	}
//
//	public boolean login(String uname, String password) throws SQLException {
//		String sql = "select * from user where uname = ? and password =?";
//		PreparedStatement stat = conn.prepareStatement(sql);
//		stat.setString(1, uname);
//		stat.setString(2, password);
//		ResultSet res = stat.executeQuery();
//		return res.next();
//	}

	public String findByID(String id) throws RemoteException {
		try {
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
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	public String findByName(String partName) throws RemoteException {
		try {
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
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
