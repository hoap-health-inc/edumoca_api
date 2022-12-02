package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.models.ParentRequest;
import com.edumoca.soma.entities.models.StudentRequest;
import com.edumoca.soma.entities.models.TeacherRequest;
import com.edumoca.soma.services.fileUtils.FileOperationUtils;
import com.edumoca.soma.services.services.GridFSFileService;
import com.edumoca.soma.services.services.ProfilePhotoService;
import com.edumoca.soma.services.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final GridFSFileService gridFSFileService;
    private final ProfilePhotoService profilePhotoService;

    private final ModelMapper modelMapper;
    
    //Logger log = LoggerFactory.getLogger(UsersController.class);

    @PostMapping(value = "/student",consumes = {MediaType.APPLICATION_JSON_VALUE})
	@Operation(
	    		tags = {"Register student"},
	    		operationId = "registerStudent",
	    		summary = "Register student summary",
	    		description = "Register student desc"
	    )
    //public Student registerStudent(@RequestPart("student") String studentJson,@RequestPart("file") MultipartFile profilePic) throws IOException {
    public Student registerStudent(@RequestBody StudentRequest studentRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(studentRequest.getProfilePic());
        studentRequest.setProfilePic(profilePhotoService.uploadPhoto(studentRequest.getFirstName(),file));
        return usersService.registerStudent(modelMapper.map(studentRequest,Student.class));
    }

    @PostMapping(value = "/teacher",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Teacher registerTeacher(@RequestBody TeacherRequest teacherRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
        return usersService.registerTeacher(modelMapper.map(teacherRequest,Teacher.class));
    }
    
    @PostMapping(value = "/homeRoomTeacher",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HomeRoomTeacher registerHomeRoomTeacher(@RequestBody TeacherRequest teacherRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
    	return usersService.registerHomeRoomTeacher(modelMapper.map(teacherRequest, HomeRoomTeacher.class));
    }
    
    @PostMapping(value = "/subjectHead",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SubjectHead registerSubjectHead(@RequestBody TeacherRequest teacherRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
    	return usersService.registerSubjectHead(modelMapper.map(teacherRequest,SubjectHead.class));
    }
    
    @PostMapping(value = "/principal",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Principal registerPrincipal(@RequestBody TeacherRequest teacherRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
    	return usersService.registerPrincipal(modelMapper.map(teacherRequest, Principal.class));
    }

    @PostMapping(value = "/schoolAdmin",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SchoolAdmin registerSchoolAdmin(@RequestBody TeacherRequest teacherRequest) throws IOException {
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
        return  usersService.registerSchoolAdmin(modelMapper.map(teacherRequest, SchoolAdmin.class));
    }

    @PostMapping(value = "/superAdmin",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SuperAdmin registerSuperAdmin(@RequestBody TeacherRequest teacherRequest) throws IOException{
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(teacherRequest.getProfilePic());
        teacherRequest.setProfilePic(profilePhotoService.uploadPhoto(teacherRequest.getFirstName(),file));
        return usersService.registerSuperAdmin(modelMapper.map(teacherRequest,SuperAdmin.class));
    }
    
    
    @PostMapping(value = "/parent",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Parent registerParent(@RequestBody ParentRequest parentRequest) throws IOException {
        //Objects.nonNull(parentRequest.getStudents());
        MultipartFile file = FileOperationUtils.convertFileToMultipartFile(parentRequest.getProfilePic());
        parentRequest.setProfilePic(profilePhotoService.uploadPhoto(parentRequest.getFirstName(),file));
        return usersService.registerParent(modelMapper.map(parentRequest, Parent.class));
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Parent getParentById(@PathVariable Integer id){
        return usersService.getParentById(id);
    }
    
    
    @GetMapping(value = "{studentId}/student",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentById(@PathVariable Integer studentId) {
    	return usersService.getStudentById(studentId);
    }
   
}
