package sgu.mhpl.school.coursemanager.bll;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import sgu.mhpl.school.coursemanager.dal.OnlineCourseDAL;
import sgu.mhpl.school.coursemanager.dto.OnlineCourse;



public class OnlineCourseBLL {
	OnlineCourseDAL onlineCourseDAL =  new OnlineCourseDAL();
	
	public ArrayList<OnlineCourse> readOnlineCourse() {
		return onlineCourseDAL.readOnlineCourse();
	}
	
	public int updateOnlineCourse(OnlineCourse onlineCourse) {
		return onlineCourseDAL.updateOnlineCourse(onlineCourse);
	}
	
	public int insertOnlineCourse(OnlineCourse onlineCourse) {
		return onlineCourseDAL.insertOnlineCourse(onlineCourse);
	}
	
	public ArrayList<OnlineCourse> findOnlineCourseByName(String courseName) {
		return onlineCourseDAL.findOnlineCourseByName(courseName);
	}
	
	public int deleteOnlineCourse(int courseID) {
		return onlineCourseDAL.deleteOnlineCourse(courseID);
	}
	
}
