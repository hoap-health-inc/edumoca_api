package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.dtos.CourseSessionDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CourseSessionService {
	CourseSessionDto createCourseSession(CourseSession courseSession);
	CourseSessionDto updateCourseSession(CourseSession courseSession, Integer coureseSessionId);

	List<CourseSessionDto> getAllCourseSessionByInstitution(Integer institutionId);
	CourseSessionDto getCourseSessionByInstitutionAndSession(Integer institutionId, Integer courseSessionId);

	Map<String, Set<CourseSessionDto>> loadCourseSessions(XSSFSheet courseSessionSheet, String courseSessionSheetName);
}
