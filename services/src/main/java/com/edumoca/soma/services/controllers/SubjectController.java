package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.dtos.SubjectDto;
import com.edumoca.soma.entities.models.SubjectRequest;
import com.edumoca.soma.services.services.SubjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/subject")
@AllArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectRequest subjectRequest) {
		return new ResponseEntity<>(subjectService.createSubject(modelMapper.map(subjectRequest,Subject.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{subjectId}")
	public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectRequest subjectRequest, @PathVariable("subjectId") Integer subjectId) {
		return new ResponseEntity<>(subjectService.updateSubject(modelMapper.map(subjectRequest,Subject.class),subjectId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubjectDto>> getAllSubjectsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(subjectService.getAllSubjectsByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{subjectId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("institutionId") Integer institutionId, @PathVariable("subjectId") Integer subjectId) {
		return new ResponseEntity<>(subjectService.getSubjectByInstitutionAndSubject(institutionId,subjectId),HttpStatus.OK);
	}
}
