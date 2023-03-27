package sgu.mhpl.school.coursemanager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgu.mhpl.school.coursemanager.api.MyDatabase;
import sgu.mhpl.school.coursemanager.dto.AssignmentDTO;


public class AssignmentDAL {
	public static final String GET_ALL_officeassignment= "SELECT * FROM officeassignment";
	
	public List<AssignmentDTO> getAllAssignment() {
		String GET_ALL_ASSIGNMENT = "SELECT * FROM officeassignment";
		List<AssignmentDTO> assignment = new ArrayList<AssignmentDTO>();
		Connection connection = MyDatabase.connect();
		try {
			PreparedStatement stm = connection.prepareStatement(GET_ALL_officeassignment);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				assignment.add(
						new AssignmentDTO(rs.getInt(1), rs.getString(2), rs.getDate(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		return assignment;
	}
	public AssignmentDTO getAssignmentByIntrustorID(int ID) {
		String commandText = "SELECT * FROM officeassignment WHERE InstructorID=?";
		
		try {
			Connection connection = MyDatabase.connect();

			PreparedStatement stm = connection.prepareStatement(commandText);
			stm.setInt(1, ID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				
				return new AssignmentDTO(rs.getInt(1), rs.getString(2), rs.getDate(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		return null;
	}
	
	public List<AssignmentDTO> getAssignmentListBySearch(Map<String, String> searchMap) {
		List<AssignmentDTO> assignmentList = new ArrayList<AssignmentDTO>();
		try {
			Connection connect = MyDatabase.connect();
			String sql="SELECT * FROM officeassignment where InstructorID="+searchMap.get("id");
			if(searchMap.get("id").equals("")) {
				sql = "SELECT * FROM officeassignment where Location Like '%" +searchMap.get("location")+"%'";	
			}
			System.out.println(sql);
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while (rs.next()) {
				assignmentList.add(new AssignmentDTO(rs.getInt(1), rs.getString(2), rs.getDate(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}

		return assignmentList;
	}
	
	
	
	public void insertAssignment(AssignmentDTO assignment) {
		String INSERT_ASSIGNMENT = "INSERT INTO officeassignment (Location)"
				+ " VALUES (?) ";
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(INSERT_ASSIGNMENT);
			stm.setString(1, assignment.getLocation());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
	}
	public void updateAssignment (AssignmentDTO assignment) {
		String UPDATE_ASSIGNMENT= "UPDATE officeassignment SET Location=? WHERE InstructorID=?;";
		
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(UPDATE_ASSIGNMENT);
			stm.setString(1, assignment.getLocation());
			stm.setInt(2, assignment.getInstructorID());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		
	}
	
	public void deleteAssignmentByIntrustorID (int ID) {
		String DELETE_ASSIGNMENT = "DELETE FROM officeassignment WHERE InstructorID=?;";
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(DELETE_ASSIGNMENT);
			stm.setInt(1, ID);
//			System.out.print(stm.toString());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
	}
	
	
	//support
	
	public boolean isExistID(int iD) {
		boolean existID = false;
		String commandText = "SELECT * FROM officeassignment WHERE InstructorID= ?";
		try {
			Connection connection = MyDatabase.connect();

			PreparedStatement stm = connection.prepareStatement(commandText);
			stm.setInt(1, iD);
//			System.out.print(commandText);
			ResultSet rst = stm.executeQuery();
			if (rst.next()) {
				existID = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		return existID;
	}
	
}
