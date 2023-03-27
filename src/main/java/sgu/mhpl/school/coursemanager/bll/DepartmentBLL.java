package sgu.mhpl.school.coursemanager.bll;

import java.util.ArrayList;

import sgu.mhpl.school.coursemanager.dal.DepartmentDAL;
import sgu.mhpl.school.coursemanager.dto.Department;



public class DepartmentBLL {
	private DepartmentDAL departmentDAL = new DepartmentDAL();
	
	public ArrayList<Department> readDepartment() {
		return departmentDAL.readDepartment();
	}
}
