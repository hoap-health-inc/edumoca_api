package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.*;
import com.edumoca.soma.services.services.*;
import com.edumoca.soma.services.worksheet.propsconfig.WorksheetPropsConfig;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/loadExcelSheet")
@AllArgsConstructor
public class DataLoaderController {

	private final LoaderService loaderService;
	private final WorksheetPropsConfig worksheetPropsConfig;
	private final InstitutionService institutionService;
	private final AcademicYearService academicYearService;
	private final GradeService gradeService;
	private final SectionService sectionService;
	private final GradeSectionInstitutionMappingService gradeSectionInstitutionMappingService;
	private final GradeSectionInstitutionYearMappingService gradeSectionInstitutionYearMappingService;
	private final UsersService usersService;
	private final RoleService roleService;
	private final DepartmentService departmentService;
	private final SubjectService subjectService;
	private final TeacherGradeSectionSubjectMappingService teacherGradeSectionSubjectMappingService;
	private final PeriodService periodService;
	private final CourseSessionService courseSessionService;
	private final ScheduleService scheduleService;
	private final BookService bookService;
	private final ChapterService chapterService;

	private final BookChapterMapService bookChapterMapService;
	private final TopicService topicService;

	private final BookChapterTopicMapService bookChapterTopicMapService;


    //institution
	@PostMapping(value = "/loadInstitutionExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String, Set<InstitutionDto>> loadInstitutionExcelSheet(@RequestParam("file") MultipartFile institutionFile) throws IOException {
		return institutionService.loadInstitution(getWorksheetName(institutionFile.getInputStream(),worksheetPropsConfig.getInstitution()),worksheetPropsConfig.getInstitution());
	}

	//academicYear
	@PostMapping(value = "/loadAcademicYearExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<AcademicYearDto>> loadAcademicYearExcelSheet(@RequestParam("file") MultipartFile academicYearFile) throws IOException {
		return academicYearService.loadAcademicYear(getWorksheetName(academicYearFile.getInputStream(),worksheetPropsConfig.getAcademicYear()),worksheetPropsConfig.getAcademicYear());
	}

	//grade
	@PostMapping(value = "/loadGradeExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<GradeDto>> loadGradeExcelSheet(@RequestParam("file") MultipartFile gradeFile) throws IOException {
		return gradeService.loadGrades(getWorksheetName(gradeFile.getInputStream(),worksheetPropsConfig.getGrades()),worksheetPropsConfig.getGrades());
	}

	//section
	@PostMapping(value = "/loadSectionExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<SectionDto>> loadSectionExcelSheet(@RequestParam("file") MultipartFile sectionFile) throws IOException {
		return sectionService.loadSections(getWorksheetName(sectionFile.getInputStream(),worksheetPropsConfig.getSections()),worksheetPropsConfig.getSections());
	}

	//gradeSectionInstitutionMapping
	@PostMapping(value = "/loadGradeSectionInstitutionMappingExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<GradeSectionInstitutionMapDto>> loadGradeSectionInstitutionMappingExcelSheet(@RequestParam("file") MultipartFile gradeSectionInstitutionMappingFile) throws IOException {
		return gradeSectionInstitutionMappingService.loadGradeSectionInstitutionMapping(getWorksheetName(gradeSectionInstitutionMappingFile.getInputStream(),worksheetPropsConfig.getGradeSectionInstitutionMaps()),worksheetPropsConfig.getGradeSectionInstitutionMaps());
	}

	//gradeSectionInstitutionYearMapping
	@PostMapping(value = "/loadGradeSectionInstitutionYearMappingExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<GradeSectionInstitutionYearMapDto>> loadGradeSectionInstitutionYearMappingExcelSheet(@RequestParam("file") MultipartFile gradeSectionInstitutionYearMappingFile) throws IOException {
		return gradeSectionInstitutionYearMappingService.loadGradeSectionInstitutionYearMappings(getWorksheetName(gradeSectionInstitutionYearMappingFile.getInputStream(),worksheetPropsConfig.getGradeSectionInstitutionYearMaps()),worksheetPropsConfig.getGradeSectionInstitutionYearMaps());
	}

