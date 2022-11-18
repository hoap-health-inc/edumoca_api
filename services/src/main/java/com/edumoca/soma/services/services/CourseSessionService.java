package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.dtos.CourseSessionDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CourseSessionService {
	CourseSessionDTO createCourseSession(CourseSession courseSession);
	CourseSessionDTO updateCourseSession(CourseSession courseSession, Integer coureseSessionId);

	List<CourseSessionDTO> getAllCourseSessionByInstitution(Integer institutionId);
	CourseSessionDTO getCourseSessionByInstitutionAndSession(Integer institutionId,Integer courseSessionId);

	Map<String, Set<CourseSessionDTO>> loadCourseSessions(XSSFSheet courseSessionSheet,String courseSessionSheetName);
}
