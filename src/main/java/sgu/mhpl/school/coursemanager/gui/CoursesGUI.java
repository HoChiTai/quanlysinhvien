package sgu.mhpl.school.coursemanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import sgu.mhpl.school.coursemanager.bll.OnlineCourseBLL;
import sgu.mhpl.school.coursemanager.bll.OnsiteCourseBLL;
import sgu.mhpl.school.coursemanager.dto.OnlineCourse;
import sgu.mhpl.school.coursemanager.dto.OnsiteCourse;

public class CoursesGUI extends JPanel implements ActionListener {
	private OnsiteCourseBLL onsiteCourseBLL;
	private OnlineCourseBLL onlineCourseBLL;
	private JPanel header, content, settings;
	private JLabel heading;
	private JTextField txtSearch;
	private JButton btnAdd, btnSearch, btnUpdate, btnDelete, dialogButton;
	private JTable tableCourses;
	private JComboBox cbCourses;
	private DefaultTableModel model;
	private String[] columnsNameOnline = new String[] { "Index","CourseID", "Title", "Credits", "DepartmentID", "Url" };
	private String[] columnsNameOnsite = new String[] { "Index","CourseID", "Title", "Credits", "DepartmentID", "Location", "Days",
			"Time" };
	JFrame subFrame;

	public CoursesGUI() {
		onsiteCourseBLL = new OnsiteCourseBLL();
		onlineCourseBLL = new OnlineCourseBLL();
		initialize();
	}

	public void initialize() {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 128, 128));
		this.setBounds(0, 0, 597, 409);

		header = new JPanel();
		header.setBackground(new Color(0, 128, 128));
		header.setPreferredSize(new Dimension(600,100));
		header.setLayout(new BorderLayout());
		
		content = new JPanel();
		content.setBackground(new Color(255,255,255));
		content.setBorder(new EmptyBorder(10, 10, 10, 10));
		content.setLayout(null);
		
		settings = new JPanel();
		settings.setBackground(new Color(0, 128, 128));
		settings.setPreferredSize(new Dimension(600,50));
		
		heading = new JLabel("COURSES");
		heading.setPreferredSize(new Dimension(100, 50));
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setFont(new Font("Dialog", Font.BOLD, 13));
		heading.setForeground(new Color(255, 255, 255));
		
		txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(100, 30));
	
		btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(this);
		btnSearch.setBackground(new Color(0, 139, 139));
		btnSearch.setForeground(new Color(255,255,255));
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(this);
		btnAdd.setBackground(new Color(0, 139, 139));
		btnAdd.setForeground(new Color(255,255,255));
		
		btnUpdate = new JButton("EDIT");
		btnUpdate.addActionListener(this);
		btnUpdate.setBackground(new Color(0, 139, 139));
		btnUpdate.setForeground(new Color(255,255,255));
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(this);
		btnDelete.setBackground(new Color(0, 139, 139));
		btnDelete.setForeground(new Color(255,255,255));
		

		cbCourses = new JComboBox(new String[] { "Online courses", "Onsite courses" });
		cbCourses.addActionListener(this);
		cbCourses.setBackground(new Color(0, 139, 139));
		cbCourses.setForeground(new Color(255,255,255));

		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(10,10, 580,290);
		jScrollPane1.setPreferredSize(new Dimension(600,300));
		jScrollPane1.setBackground(new Color(255,255,255));


		tableCourses = new JTable();
		
		jScrollPane1.setViewportView(tableCourses);
		