	//roles
	@PostMapping(value = "/loadRolesExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<RoleDto>> loadRolesExcelSheet(@RequestParam("file") MultipartFile rolesFile) throws IOException {
		return roleService.loadRoles(getWorksheetName(rolesFile.getInputStream(),worksheetPropsConfig.getRoles()),worksheetPropsConfig.getRoles());
	}

	//student
	@PostMapping(value = "/loadStudentsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String, Set<StudentDto>> loadStudentsExcelSheet(@RequestParam("file") MultipartFile studentsFile) throws IOException {
		return usersService.loadStudents(getWorksheetName(studentsFile.getInputStream(),worksheetPropsConfig.getStudents()),worksheetPropsConfig.getStudents());
	}

	@PostMapping(value = "/loadParentsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<ParentDto>> loadParentExcelSheet(@RequestParam("file") MultipartFile parentsFile) throws IOException{
		return usersService.loadParents(getWorksheetName(parentsFile.getInputStream(),worksheetPropsConfig.getParents()),worksheetPropsConfig.getParents());
	}

	//department
	@PostMapping(value = "/loadDepartmentsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<DepartmentDto>> loadDepartmentsExcelSheet(@RequestParam("file") MultipartFile departmentFile) throws IOException {
		return departmentService.loadDepartments(getWorksheetName(departmentFile.getInputStream(),worksheetPropsConfig.getDepartments()),worksheetPropsConfig.getDepartments());
	}

	//teacher
	@PostMapping(value = "/loadTeachersExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadTeachersExcelSheet(@RequestParam("file") MultipartFile teacherFile) throws IOException {
		return usersService.loadTeachers(getWorksheetName(teacherFile.getInputStream(),worksheetPropsConfig.getTeachers()),worksheetPropsConfig.getTeachers(),Teacher.class);
	}
	//homeRoomTeacher
	@PostMapping(value = "/loadHomeRoomTeachersExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadHomeRoomTeachersExcelSheet(@RequestParam("file") MultipartFile homeRoomTeacher)throws IOException{
		return usersService.loadTeachers(getWorksheetName(homeRoomTeacher.getInputStream(), worksheetPropsConfig.getHomeRoomTeachers()),worksheetPropsConfig.getHomeRoomTeachers(),HomeRoomTeacher.class);
	}

	@PostMapping(value = "/loadPrincipalExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadPrincipalExcelSheet(@RequestParam("file") MultipartFile principal)throws IOException{
		return usersService.loadTeachers(getWorksheetName(principal.getInputStream(), worksheetPropsConfig.getPrincipal()),worksheetPropsConfig.getPrincipal(),Principal.class);
	}

	@PostMapping(value = "/loadSchoolAdminExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadSchoolAdminExcelSheet(@RequestParam("file") MultipartFile schoolAdmin)throws IOException{
		return usersService.loadTeachers(getWorksheetName(schoolAdmin.getInputStream(),worksheetPropsConfig.getSchoolAdmin()),worksheetPropsConfig.getSchoolAdmin(),SchoolAdmin.class);
	}

	@PostMapping(value = "/loadSubjectHeadExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadSubjectHeadExcelSheet(@RequestParam("file") MultipartFile subjectHead)throws IOException{
		return usersService.loadTeachers(getWorksheetName(subjectHead.getInputStream(), worksheetPropsConfig.getSubjectHead()),worksheetPropsConfig.getSubjects(),SubjectHead.class);
	}

	@PostMapping(value = "/loadSuperAdminExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TeacherDto>> loadSuperAdminExcelSheet(@RequestParam("file") MultipartFile superAdmin)throws IOException{
		return usersService.loadTeachers(getWorksheetName(superAdmin.getInputStream(),worksheetPropsConfig.getSuperAdmin()),worksheetPropsConfig.getSuperAdmin(),SuperAdmin.class);
	}

	//subjects
	@PostMapping(value = "/loadSubjectsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<SubjectDto>> loadSubjectsExcelSheet(@RequestParam("file") MultipartFile subjectsFile)throws IOException{
		return subjectService.loadSubjects(getWorksheetName(subjectsFile.getInputStream(),worksheetPropsConfig.getSubjects()),worksheetPropsConfig.getSubjects());
	}

