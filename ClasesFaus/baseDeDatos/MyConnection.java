package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	private static Connection con = null;
	private static Statement stmt = null;
	static {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:example.db");
			stmt = con.createStatement();
		} catch (java.sql.SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		return con;
	}
	
	public static Statement getStatement(){
		return stmt;
	}

	private MyConnection() {
		
	}
}