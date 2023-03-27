package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sgu.mhpl.school.coursemanager.bll.DepartmentBLL;
import sgu.mhpl.school.coursemanager.bll.OnlineCourseBLL;
import sgu.mhpl.school.coursemanager.bll.OnsiteCourseBLL;
import sgu.mhpl.school.coursemanager.dto.Department;
import sgu.mhpl.school.coursemanager.dto.OnlineCourse;
import sgu.mhpl.school.coursemanager.dto.OnsiteCourse;

public class CourseAddForm extends JFrame implements ActionListener {
	private JButton btnCancel, btnAdd, btnUpdate;
	private JPanel jpanel, jpanel1, jpanel2, jpanel3, jpanel4, jpanel5, jpanel6,jpanel7,jpanel8,jpanel9,jpanel10;
	private JLabel heading, lbCourseID,lbTitle, lbCredit, lbDepartment, lbCourse, lbLocation,lbDays,lbTime,lbUrl;
	private JLabel lbBudget = new JLabel(), lbStartDate= new JLabel(), lbAdmin = new JLabel();
	private JTextField txtCourseID, txtTitle, txtCredit, txtLocation, txtDays, txtUrl;
	private ArrayList<Department> departments;
	private DepartmentBLL departmentBLL;
	private JComboBox cbDepartment, cbCourse, cbTime;
	private OnlineCourseBLL onlineCourseBLL;
	private OnsiteCourseBLL onsiteCourseBLL;
	private boolean isUpdated = false;
	
