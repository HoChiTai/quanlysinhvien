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

public class AssignmentAddGUI extends JDialog {
	JDialog dialog;
	private AssignmentGUI assignmentGUI;
	private final JPanel contentPanel = new JPanel();
	private AssignmentBLL assignmentBLL = new AssignmentBLL();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public AssignmentAddGUI(final AssignmentGUI assignmentGUI) {
		setBounds(100, 100, 362, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(new Font("Dialog", Font.BOLD, 12));
			contentPanel.add(lblLocation);
		}
		{
			textField = new JTextField();
			textField.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (checkEmpty(textField, "Location")) {
							return;
						}

						AssignmentDTO assignmentDTO = new AssignmentDTO();
						assignmentDTO.setLocation(textField.getText());
						boolean add = assignmentBLL.addAssignment(assignmentDTO);
						if (add) {
							JOptionPane.showMessageDialog(dialog, "thành công!");
							assignmentGUI.updateTable();
							dispose();
						} else {
							JOptionPane.showMessageDialog(dialog, "bị lỗi!");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean checkEmpty(JTextField textField, String name) {
		if (textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, name + " không được để trống!");
			return true;
		}
		return false;
	}
}
