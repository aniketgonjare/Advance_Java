package dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
	private static Connection cn;
	public static Connection openConnection() throws SQLException
	{
		
		String url="jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		cn=DriverManager.getConnection(url,"root","admin@123");
		System.out.println("db cn established....");
		return cn;
	}
	
	public static void closeConnection() throws SQLException{
		if(cn != null)
			cn.close();
		System.out.println("db cn closed....");
	}

}
