package test;

public class Queries {
	public static final String getExistingTables = "SELECT * FROM information_schema.tables";
	public static final String createCoursesTable = "CREATE TABLE Courses("
	+ "CourseNo DECIMAL(3,0) PRIMARY KEY,"
	+ "Title VARCHAR(100) NOT NULL"
	+ ")";
}
