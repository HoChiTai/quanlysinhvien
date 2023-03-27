package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PersonDeleteGUI extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonDeleteGUI dialog = new PersonDeleteGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonDeleteGUI() {
		setBounds(100, 100, 243, 125);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 47, 207, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton yesButton = new JButton("YES");
				yesButton.setBounds(28, 5, 61, 23);
				yesButton.setActionCommand("OK");
				buttonPane.add(yesButton);
				getRootPane().setDefaultButton(yesButton);
			}
			{
				JButton NoButton = new JButton("NO");
				NoButton.setBounds(124, 5, 61, 23);
				NoButton.setActionCommand("Cancel");
				buttonPane.add(NoButton);
			}
		}
		{
			JLabel lblDelete = new JLabel("Are you sure ?");
			lblDelete.setFont(new Font("Dialog", Font.BOLD, 12));
			lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
			lblDelete.setBounds(29, 11, 173, 25);
			getContentPane().add(lblDelete);
		}
	}

}
