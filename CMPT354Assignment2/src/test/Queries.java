package test;

/**
 * Storing SQL query strings
 * */
public class Queries {
	public static final String getExistingTables = "SELECT * FROM information_schema.tables";
	public static final String createCoursesTable = "CREATE TABLE Courses("
			+ "CourseNo DECIMAL(3,0) NOT NULL PRIMARY KEY,"
			+ "Title CHAR(100) NOT NULL"
			+ ")";
	public static final String createCourseSectionsTable = "CREATE TABLE CourseSections("
			+ "CourseNo DECIMAL(3,0) NOT NULL REFERENCES Courses(CourseNo)"
			+ "						ON DELETE CASCADE "
			+ "						ON UPDATE CASCADE,"
			+ "SectionNo DECIMAL(1,0) NOT NULL,"
			+ "InstructorNo DECIMAL(3,0) NOT NULL REFERENCES Instructors(InstructorNo)"
			+ "									ON DELETE CASCADE"
			+ "									ON UPDATE CASCADE,"
			+ "[Year] INTEGER NOT NULL,"
			+ "Semester DECIMAL(1,0) NOT NULL,"
			+ "RoomNo DECIMAL(3,0) NOT NULL,"
			+ "Weekday DECIMAL(1,0) NOT NULL,"
			+ "StartTime INTEGER NOT NULL,"
			+ "FinishTime INTEGER NOT NULL,"
			+ "Capacity INTEGER NOT NULL,"
			+ "PRIMARY KEY(CourseNo, SectionNo, [Year], Semester),"
			+ "UNIQUE(InstructorNo, [Year], Semester, Weekday, StartTime),"
			+ "UNIQUE([Year], Semester, RoomNo, Weekday, StartTime),"
			+ "CHECK(StartTime < FinishTime)"
			+ ")";
	public static final String createInstructorsTable = "CREATE TABLE Instructors("
			+ "	InstructorNo DECIMAL(3,0) NOT NULL PRIMARY KEY, "
			+ "	FirstName CHAR(40) NOT NULL, "
			+ "	LastName CHAR(40) NOT NULL"
			+ ")"
			;
	public static final String createStudentsTable = "CREATE TABLE Students("
			+ "	StudentNo DECIMAL(3,0) NOT NULL PRIMARY KEY, "
			+ "	FirstName CHAR(40) NOT NULL, "
			+ "	LastName CHAR(40) NOT NULL, "
			+ "	[Year] INTEGER,"
			+ "	GPA FLOAT"
			+ ")";
	public static final String createEnrollmentsTable="CREATE TABLE Enrollments("
			+"	CourseNo DECIMAL(3,0) NOT NULL REFERENCES Courses(CourseNo),"
			+"	SectionNo DECIMAL(1,0) NOT NULL,"
			+"	[Year] INTEGER NOT NULL,"
			+"	Semester DECIMAL(1,0) NOT NULL,"
			+"	StudentNo DECIMAL(3,0) NOT NULL REFERENCES Students(StudentNo)"
			+"									ON DELETE CASCADE"
			+"									ON UPDATE CASCADE,"
			+"	Grade FLOAT,"
			+"	PRIMARY KEY(CourseNo, [Year], Semester, StudentNo),"
			+"	FOREIGN KEY(CourseNo, SectionNo, [Year], Semester) REFERENCES"
			+"	CourseSections(CourseNo, SectionNo, [Year], Semester)"
			+"		ON DELETE CASCADE"
			+"		ON UPDATE CASCADE"
			+")";
	public static final String createAreasTable ="CREATE TABLE Areas("
			+"	AreaName CHAR(40) NOT NULL PRIMARY KEY"
			+")";
	public static final String createAreasOfCourseTable = "CREATE TABLE AreasOfCourse("
			+"	CourseNo DECIMAL(3,0) NOT NULL REFERENCES Courses(CourseNo)"
			+"									ON DELETE CASCADE"
			+"									ON UPDATE CASCADE,"
			+"	AreaName CHAR(40) NOT NULL REFERENCES Areas(AreaName)"
			+"									ON DELETE CASCADE"
			+"									ON UPDATE CASCADE,"
			+"	PRIMARY KEY(CourseNo, AreaName)"
			+")";
	public static final String createAreasOfInstructorTable = "CREATE TABLE AreasOfInstructor("
			+"	InstructorNo DECIMAL(3,0) NOT NULL REFERENCES Instructors(InstructorNo)"
			+"										ON DELETE CASCADE"
			+"										ON UPDATE CASCADE,"
			+"	AreaName CHAR(40) NOT NULL REFERENCES Areas(AreaName)"
			+"								ON DELETE CASCADE"
			+"								ON UPDATE CASCADE,"
			+"	PRIMARY KEY(InstructorNo, AreaName)"
			+")";

	//Assignment 3:
	public static final String InsertStudentJinYi = "INSERT INTO Students "
			+ "VALUES('250','Jin','Yi','2012',NULL)";
	public static final String InsertStudentLiuFangzhen = "INSERT INTO Students "
			+ "VALUES('251','Liu','Fangzhen','2012',NULL)";
	public static final String UpdateStudentGPA = "UPDATE Students "
			+ "SET GPA = (SELECT ROUND(AVG(Grade),1) "
			+ "FROM Enrollments E "
			+ "WHERE Students.StudentNo = E.StudentNo) "
			+ "WHERE EXISTS(SELECT * "
			+ "FROM Enrollments E "
			+ "WHERE Students.StudentNo = E.StudentNo)";
}
