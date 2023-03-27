package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sgu.mhpl.school.coursemanager.bll.AssignmentBLL;
import sgu.mhpl.school.coursemanager.dto.AssignmentDTO;

public class AssignmentSearchGUI extends JDialog {
	private AssignmentBLL assignmentBLL = new AssignmentBLL();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
//	private static final Map<String, String> searchKeyMap = new LinkedHashMap<String, String>();
//	private static final Map<String, String> searchDirMap = new LinkedHashMap<String, String>();
	private final JPanel searchContentPanel = new JPanel();
	private AssignmentGUI assignmentGUI;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AssignmentSearchGUI dialog = new AssignmentSearchGUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AssignmentSearchGUI(final AssignmentGUI assignmentGUI  ) {
		setBounds(100, 100, 450, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(77, 35, 45, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Location");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 107, 71, 13);
		contentPanel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(132, 34, 146, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 106, 146, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {

						// TODO Auto-generated method stub
						Map<String, String> searchMap = new HashMap<String, String>();
						searchMap.put("id", textField.getText().trim());
						searchMap.put("location", textField_1.getText().trim());
						List<AssignmentDTO> assignmentDTOs = assignmentBLL.getAssignmentListBySearch(searchMap);
//						JOptionPane.showMessageDialog(null, searchMap);
						assignmentGUI.updateTableSearch(assignmentDTOs);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
							
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(JOptionPane.showConfirmDialog(null, "Are you sure want to close this application ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
							AssignmentSearchGUI.this.dispose();
						}
					}
				});
				buttonPane.add(cancelButton);
			
		
	}
}
