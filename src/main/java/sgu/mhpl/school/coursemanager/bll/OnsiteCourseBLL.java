package sgu.mhpl.school.coursemanager.bll;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import sgu.mhpl.school.coursemanager.dal.OnsiteCourseDAL;
import sgu.mhpl.school.coursemanager.dto.OnsiteCourse;

public class OnsiteCourseBLL {
	OnsiteCourseDAL onsiteCourseDAL =  new OnsiteCourseDAL();
	
	public ArrayList<OnsiteCourse> readOnsiteCourse() {
		return onsiteCourseDAL.readOnsiteCourse();
	}
	
	public int updateOnsiteCourse(OnsiteCourse onsiteCourse) {
		return onsiteCourseDAL.updateOnsiteCourse(onsiteCourse);
	}
	
	public int insertOnsiteCourse(OnsiteCourse onsiteCourse) {
		return onsiteCourseDAL.insertOnsiteCourse(onsiteCourse);
	}
	
	public ArrayList<OnsiteCourse> findOnsiteCourseByName(String courseName) {
		return onsiteCourseDAL.findOnsiteCourseByName(courseName);
	}
	
	public int deleteOnsiteCourse(int courseID) {
		return onsiteCourseDAL.deleteOnsiteCourse(courseID);
	}
	
}
