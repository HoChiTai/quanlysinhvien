package sgu.mhpl.school.coursemanager.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sgu.mhpl.school.coursemanager.api.MyDatabaseManager;
import sgu.mhpl.school.coursemanager.dto.OnlineCourse;

public class OnlineCourseDAL extends MyDatabaseManager{
	
	public OnlineCourseDAL() {
		super();
		this.connectDB();
	}
	
	public ArrayList<OnlineCourse> readOnlineCourse() {
		ArrayList<OnlineCourse> onlineCourseList = new ArrayList<OnlineCourse>();
		String query = "SELECT * FROM Course, OnlineCourse WHERE course.CourseID = OnlineCourse.CourseID";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					OnlineCourse course = new OnlineCourse(
								rs.getInt("CourseID"),
								rs.getString("Title"),
								rs.getInt("Credits"),
								rs.getInt("DepartmentID"),
								rs.getString("url")
							);
					onlineCourseList.add(course);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return onlineCourseList;
	}
	
	public int updateOnlineCourse(OnlineCourse onlineCourse) {
		int result = 0;
		String query = "UPDATE course SET course.Title = ? , course.Credits = ? , course.DepartmentID = ? "
		+ " WHERE course.CourseID = ? ";
		String query1 = "DELETE FROM Onlinecourse WHERE CourseID = ?";
		String query2 = "DELETE FROM OnsiteCourse WHERE CourseID = ?";
		
		
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString	(1, onlineCourse.getTitle());
			p.setInt	(2, onlineCourse.getCredits());
			p.setInt	(3, onlineCourse.getDepartmentID());
			p.setInt	(4, onlineCourse.getCourseID());
			result = p.executeUpdate();
			
			p = this.getConnection().prepareStatement(query1);
			p.setInt	(1, onlineCourse.getCourseID());
			result = p.executeUpdate();
			
			p = this.getConnection().prepareStatement(query2);
			p.setInt	(1, onlineCourse.getCourseID());
			result = p.executeUpdate();
			insertOnlineCourseByID(onlineCourse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int insertOnlineCourse(OnlineCourse onlineCourse) {
		int result = 0;
		String query = "INSERT INTO  course (Title,Credits,DepartmentID) VALUES (?, ?, ?)";
		String query1 = "SELECT Max(CourseID) as LastID FROM course";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString	(1, onlineCourse.getTitle());
			p.setInt	(2, onlineCourse.getCredits());
			p.setInt	(3, onlineCourse.getDepartmentID());
			result = p.executeUpdate();
			
			ResultSet rs = this.doReadQuery(query1);
			if (rs != null) {
				while (rs.next()) {
					int lastID = rs.getInt("LastID");
					String query2 = "INSERT INTO OnlineCourse (CourseID,url) VALUES (?, ?)";
					p = this.getConnection().prepareStatement(query2);
					p.setInt	(1, lastID);
					p.setString	(2, onlineCourse.getUrl());
					result = p.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public int insertOnlineCourseByID(OnlineCourse onlineCourse) {
		int result = 0;
		String query = "INSERT INTO onlinecourse (CourseID,url) VALUES (?, ?)";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setInt	(1, onlineCourse.getCourseID());
			p.setString	(2, onlineCourse.getUrl());
			result = p.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<OnlineCourse> findOnlineCourseByName(String courseName) {
		ArrayList<OnlineCourse> onlineCourseList = new ArrayList<OnlineCourse>();
		String query = "SELECT * FROM Course, OnlineCourse WHERE course.CourseID = OnlineCourse.CourseID "
				+ " AND course.Title LIKE ?";
		PreparedStatement p;
		try {
			p = this.getConnection().prepareStatement(query);
			p.setString(1, "%" + courseName + "%");
			ResultSet rs = p.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					OnlineCourse course = new OnlineCourse(
							rs.getInt("CourseID"),
							rs.getString("Title"),
							rs.getInt("Credits"),
							rs.getInt("DepartmentID"),
							rs.getString("url")
						);
				onlineCourseList.add(course);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return onlineCourseList;
	}
	
	public int deleteOnlineCourse(int courseID) {
		int result = 0;
		String query1 = "DELETE FROM OnlineCourse WHERE CourseID= ?;";
		String query2 = "DELETE FROM course WHERE CourseID= ?";
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
