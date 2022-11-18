package com.edumoca.soma.services.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.services.services.GridFSFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.edumoca.soma.services.services.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final GridFSFileService gridFSFileService;
    
   // Logger log = LoggerFactory.getLogger(UsersController.class);

    @PostMapping(value = "/student",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(
	    		tags = {"Register student"},
	    		operationId = "registerStudent",
	    		summary = "Register student summary",
	    		description = "Register student desc"
	    )
    //public Student registerStudent(@RequestBody @NotNull Student user) throws MalformedURLException {
    public Student registerStudent(@RequestPart("student") String studentJson,@RequestPart("file") MultipartFile profilePic) throws IOException {
        Student student = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(studentJson,Student.class);
        //System.out.println("---> "+Optional.ofNullable(student.getLastName()).orElse(""));
        //student.setFullName();
        DBObject metadata = new BasicDBObject();
        metadata.put("studentName",student.getFirstName());
        metadata.put("fileSize", profilePic.getSize());
        student.setProfilePic(gridFSFileService.uploadFile(profilePic,metadata));
        return usersService.registerStudent(student);
    }

    @PostMapping(value = "/teacher",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    //public Teacher registerTeacher(@RequestBody @NotNull Teacher user) throws MalformedURLException {
    public Teacher registerTeacher(@RequestPart("teacher") String teacherJson,@RequestPart("file") MultipartFile profilePic) throws IOException {
        Teacher teacher = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(teacherJson,Teacher.class);
        DBObject metadata = new BasicDBObject();
        metadata.put("teacherName",teacher.getFirstName());
        metadata.put("fileSize", profilePic.getSize());
        metadata.put("department",teacher.getDepartment().getDeptName());
        teacher.setProfilePic(gridFSFileService.uploadFile(profilePic,metadata));
        return usersService.registerTeacher(teacher);
    }
    
    @PostMapping(value = "/homeRoomTeacher")
    public HomeRoomTeacher registerHomeRoomTeacher(@RequestBody @NotNull HomeRoomTeacher user) throws MalformedURLException {
    	return usersService.registerHomeRoomTeacher(user);
    }
    
    @PostMapping(value = "/subjectHead")
    public SubjectHead registerSubjectHead(@RequestBody @NotNull SubjectHead user) throws MalformedURLException {
    	return usersService.registerSubjectHead(user);
    }
    
    @PostMapping(value = "/principal")
    public Principal registerPrincipal(@RequestBody @NotNull Principal user) throws MalformedURLException {
    	return usersService.registerPrincipal(user);
    }
    
    
    @PostMapping(value = "/parent")
    public Parent registerParent(@RequestBody @NotNull Parent user) throws MalformedURLException {
        Objects.nonNull(user.getStudents());
        return usersService.registerParent(user);
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