//		updateDataOnlineCourseTable(listOnlineCourse);
		updateDataOnlineCourseTable(onlineCourseBLL.readOnlineCourse());

		settings.add(cbCourses);
		settings.add(txtSearch);
		settings.add(btnSearch);
		settings.add(btnAdd);
		settings.add(btnUpdate);
		settings.add(btnDelete);
		
		header.add(heading, BorderLayout.NORTH);
		header.add(settings, BorderLayout.CENTER);

		content.add(jScrollPane1);

		this.add(header, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);

	}
	
	public void updateDataOnlineCourseTable(ArrayList<OnlineCourse> arrayOnlineCourse) {
		DefaultTableModel modelOnline = new DefaultTableModel(null, columnsNameOnline);
		int i=1;
		for (OnlineCourse onlineCourse : arrayOnlineCourse) {
			Vector vt = new Vector();
			vt.add(i);
			vt.add(onlineCourse.getCourseID());
			vt.add(onlineCourse.getTitle());
			vt.add(onlineCourse.getCredits());
			vt.add(onlineCourse.getDepartmentID());
			vt.add(onlineCourse.getUrl());
			modelOnline.addRow(vt);
			i++;
		}
		tableCourses.setModel(modelOnline);
	}
	
	public void updateDataOnsiteCourseTable(ArrayList<OnsiteCourse> arrayOnsiteCourse) {
		DefaultTableModel modelOnsite = new DefaultTableModel(null, columnsNameOnsite);
		int i=1;
		for (OnsiteCourse onsiteCourse : arrayOnsiteCourse) {
			Vector vt = new Vector();
			vt.add(i);
			vt.add(onsiteCourse.getCourseID());
			vt.add(onsiteCourse.getTitle());
			vt.add(onsiteCourse.getCredits());
			vt.add(onsiteCourse.getDepartmentID());
			vt.add(onsiteCourse.getLocation());
			vt.add(onsiteCourse.getDays());
			vt.add(onsiteCourse.getTime());
			modelOnsite.addRow(vt);
			i++;
		}
		tableCourses.setModel(modelOnsite);
	}

	public void btnAdd_click() {
		final CourseAddForm addForm =  new CourseAddForm();
		addForm.start();
		addForm.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				if (addForm.isUpdated()) {
					String selectedItem = cbCourses.getSelectedItem().toString();
					switch (selectedItem) {
					case "Online courses":
						updateDataOnlineCourseTable(onlineCourseBLL.readOnlineCourse());
						break;
					case "Onsite courses":
						updateDataOnsiteCourseTable(onsiteCourseBLL.readOnsiteCourse());
						break;
					}
				}
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void btnSearch_click() {
		String textSearch = txtSearch.getText();
		String selectedItem = cbCourses.getSelectedItem().toString();
		switch (selectedItem) {
			case "Online courses":
				ArrayList<OnlineCourse> arraySearchx = new ArrayList<OnlineCourse>();
				arraySearchx = onlineCourseBLL.findOnlineCourseByName(textSearch);
				updateDataOnlineCourseTable(arraySearchx);
				break;
	
			case "Onsite courses":
				ArrayList<OnsiteCourse> arraySearchy = new ArrayList<OnsiteCourse>();
				arraySearchy = onsiteCourseBLL.findOnsiteCourseByName(textSearch);
				updateDataOnsiteCourseTable(arraySearchy);
				break;
		}	
	}
	
	public void btnUpdate_click() {
		int row = tableCourses.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
		} else {
			final CourseAddForm addForm =  new CourseAddForm();
			addForm.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					if (addForm.isUpdated()) {
						String selectedItem = cbCourses.getSelectedItem().toString();
						switch (selectedItem) {
						case "Online courses":
							updateDataOnlineCourseTable(onlineCourseBLL.readOnlineCourse());
							break;
						case "Onsite courses":
							updateDataOnsiteCourseTable(onsiteCourseBLL.readOnsiteCourse());
							break;
						}
					}
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			int courseID = Integer.parseInt(tableCourses.getModel().getValueAt(row, 1).toString());
			String selectedItem = cbCourses.getSelectedItem().toString();
			switch (selectedItem) {
			case "Online courses":
				OnlineCourse updateOnlineCourse = new OnlineCourse(
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 1).toString()), 
						tableCourses.getModel().getValueAt(row, 2).toString(), 
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 3).toString()),
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 4).toString()),
						tableCourses.getModel().getValueAt(row, 5).toString()
					);
				
				addForm.updateOnlineCourseForm(updateOnlineCourse);
				addForm.start();
				break;

			case "Onsite courses":
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				Time time = null;
				try {
					time = new Time(formatter.parse(tableCourses.getModel().getValueAt(row, 7).toString()).getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OnsiteCourse updateOnsiteCourse = new OnsiteCourse(
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 1).toString()), 
						tableCourses.getModel().getValueAt(row, 2).toString(), 
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 3).toString()),
						Integer.parseInt(tableCourses.getModel().getValueAt(row, 4).toString()),
						tableCourses.getModel().getValueAt(row, 5).toString(),
						tableCourses.getModel().getValueAt(row, 6).toString(),
						time
					);
				
				addForm.updateOnsiteCourseForm(updateOnsiteCourse);
				addForm.start();
				break;
			}		
			
		}
	}
	
	public void btnDelete_click() {
		int row = tableCourses.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
		} else {
			int dialogResult = JOptionPane.showConfirmDialog (this, "Bạn có chắc là muốn xóa khóa học này không?");
			if(dialogResult == JOptionPane.YES_OPTION){
				int courseID = Integer.parseInt(tableCourses.getModel().getValueAt(row, 1).toString());
				String selectedItem = cbCourses.getSelectedItem().toString();
				int deleteState = 0;
				switch (selectedItem) {
				case "Online courses":
					deleteState = onlineCourseBLL.deleteOnlineCourse(courseID);
					updateDataOnlineCourseTable(onlineCourseBLL.readOnlineCourse());
					break;

				case "Onsite courses":
					deleteState = onsiteCourseBLL.deleteOnsiteCourse(courseID);
					updateDataOnsiteCourseTable(onsiteCourseBLL.readOnsiteCourse());
					break;
				}		
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbCourses) {
			String selectedItem = cbCourses.getSelectedItem().toString();
			switch (selectedItem) {
			case "Online courses":
				updateDataOnlineCourseTable(onlineCourseBLL.readOnlineCourse());
				break;

			case "Onsite courses":
				updateDataOnsiteCourseTable(onsiteCourseBLL.readOnsiteCourse());
				break;
			}
		}
		if (e.getSource() == btnAdd) {
			btnAdd_click();
		}

		if (e.getSource() == btnSearch) {
			btnSearch_click();
		}
		if (e.getSource() == btnUpdate) {
			btnUpdate_click();
		}
		if (e.getSource() == btnDelete) {
			btnDelete_click();
		}
	}

}
