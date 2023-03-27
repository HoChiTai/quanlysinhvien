package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

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

public class PersonEditGUI extends JDialog {
	JDialog dialog;
	private final JPanel editContentPanel = new JPanel();
	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;
	private PersonBLL personBLL = new PersonBLL();
	PersonDTO personDTO;
	private JTextField textFieldID;
	private PanelHome panelHome;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			PersonAddGUI dialog = new PersonAddGUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param personDTO2 
	 * @param personGUI 
	 */
	public PersonEditGUI(final PanelHome panelHome, PersonDTO personDTO) {
		this.panelHome = panelHome;
		this.personDTO = personDTO;
		this.dialog = this;
		setBounds(100, 100, 433, 269);
		getContentPane().setLayout(new BorderLayout());
		editContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(editContentPanel, BorderLayout.CENTER);
		editContentPanel.setLayout(null);
		{
			JLabel lblFirstname = new JLabel("Firstname");
			lblFirstname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFirstname.setBounds(28, 64, 73, 14);
			editContentPanel.add(lblFirstname);
		}
		{
			JLabel lblLastname = new JLabel("Lastname");
			lblLastname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblLastname.setBounds(28, 95, 73, 14);
			editContentPanel.add(lblLastname);
		}
		{
			JLabel lblRole = new JLabel("Role");
			lblRole.setFont(new Font("Dialog", Font.BOLD, 12));
			lblRole.setBounds(28, 153, 73, 14);
			editContentPanel.add(lblRole);
		}
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setBounds(111, 62, 281, 20);
		textFieldFirstname.setText(personDTO.getFirstName());
		editContentPanel.add(textFieldFirstname);
		textFieldFirstname.setColumns(10);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setColumns(10);
		textFieldLastname.setText(personDTO.getLastName());
		textFieldLastname.setBounds(111, 93, 281, 20);
		editContentPanel.add(textFieldLastname);
		
		JPanel panelRole = new JPanel();
		panelRole.setBounds(111, 138, 281, 29);
		editContentPanel.add(panelRole);
		
		final JRadioButton rdbtnStudent = new JRadioButton("Student");
		panelRole.add(rdbtnStudent);
		
		final JRadioButton rdbtnTeacher = new JRadioButton("Teacher");
		panelRole.add(rdbtnTeacher);
		ButtonGroup btnG_Role = new ButtonGroup();
		btnG_Role.add(rdbtnTeacher);
		btnG_Role.add(rdbtnStudent);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Dialog", Font.BOLD, 12));
		lblID.setBounds(28, 33, 73, 14);
		editContentPanel.add(lblID);
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setText((String) null);
		textFieldID.setColumns(10);
		textFieldID.setBounds(111, 31, 281, 20);
		textFieldID.setText(personDTO.getPersonID() + "");
		editContentPanel.add(textFieldID);
		if (personDTO.getHireDate() != null){
			rdbtnTeacher.setSelected(false);
			rdbtnStudent.setSelected(true);
		}
		else{
			rdbtnTeacher.setSelected(true);
			rdbtnStudent.setSelected(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if (checkEmpty(textFieldFirstname, "First name")) {
							return;
						}
						if (checkEmpty(textFieldLastname, "Last name")) {
							return;
						}
						if (!Pattern.compile("[A-Za-z]+").matcher(textFieldFirstname.getText()).find()) {
							JOptionPane.showMessageDialog(dialog, "Tài khoản chỉ chứa ký tự a-z A-Z!");
							return;
						}
						if (!Pattern.compile("[A-Za-z]+").matcher(textFieldLastname.getText()).find()) {
							JOptionPane.showMessageDialog(dialog, "Tài khoản chỉ chứa ký tự a-z A-Z!");
							return;
						}
						PersonDTO personDTO = new PersonDTO();
						personDTO.setLastName(textFieldLastname.getText());
						personDTO.setFirstName(textFieldFirstname.getText());
						int ID = Integer.parseInt(textFieldID.getText());
						personDTO.setPersonID(ID);
						System.out.println(personDTO);
						boolean check = true;
						if (rdbtnStudent.isSelected()) {
							check = true;
						}else if (rdbtnTeacher.isSelected()) {
							check = false;
						}
						boolean update = personBLL.updatePerson(personDTO, check);
						if (update) {
							JOptionPane.showMessageDialog(dialog, "Sửa tài khoản thành công!");
							panelHome.updateTable();
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
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(JOptionPane.showConfirmDialog(null, "Are you sure want to close this application ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
							PersonEditGUI.this.dispose();
						}
					}
				});
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
