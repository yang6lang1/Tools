package test;

import java.sql.*;

public class jdbc_odbc_sample {
	private static Connection con;
	private static String space = "                                           ";

	public static void main(String[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sSQL= "select * from helpdesk";	//the table was created by helpdesk
		String temp="";


		String connectionUrl = "jdbc:odbc:myDSN";

		//System.out.println("\n connectionUrl = " + connectionUrl + "\n\n");

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ce)
		{
			System.out.println("\n\nNo JDBC-ODBC bridge; exit now.\n\n");
			return;
		}

		try
		{
			con = DriverManager.getConnection(connectionUrl,"","");
		}catch (SQLException se)
		{
			System.out.println("\n\nNo proper DSN; exit now.\n\n");
			return;
		}

		try
		{
			pstmt = con.prepareStatement(sSQL);
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
