package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.TeacherGradeSectionSubjectMap;
import com.edumoca.soma.services.services.TeacherGradeSectionSubjectMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacherGradeSectionSubjectMapping")
@AllArgsConstructor
public class TeacherGradeSectionSubjectController {

	private final TeacherGradeSectionSubjectMappingService teacherGradeSectionSubjectMappingService;
	
	@PostMapping
	public TeacherGradeSectionSubjectMap createTeacherGradeSectionSubjectMapping(@RequestBody TeacherGradeSectionSubjectMap teacherGradeSectionSubjectMap) {
		return teacherGradeSectionSubjectMappingService.createTeacherGradeSectionSubjectMapping(teacherGradeSectionSubjectMap);
	}
	
	@PutMapping
	public TeacherGradeSectionSubjectMap updateTeacherGradeSectionSubjectMapping(@RequestBody TeacherGradeSectionSubjectMap teacherGradeSectionSubjectMap) {
		return teacherGradeSectionSubjectMappingService.createTeacherGradeSectionSubjectMapping(teacherGradeSectionSubjectMap);
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public TeacherGradeSectionSubjectMap getTeacherGradeSectionSubjectMapping(@PathVariable("id") Integer teacherGradeSectionSubjectMappingId) {
		return teacherGradeSectionSubjectMappingService.getTeacherGradeSectionSubjectMappingById(teacherGradeSectionSubjectMappingId);
	}
}
