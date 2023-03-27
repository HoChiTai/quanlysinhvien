package sgu.mhpl.school.coursemanager.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import sgu.mhpl.school.coursemanager.bll.PersonBLL;
import sgu.mhpl.school.coursemanager.dto.PersonDTO;

public class PanelHome extends JPanel {
	private JTable tableInfoPerson;
	private DefaultTableModel tableModel;
	private Map<String, String> searchMap = new HashMap<String, String>();
	private PersonBLL personBLL = new PersonBLL();
	private PersonAddGUI personAddGUI;;
	private PersonSearchGUI personSearchGUI;
	private PersonEditGUI personEditGUI;
//	private PersonDeleteGUI personDeleteGUI = new PersonDeleteGUI();
	private JPanel panel;
	private PanelHome panelHome;
	/**
	 * Create the panel.
	 */
	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	
	public PanelHome() {
		this.personSearchGUI = new PersonSearchGUI(this);
		this.personAddGUI = new PersonAddGUI(this);
		this.panelHome = this;
		setBackground(new Color(0, 128, 128));
		setBounds(0, 0, 597, 409);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("STUDENT");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 11, 597, 31);
		add(lblNewLabel);

		JPanel panelOption = new JPanel();
		panelOption.setBackground(new Color(0, 128, 128));
		panelOption.setBounds(0, 53, 597, 48);
		add(panelOption);

		JButton btnadd = new JButton("ADD");
		btnadd.setBackground(new Color(0, 139, 139));
		btnadd.setBounds(43, 11, 89, 23);
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personAddGUI.setVisible(true);
			}
		});
		panelOption.setLayout(null);
		panelOption.add(btnadd);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBackground(new Color(0, 139, 139));
		btnEdit.setBounds(181, 11, 89, 23);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableInfoPerson.getSelectedRow();

				if (row >= 0) {
					String temp = (String) tableModel.getValueAt(row, 1);
					int ID = Integer.parseInt(temp);
					new PersonEditGUI(panelHome, personBLL.getPersonByPersonID(ID)).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(panel, "Hãy chọn 1 dòng để chỉnh sửa!");
				}
			}
		});
		panelOption.add(btnEdit);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personSearchGUI.setVisible(true);
			}
		});
		btnSearch.setBackground(new Color(0, 139, 139));
		btnSearch.setBounds(458, 11, 89, 23);
		panelOption.add(btnSearch);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(0, 139, 139));
		btnDelete.setBounds(328, 11, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableInfoPerson.getSelectedRow();

				if (row >= 0) {
					String temp = (String) tableModel.getValueAt(row, 1);
					int ID = Integer.parseInt(temp);
					PersonDTO personDTO = personBLL.getPersonByPersonID(ID);

					if (JOptionPane.showConfirmDialog(panel, "Chắc chắn xóa " + personDTO.getPersonID()
							+ " ?") != JOptionPane.YES_OPTION) {
						return;
					}
					personBLL.deletePersonByPersonID(ID);
					JOptionPane.showMessageDialog(panel, "Xóa thành công!");
					panelHome.updateTable();
				} else {
					JOptionPane.showMessageDialog(panel, "Chọn dòng để xóa!");
				}
			}
		});
		panelOption.add(btnDelete);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(255, 255, 255));
		panelContent.setBounds(10, 101, 577, 297);
		add(panelContent);
		panelContent.setLayout(null);

		JScrollPane scrollPaneInfoPerson = new JScrollPane();
		scrollPaneInfoPerson.setBounds(10, 11, 557, 275);
		panelContent.add(scrollPaneInfoPerson);

		tableInfoPerson = new JTable();
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Index", "Person ID", "Lastname", "Firstname", "Hire Date", "Enrollment Date" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableInfoPerson.setModel(tableModel);
		updateTable();
		scrollPaneInfoPerson.setViewportView(tableInfoPerson);
		setVisible(true);
	}

	public static String toDateString(Date date) {
		if (date == null)
			return "";
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public void updateTable() {
		int rowCount = tableModel.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		int stt = 1;
		for (PersonDTO person : personBLL.getPersons()) {
			String[] rowData = new String[6];
			rowData[0] = stt + "";
			rowData[1] = person.getPersonID() + "";
			rowData[2] = person.getLastName();
			rowData[3] = person.getFirstName();
			rowData[4] = toDateString(person.getHireDate());
			rowData[5] = toDateString(person.getEnrollmentDate());
			stt++;

			tableModel.addRow(rowData);
		}
	}
	
	public void updateTableSearch(List<PersonDTO> personDTOs) {
		int rowCount = tableModel.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		int stt = 1;
		for (PersonDTO person : personDTOs) {
			String[] rowData = new String[6];
			rowData[0] = stt + "";
			rowData[1] = person.getPersonID() + "";
			rowData[2] = person.getLastName();
			rowData[3] = person.getFirstName();
			rowData[4] = toDateString(person.getHireDate());
			rowData[5] = toDateString(person.getEnrollmentDate());
			stt++;

			tableModel.addRow(rowData);
		}
	}
}
