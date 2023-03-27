package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class PersonSearchGUI extends JDialog {
	private PersonBLL personBLL = new PersonBLL();
	private static final Map<String, String> searchKeyMap = new LinkedHashMap<String, String>();
	private static final Map<String, String> searchDirMap = new LinkedHashMap<String, String>();
	private final JPanel searchContentPanel = new JPanel();
	private PanelHome panelHome;
	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;

	/**
	 * Launch the application.
	 */

	static {
		searchKeyMap.put("Lastname","Lastname");
		searchKeyMap.put("Firstname","Firstname");
	}
	
	/**
	 * Create the dialog.
	 */
	public PersonSearchGUI(final PanelHome panelHome) {
		
		this.panelHome = panelHome;
		setBounds(100, 100, 433, 200);
		getContentPane().setLayout(new BorderLayout());
		searchContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(searchContentPanel, BorderLayout.CENTER);
		searchContentPanel.setLayout(null);
		{
			JLabel lblFirstname = new JLabel("Firstname");
			lblFirstname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFirstname.setBounds(28, 11, 73, 14);
			searchContentPanel.add(lblFirstname);
		}
		{
			JLabel lblLastname = new JLabel("Lastname");
			lblLastname.setFont(new Font("Dialog", Font.BOLD, 12));
			lblLastname.setBounds(28, 48, 73, 14);
			searchContentPanel.add(lblLastname);
		}
		{
			JLabel lblRole = new JLabel("Role");
			lblRole.setFont(new Font("Dialog", Font.BOLD, 12));
			lblRole.setBounds(28, 88, 73, 14);
			searchContentPanel.add(lblRole);
		}
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setBounds(111, 9, 281, 20);
		searchContentPanel.add(textFieldFirstname);
		textFieldFirstname.setColumns(10);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setColumns(10);
		textFieldLastname.setBounds(111, 46, 281, 20);
		searchContentPanel.add(textFieldLastname);
		
		JPanel panelRole = new JPanel();
		panelRole.setBounds(111, 88, 281, 29);
		searchContentPanel.add(panelRole);
		
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		panelRole.add(rdbtnStudent);
		
		JRadioButton rdbtnTeacher = new JRadioButton("Teacher");
		panelRole.add(rdbtnTeacher);
		ButtonGroup btnG_Role = new ButtonGroup();
		btnG_Role.add(rdbtnTeacher);
		btnG_Role.add(rdbtnStudent);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {

						// TODO Auto-generated method stub
						Map<String, String> searchMap = new HashMap<String, String>();
						if (textFieldFirstname.getText().isEmpty()) {
							searchMap.remove("Firstname");
						} else {
							searchMap.put("Firstname", textFieldFirstname.getText());
						}
						if (textFieldLastname.getText().isEmpty()) {
							searchMap.remove("Lastname");
						} else {
							searchMap.put("Lastname", textFieldLastname.getText());
						}
						List<PersonDTO> personDTOs = personBLL.getPersonListBySearch(searchMap);
						panelHome.updateTableSearch(personDTOs);
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
							PersonSearchGUI.this.dispose();
						}
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
