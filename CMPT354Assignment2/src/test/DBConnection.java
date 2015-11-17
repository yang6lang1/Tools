package test;

/**
 * Main class.
 * */
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

			//create tables & load data:
			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Courses'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Courses");
				con.prepareStatement(Queries.createCoursesTable).executeUpdate();
			}
			insertDataIntoTable("Courses",con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Instructors'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Instructors");
				con.prepareStatement(Queries.createInstructorsTable).executeUpdate();
			}
			insertDataIntoTable("Instructors",con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'CourseSections'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table CourseSections");
				con.prepareStatement(Queries.createCourseSectionsTable).executeUpdate();
			}
			insertDataIntoTable("CourseSections",con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Students'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Students");
				con.prepareStatement(Queries.createStudentsTable).executeUpdate();
			}
			insertDataIntoTable("Students", 5 ,con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Enrollments'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Enrollments");
				con.prepareStatement(Queries.createEnrollmentsTable).executeUpdate();
			}
			insertDataIntoTable("Enrollments", 6 ,con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'Areas'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table Areas");
				con.prepareStatement(Queries.createAreasTable).executeUpdate();
			}
			insertDataIntoTable("Areas",con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'AreasOfCourse'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table AreasOfCourse");
				con.prepareStatement(Queries.createAreasOfCourseTable).executeUpdate();
			}
			insertDataIntoTable("AreasOfCourse",con);
			rs.close();
			System.out.println();

			pstmt = con.prepareStatement(Queries.getExistingTables+" WHERE TABLE_NAME = 'AreasOfInstructor'");
			rs = pstmt.executeQuery();
			if(!rs.next()){//table already created
				System.out.println("Created new table AreasOfInstructor");
				con.prepareStatement(Queries.createAreasOfInstructorTable).executeUpdate();
			}
			insertDataIntoTable("AreasOfInstructor",con);
			rs.close();
			System.out.println();

			con.prepareStatement(Queries.InsertStudentJinYi).executeUpdate();
			System.out.println(Queries.InsertStudentJinYi);
			System.out.println();

			con.prepareStatement(Queries.InsertStudentLiuFangzhen).executeUpdate();
			System.out.println(Queries.InsertStudentLiuFangzhen);
			System.out.println();

			updateDataInEnrollmentsTable("EnrollmentsWithGrades", 6, con);
			System.out.println();
			
			con.prepareStatement(Queries.UpdateStudentGPA).executeUpdate();
			System.out.println(Queries.UpdateStudentGPA);
			System.out.println();
			
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

	private void insertDataIntoTable(String tableName, int numOfCols, Connection con){
		try {
			TXTReader reader = new TXTReader("../../data/"+tableName+".txt");
			String line;
			while((line = reader.readNextLine())!= null){
				String[] tokens = line.split(",");
				StringBuffer stmt = new StringBuffer();
				for(int i = 0; i < numOfCols; i++){
					if(i < tokens.length){
						stmt.append("'");
						stmt.append(tokens[i]);
						stmt.append("'");
					}
					else{
						stmt.append("NULL");
					}
					if(i+1 < numOfCols)	stmt.append(",");
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
	
	private void updateDataInEnrollmentsTable(String fileName, int numOfCols, Connection con){
		try {
			TXTReader reader = new TXTReader("../../data/"+fileName+".txt");
			String line;
			while((line = reader.readNextLine())!= null){
				String[] tokens = line.split(",");
				String CourseNo = tokens.length >= 1? tokens[0] : "NULL" ;
				String SectionNo = tokens.length >= 2? tokens[1] : "NULL";
				String Year = tokens.length >= 3? tokens[2] : "NULL";
				String Semester = tokens.length >= 4? tokens[3] : "NULL";
				String StudentNo = tokens.length >= 5? tokens[4] : "NULL";
				String Grade = tokens.length >= 6? tokens[5] : "NULL";
				StringBuffer stmt = new StringBuffer();
				if(tokens.length >= numOfCols){
					stmt.append("Grade = ").append("'").append(Grade).append("'");
				}
				else{
					stmt.append("Grade = NULL");
				}
				String strStmt = "UPDATE Enrollments SET " + stmt.toString()+ " WHERE "
						+ "CourseNo = '"+ CourseNo + "' AND "
						+ "[Year] = '"+ Year + "' AND "
						+ "Semester = '"+ Semester + "' AND "
						+ "StudentNo = '"+ StudentNo + "'";
				
				System.out.println(strStmt);
				con.prepareStatement(strStmt).executeUpdate();
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
