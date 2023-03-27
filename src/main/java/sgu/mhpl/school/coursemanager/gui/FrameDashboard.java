package sgu.mhpl.school.coursemanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FrameDashboard extends JFrame {
	
	private Image img_logo = new ImageIcon(FrameDashboard.class.getResource("/img/logo.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	private JPanel contentPane;
	private PanelHome homePage;
	private CoursesGUI coursesPage;
	private AssignmentGUI assignmentPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDashboard frame = new FrameDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 445);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		homePage = new PanelHome();
		
		coursesPage = new CoursesGUI();
		
		assignmentPage = new AssignmentGUI();

		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(47, 79, 79));
		panelMenu.setBounds(0, 0, 206, 445);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setBackground(new Color(47, 79, 79));
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 11, 186, 122);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		panelMenu.add(lblIconLogo);
		
		JPanel panelHome = new JPanel();
		panelHome.addMouseListener(new PanelButtonMouseAdapter(panelHome) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(homePage);
			}
		});
		panelHome.setBackground(new Color(47, 79, 79));
		panelHome.setBounds(0, 147, 206, 40);
		panelMenu.add(panelHome);
		panelHome.setLayout(null);
		
		JLabel lblHome = new JLabel("PERSON");
		lblHome.setBackground(new Color(47, 79, 79));
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHome.setBounds(10, 11, 186, 14);
		panelHome.add(lblHome);
		
		JPanel panelCourses = new JPanel();
		panelCourses.addMouseListener(new PanelButtonMouseAdapter(panelCourses){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(coursesPage);
			}
		});
		
		
		
		panelCourses.setBounds(0, 187, 206, 40);
		panelMenu.add(panelCourses);
		panelCourses.setBackground(new Color(47, 79, 79));
		panelCourses.setLayout(null);
		
		JLabel lblCourses = new JLabel("COURSES");
		lblCourses.setBackground(new Color(47, 79, 79));
		lblCourses.setBounds(10, 11, 186, 16);
		lblCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourses.setForeground(Color.WHITE);
		lblCourses.setFont(new Font("Dialog", Font.BOLD, 12));
		panelCourses.add(lblCourses);
		
		
		
		JPanel panelAssignment = new JPanel();
		panelAssignment.addMouseListener(new PanelButtonMouseAdapter(panelAssignment){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(assignmentPage);
			}
		});
		panelAssignment.setBounds(0, 230, 206, 40);
		panelMenu.add(panelAssignment);
		panelAssignment.setBackground(new Color(47, 79, 79));
		panelAssignment.setLayout(null);
		
		JLabel lblAssignment = new JLabel("ASSIGNMENT");
		lblAssignment.setBackground(new Color(47, 79, 79));
		lblAssignment.setBounds(10, 11, 186, 16);
		lblAssignment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignment.setForeground(Color.WHITE);
		lblAssignment.setFont(new Font("Dialog", Font.BOLD, 12));
		panelAssignment.add(lblAssignment);
		
		final JLabel lblExit = new JLabel("X");
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setBounds(803, 6, 10, 19);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure want to close this application ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					FrameDashboard.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblExit.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblExit.setForeground(Color.WHITE);
			}
		});
		contentPane.add(lblExit);
		
		JPanel panelMainContent = new JPanel();
		panelMainContent.setBounds(216, 25, 597, 409);
		contentPane.add(panelMainContent);
		panelMainContent.setLayout(null);
		
		panelMainContent.add(homePage);
		panelMainContent.add(coursesPage);
		panelMainContent.add(assignmentPage);
		
		menuClicked(homePage);

	}
	
	public void menuClicked(JPanel panel) {
		homePage.setVisible(false);
		coursesPage.setVisible(false);
		assignmentPage.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		
		JPanel panel;
		public PanelButtonMouseAdapter (JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
		
		@Override
		public void mouseExited (MouseEvent e) {
			panel.setBackground(new Color(47, 79, 79));
		}
		
		@Override
		public void mousePressed (MouseEvent e) {
			panel.setBackground(new Color(60, 179, 113));
		}
		
		@Override
		public void mouseReleased (MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
	}
}
