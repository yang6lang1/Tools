package test;

/**
 * Storing SQL query strings
 * */
public class Queries {
	public static final String getExistingTables = "SELECT * FROM information_schema.tables";
	public static final String createCoursesTable = "CREATE TABLE Courses("
			+ "CourseNo DECIMAL(3,0) PRIMARY KEY,"
			+ "Title VARCHAR(100) NOT NULL"
			+ ")";
	public static final String createCourseSectionsTable = "CREATE TABLE CourseSections("
			+ "CourseNo DECIMAL(3,0),"
			+ "SectionNo DECIMAL(1,0),"
			+ "InstructorNo DECIMAL(3,0) NOT NULL,"
			+ "[Year] INTEGER,"
			+ "Semester DECIMAL(1,0),"
			+ "RoomNo DECIMAL(3,0) NOT NULL,"
			+ "Weekday INTEGER NOT NULL,"
			+ "StartTime INTEGER NOT NULL,"
			+ "FinishTime INTEGER NOT NULL,"
			+ "Capacity INTEGER NOT NULL,"
			+ "UNIQUE(CourseNo, SectionNo, [Year], Semester)"
			+ ")";
	public static final String createInstructorsTable = "CREATE TABLE Instructors("
			+ "InstructorNo DECIMAL(3,0) PRIMARY KEY,"
			+ "FirstName VARCHAR(40) NOT NULL,"
			+ "LastName VARCHAR(40) NOT NULL"
			+ ")";
	public static final String createStudentsTable = "CREATE TABLE Students("
			+ "StudentNo DECIMAL(3,0) PRIMARY KEY,"
			+ "FirstName VARCHAR(40) NOT NULL,"
			+ "LastName VARCHAR(40) NOT NULL,"
			+ "Year INTEGER,"
			+ "GPA FLOAT"
			+ ")";
	public static final String createEnrollmentsTable="CREATE TABLE Enrollments("
			+ "CourseNo DECIMAL(3,0),"
			+ "SectionNo DECIMAL(1,0) NOT NULL,"
			+ "[Year] INTEGER,"
			+ "Semester DECIMAL(1,0),"
			+ "StudentNo DECIMAL(3,0),"
			+ "Grade FLOAT,"
			+ "UNIQUE(CourseNo, [Year], Semester, StudentNo)"
			+ ")";
	public static final String createAreasTable = "CREATE TABLE Areas("
			+ "AreaName VARCHAR(40) PRIMARY KEY"
			+ ")";
	public static final String createAreasOfCourseTable = "CREATE TABLE AreasOfCourse("
			+ "CourseNo DECIMAL(3,0),"
			+ "AreaName VARCHAR(40),"
			+ "UNIQUE(CourseNo, AreaName)"
			+ ")";
	public static final String createAreasOfInstructorTable = "CREATE TABLE AreasOfInstructor("
			+ "InstructorNo DECIMAL(3,0),"
			+ "AreaName VARCHAR(40),"
			+ "UNIQUE(InstructorNo, AreaName)"
			+ ")";
}
