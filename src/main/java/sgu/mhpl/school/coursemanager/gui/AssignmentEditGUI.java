package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sgu.mhpl.school.coursemanager.bll.AssignmentBLL;
import sgu.mhpl.school.coursemanager.dto.AssignmentDTO;

public class AssignmentEditGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AssignmentGUI assignmentGUI;
	AssignmentDTO assignmentDTO;
	private JTextField textField;
	private JTextField textField_1;
	private AssignmentBLL assignmentBLL = new AssignmentBLL();
	JDialog dialog;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public AssignmentEditGUI(final AssignmentGUI assignmentGUI, AssignmentDTO assignmentDTO) {
		setBounds(100, 100, 450, 211);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblID = new JLabel("ID");
			lblID.setFont(new Font("Dialog", Font.BOLD, 14));
			lblID.setBounds(51, 34, 45, 13);
			contentPanel.add(lblID);
		}
		{
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(new Font("Dialog", Font.BOLD, 14));
			lblLocation.setBounds(26, 102, 60, 13);
			contentPanel.add(lblLocation);
		}
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(79, 33, 123, 19);
		textField.setText((String) null);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(assignmentDTO.getInstructorID() + "");
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 99, 130, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(assignmentDTO.getLocation());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if (checkEmpty(textField_1, "Location")) {
							return;
						}
						AssignmentDTO assignmentDTO = new AssignmentDTO();
						assignmentDTO.setLocation(textField_1.getText());
						int ID = Integer.parseInt(textField.getText());
						assignmentDTO.setInstructorID(ID);
						System.out.println(assignmentDTO);
						boolean update = assignmentBLL.updateAssignment(assignmentDTO);
						if (update) {
							JOptionPane.showMessageDialog(dialog, "Sửa tài khoản thành công!");
							assignmentGUI.updateTable();
							dispose();
						} else {
							JOptionPane.showMessageDialog(dialog, "Sửa tài khoản bị lỗi!");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public boolean checkEmpty(JTextField textField_1, String Location) {
		if (textField_1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, Location + " không được để trống!");
			return true;
		}
		return false;
	}
}