	//teacherGradeSectionInstitutionYearSubjectMappings
	@PostMapping(value = "/loadTeacherGradeSectionSubjectMappingsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String, Set<TeacherGradeSectionSubjectMapDto>> loadTeacherGradeSectionSubjectMappingsExcelSheet(@RequestParam("file") MultipartFile teacherGradeSectionSubjectMappingsFile)throws IOException{
        return teacherGradeSectionSubjectMappingService.loadTeacherGradeSectionSubjectMappings(getWorksheetName(teacherGradeSectionSubjectMappingsFile.getInputStream(),worksheetPropsConfig.getTeacherGradeSectionSubjectMaps()),worksheetPropsConfig.getTeacherGradeSectionSubjectMaps());
	}

    //periods
	@PostMapping(value = "/loadPeriodsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String, Set<PeriodDto>> loadPeriodsExcelSheet(@RequestParam("file") MultipartFile periodsFile)throws IOException{
		return periodService.loadPeriods(getWorksheetName(periodsFile.getInputStream(),worksheetPropsConfig.getPeriods()),worksheetPropsConfig.getPeriods());
	}

	//courseSession
	@PostMapping(value = "/loadCourseSessionsExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<CourseSessionDto>> loadCourseSessionExcelSheet(@RequestParam("file") MultipartFile courseSessionFile)throws IOException{
		return courseSessionService.loadCourseSessions(getWorksheetName(courseSessionFile.getInputStream(),worksheetPropsConfig.getCourseSession()),worksheetPropsConfig.getCourseSession());
	}

	//schedules
	@PostMapping(value = "/loadSchedulesExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<ScheduleDto>> loadSchedulesExcelSheet(@RequestParam("file") MultipartFile schedulesFile)throws  IOException{
		return scheduleService.loadSchedules(getWorksheetName(schedulesFile.getInputStream(),worksheetPropsConfig.getSchedules()),worksheetPropsConfig.getSchedules());
	}

	@PostMapping(value = "/loadBooksExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<BookDto>> loadBookExcelSheet(@RequestParam("file") MultipartFile booksFile)throws IOException{
		return bookService.loadBooks(getWorksheetName(booksFile.getInputStream(),worksheetPropsConfig.getBooks()),worksheetPropsConfig.getBooks());
	}

	@PostMapping(value = "/loadChapterExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<ChapterDto>> loadChapterExcelSheet(@RequestParam("file") MultipartFile chapterFile)throws IOException{
		return chapterService.loadChapter(getWorksheetName(chapterFile.getInputStream(),worksheetPropsConfig.getChapters()),worksheetPropsConfig.getChapters());
	}

	@PostMapping(value = "/loadBookChapterMapExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<BookChapterMapDto>> loadBookChapterMapExcelSheet(@RequestParam("file") MultipartFile bookChapterMapFile) throws IOException {
		return bookChapterMapService.loadBookChapter(getWorksheetName(bookChapterMapFile.getInputStream(),worksheetPropsConfig.getBookChapterMap()),worksheetPropsConfig.getBookChapterMap());
	}

	@PostMapping(value = "/loadTopicExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<TopicDto>> loadTopicExcelSheet(@RequestParam("file") MultipartFile topicFile) throws IOException{
		return topicService.loadTopic(getWorksheetName(topicFile.getInputStream(),worksheetPropsConfig.getTopics()),worksheetPropsConfig.getTopics());
	}

	@PostMapping(value = "/loadBookChapterTopicMapExcelSheet",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<String,Set<BookChapterTopicMapDto>> loadBookChapterTopicExcelSheet(@RequestParam("file") MultipartFile bookChapterTopicFile) throws IOException{
		return bookChapterTopicMapService.loadBookChapterTopic(getWorksheetName(bookChapterTopicFile.getInputStream(),worksheetPropsConfig.getBookChapterTopicMap()),worksheetPropsConfig.getBookChapterTopicMap());
	}


	private XSSFSheet getWorksheetName(InputStream inputStream,String worksheetName) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		return loaderService.getWorksheet(workbook, worksheetName);
	}
}

