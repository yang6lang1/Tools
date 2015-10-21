package test;

import java.sql.*;

public class JDBCODBCBridge {
	public JDBCODBCBridge(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn=DriverManager.getConnection("jdbc:odbc:alexDSN","","");
			Statement statement = conn.createStatement();
			//statement = conn.prepareStatement("");
			//rs = statement.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		JDBCODBCBridge j = new JDBCODBCBridge();
	}
}
