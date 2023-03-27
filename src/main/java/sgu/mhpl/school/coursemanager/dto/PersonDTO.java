package sgu.mhpl.school.coursemanager.dto;

import java.sql.Date;

public class PersonDTO {
	private int personID;
	private String firstName;
	private String lastName;
	private Date hireDate;
	private Date enrollmentDate;
	
	public PersonDTO() {
		
	}
	
	public PersonDTO(int personID, String lastName, String firstName, Date hireDate, Date enrollmentDate) {
		super();
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.enrollmentDate = enrollmentDate;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "PersonDTO [personID=" + personID + ", firstName=" + firstName + ", lastName=" + lastName + ", hireDate="
				+ hireDate + ", enrollmentDate=" + enrollmentDate + "]";
	}
	
	
}
