package dao;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2, pst3;

	public UserDaoImpl() throws SQLException {
		// open conn
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2 = cn.prepareStatement(
				"insert into users (first_name,last_name,email,password,dob,status,role) values (?,?,?,?,?,?,?)");
		pst3 = cn.prepareStatement("update users set status=1 where id=?");

		System.out.println("user dao created!");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next()) // => success
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
		System.out.println("user dao cleaned up!");
	}

	@Override
	public boolean authenticateNewUser(String firstName, String lastName, String email, String password, String dob)
			throws SQLException {
		Date ld = Date.valueOf(dob);
		LocalDate todays = LocalDate.now();

		boolean status = false;
		String role = "voter";
		// int age = Period.between(ld, todays).getYears();

		if (Period.between(LocalDate.parse(dob), todays).getYears() > 21) {
			// first_name,last_name,email,password,dob,status,role
			pst2.setString(1, firstName);
			pst2.setString(2, lastName);
			pst2.setString(3, email);
			pst2.setString(4, password);
			pst2.setDate(5, ld);
			pst2.setBoolean(6, status);
			pst2.setString(7, role);
			int rst1 = pst2.executeUpdate();
			if (rst1 > 0) {
				return true;
			}
		}
		return false;

	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		pst3.setInt(1, voterId);
		int updateCount = pst3.executeUpdate();
		if (updateCount == 1)
			return "Updated voting status";
		return "Updation failed!!!!!";
	}

}
