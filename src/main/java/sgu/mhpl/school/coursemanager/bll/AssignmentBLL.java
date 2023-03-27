package sgu.mhpl.school.coursemanager.bll;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import sgu.mhpl.school.coursemanager.dal.AssignmentDAL;
import sgu.mhpl.school.coursemanager.dto.AssignmentDTO;
import sgu.mhpl.school.coursemanager.dto.PersonDTO;

public class AssignmentBLL {
	
	private AssignmentDAL DAL = new AssignmentDAL();
	
	public List<AssignmentDTO> getAssignment() {
		 return DAL.getAllAssignment();
	}
	
	public boolean addAssignment(AssignmentDTO assignment) {
		LocalDate now = LocalDate.now();  
		Date date = java.sql.Date.valueOf(now);
			assignment.setTimestamp(date);
		DAL.insertAssignment(assignment);
		return true;
	}
	
	
	public boolean updateAssignment(AssignmentDTO assignment) {
		if (isExistID(assignment.getInstructorID())) {  
			DAL.updateAssignment(assignment);
			return true;
		}
		return false;
	}
	
	
	public AssignmentDTO getAssignmentByIntrustorID(int ID) {
		return DAL.getAssignmentByIntrustorID(ID);
	}
	
	public boolean isExistID(int ID) {
		return DAL.isExistID(ID);
	}
	public boolean deleteAssignmentByIntrustorID(int ID) {
		if (isExistID(ID)) {
			DAL.deleteAssignmentByIntrustorID(ID);
			return true;
		}
		return false;
	}
	public List<AssignmentDTO> getAssignmentListBySearch(Map<String, String> searchMap) {
		return DAL.getAssignmentListBySearch(searchMap);
	}
	
}
