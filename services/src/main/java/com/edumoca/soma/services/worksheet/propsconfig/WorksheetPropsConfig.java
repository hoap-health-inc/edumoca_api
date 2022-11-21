package com.edumoca.soma.services.worksheet.propsconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix="worksheet.name")
@PropertySource("classpath:workSheetConfig.properties")
@Component
@Data
public class WorksheetPropsConfig {
	private String institution;
	private String academicYear;
	private String roles;
	private String grades;
	private String sections;
	private String gradeSectionInstitutionMaps;
    private String gradeSectionInstitutionYearMaps;
	private String subjects;
	private String publications;
	private String books;
	private String chapters;
	private String bookChapterMap;
	private String topics;
	private String bookChapterTopicMap;
	private String teachers;
	private String homeRoomTeachers;
	private String subjectHead;
	private String principal;
	private String schoolAdmin;
	private String superAdmin;
	private String students;
	private String parents;
	private String resources;
	private String periods;
	private String departments;
	private String schedules;
	private String teacherGradeSectionSubjectMaps;
	private String courseSession;
}
