package sgu.mhpl.school.coursemanager.dto;

import java.sql.Date;

public class AssignmentDTO {
	private int InstructorID;
	private String Location;
	private Date Timestamp;
	
	public AssignmentDTO() {
		
	}
	
	public AssignmentDTO(int instructorID , String location, Date timestamp) {
		super();
		InstructorID = instructorID;
		Location = location;
		Timestamp = timestamp;
	}
	


	public int getInstructorID() {
		return InstructorID;
	}

	public void setInstructorID(int instructorID) {
		InstructorID = instructorID;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "AssignmentDTO [InstructorID=" + InstructorID + ", Location=" + Location + ", Timestamp=" + Timestamp
				+ "]";
	}
	
	

}
