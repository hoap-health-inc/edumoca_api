package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.ParentDTO;
import com.edumoca.soma.entities.dtos.StudentDTO;
import com.edumoca.soma.entities.dtos.TeacherDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface UsersService {
    Student registerStudent(Student student);
    Teacher registerTeacher(Teacher teacher);
    HomeRoomTeacher registerHomeRoomTeacher(HomeRoomTeacher homeRoomTeacher);
    SubjectHead registerSubjectHead(SubjectHead subjectHead);
    Principal registerPrincipal(Principal principal);
    Parent registerParent(Parent parent);
    Parent getParentById(Integer id);
    
    Student getStudentById(Integer studentId);

    Map<String, Set<StudentDTO>> loadStudents(XSSFSheet studentsSheet,String studentsSheetName);

    Map<String, Set<TeacherDTO>> loadTeachers(XSSFSheet teachersSheet,String teachersSheetName,Class<?> classType);

    Map<String,Set<ParentDTO>> loadParents(XSSFSheet parentsSheet,String parentsSheetName);


    StudentDTO showStudentDetails(String userName) throws IOException;

    TeacherDTO showTeacherDetails(String userName) throws IOException;

    TeacherDTO showSubjectHeadDetails(String userName) throws IOException;

    TeacherDTO showHomeRoomDetails(String userName) throws IOException;

    public TeacherDTO showSchoolAdminByLoginId(String userName) throws IOException;

    public TeacherDTO showSuperAdminByLoginId(String userName) throws IOException;

    public ParentDTO showParentByLoginId(String userName) throws IOException;

    public TeacherDTO showPrincipalByLoginId(String userName) throws IOException;

}