	public CourseAddForm() {
		initialize();
	}
	
	
	public void initialize() {
		this.setSize(500, 500);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.setResizable(false);
		
		departmentBLL = new DepartmentBLL();
		departments = departmentBLL.readDepartment();
		
		onlineCourseBLL = new OnlineCourseBLL();
		onsiteCourseBLL = new OnsiteCourseBLL();
		
		
		heading = new JLabel("Thêm khóa học mới!");
		heading.setPreferredSize(new Dimension(500,30));
		
		
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jpanel.setPreferredSize(new Dimension(500,60));
		jpanel.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbCourseID = new JLabel("Course ID: ");
		lbCourseID.setPreferredSize(new Dimension(80,30));
		lbCourseID.setAlignmentX(LEFT_ALIGNMENT);
		
		txtCourseID = new JTextField();
		txtCourseID.setEditable(false);
		
		jpanel.add(heading, BorderLayout.NORTH);
		jpanel.add(lbCourseID, BorderLayout.WEST);
		jpanel.add(txtCourseID, BorderLayout.CENTER);
		
		jpanel1 = new JPanel();
		jpanel1.setLayout(new BorderLayout());
		jpanel1.setPreferredSize(new Dimension(500,30));
		jpanel1.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		
		lbTitle = new JLabel("Title");
		lbTitle.setPreferredSize(new Dimension(80,30));
		lbTitle.setAlignmentX(LEFT_ALIGNMENT);
		
		txtTitle = new JTextField();
		
		jpanel1.add(lbTitle, BorderLayout.WEST);
		jpanel1.add(txtTitle, BorderLayout.CENTER);
		

		jpanel2 = new JPanel();
		jpanel2.setLayout(new BorderLayout());
		jpanel2.setPreferredSize(new Dimension(500,30));
		jpanel2.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbCredit = new JLabel("Credit");
		lbCredit.setPreferredSize(new Dimension(80,30));
		lbCredit.setAlignmentX(LEFT_ALIGNMENT);
		
		txtCredit = new JTextField();
		
		jpanel2.add(lbCredit, BorderLayout.WEST);
		jpanel2.add(txtCredit, BorderLayout.CENTER);
		
		
		jpanel3 = new JPanel();
		jpanel3.setLayout(new BorderLayout());
		jpanel3.setPreferredSize(new Dimension(500,30));
		jpanel3.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbDepartment = new JLabel("Department");
		lbDepartment.setPreferredSize(new Dimension(80,30));
		lbDepartment.setAlignmentX(LEFT_ALIGNMENT);
		
		
		cbDepartment = new JComboBox();
		cbDepartment.addActionListener(this);
		for (Department department : departments) {
			cbDepartment.addItem(department.getName());
		}
		
		
		jpanel3.add(lbDepartment, BorderLayout.WEST);
		jpanel3.add(cbDepartment, BorderLayout.CENTER);
			
		
		jpanel4 = new JPanel();
		jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpanel4.setPreferredSize(new Dimension(500,80));
		jpanel4.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbBudget.setPreferredSize(new Dimension(200,30));
		
		lbStartDate.setPreferredSize(new Dimension(200,30));
		
		lbAdmin.setPreferredSize(new Dimension(200,30));
		
		jpanel4.add(lbBudget);
		jpanel4.add(lbStartDate);
		jpanel4.add(lbAdmin);
		
		btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(this);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(this);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(this);
		
		jpanel5 = new JPanel();
		jpanel5.setLayout(new BorderLayout());
		jpanel5.setPreferredSize(new Dimension(500,30));
		jpanel5.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbCourse = new JLabel("Course");
		lbCourse.setPreferredSize(new Dimension(80,30));
		lbCourse.setAlignmentX(LEFT_ALIGNMENT);
		
		
		cbCourse = new JComboBox(new String[] { "Online courses", "Onsite courses" });
		cbCourse.addActionListener(this);
		
		jpanel5.add(lbCourse, BorderLayout.WEST);
		jpanel5.add(cbCourse, BorderLayout.CENTER);
		
		
		jpanel6 = new JPanel();
		jpanel6.setLayout(new FlowLayout());
		jpanel6.setPreferredSize(new Dimension(500,120));
		
		jpanel7 = new JPanel();
		jpanel7.setLayout(new BorderLayout());
		jpanel7.setPreferredSize(new Dimension(500,30));
		jpanel7.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbLocation = new JLabel("Location");
		lbLocation.setPreferredSize(new Dimension(80,30));
		lbLocation.setAlignmentX(LEFT_ALIGNMENT);
		
		txtLocation = new JTextField();
		
		jpanel7.add(lbLocation, BorderLayout.WEST);
		jpanel7.add(txtLocation, BorderLayout.CENTER);
		
		jpanel8 = new JPanel();
		jpanel8.setLayout(new BorderLayout());
		jpanel8.setPreferredSize(new Dimension(500,30));
		jpanel8.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbDays = new JLabel("Days");
		lbDays.setPreferredSize(new Dimension(80,30));
		lbDays.setAlignmentX(LEFT_ALIGNMENT);
		
		txtDays = new JTextField();
		
		jpanel8.add(lbDays, BorderLayout.WEST);
		jpanel8.add(txtDays, BorderLayout.CENTER);
		
		jpanel9 = new JPanel();
		jpanel9.setLayout(new BorderLayout());
		jpanel9.setPreferredSize(new Dimension(500,30));
		jpanel9.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbTime = new JLabel("Time");
		lbTime.setPreferredSize(new Dimension(80,30));
		lbTime.setAlignmentX(LEFT_ALIGNMENT);
		
		cbTime = new JComboBox(new String[] {"07:00:00","09:00:00","13:00:00","15:00:00"});
		
		jpanel9.add(lbTime, BorderLayout.WEST);
		jpanel9.add(cbTime, BorderLayout.CENTER);
		
		jpanel10 = new JPanel();
		jpanel10.setLayout(new BorderLayout());
		jpanel10.setPreferredSize(new Dimension(500,30));
		jpanel10.setBorder(new EmptyBorder(0, 50, 0, 20));
		
		lbUrl = new JLabel("Url");
		lbUrl.setPreferredSize(new Dimension(80,30));
		lbUrl.setAlignmentX(LEFT_ALIGNMENT);
		
		txtUrl = new JTextField();
		
		jpanel10.add(lbUrl, BorderLayout.WEST);
		jpanel10.add(txtUrl, BorderLayout.CENTER);
		
		jpanel6.add(jpanel10);
		
		this.add(jpanel);
		this.add(jpanel1);
		this.add(jpanel2);
		this.add(jpanel3);
		this.add(jpanel4);
		this.add(jpanel5);
		this.add(jpanel6);
		this.add(btnCancel);
		this.add(btnAdd);

		
	}
	
