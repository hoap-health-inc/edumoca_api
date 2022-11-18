package com.edumoca.soma.services.controllers;

import java.util.List;

import com.edumoca.soma.entities.dtos.SubjectDTO;
import com.edumoca.soma.entities.models.SubjectRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.services.services.SubjectService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/subject")
@AllArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectRequest subjectRequest) {
		return new ResponseEntity<>(subjectService.createSubject(modelMapper.map(subjectRequest,Subject.class)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{subjectId}")
	public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectRequest subjectRequest, @PathVariable("subjectId") Integer subjectId) {
		return new ResponseEntity<>(subjectService.updateSubject(modelMapper.map(subjectRequest,Subject.class),subjectId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubjectDTO>> getAllSubjectsByInstitution(@PathVariable("institutionId") Integer institutionId){
		return new ResponseEntity<>(subjectService.getAllSubjectsByInstitution(institutionId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{institutionId}/{subjectId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("institutionId") Integer institutionId,@PathVariable("subjectId") Integer subjectId) {
		return new ResponseEntity<>(subjectService.getSubjectByInstitutionAndSubject(institutionId,subjectId),HttpStatus.OK);
	}
}
