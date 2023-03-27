package sgu.mhpl.school.coursemanager.dto;

import java.sql.Date;

public class Student {
	String firstName, lastName;
	int personId;
	Date enrollmentDate;
	
	public Student(String firsrName, String lastName, int personId, Date enrollmentDate) {
		super();
		this.firstName = firsrName;
		this.lastName = lastName;
		this.personId = personId;
		this.enrollmentDate = enrollmentDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firsrName) {
		this.firstName = firsrName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	
}
