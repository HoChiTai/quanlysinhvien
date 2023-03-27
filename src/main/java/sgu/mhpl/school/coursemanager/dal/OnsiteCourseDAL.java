package sgu.mhpl.school.coursemanager.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sgu.mhpl.school.coursemanager.api.MyDatabaseManager;
import sgu.mhpl.school.coursemanager.dto.OnsiteCourse;

public class OnsiteCourseDAL extends MyDatabaseManager{
	
	public OnsiteCourseDAL() {
		super();
		this.connectDB();
	}
	
	public ArrayList<OnsiteCourse> readOnsiteCourse() {
		ArrayList<OnsiteCourse> onsiteCourseList = new ArrayList<OnsiteCourse>();
		String query = "SELECT * FROM Course, onsitecourse WHERE course.CourseID = onsitecourse.CourseID";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					OnsiteCourse course = new OnsiteCourse(
								rs.getInt("CourseID"),
								rs.getString("Title"),
								rs.getInt("Credits"),
								rs.getInt("DepartmentID"),
								rs.getString("Location"),
								rs.getString("Days"),
								rs.getTime("Time")
							);
					onsiteCourseList.add(course);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return onsiteCourseList;
	}
	
	public int updateOnsiteCourse(OnsiteCourse onsiteCourse) {
		int result = 0;
		String query = "UPDATE course SET course.Title = ? , course.Credits = ? , course.DepartmentID = ? "
		+ " WHERE course.CourseID = ? ";
		String query1 = "DELETE FROM Onlinecourse WHERE CourseID = ?";
		String query2 = "DELETE FROM OnsiteCourse WHERE CourseID = ?";
		
		
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString	(1, onsiteCourse.getTitle());
			p.setInt	(2, onsiteCourse.getCredits());
			p.setInt	(3, onsiteCourse.getDepartmentID());
			p.setInt	(4, onsiteCourse.getCourseID());
			result = p.executeUpdate();
			
			p = this.getConnection().prepareStatement(query1);
			p.setInt	(1, onsiteCourse.getCourseID());
			result = p.executeUpdate();
			
			p = this.getConnection().prepareStatement(query2);
			p.setInt	(1, onsiteCourse.getCourseID());
			result = p.executeUpdate();
			insertOnsiteCourseByID(onsiteCourse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int insertOnsiteCourse(OnsiteCourse onsiteCourse) {
		int result = 0;
		String query = "INSERT INTO  course (Title,Credits,DepartmentID) VALUES (?, ?, ?)";
		String query1 = "SELECT Max(CourseID) as LastID FROM course";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString	(1, onsiteCourse.getTitle());
			p.setInt	(2, onsiteCourse.getCredits());
			p.setInt	(3, onsiteCourse.getDepartmentID());
			result = p.executeUpdate();
			
			ResultSet rs = this.doReadQuery(query1);
			if (rs != null) {
				while (rs.next()) {
					int lastID = rs.getInt("LastID");
					String query2 = "INSERT INTO onsitecourse (CourseID,Location, Days, Time) VALUES (?, ?, ?, ?)";
					p = this.getConnection().prepareStatement(query2);
					p.setInt	(1, lastID);
					p.setString	(2, onsiteCourse.getLocation());
					p.setString	(3, onsiteCourse.getDays());
					p.setTime	(4, onsiteCourse.getTime());
					result = p.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertOnsiteCourseByID(OnsiteCourse onsiteCourse) {
		int result = 0;
		String query = "INSERT INTO onsitecourse (CourseID,Location, Days, Time) VALUES (?, ?, ?, ?)";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setInt	(1, onsiteCourse.getCourseID());
			p.setString	(2, onsiteCourse.getLocation());
			p.setString	(3, onsiteCourse.getDays());
			p.setTime	(4, onsiteCourse.getTime());
			result = p.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<OnsiteCourse> findOnsiteCourseByName(String courseName) {
		ArrayList<OnsiteCourse> onsiteCourseList = new ArrayList<OnsiteCourse>();
		String query = "SELECT * FROM Course, onsitecourse WHERE course.CourseID = onsitecourse.CourseID "
				+ " AND course.Title LIKE ?";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString(1, "%" + courseName + "%");
			ResultSet rs = p.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					OnsiteCourse course = new OnsiteCourse(
							rs.getInt("CourseID"),
							rs.getString("Title"),
							rs.getInt("Credits"),
							rs.getInt("DepartmentID"),
							rs.getString("Location"),
							rs.getString("Days"),
							rs.getTime("Time")
						);
				onsiteCourseList.add(course);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return onsiteCourseList;
	}
	
	public int deleteOnsiteCourse(int courseID) {
		int result = 0;
		String query1 = "DELETE FROM OnsiteCourse WHERE CourseID = ?;";
		String query2 = "DELETE FROM course WHERE CourseID = ?";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query1);
			p.setInt(1, courseID);
			result = p.executeUpdate();
			p = this.getConnection().prepareStatement(query2);
			p.setInt(1, courseID);
			result = p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