	public void start() {
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			this.dispose();
		}
		
		if (e.getSource() == btnAdd) {
			if (validation().isEmpty()) {
				String selectedItemCourse = cbCourse.getSelectedItem().toString();
				String title = txtTitle.getText();
				int credits = Integer.parseInt(txtCredit.getText());
				int departmentID = departments.get(cbDepartment.getSelectedIndex()).getDepartmentID();
				if (selectedItemCourse.equals("Online courses")) {
					String url = txtUrl.getText();
					OnlineCourse newCourse = new OnlineCourse(0, title, credits, departmentID, url);
					int result = onlineCourseBLL.insertOnlineCourse(newCourse);
					this.isUpdated = true;
					this.dispose();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else if (selectedItemCourse.equals("Onsite courses")) {
					String location = txtLocation.getText();
					String days = txtDays.getText();
					DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					Time time = null;
					try {
						time = new Time(formatter.parse(cbTime.getSelectedItem().toString()).getTime());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					OnsiteCourse newCourse = new OnsiteCourse(0, title, credits, departmentID, location, days, time);
					int result = onsiteCourseBLL.insertOnsiteCourse(newCourse);
					this.isUpdated = true;
					this.dispose();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				}
			} else {
				JOptionPane.showMessageDialog(this, validation().toString());
			}		
		}
		
		if (e.getSource() == btnUpdate) {
			if (validation().isEmpty()) {
				String selectedItemCourse = cbCourse.getSelectedItem().toString();
				int courseID = Integer.parseInt(txtCourseID.getText());
				String title = txtTitle.getText();
				int credits = Integer.parseInt(txtCredit.getText());
				int departmentID = departments.get(cbDepartment.getSelectedIndex()).getDepartmentID();
				if (selectedItemCourse.equals("Online courses")) {
					String url = txtUrl.getText();
					OnlineCourse newCourse = new OnlineCourse(courseID, title, credits, departmentID, url);
					int result = onlineCourseBLL.updateOnlineCourse(newCourse);
					this.isUpdated = true;
					this.dispose();
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				} else if (selectedItemCourse.equals("Onsite courses")) {
					String location = txtLocation.getText();
					String days = txtDays.getText();
					DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					Time time = null;
					try {
						time = new Time(formatter.parse(cbTime.getSelectedItem().toString()).getTime());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					OnsiteCourse newCourse = new OnsiteCourse(courseID, title, credits, departmentID, location, days, time);
					int result = onsiteCourseBLL.updateOnsiteCourse(newCourse);
					this.isUpdated = true;
					this.dispose();
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				}
				this.validate();
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(this, validation().toString());
			}
		}
		
		if (e.getSource() == cbDepartment) {
			double budget = departments.get(cbDepartment.getSelectedIndex()).getBudget();
			lbBudget.setText("Budget: " + String.valueOf(budget)); 
			Date startDate = departments.get(cbDepartment.getSelectedIndex()).getStartDate();
			lbStartDate.setText("Start Date : " + String.valueOf(startDate)); 
			int admin = departments.get(cbDepartment.getSelectedIndex()).getAdministrator();
			lbAdmin.setText("Budget: " + String.valueOf(admin)); 
		}
		if (e.getSource() == cbCourse) {
			String selectedItem = cbCourse.getSelectedItem().toString();
			switch (selectedItem) {
			case "Online courses":
				jpanel6.removeAll();
				jpanel6.add(jpanel10);
				jpanel6.revalidate();
				jpanel6.repaint();
				break;

			case "Onsite courses":
				jpanel6.removeAll();
				jpanel6.add(jpanel7);
				jpanel6.add(jpanel8);
				jpanel6.add(jpanel9);
				jpanel6.revalidate();
				jpanel6.repaint();
				break;
			}
		}
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	public void updateOnlineCourseForm(OnlineCourse onl) {
		for (Department de : departments) {
			if (de.getDepartmentID() == onl.getDepartmentID()) {
				double budget = de.getBudget();
				lbBudget.setText("Budget: " + String.valueOf(budget)); 
				Date startDate = de.getStartDate();
				lbStartDate.setText("Start Date : " + String.valueOf(startDate)); 
				int admin = de.getAdministrator();
				lbAdmin.setText("Budget: " + String.valueOf(admin)); 
				cbDepartment.setSelectedItem(de.getName());
			}
		}
		heading.setText("Thay đổi thông tin khóa học!");
		txtCourseID.setText(String.valueOf(onl.getCourseID()));
		txtTitle.setText(onl.getTitle());
		txtCredit.setText(String.valueOf(onl.getCredits()));
		txtUrl.setText(onl.getUrl());
		cbCourse.setSelectedItem("Online courses");

		jpanel6.removeAll();
		jpanel6.add(jpanel10);
		jpanel6.revalidate();
		jpanel6.repaint();
		
		this.remove(btnAdd);
		this.add(btnUpdate);
		this.validate();
		this.repaint();
	}
	
	public void updateOnsiteCourseForm(OnsiteCourse ons) {
		for (Department de : departments) {
			if (de.getDepartmentID() == ons.getDepartmentID()) {
				double budget = de.getBudget();
				lbBudget.setText("Budget: " + String.valueOf(budget)); 
				Date startDate = de.getStartDate();
				lbStartDate.setText("Start Date : " + String.valueOf(startDate)); 
				int admin = de.getAdministrator();
				lbAdmin.setText("Budget: " + String.valueOf(admin)); 
				cbDepartment.setSelectedItem(de.getName());
			}
		}
		heading.setText("Thay đổi thông tin khóa học!");
		txtCourseID.setText(String.valueOf(ons.getCourseID()));
		txtTitle.setText(ons.getTitle());
		txtCredit.setText(String.valueOf(ons.getCredits()));
		txtLocation.setText(String.valueOf(ons.getLocation()));
		txtDays.setText(String.valueOf(ons.getDays()));
		cbTime.setSelectedItem(ons.getTime().toString());
		cbCourse.setSelectedItem("Onsite courses");
		
		jpanel6.removeAll();
		jpanel6.add(jpanel7);
		jpanel6.add(jpanel8);
		jpanel6.add(jpanel9);
		jpanel6.revalidate();
		jpanel6.repaint();
		
		this.remove(btnAdd);
		this.add(btnUpdate);
		this.validate();
		this.repaint();
	}
	
	public String validation() {
		String result = "";
		String STRINGVN_PATTERN = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ/\\s]+$";
		String STRING_PATTERN = "^[a-zA-Z\\s]+$";
		String NUMBER_PATTERN = "^[0-9]+$";
		String TIME_PATTERN = "^[0-9]{2}:[0-9]{2}:[0-9]{2}$";
		
		String title = txtTitle.getText();
		String credits = txtCredit.getText();

		if (title.isEmpty()) {
			return "Vui lòng không để trống title!";
		}
		if (credits.isEmpty()) {
			return "Vui lòng không để trống credits!";
		} else if (!Pattern.matches(NUMBER_PATTERN, credits)) {
			return "Vui lòng chỉ nhập số credits!";
		}
		
		String selectedItem = cbCourse.getSelectedItem().toString();
		switch (selectedItem) {
		case "Online courses":
			String url = txtUrl.getText();
			
			if (url.isEmpty()) {
				return "Vui lòng không để trống url!";
			}
			
			break;
			
		case "Onsite courses":
			String location = txtLocation.getText();
			String days = txtDays.getText();
			
			if (location.isEmpty()) {
				return "Vui lòng không để trống location!";
			} else if (!Pattern.matches(STRINGVN_PATTERN, location)) {
				return "Vui lòng chỉ nhập chữ location!";
			}
			
			if (days.isEmpty()) {
				return "Vui lòng không để trống days!";
			} else if (!Pattern.matches(STRINGVN_PATTERN, days)) {
				return "Vui lòng chỉ nhập chữ days!";
			}
			
			
			break;
		}
		
		return result;
	}
}
