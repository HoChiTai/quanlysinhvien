package sgu.mhpl.school.coursemanager.dto;

import java.sql.Time;

public class OnsiteCourse extends Course {
	private String location;
	private String days;
	private Time time;
	
	public OnsiteCourse(int courseID, String title, int credits, int departmentID, String location, String days,
			Time time) {
		super(courseID, title, credits, departmentID);
		this.location = location;
		this.days = days;
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	
}
