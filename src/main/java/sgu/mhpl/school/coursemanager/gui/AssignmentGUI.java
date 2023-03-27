package sgu.mhpl.school.coursemanager.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sgu.mhpl.school.coursemanager.bll.AssignmentBLL;
import sgu.mhpl.school.coursemanager.dto.AssignmentDTO;


public class AssignmentGUI extends JPanel {
	private JTable tableAssignment;
	private JPanel panel;
	private AssignmentGUI assignmentGUI;
	private AssignmentAddGUI assignmentAddGUI;
	private DefaultTableModel tableModel;
	private AssignmentBLL assignmentBLL = new AssignmentBLL();
//	private PanelHome panelHome;
	private AssignmentSearchGUI assignmentSearchGUI;
	private AssignmentEditGUI assignmentEditGUI;
	private Map<String, String> searchMap = new HashMap<String, String>();
	/**
	 * Create the panel.
	 */
	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	public AssignmentGUI() {
		this.assignmentAddGUI = new AssignmentAddGUI(this);
		this.assignmentSearchGUI = new AssignmentSearchGUI(this);
		this.assignmentGUI = this;
		setBackground(new Color(255, 255, 0));
		setBounds(0, 0, 597, 409);
		setLayout(null);

		JPanel panelMenuAssignment = new JPanel();
		panelMenuAssignment.setBounds(10, 10, 577, 44);
		add(panelMenuAssignment);
		panelMenuAssignment.setLayout(null);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAdd.setBounds(44, 12, 85, 21);
		panelMenuAssignment.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				assignmentAddGUI.setVisible(true);
			}
		});
		

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEdit.setBounds(162, 12, 85, 21);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableAssignment.getSelectedRow();

				if (row >= 0) {
					String temp = (String) tableModel.getValueAt(row, 0);
					int ID = Integer.parseInt(temp);
					new AssignmentEditGUI(assignmentGUI, assignmentBLL.getAssignmentByIntrustorID(ID)).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(panel, "Hãy chọn 1 dòng để chỉnh sửa!");
				}
			}
		});
		panelMenuAssignment.add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDelete.setBounds(293, 12, 103, 21);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int row = tableAssignment.getSelectedRow();
				
				if (row >= 0) {
					String temp = (String) tableModel.getValueAt(row, 0);
					int ID = Integer.parseInt(temp);
					if (JOptionPane.showConfirmDialog(panel, "Chắc chắn xóa " + ID
							+ " ?") != JOptionPane.YES_OPTION) {
						return;
					}
					assignmentBLL.deleteAssignmentByIntrustorID(ID);
					JOptionPane.showMessageDialog(panel, "Xóa thành công!");
					updateTable();
				} else {
					JOptionPane.showMessageDialog(panel, "Chọn dòng để xóa!");
				}
			}
		});
		panelMenuAssignment.add(btnDelete);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			assignmentSearchGUI.setVisible(true);
		}
		});
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSearch.setBounds(445, 12, 103, 21);
		panelMenuAssignment.add(btnSearch);
		
		
		

		JPanel panelContentAssignmentGUI = new JPanel();
		panelContentAssignmentGUI.setBounds(0, 64, 597, 345);
		add(panelContentAssignmentGUI);
		panelContentAssignmentGUI.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 577, 325);
		panelContentAssignmentGUI.add(scrollPane);

		tableAssignment = new JTable();
		tableAssignment.setFont(new Font("Dialog", Font.BOLD, 12));
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "InstructorID","Location", "Timestamp"  }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				};
				tableAssignment.setModel(tableModel);
				updateTable();
		scrollPane.setViewportView(tableAssignment);
		setVisible(true);
	}

	public void updateTable() {
		int rowCount = tableModel.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (AssignmentDTO assignment : assignmentBLL.getAssignment()) {
			String[] rowData = new String[3];
			rowData[0] = assignment.getInstructorID() + "";
			rowData[1] = assignment.getLocation();
			rowData[2] = assignment.getTimestamp() + "";
			tableModel.addRow(rowData);
		}
	}
	public void updateTableSearch(List<AssignmentDTO> assignmentDTOs) {
		int rowCount = tableModel.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (AssignmentDTO assignment: assignmentDTOs) {
			String[] rowData = new String[3];
			rowData[0] = assignment.getInstructorID() + "";
			rowData[1] = assignment.getLocation();
			rowData[2] = assignment.getTimestamp() + "";

			tableModel.addRow(rowData);
		}
	}
}
