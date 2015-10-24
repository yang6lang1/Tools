package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbc_sample {
	private static Connection con;
	private static String space = "                                           ";

	public static void main(String[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sSQL= "select * from helpdesks";	//the table was created by helpdesk
		String temp="";

		String sUsername = "s_gya10";
		String sPassword= "3YbNhjH3q3rR4tn6";
		// ^^^ modify these 2 lines before compiling this program
		// please replace the username with your CCN id
		// please get the password from table 'helpdesk' of your course database

		String connectionUrl = "jdbc:sqlserver://cypress;" +
				"user = " + sUsername + ";" +
				"password = " + sPassword;

		//System.out.println("\n connectionUrl = " + connectionUrl + "\n\n");

		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
			//System.out.println("\n\nNo JDBC dirver; exit now.\n\n");
			//return;
		}

		try
		{
			con = DriverManager.getConnection(connectionUrl);
		}catch (SQLException se)
		{
			System.out.println("\n\nFail to connect to CSIL SQL Server; exit now.\n\n");
			return;
		}

		try
		{
			pstmt = con.prepareStatement("SELECT * FROM helpdesk");
			rs = pstmt.executeQuery();

			System.out.println("\nThe table 'helpdesk' contains:\n\n");

			while (rs.next()) {
				temp= rs.getString("username");	//the table has a field 'username'
				System.out.println(temp);
			}
			rs.close();
			System.out.println("\nSuccessfully connected to CSIL SQL Server!\n\n");
		}catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+
					se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}
	}//end of main

}
