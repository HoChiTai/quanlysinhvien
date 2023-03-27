package sgu.mhpl.school.coursemanager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgu.mhpl.school.coursemanager.api.MyDatabase;
import sgu.mhpl.school.coursemanager.dto.PersonDTO;

public class PersonDAL {
	public static final String GET_ALL_PERSON = "SELECT * FROM PERSON";
	
	//query
	
	public List<PersonDTO> getAllPerson() {
		List<PersonDTO> persons = new ArrayList<PersonDTO>();

		Connection connection = MyDatabase.connect();
		try {
			PreparedStatement stm = connection.prepareStatement(GET_ALL_PERSON);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				persons.add(
						new PersonDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		return persons;
	}
	
	public PersonDTO getPersonByPersonID(int ID) {
		String commandText = "SELECT * FROM person WHERE PersonID=?";

		try {
			Connection connection = MyDatabase.connect();

			PreparedStatement stm = connection.prepareStatement(commandText);
			stm.setInt(1, ID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return new PersonDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		return null;
	}
	
	public List<PersonDTO> getPersonListBySearch(Map<String, String> searchMap) {
		List<PersonDTO> personList = new ArrayList<PersonDTO>();

		try {
			Connection connect = MyDatabase.connect();

			String sql = "SELECT * FROM person";
			String where = "";
			String order = "";

			List<String> conditions = new ArrayList<String>();
			if (searchMap.containsKey("Lastname")) {
				conditions.add("UPPER(Lastname) LIKE UPPER('%" + searchMap.get("Lastname") + "%')");
			}

			if (searchMap.containsKey("Firstname")) {
				conditions.add("UPPER(Firstname) LIKE UPPER('%" + searchMap.get("Firstname") + "%')");
			}
			if (!conditions.isEmpty()) {
				where = "WHERE " + String.join(" AND ", conditions);
			}

			sql = sql + " " + where + " " + order;
			System.out.println(sql);
			ResultSet rs = connect.createStatement().executeQuery(sql);

			while (rs.next()) {
				personList.add(new PersonDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getDate(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}

		return personList;
	}
	
	//mutation
	
	public void insertPerson(PersonDTO person) {
		String INSERT_PERSON = "INSERT INTO person (Lastname, Firstname, HireDate, EnrollmentDate)"
				+ " VALUES (?,?,?,?) ";
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(INSERT_PERSON);
			stm.setString(1, person.getLastName());
			stm.setString(2, person.getFirstName());
			stm.setDate(3, person.getHireDate());
			stm.setDate(4, person.getEnrollmentDate());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
	}
	
	public void updatePerson (PersonDTO person) {
		String UPDATE_PERSON = "UPDATE person SET Lastname=?,Firstname=? WHERE PersonID=?;";
		
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(UPDATE_PERSON);
			stm.setString(1, person.getLastName());
			stm.setString(2, person.getFirstName());
			stm.setInt(3, person.getPersonID());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyDatabase.disconnect();
		}
		
	}
	
	public void deletePersonByPersonID (int ID) {
		String DELETE_PERSON = "DELETE FROM person WHERE PersonID=?;";
		try {
			Connection connection = MyDatabase.connect();
			PreparedStatement stm = connection.prepareStatement(DELETE_PERSON);
			stm.setInt(1, ID);
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
		String commandText = "SELECT * FROM person WHERE PersonID=?;";
		try {
			Connection connection = MyDatabase.connect();

			PreparedStatement stm = connection.prepareStatement(commandText);
			stm.setInt(1, iD);
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
