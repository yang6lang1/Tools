package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class DBConnection {

	private String username = "s_gya10";
	private String password = "3YbNhjH3q3rR4tn6";
	private Connection con;
	public DBConnection(){
		String connectionUrl = "jdbc:sqlserver://cypress;" +
				"user = " + username + ";" +
				"password = " + password;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			//create tables:
			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Courses'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Courses");
				con.prepareStatement(Queries.createCoursesTable).executeUpdate();
			}
			insertDataIntoTable("Courses",con);
			rs.close();
			
//			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Courses'");
//			rs = pstmt.executeQuery();
//			if(!rs.next()){//table already created
//				System.out.println("Created new table Courses");
//				con.prepareStatement(Queries.createCoursesTable).executeUpdate();
//			}
//			rs.close();
			
			System.out.println("Finish printing results"); 
			con.close();
		}catch(ClassNotFoundException ce)
		{
			System.out.println("\n\nNo JDBC dirver; exit now.\n\n");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			return;
		}
	}
	
	private void insertDataIntoTable(String tableName, Connection con){
		try {
			TXTReader reader = new TXTReader("../../data/"+tableName+".txt");
			String line;
			while((line = reader.readNextLine())!= null){
				//System.out.println(line);
				String[] tokens = line.split(",");
				StringBuffer stmt = new StringBuffer();
				for(int i = 0; i <tokens.length; i++){
					stmt.append("'");
					stmt.append(tokens[i]);
					stmt.append("'");
					if(i+1 < tokens.length) stmt.append(",");
				}
				System.out.println("INSERT INTO " + tableName + " VALUES ("+stmt.toString()+")");
				con.prepareStatement("INSERT INTO " + tableName + " VALUES ("+stmt.toString()+")")
					.executeUpdate();
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		DBConnection con = new DBConnection();
	}
}
