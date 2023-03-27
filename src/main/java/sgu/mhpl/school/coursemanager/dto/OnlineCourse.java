package sgu.mhpl.school.coursemanager.dto;

import java.sql.Time;

public class OnlineCourse extends Course {
	private String url;

	public OnlineCourse(int courseID, String title, int credits, int departmentID, String url) {
		super(courseID, title, credits, departmentID);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
