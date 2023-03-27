package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sgu.mhpl.school.coursemanager.bll.PersonBLL;
import sgu.mhpl.school.coursemanager.dto.PersonDTO;

public class PersonAddGUI extends JDialog {
	JDialog dialog;
	private final JPanel addContentPanel = new JPanel();
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private PersonBLL personBLL = new PersonBLL();
	private PanelHome panelHome;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public PersonAddGUI(final PanelHome panelHome) {
		this.panelHome = panelHome;
		setBounds(100, 100, 433, 200);
		getContentPane().setLayout(new BorderLayout());
		addContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(addContentPanel, BorderLayout.CENTER);
		addContentPanel.setLayout(null);
		{
			JLabel lblFirstname = new JLabel("Firstname");
			lblFirstname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFirstname.setBounds(28, 11, 73, 14);
			addContentPanel.add(lblFirstname);
		}
		{
			JLabel lblLastname = new JLabel("Lastname");
			lblLastname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblLastname.setBounds(28, 48, 73, 14);
			addContentPanel.add(lblLastname);
		}
		{
			JLabel lblRole = new JLabel("Role");
			lblRole.setFont(new Font("Dialog", Font.BOLD, 12));
			lblRole.setBounds(28, 88, 73, 14);
			addContentPanel.add(lblRole);
		}

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(111, 9, 281, 20);
		addContentPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(111, 46, 281, 20);
		addContentPanel.add(textFieldLastName);

		JPanel panelRole = new JPanel();
		panelRole.setBounds(111, 88, 281, 29);
		addContentPanel.add(panelRole);

		final JRadioButton rdbtnStudent = new JRadioButton("Student");
		panelRole.add(rdbtnStudent);

		final JRadioButton rdbtnTeacher = new JRadioButton("Teacher");
		panelRole.add(rdbtnTeacher);
		ButtonGroup btnG_Role = new ButtonGroup();
		btnG_Role.add(rdbtnTeacher);
		btnG_Role.add(rdbtnStudent);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (checkEmpty(textFieldFirstName, "Firstname")) {
					return;
				}
				if (checkEmpty(textFieldLastName, "Lastname")) {
					return;
				}
				boolean check = true;
				if (rdbtnStudent.isSelected()) {
					check = true;
				}else if (rdbtnTeacher.isSelected()){
					check = false;
				}
				
				PersonDTO personDTO = new PersonDTO();
				personDTO.setLastName(textFieldFirstName.getText());
				personDTO.setFirstName(textFieldFirstName.getText());
				boolean add = personBLL.addPerson(personDTO, check);
				if (add) {
					JOptionPane.showMessageDialog(dialog, "Tạo tài khoản thành công!");
					panelHome.updateTable();
					dispose();
				} else {
					JOptionPane.showMessageDialog(dialog, "Tạo tài khoản bị lỗi!");
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure want to close this application ?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == 0) {
					PersonAddGUI.this.dispose();
				}
			}
		});
		buttonPane.add(cancelButton);

	}
	public boolean checkEmpty(JTextField textField, String name) {
		if (textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, name + " không được để trống!");
			return true;
		}
		return false;
	}
}
