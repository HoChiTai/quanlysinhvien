package sgu.mhpl.school.coursemanager.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sgu.mhpl.school.coursemanager.api.MyDatabaseManager;
import sgu.mhpl.school.coursemanager.dto.Department;

public class DepartmentDAL extends MyDatabaseManager{
	public DepartmentDAL() {
		super();
		this.connectDB();
	}
	public ArrayList<Department> readDepartment() {
		ArrayList<Department> departmentList = new ArrayList<Department>();
		String query = "SELECT * FROM department";
		ResultSet rs = this.doReadQuery(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					Department course = new Department(
								rs.getInt("DepartmentID"),
								rs.getString("Name"),
								rs.getDouble("Budget"),
								rs.getDate("StartDate"),
								rs.getInt("Administrator")
							);
					departmentList.add(course);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return departmentList;
	}
}
