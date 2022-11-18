package com.edumoca.soma.services.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.TeacherGradeSectionSubjectMapping;
import com.edumoca.soma.services.services.TeacherGradeSectionSubjectMappingService;

@RestController
@RequestMapping(value = "/teacherGradeSectionSubjectMapping")
@AllArgsConstructor
public class TeacherGradeSectionSubjectController {

	private final TeacherGradeSectionSubjectMappingService teacherGradeSectionSubjectMappingService;
	
	@PostMapping
	public TeacherGradeSectionSubjectMapping createTeacherGradeSectionSubjectMapping(@RequestBody TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping) {
		return teacherGradeSectionSubjectMappingService.createTeacherGradeSectionSubjectMapping(teacherGradeSectionSubjectMapping);
	}
	
	@PutMapping
	public TeacherGradeSectionSubjectMapping updateTeacherGradeSectionSubjectMapping(@RequestBody TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping) {
		return teacherGradeSectionSubjectMappingService.createTeacherGradeSectionSubjectMapping(teacherGradeSectionSubjectMapping);
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public TeacherGradeSectionSubjectMapping getTeacherGradeSectionSubjectMapping(@PathVariable("id") Integer teacherGradeSectionSubjectMappingId) {
		return teacherGradeSectionSubjectMappingService.getTeacherGradeSectionSubjectMappingById(teacherGradeSectionSubjectMappingId);
	}
}
