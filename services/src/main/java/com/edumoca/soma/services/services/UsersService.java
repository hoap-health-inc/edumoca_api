package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.AdminDto;
import com.edumoca.soma.entities.dtos.ParentDto;
import com.edumoca.soma.entities.dtos.StudentDto;
import com.edumoca.soma.entities.dtos.TeacherDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface UsersService {
    Student registerStudent(Student student);
    Teacher registerTeacher(Teacher teacher);
    HomeRoomTeacher registerHomeRoomTeacher(HomeRoomTeacher homeRoomTeacher);
    SubjectHead registerSubjectHead(SubjectHead subjectHead);
    Principal registerPrincipal(Principal principal);
    SchoolAdmin registerSchoolAdmin(SchoolAdmin schoolAdmin);
    SuperAdmin registerSuperAdmin(SuperAdmin superAdmin);
    Parent registerParent(Parent parent);
    Parent getParentById(Integer id);
    
    Student getStudentById(Integer studentId);

    Map<String, Set<StudentDto>> loadStudents(XSSFSheet studentsSheet, String studentsSheetName);

    Map<String, Set<TeacherDto>> loadTeachers(XSSFSheet teachersSheet, String teachersSheetName, Class<?> classType);

    Map<String,Set<ParentDto>> loadParents(XSSFSheet parentsSheet, String parentsSheetName);


    StudentDto showStudentDetails(String userName) throws IOException;

    TeacherDto showTeacherDetails(String userName) throws IOException;

    TeacherDto showSubjectHeadDetails(String userName) throws IOException;

    TeacherDto showHomeRoomDetails(String userName) throws IOException;

    public AdminDto showSchoolAdminByLoginId(String userName) throws IOException;

    public AdminDto showSuperAdminByLoginId(String userName) throws IOException;

    public ParentDto showParentByLoginId(String userName) throws IOException;

    public TeacherDto showPrincipalByLoginId(String userName) throws IOException;

}
