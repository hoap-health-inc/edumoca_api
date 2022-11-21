package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.dtos.CourseSessionDto;
import com.edumoca.soma.entities.models.CourseSessionRequest;
import com.edumoca.soma.services.services.CourseSessionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/courseSession")
@AllArgsConstructor
public class CourseSessionController {
   
	private final CourseSessionService courseSessionService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<CourseSessionDto> createCourseSession(@Valid @RequestBody CourseSessionRequest courseSessionRequest) {
		return new ResponseEntity<>(courseSessionService.createCourseSession(modelMapper.map(courseSessionRequest,CourseSession.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{courseSessionId}")
	public ResponseEntity<CourseSessionDto> updateCourseSession(@Valid @RequestBody CourseSessionRequest courseSessionRequest, @PathVariable("courseSessionId") Integer courseSessionId) {
		return new ResponseEntity<>(courseSessionService.updateCourseSession(modelMapper.map(courseSessionRequest,CourseSession.class),courseSessionId),HttpStatus.OK);
	}

	@GetMapping(value = "/{institutionId}")
    public ResponseEntity<List<CourseSessionDto>> getAllCourseSessionsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(courseSessionService.getAllCourseSessionByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{courseSessionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseSessionDto> getCourseSessionById(@PathVariable("institutionId") Integer institutionId, @PathVariable("courseSessionId") Integer courseSessionId) {
		return new ResponseEntity<>(courseSessionService.getCourseSessionByInstitutionAndSession(institutionId,courseSessionId),HttpStatus.OK);
	}
}
