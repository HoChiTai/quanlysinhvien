package sgu.mhpl.school.coursemanager.bll;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import sgu.mhpl.school.coursemanager.dal.PersonDAL;
import sgu.mhpl.school.coursemanager.dto.PersonDTO;

public class PersonBLL {
	private PersonDAL DAL = new PersonDAL();
	
	public List<PersonDTO> getPersons() {
		 return DAL.getAllPerson();
	}
	
	public boolean addPerson(PersonDTO user, boolean role) {
//		if (!isExistTenTaiKhoan(user.getTenTaiKhoan())) {
//			DAL.insertTaiKhoan(user);
//			return true;
//		}
//		return false;
		// true = student  
		LocalDate now = LocalDate.now();  
		Date date = java.sql.Date.valueOf(now);
		if(role == true) {
			user.setHireDate(date);
			user.setEnrollmentDate(null);
		}
		else {
			user.setHireDate(null);
			user.setEnrollmentDate(date);
		}
		DAL.insertPerson(user);
		return true;
	}
	
	public boolean updatePerson(PersonDTO peson, boolean role) {
		if (isExistID(peson.getPersonID())) {
			LocalDate now = LocalDate.now();  
			Date date = java.sql.Date.valueOf(now);
			if(role == true) {
				peson.setHireDate(date);
				peson.setEnrollmentDate(null);
			}
			else {
				peson.setHireDate(null);
				peson.setEnrollmentDate(date);
			}
			DAL.updatePerson(peson);
			return true;
		}
		return false;
	}
	
	public PersonDTO getPersonByPersonID(int ID) {
		return DAL.getPersonByPersonID(ID);
	}
	
	public boolean isExistID(int ID) {
		return DAL.isExistID(ID);
	}
	
//	public PersonDTO getCurrentPerson() {
//		return getPersonByPersonID(Session.ID);
//	}

	
	public boolean deletePersonByPersonID(int ID) {
		if (isExistID(ID)) {
			DAL.deletePersonByPersonID(ID);
			return true;
		}
		return false;
	}
	
	public List<PersonDTO> getPersonListBySearch(Map<String, String> searchMap) {
		return DAL.getPersonListBySearch(searchMap);
	}
}
